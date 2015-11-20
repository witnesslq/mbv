package com.mbv.biz.config.iface.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.mbv.biz.config.entity.iface.VpDegModifyRecordEntity;
import com.mbv.biz.config.entity.iface.VpWarehProdEntity;
import com.mbv.biz.config.iface.dao.UDCommDegDao;
import com.mbv.biz.config.iface.dao.UDCommDegDtlDao;
import com.mbv.biz.config.iface.dao.UDCommDegModifyRecordDao;
import com.mbv.biz.config.iface.dao.UDCommWarehProdDao;
import com.mbv.biz.config.iface.dao.UDCommWnDao;
import com.mbv.comm.bean.IfaceResponse;
import com.mbv.comm.bean.VpDegDtlEntity;
import com.mbv.comm.bean.VpDegEntity;
import com.mbv.comm.bean.VpDegModifyEntity;
import com.mbv.comm.bean.VpDegRequest;
import com.mbv.comm.bean.VpWnEntity;
import com.metersbonwe.oms.api.rsa.service.RSAService;

@Service("uDCommServiceBLL")
public class UDCommServiceBLL {

	@Autowired
	private UDCommDegDao uDCommDegDao;

	@Autowired
	private UDCommDegDtlDao uDCommDegDtlDao;

	@Autowired
	private UDCommDegModifyRecordDao uDCommDegModifyRecordDao;

	@Autowired
	private UDCommWarehProdDao uDCommWarehProdDao;

	@Autowired
	private UDCommWnDao uDCommWnDao;

	@Autowired
	private RSAService rSAService;

	/**
	 * 插入发货单信息 批量 循环插入 性能待验证
	 */
	@Transactional(value = "local", rollbackFor = Exception.class)
	public String saveNewVpDeg(List<VpDegRequest> lstVpDegReq) throws Exception {
		// TODO Auto-generated method stub

		if (lstVpDegReq == null || lstVpDegReq.size() == 0) {
			return "传入的参数为空！";
		}

		for (VpDegRequest req : lstVpDegReq) {

			String strRe = insertDegOne(req);

			if (!StringUtils.isEmpty(strRe)) {
				return strRe;
			}

		}

		return StringUtils.EMPTY;
	}

	/**
	 * 撤单
	 */
	@Transactional(value = "local", rollbackFor = Exception.class)
	public IfaceResponse cancelVpDeg(List<VpDegEntity> lstCancelVpDeg)
			throws Exception {
		// TODO Auto-generated method stub

		IfaceResponse res = new IfaceResponse();

		if (lstCancelVpDeg == null || lstCancelVpDeg.size() == 0) {
			res.setResult(false);
			res.setMessage("传入参数为空！");
			return res;
		}

		String cancelState = "8"; // 已撤销

		Map<String, String> mapState = new HashMap<String, String>();
		mapState.put("1", "待发货");
		mapState.put("2", "已发货");
		mapState.put("8", "已撤销");

		// 发货单更新前的原始状态
		List<VpDegEntity> lstOldDeg = new ArrayList<VpDegEntity>();

		for (VpDegEntity deg : lstCancelVpDeg) {

			if (StringUtils.isEmpty(deg.getDocCode())) {
				res.setResult(false);
				res.setMessage("发货单编码为空！");
				return res;
			}

			if (StringUtils.isEmpty(deg.getDocState())) {
				res.setResult(false);
				res.setMessage("发货单单据进度为空！");
				return res;
			}

			VpDegEntity dbOldDeg = uDCommDegDao.selectByDocCode(deg
					.getDocCode());
			if (dbOldDeg == null) {
				res.setResult(false);
				res.setMessage("原始发货单编码：" + deg.getDocCode() + " ,在系统中不存在！");
				return res;
			}

			List<VpDegDtlEntity> dbDegDtls = uDCommDegDtlDao
					.selectDegDtlByParams(dbOldDeg.getId());

			// 更新库存表已分配数量
			if (deg.getDocState().equals(cancelState)) {
				for (VpDegDtlEntity dbDegDtl : dbDegDtls) {
					VpWarehProdEntity wp = new VpWarehProdEntity();
					wp.setProdNum(dbDegDtl.getProdNum());
					wp.setWarehCode(dbOldDeg.getWarehCode());
					wp.setQtyCommitted(-1 * dbDegDtl.getQty());

					if (uDCommWarehProdDao.updateQtyCommitted(wp) < 1) {
						res.setResult(false);
						res.setMessage("发货单编码：" + deg.getDocCode()
								+ "，商品明细扣减锁定库存失败！");
						return res;
					}
				}
			} else {
				for (VpDegDtlEntity dbDegDtl : dbDegDtls) {
					VpWarehProdEntity wp = new VpWarehProdEntity();
					wp.setProdNum(dbDegDtl.getProdNum());
					wp.setWarehCode(dbOldDeg.getWarehCode());
					wp.setQtyCommitted(dbDegDtl.getQty());

					if (uDCommWarehProdDao.updateQtyCommitted(wp) < 1) {
						res.setResult(false);
						res.setMessage("发货单编码：" + deg.getDocCode()
								+ "，商品明细添加锁定库存失败！");
						return res;
					}
				}
			}

			VpDegEntity tmpOldDeg = new VpDegEntity();
			tmpOldDeg.setDocCode(deg.getDocCode());
			tmpOldDeg.setDocState(dbOldDeg.getDocState());

			lstOldDeg.add(tmpOldDeg);

			VpDegModifyRecordEntity degModify = new VpDegModifyRecordEntity();
			degModify.setVpDegCode(deg.getDocCode());
			degModify.setModifyCode("状态变更");
			degModify.setStatus(new Byte("0"));

			degModify.setOriginalValDesc("状态变更："
					+ mapState.get(dbOldDeg.getDocState()) + " -> "
					+ mapState.get(deg.getDocState()));

			if (uDCommDegModifyRecordDao.insert(degModify) < 1) {
				res.setResult(false);
				res.setMessage("发货单编码：" + deg.getDocCode() + "，新增变更记录失败！");
				return res;
			}

			deg.setId(dbOldDeg.getId());
			deg.setLastModifiedUser("ERP_UD_Admin");
			deg.setLastModifiedDate(new Date());

			deg.setTtlQty(dbOldDeg.getTtlQty());
			deg.setTtlVal(dbOldDeg.getTtlVal());

			if (uDCommDegDao.updateByPrimaryKeySelective(deg) < 0) {
				res.setResult(false);
				res.setMessage("发货单编码：" + deg.getDocCode() + "，变更进度失败！");
				return res;
			}

		}

		res.setResult(true);
		res.setData((Serializable) lstOldDeg);
		return res;
	}

	/**
	 * 删除商品 删除商品 数量为负数 添加则为正数
	 */
	@Transactional(value = "local", rollbackFor = Exception.class)
	public String cancelVpDegProduct(List<VpDegDtlEntity> lstCancelProduct)
			throws Exception {

		Map<String, List<VpDegDtlEntity>> mapCancel = new HashMap<String, List<VpDegDtlEntity>>();

		for (VpDegDtlEntity cancelProd : lstCancelProduct) {
			if (StringUtils.isEmpty(cancelProd.getVpDegCode())) {
				return "发货单编码为空！";
			}

			List<VpDegDtlEntity> tmpMapObj = null;
			if (!mapCancel.containsKey(cancelProd.getVpDegCode())) {
				tmpMapObj = new ArrayList<VpDegDtlEntity>();
				tmpMapObj.add(cancelProd);
				mapCancel.put(cancelProd.getVpDegCode(), tmpMapObj);
			} else {
				tmpMapObj = mapCancel.get(cancelProd.getVpDegCode());
				tmpMapObj.add(cancelProd);
			}
		}

		for (Map.Entry<String, List<VpDegDtlEntity>> entry : mapCancel
				.entrySet()) {

			String strRe = cancelVpDegProductByDegCode(entry.getKey(),
					entry.getValue());
			if (!StringUtils.isEmpty(strRe)) {
				return strRe;
			}
		}

		return StringUtils.EMPTY;
	}

	/**
	 * 变更发货单收货信息
	 */
	@Transactional(value = "local", rollbackFor = Exception.class)
	public String modifyVpDeg(VpDegModifyEntity modifyInfo) throws Exception {
		// TODO Auto-generated method stub

		if (StringUtils.isEmpty(modifyInfo.getOrderSn())) {
			return "OS订单号为空！";
		}

		List<VpDegEntity> dbDegs = uDCommDegDao.selectByOrderSn(modifyInfo
				.getOrderSn());
		if (dbDegs == null || dbDegs.size() == 0) {
			return "OS订单号：" + modifyInfo.getOrderSn() + " 的发货单，在系统中不存在！";
		}

		com.alibaba.fastjson.JSONObject jsonObj = null;

		if (!StringUtils.isEmpty(modifyInfo.getMobile())) {
			List<String> lstMobile = new ArrayList<String>();
			lstMobile.add(modifyInfo.getMobile());

			for (VpDegEntity dbDeg : dbDegs) {
				if (!lstMobile.contains(dbDeg.getMobile()))
					lstMobile.add(dbDeg.getMobile());
			}
			String res = rSAService.decode_array(lstMobile);
			jsonObj = JSON.parseObject(res);

			if (jsonObj != null) {
				modifyInfo.setDecodeMobile(jsonObj.getString(modifyInfo
						.getMobile()));
				for (VpDegEntity dbDeg : dbDegs) {
					dbDeg.setMobile(jsonObj.getString(dbDeg.getMobile()));
				}
			}

		}

		for (VpDegEntity dbDeg : dbDegs) {
			String strRe = modifyDegRcvInfoOne(modifyInfo, dbDeg);
			if (!StringUtils.isEmpty(strRe)) {
				return strRe;
			}
		}

		return StringUtils.EMPTY;
	}

	/**
	 * 单条插入发货单信息
	 * 
	 * @param degReq
	 * @return
	 */
	private String insertDegOne(VpDegRequest degReq) {
		VpDegEntity degInfo = degReq.getDegInfo();

		if (StringUtils.isEmpty(degInfo.getDocCode()))
			return "发货单编码为空！";

		VpDegEntity dbDeg = uDCommDegDao.selectByDocCode(degInfo.getDocCode());
		if (dbDeg != null && !StringUtils.isEmpty(dbDeg.getDocCode())) {
			return "发货单：" + degInfo.getDocCode() + "，在系统中存在！";
		}

		float ttlQty = 0, ttlVal = 0;

		for (VpDegDtlEntity dtl : degReq.getDegDtls()) {

			if (StringUtils.isEmpty(dtl.getProdNum())) {
				return "发货单：" + degInfo.getDocCode() + "，商品编码有空值！";
			}

			// 在此检查商品是否在系统中存在！

			if (dtl.getQty() == 0) {
				return "发货单：" + degInfo.getDocCode() + "，商品编码："
						+ dtl.getProdNum() + "，订购数量为 0 ！";
			}

			ttlQty = ttlQty + dtl.getQty();
			ttlVal = ttlVal + dtl.getQty() * dtl.getUnitPrice();
		}

		degInfo.setTtlQty(ttlQty);
		degInfo.setTtlVal(ttlVal);
		degInfo.setDocState("1"); // 待发货

		if (uDCommDegDao.insert(degInfo) < 1) {
			return "发货单编码：" + degInfo.getDocCode() + "，新增失败！";
		}
		Long id = degInfo.getId();

		for (VpDegDtlEntity dtl : degReq.getDegDtls()) {
			dtl.setVpDegId(id);
			if (uDCommDegDtlDao.insert(dtl) < 1) {
				return "发货单编码：" + degInfo.getDocCode() + "，商品编码："
						+ dtl.getProdNum() + "，新增失败！";
			}
		}

		return StringUtils.EMPTY;
	}

	private String modifyDegRcvInfoOne(VpDegModifyEntity modifyInfo,
			VpDegEntity dbDeg) {
		StringBuilder strOriginalDesc = new StringBuilder();

		strOriginalDesc.append("收货信息变更：[");

		if (!StringUtils.isEmpty(modifyInfo.getShippingCode())
				&& !modifyInfo.getShippingCode().equals(dbDeg.getTspComCode()))
			strOriginalDesc.append("承运商:" + dbDeg.getTspComCode() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getConsignee())
				&& !modifyInfo.getConsignee().equals(dbDeg.getConsignee()))
			strOriginalDesc.append("收货人:" + dbDeg.getConsignee() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getMobile())
				&& !modifyInfo.getDecodeMobile().equals(dbDeg.getMobile()))
			strOriginalDesc.append("手机:" + dbDeg.getMobile() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getCountry())
				&& !modifyInfo.getCountry().equals(dbDeg.getCountry()))
			strOriginalDesc.append("国家:" + dbDeg.getCountry() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getProvince())
				&& !modifyInfo.getProvince().equals(dbDeg.getProvince()))
			strOriginalDesc.append("省份:" + dbDeg.getProvince() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getCity())
				&& !modifyInfo.getCity().equals(dbDeg.getCity()))
			strOriginalDesc.append("城市:" + dbDeg.getCity() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getDistrict())
				&& !modifyInfo.getDistrict().equals(dbDeg.getDistrict()))
			strOriginalDesc.append("区县:" + dbDeg.getDistrict() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getDelivAddress())
				&& !modifyInfo.getDelivAddress()
						.equals(dbDeg.getDelivAddress()))
			strOriginalDesc.append("地址:" + dbDeg.getDelivAddress() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getDelivPstd())
				&& !modifyInfo.getDelivPstd().equals(dbDeg.getDelivPstd()))
			strOriginalDesc.append("邮编:" + dbDeg.getDelivPstd() + ",");

		// 如果没有信息变更，则不新增 发货单变更记录
		if (strOriginalDesc.toString().endsWith("["))
			return StringUtils.EMPTY;

		//如果快递单号不为空，则添加变更显示信息
		if(!StringUtils.isEmpty(dbDeg.getExpressCode()))
		strOriginalDesc.append("快递单号:" + dbDeg.getExpressCode() + ",");
		
		if (strOriginalDesc.toString().endsWith(","))
			strOriginalDesc = strOriginalDesc.deleteCharAt(strOriginalDesc
					.length() - 1);

		strOriginalDesc.append("] -> [");

		if (!StringUtils.isEmpty(modifyInfo.getShippingCode())
				&& !modifyInfo.getShippingCode().equals(dbDeg.getTspComCode()))
			strOriginalDesc.append("承运商:" + modifyInfo.getShippingCode() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getConsignee())
				&& !modifyInfo.getConsignee().equals(dbDeg.getConsignee()))
			strOriginalDesc.append("收货人:" + modifyInfo.getConsignee() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getMobile())
				&& !modifyInfo.getDecodeMobile().equals(dbDeg.getMobile()))
			strOriginalDesc.append("手机:" + modifyInfo.getDecodeMobile() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getCountry())
				&& !modifyInfo.getCountry().equals(dbDeg.getCountry()))
			strOriginalDesc.append("国家:" + modifyInfo.getCountry() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getProvince())
				&& !modifyInfo.getProvince().equals(dbDeg.getProvince()))
			strOriginalDesc.append("省份:" + modifyInfo.getProvince() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getCity())
				&& !modifyInfo.getCity().equals(dbDeg.getCity()))
			strOriginalDesc.append("城市:" + modifyInfo.getCity() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getDistrict())
				&& !modifyInfo.getDistrict().equals(dbDeg.getDistrict()))
			strOriginalDesc.append("区县:" + modifyInfo.getDistrict() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getDelivAddress())
				&& !modifyInfo.getDelivAddress()
						.equals(dbDeg.getDelivAddress()))
			strOriginalDesc.append("地址:" + modifyInfo.getDelivAddress() + ",");

		if (!StringUtils.isEmpty(modifyInfo.getDelivPstd())
				&& !modifyInfo.getDelivPstd().equals(dbDeg.getDelivPstd()))
			strOriginalDesc.append("邮编:" + modifyInfo.getDelivPstd() + ",");

		if(!StringUtils.isEmpty(dbDeg.getExpressCode()))
		strOriginalDesc.append("快递单号:,");
		
		if (strOriginalDesc.toString().endsWith(","))
			strOriginalDesc = strOriginalDesc.deleteCharAt(strOriginalDesc
					.length() - 1);

		strOriginalDesc.append("]");

		VpDegModifyRecordEntity modifyRec = new VpDegModifyRecordEntity();
		modifyRec.setVpDegCode(dbDeg.getDocCode());
		modifyRec.setStatus(new Byte("0"));
		modifyRec.setOriginalValDesc(strOriginalDesc.toString());
		modifyRec.setModifyCode("变更收货信息");

		if (uDCommDegModifyRecordDao.insert(modifyRec) < 1)
			return "发货单编码：" + dbDeg.getDocCode() + "，收货信息变更记录新增失败！";

		if (uDCommDegDao.updateDegRcvInfoByOrderSn(modifyInfo) < 0)
			return "发货单编码：" + dbDeg.getDocCode() + "，收货信息变更失败！";

		return StringUtils.EMPTY;
	}

	private String cancelVpDegProductByDegCode(String degCode,
			List<VpDegDtlEntity> lstCancelProduct) throws Exception {

		if (lstCancelProduct == null || lstCancelProduct.size() == 0) {
			return "删除商品明细为空!";
		}

		VpDegEntity dbDeg = uDCommDegDao.selectByDocCode(degCode);

		if (dbDeg == null) {
			return "发货单编码：" + degCode + "，在系统中不存在！";
		}

		List<VpDegDtlEntity> lstDbDegDtl = uDCommDegDtlDao
				.selectDegDtlByParams(dbDeg.getId());

		if (lstDbDegDtl == null || lstDbDegDtl.size() == 0) {
			return "发货单：" + degCode + ",商品明细在系统中不存在！";
		}

		StringBuilder strOriginalDesc = new StringBuilder("商品变更：");

		VpDegEntity vpDegTtl = new VpDegEntity();
		vpDegTtl.setId(dbDeg.getId());

		for (VpDegDtlEntity cancelProd : lstCancelProduct) {

			boolean isExists = false; // 发货单明细中是否存在
			VpDegDtlEntity dbDegDtl = null; // 发货单商品明细
			for (VpDegDtlEntity dbProd : lstDbDegDtl) {
				if (cancelProd.getProdNum().equals(dbProd.getProdNum())) {
					dbDegDtl = dbProd;
					isExists = true;
					break;
				}
			}

			// 负数说明是扣减或删除 正数则是添加
			if (cancelProd.getQty() < 0) {
				if (!isExists) {
					return "商品：" + cancelProd.getProdNum() + "，在发货单商品明细中不存在！";
				}

				if (cancelProd.getQty() + dbDegDtl.getQty() < 0) {
					return "商品：" + cancelProd.getProdNum() + "，删除数量："
							+ ((int) cancelProd.getQty()) + " 大于订购数量："
							+ ((int) dbDegDtl.getQty());
				}

				if (cancelProd.getQty() + dbDegDtl.getQty() == 0) {

					if (uDCommDegDtlDao.deleteByPrimaryKey(dbDegDtl.getId()) < 1)
						return "发货单编码：" + degCode + "，商品编码："
								+ cancelProd.getProdNum()
								+ ",移除失败，请确认是否在发货单明细中存在！";

					strOriginalDesc.append("[商品编码：" + cancelProd.getProdNum()
							+ "，数量：" + ((int) dbDegDtl.getQty()) + "，删除]");
				} else {
					cancelProd.setId(dbDegDtl.getId());

					if (uDCommDegDtlDao.updateQtyById(cancelProd) < 0)
						return "发货单编码：" + degCode + "，商品编码："
								+ cancelProd.getProdNum()
								+ ",订购数量更新失败，请确认是否在发货单明细中存在！";

					strOriginalDesc
							.append("[商品编码：")
							.append(cancelProd.getProdNum())
							.append("，数量变更：")
							.append(((int) dbDegDtl.getQty()))
							.append(" -> ")
							.append(((int) (cancelProd.getQty() + dbDegDtl
									.getQty()))).append("]");
				}

			} else {

				if (!isExists) {
					cancelProd.setVpDegId(dbDeg.getId());

					if (uDCommDegDtlDao.insertSelective(cancelProd) < 1)
						return "发货单编码：" + degCode + "，商品编码："
								+ cancelProd.getProdNum() + "，插入失败";

					strOriginalDesc.append("[商品编码：" + cancelProd.getProdNum()
							+ "，数量：" + ((int) cancelProd.getQty()) + "，新增]");
				} else {
					cancelProd.setId(dbDegDtl.getId());

					if (uDCommDegDtlDao.updateQtyById(cancelProd) < 1)
						return "发货单编码：" + degCode + "，商品编码："
								+ cancelProd.getProdNum()
								+ ",订购数量更新失败，请确认是否在发货单明细中存在！";

					strOriginalDesc
							.append("[商品编码：")
							.append(cancelProd.getProdNum())
							.append("，数量变更：")
							.append(((int) dbDegDtl.getQty()))
							.append(" -> ")
							.append(((int) (cancelProd.getQty() + dbDegDtl
									.getQty()))).append("]");
				}

			}

			VpWarehProdEntity wp = new VpWarehProdEntity();
			wp.setProdNum(cancelProd.getProdNum());
			wp.setWarehCode(dbDeg.getWarehCode());
			wp.setQtyCommitted(cancelProd.getQty());

			if (uDCommWarehProdDao.updateQtyCommitted(wp) < 1)
				return "仓库编码：" + wp.getWarehCode() + "，商品编码：" + wp.getProdNum()
						+ "，锁定库存更新失败，请确认是否在商品库存中存在！";

			vpDegTtl.setTtlQty(vpDegTtl.getTtlQty() + cancelProd.getQty());
			vpDegTtl.setTtlVal(vpDegTtl.getTtlVal() + cancelProd.getQty()
					* cancelProd.getUnitPrice());

		}

		// 插入发货单变更记录表
		VpDegModifyRecordEntity modifyRecord = new VpDegModifyRecordEntity();
		modifyRecord.setVpDegCode(degCode);
		modifyRecord.setOriginalValDesc(strOriginalDesc.toString());
		modifyRecord.setStatus(new Byte("0"));
		modifyRecord.setModifyCode("商品变更");

		if (uDCommDegModifyRecordDao.insert(modifyRecord) < 0)
			return "发货单编码：" + degCode + "，商品变更记录新增失败！";

		if (uDCommDegDao.updateDetTtlQtyById(vpDegTtl) < 1)
			return "发货单编码：" + degCode + "，总数量、总金额更新失败！";

		return StringUtils.EMPTY;
	}

	@Transactional(value = "local", rollbackFor = Exception.class)
	public String writeVpWnResult(VpWnEntity wnInfo) throws Exception {

		if (wnInfo == null)
			return "传入对象为空！";

		VpWnEntity dbWnInfo = uDCommWnDao.selectByPrimaryKey(wnInfo.getId());

		if (dbWnInfo == null)
			return "工单ID：" + wnInfo.getId() + "，在系统中不存在！";

		// 处理中
		if (dbWnInfo.getDocState().equals("1")) {
			if (uDCommWnDao.updateTradeResult(wnInfo) < 1)
				return "工单ID：" + wnInfo.getId() + "，回写处理结果失败！";
		}

		return StringUtils.EMPTY;
	}

}
