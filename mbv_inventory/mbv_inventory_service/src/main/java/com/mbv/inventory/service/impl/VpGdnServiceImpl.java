package com.mbv.inventory.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.mbv.common.constant.MbvConstant;
import com.mbv.common.exception.MbvException;
import com.mbv.inventory.bean.InventoryBean;
import com.mbv.inventory.bean.VpGdnBean;
import com.mbv.inventory.bean.VpGdnDtlBean;
import com.mbv.inventory.dao.VpGdnDtlEntityDao;
import com.mbv.inventory.dao.VpGdnEntityDao;
import com.mbv.inventory.entity.DegDtlEntity;
import com.mbv.inventory.entity.InventoryEntity;
import com.mbv.inventory.entity.VpGdnDtlEntity;
import com.mbv.inventory.entity.VpGdnEntity;
import com.mbv.inventory.entity.VpWarehProdTranEntity;
import com.mbv.inventory.service.DeliveryService;
import com.mbv.inventory.service.InventoryService;
import com.mbv.inventory.service.VpGdnService;
import com.mbv.inventory.service.VpWarehProdTranService;
import com.metersbonwe.pca.common.ResultData;
import com.metersbonwe.pca.model.ProductInfo;
import com.metersbonwe.pca.service.UnitProductService;

@Service("vpGdnService")
public class VpGdnServiceImpl implements VpGdnService {

	private Logger log = LoggerFactory.getLogger(VpGdnServiceImpl.class);

	@Autowired
	private VpGdnEntityDao vpGdnEntityDao;

	@Autowired
	private VpGdnDtlEntityDao vpGdnDtlEntityDao;

	@Resource
	private InventoryService inventoryService;

	@Resource
	private VpWarehProdTranService vpWarehProdTranService;
	
	@Resource
	private UnitProductService unitProductService;
	
	@Resource
	private DeliveryService deliveryService;
	
	@Override
	public List<VpGdnEntity> queryByParams(VpGdnBean bean, int offset, int limit)
			throws MbvException {
		if (bean == null) {
			throw new MbvException("查询条件不能为空！");
		}
		return vpGdnEntityDao
				.selectByParams(bean, new RowBounds(offset, limit));
	}

	@Override
	public Integer queryByParamsCount(VpGdnBean bean) throws MbvException {
		if (bean == null) {
			throw new MbvException("查询条件不能为空！");
		}
		return vpGdnEntityDao.selectByParamsCount(bean);
	}

	@Override
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean addVpGdn(VpGdnEntity vpGdnEntity,
			List<VpGdnDtlEntity> details, String mode) throws MbvException, Exception {
		if (vpGdnEntity == null || details == null || details.size() < 0) {
			throw new MbvException("非法参数");
		}
		if (existVpGdnEntity(vpGdnEntity)) {
			throw new MbvException("出库单已存在！");
		}
		// 参数验证
		String errMessage = this.validate(vpGdnEntity);
		if (errMessage != null && !"".equals(errMessage)) {
			throw new MbvException(errMessage);
		}
		// 验证明细是否重复
		errMessage = this.validateDetail(vpGdnEntity, details);
		if (errMessage != null && !"".equals(errMessage)) {
			throw new MbvException(errMessage);
		}
		// 计算总数量及设置相关属性值
		vpGdnEntity = setVpGdnValue(vpGdnEntity, details);
		// 插入出库单
		long id = vpGdnEntityDao.insertSelective(vpGdnEntity);
		if (id < 1) {
			throw new MbvException("插入出库单失败！");
		}
		log.info("插入出库单主表成功！");
		id = vpGdnEntity.getId();

		// 批量插入出库单
		details = setVpGdnDtlEntityId(details, id);
		log.info("addVpGdn details size:" + details.size());
		int j = vpGdnDtlEntityDao.insertVpGdnDtlBatch(details);
		if (j < 1) {
			throw new MbvException("插入出库单明细失败！");
		}
		log.info("插入出库单明细表成功！j:" + j);
		//进度为确认出库的情况下记录事务表及修改库存数
		if (MbvConstant.DOC_STATE_CONFIRM.equals(vpGdnEntity.getDocState())) {
			//如果是发货出库的话，需要扣减已锁定库存
			insertOrUpdateInventory(vpGdnEntity, details, mode);
			log.info("插入库存表成功！");
			// 修改库存表及出入库事务表信息
			insertProdTran(vpGdnEntity, details);
			log.info("插入出库事务表成功！");
		}
		return true;
	}

	private VpGdnEntity setVpGdnValue(VpGdnEntity vpGdnEntity,
			List<VpGdnDtlEntity> details) {
		int ttlQty = 0;
		for (VpGdnDtlEntity vgde : details) {
			ttlQty += vgde.getQty();
		}
		vpGdnEntity.setTtlQty(ttlQty);
		vpGdnEntity.setDocDate(new Date());
		vpGdnEntity.setDispTime(new Date());
		vpGdnEntity.setCreateUser(MbvConstant.CREATE_USER);
		vpGdnEntity.setCreateDate(new Date());
		vpGdnEntity.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
		vpGdnEntity.setLastModifiedDate(new Date());
		return vpGdnEntity;
	}

	private void insertProdTran(VpGdnEntity vge, List<VpGdnDtlEntity> details)
			throws MbvException, Exception {
		List<VpWarehProdTranEntity> prodTranList = new ArrayList<VpWarehProdTranEntity>();
		for (VpGdnDtlEntity vgde : details) {
			// 根据商品编码及库存编码查询当前商品库存数量
			List<InventoryEntity> inventoryEntityList = getProdInventoryByGdn(
					vge, vgde);
			if (CollectionUtils.isEmpty(inventoryEntityList)) {
				throw new MbvException("出库失败！不存在相应商品库存");
			}
//			int stockOnHand = 0;
			// 如果该商品库存不存在，则插入该商品库存数据
			InventoryEntity ie = inventoryEntityList.get(0);
			VpWarehProdTranEntity tranEntity = new VpWarehProdTranEntity();
			tranEntity.setWarehCode(vge.getWarehCode());
			tranEntity.setProdNum(vgde.getProdNum());
			tranEntity.setTranDate(new Date());
			tranEntity.setTranTime(new Date());
			tranEntity.setDocType(MbvConstant.DOC_TYPE_GDN);
			tranEntity.setDocNum(vge.getDocCode());
			tranEntity.setTranQty(BigDecimal.valueOf(vgde.getQty()));
//			stockOnHand = ie.getStkOnHand().intValue() - ie.getQtyCommitted().intValue() - ie.getQtyOnLock().intValue();
			// 当前实际数量为总数量-锁定量-已分配量
			tranEntity.setStkOnHand(ie.getStkOnHand());
			tranEntity.setQtyCommitted(ie.getQtyCommitted());
			tranEntity.setQtyOnLock(ie.getQtyOnLock());
			tranEntity.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
			tranEntity.setLastModifiedDate(new Date());
			prodTranList.add(tranEntity);
		}
		// 批量插入出入库事务表
		int k = vpWarehProdTranService.insertVpWarehProdTranBatch(prodTranList);
		if (k < 1) {
			throw new MbvException("出库失败！插入事务表失败！");
		}
	}

	private List<InventoryEntity> getProdInventoryByGdn(VpGdnEntity vge,
			VpGdnDtlEntity vgde) {
		InventoryBean bean = new InventoryBean();
		bean.setProdNum(vgde.getProdNum());
		bean.setWarehCode(vge.getWarehCode());
		List<InventoryEntity> inventoryEntityList = inventoryService
				.queryByParams(bean, MbvConstant.QUERY_OFFSET_DEFAULT_VALUE,
						MbvConstant.QUERY_LIMITSET_DEFAULT_VALUE);
		if (CollectionUtils.isEmpty(inventoryEntityList)) {
			return null;
		}
		return inventoryEntityList;
	}

	private List<VpGdnDtlEntity> setVpGdnDtlEntityId(
			List<VpGdnDtlEntity> details, long id) {
		for (VpGdnDtlEntity vgde : details) {
			if (vgde.getProdNum() == null || "".equals(vgde.getProdNum())
					|| vgde.getQty() < 0) {
				log.info("出库明细参数错误！");
				throw new MbvException("明细参数错误！！");
			}
			vgde.setVpGdnId(id);
		}
		return details;
	}

	private boolean existVpGdnEntity(VpGdnEntity vpGdnEntity) {
		if (vpGdnEntityDao.selectByVpGdnEntity(vpGdnEntity) != null) {
			return true;
		}
		return false;
	}

	@Override
	public String getVpGdnMaxDocCode(String seqName) throws MbvException {
		String docCode = vpGdnEntityDao.getMaxDocCode(seqName);
		return (Integer.valueOf(docCode) + 1 + "");
	}

	@Override
	public VpGdnEntity getVpGdnByEntity(VpGdnEntity entity) throws MbvException {
		if (entity == null) {
			throw new MbvException("参数错误！");
		}
		VpGdnEntity vge = null;
		if (entity.getId() != null && entity.getId() > 0) {
			vge = vpGdnEntityDao.selectByPrimaryKey(entity.getId());
		} else {
			vge = vpGdnEntityDao.selectByVpGdnEntity(entity);
		}
		if (vge == null) {
			throw new MbvException("查不到对应的出库单！");
		}
		List<VpGdnDtlEntity> vpGdnDtlEntityList = vpGdnDtlEntityDao
				.selectVpGdnDtlByVpGdnId(vge.getId());
		vge.setDetails(vpGdnDtlEntityList);
		return vge;
	}

	@Override
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean updateVpGdn(VpGdnEntity entity, List<VpGdnDtlEntity> details)
			throws MbvException, Exception {
		if (entity == null || details == null || details.size() < 0) {
			throw new MbvException("参数不能为空！");
		}
		// 参数验证
		String errMessage = this.validate(entity);
		if (errMessage != null && !"".equals(errMessage)) {
			throw new MbvException(errMessage);
		}
		// 验证入库明细有效性
		errMessage = this.validateDetail(entity, details);
		if (errMessage != null && !"".equals(errMessage)) {
			throw new MbvException(errMessage);
		}
		
		log.info("updateVpGdn selectByDocCode param-->" + entity.getDocCode());
		VpGdnEntity vge = vpGdnEntityDao.selectByDocCode(entity.getDocCode());
		if(vge == null || vge.getId() == null || vge.getId() < 1){
			throw new MbvException("更新出库单失败！查不到相应的出库单！");
		}
		int ttlQty = 0;
		for (VpGdnDtlEntity vgde : details) {
			ttlQty += vgde.getQty();
		}
		vge.setTtlQty(ttlQty);
		vge.setDispMode(entity.getDispMode());
		vge.setDispReason(entity.getDispReason());
		vge.setDocState(entity.getDocState());
		vge.setRemark(entity.getRemark());
		vge.setLastModifiedDate(new Date());
		vge.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
		if (vpGdnEntityDao.updateByPrimaryKeySelective(vge) < 1) {
			throw new MbvException("更新出库单失败！");
		}
		if (vpGdnDtlEntityDao.deleteByVpGdnId(vge.getId()) < 1) {
			throw new MbvException("修改出库单明细失败！");
		}
		//插入明细前赋值vp_gdn_id值
		details = setVpGdnDtlEntityId(details,vge.getId());
		if (vpGdnDtlEntityDao.insertVpGdnDtlBatch(details) < 1) {
			throw new MbvException("修改出库单明细失败！");
		}
		// 状态为已确认的情况下插入事务表并更新库存
		if (MbvConstant.DOC_STATE_CONFIRM.equals(entity.getDocState())) {
			insertOrUpdateInventory(vge, details, null);
			insertProdTran(vge, details);
		}
		return true;
	}

	private void insertOrUpdateInventory(VpGdnEntity vge,
			List<VpGdnDtlEntity> details, String mode) throws MbvException, Exception {
		log.info("insertOrUpdateInventory details size:" + details.size());
		for (VpGdnDtlEntity vgde : details) {
			// 根据商品编码及库存编码查询当前商品库存数量
			List<InventoryEntity> inventoryEntityList = getProdInventoryByGdn(vge, vgde);
			int stockOnHand = 0;
			// 如果该商品库存不存在，则插入该商品库存数据
			if (CollectionUtils.isEmpty(inventoryEntityList)) {
				throw new MbvException("出库失败！商品库存数据不存在！");
			}
			InventoryEntity ie = inventoryEntityList.get(0);
			//判断如果实际可用数据小于出库数量则不能出库
			stockOnHand = ie.getStkOnHand().intValue() - ie.getQtyCommitted().intValue() - vgde.getQty();
			if(stockOnHand < 0){
				throw new MbvException("出库失败！当前库存小于出库库存！");
			}
			//保存数据时总数量还是减去当前操作数量
			stockOnHand = ie.getStkOnHand().intValue() - vgde.getQty();
			ie.setStkOnHand(BigDecimal.valueOf(stockOnHand));
			//如果出库方式为零售出库或者发货出库的话，当前分配量需要减去出库数量，其它出库方式不更新已分配量数据
			if((mode != null && !"".equals(mode) && MbvConstant.DOC_STATE_DEG.equals(mode))
					|| (vge.getDispMode() != null && !"".equals(vge.getDispMode()) && MbvConstant.DISP_MODE_DEG.equals(vge.getDispMode()))){
				
				//已发货数量，包括当前插入明细数量
				int totalQty = 0;
				
				//根据bean查询该商品已发货的数量
				VpGdnBean bean = new VpGdnBean();
				bean.setUnitCode(vge.getUnitCode());
				bean.setWarehCode(vge.getWarehCode());
				bean.setSrcDocCode(vge.getSrcDocCode());
				bean.setProdNum(vgde.getProdNum());
				List<VpGdnDtlEntity> vpGdnDtlEntityList = vpGdnDtlEntityDao.selectByVpGdnBeanAndProdNum(bean);
				log.info("vpGdnDtlEntityList size:" + vpGdnDtlEntityList.size());
				if(vpGdnDtlEntityList != null && vpGdnDtlEntityList.size() > 0){
					for(VpGdnDtlEntity vpGdnDtlEntity : vpGdnDtlEntityList){
						totalQty += vpGdnDtlEntity.getQty();
					}
				}
				
				log.info("出库表中已发货数量totalQty：" + totalQty);
				//实际订单数量
				int orderQty = 0;
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("unitCode", vge.getUnitCode());
				params.put("warehCode", vge.getWarehCode());
				params.put("docCode", vge.getSrcDocCode());
				params.put("prodNum", vgde.getProdNum());
				orderQty = getOrderQtyByParamMap(params);
				log.info("订单数量orderQty：" + orderQty);
				//判断已发货数量是否大于订单数量
				if(totalQty > orderQty){
					throw new MbvException("出库失败！已发货数量大于订单数量！");
				}
				int qtyCommitted = 0;
				qtyCommitted = ie.getQtyCommitted().intValue() - vgde.getQty();
				if(qtyCommitted < 0){
					throw new MbvException("出库失败！已分配量小于出库数量！");
				}
				ie.setQtyCommitted(BigDecimal.valueOf(qtyCommitted));
			}
			ie.setLastModifiedDate(new Date());
			ie.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
			int updateCount = 0;
			updateCount = inventoryService.updateInventoryEntity(ie);
			if (updateCount < 1) {
				log.info("更新库存表失败！！");
				throw new MbvException("出库失败！更新库存表失败！");
			}
			log.info("更新库存表成功");
		}
	}

	private int getOrderQtyByParamMap(Map<String, Object> params) {
		List<DegDtlEntity> degDtlEntityList = deliveryService.getVpDegDtlEntityListByParamMap(params);
		if(org.apache.commons.collections.CollectionUtils.isEmpty(degDtlEntityList)){
			throw new MbvException("出库失败！订单不存在！");
		}
		int orderQty = 0;
		for(DegDtlEntity dde : degDtlEntityList){
			orderQty += dde.getQty().intValue();
		}
		return orderQty;
	}

	@Override
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean deleteVpGdnBatch(List<VpGdnEntity> vpGdnEntityList)
			throws MbvException, Exception {
		if (CollectionUtils.isEmpty(vpGdnEntityList)) {
			throw new MbvException("参数为空！");
		}
		for (VpGdnEntity vge : vpGdnEntityList) {
			if (vge.getId() == null || vge.getId() < 1) {
				throw new MbvException("出库单Id不存在，参数错误！");
			}
			if (vpGdnEntityDao.deleteByPrimaryKey(vge.getId()) < 0) {
				throw new MbvException("删除出库单失败！");
			}
			if (vpGdnDtlEntityDao.deleteByVpGdnId(vge.getId()) < 0) {
				throw new MbvException("删除出库单明细失败！");
			}
		}
		return true;
	}

	@Override
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean deleteVpGdnByEntity(VpGdnEntity vpGdnEntity)
			throws MbvException, Exception {
		if (vpGdnEntity == null || vpGdnEntity.getId() < 1
				|| vpGdnEntity.getDocState() == null
				|| "".equals(vpGdnEntity.getDocState())) {
			throw new MbvException("参数错误！");
		}
		String docState = vpGdnEntity.getDocState();
		vpGdnEntity = vpGdnEntityDao.selectByPrimaryKey(vpGdnEntity.getId());
		if (vpGdnEntity == null) {
			throw new MbvException("参数错误！");
		}
		if (MbvConstant.DOC_STATE_INPUTING.equals(docState)) {
			vpGdnEntity.setDocState(MbvConstant.DOC_STATE_DELETE);
		} else if (MbvConstant.DOC_STATE_CONFIRM.equals(docState)) {
			vpGdnEntity.setDocState(MbvConstant.DOC_STATE_UNDO);
		} else {
			throw new MbvException("未知的出库状态！");
		}
		vpGdnEntity.setLastModifiedDate(new Date());
		vpGdnEntity.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
		int updateCount = vpGdnEntityDao.updateByPrimaryKey(vpGdnEntity);
		if (updateCount < 1) {
			throw new MbvException("更新出库单失败！");
		}

		List<VpGdnDtlEntity> details = vpGdnDtlEntityDao
				.selectVpGdnDtlByVpGdnId(vpGdnEntity.getId());
		if (CollectionUtils.isEmpty(details)) {
			throw new MbvException("出库单数据错误！对应的出库单明细不存在！");
		}
		// 判断如果状态是已出库的话，则恢复库存
		if (MbvConstant.DOC_STATE_CONFIRM.equals(docState)) {
			// 根据入库明细查询对应库存，并重新计算库存数
			for (VpGdnDtlEntity detail : details) {
				InventoryBean bean = new InventoryBean();
				bean.setWarehCode(vpGdnEntity.getWarehCode());
				bean.setProdNum(detail.getProdNum());
				List<InventoryEntity> invEntityList = inventoryService
						.queryByParams(bean,
								MbvConstant.QUERY_OFFSET_DEFAULT_VALUE,
								MbvConstant.QUERY_LIMITSET_DEFAULT_VALUE);
				if (CollectionUtils.isEmpty(invEntityList)) {
					throw new MbvException("查不到库存信息！");
				}
				InventoryEntity invEntity = invEntityList.get(0);
				int stkOnHand = invEntity.getStkOnHand().intValue() + detail.getQty();
				invEntity.setStkOnHand(BigDecimal.valueOf(stkOnHand));
				invEntity.setLastModifiedDate(new Date());
				invEntity.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
				int count = inventoryService.updateInventoryEntity(invEntity);
				if (count > 0) {
					log.info("单据" + vpGdnEntity.getDocCode() + "撤单成功");
				} else {
					log.info("单据" + vpGdnEntity.getDocCode() + "撤单失败");
				}
			}
			insertProdTran(vpGdnEntity, details);
			log.info("插入事务表成功");
		}
		return true;
	}

	@Override
	public String validate(VpGdnEntity entity) throws MbvException {
		if (entity.getUnitCode() == null || "".equals(entity.getUnitCode())) {
			return "组织编码不能为空";
		}
		if (entity.getWarehCode() == null || "".equals(entity.getWarehCode())) {
			return "仓库编码不能为空";
		}
		if (entity.getDispMode() == null || "".equals(entity.getDispMode())) {
			return "出库方式不能为空";
		}
		if (entity.getDispReason() == null || "".equals(entity.getDispReason())) {
			return "出库原因不能为空";
		}
		if (entity.getDocState() == null || "".equals(entity.getDocState())) {
			return "出库进度不能为空";
		}
		return null;
	}

	@Override
	public String validateDetail(VpGdnEntity entity,
			List<VpGdnDtlEntity> details) throws MbvException {
		if (details == null || details.size() < 1) {
			return "出库明细不能为空！";
		}
		for (VpGdnDtlEntity vgde : details) {
			if (vgde.getProdNum() == null || "".equals(vgde.getProdNum())) {
				return "参数错误！商品编码不能为空！";
			}
			if(vgde.getProdNum().length() != 11){
				return "参数错误！商品编码必须为11位！";
			}
			if (vgde.getQty() < 1) {
				return "参数错误！数量不能为空而必须大于0！";
			}
			Matcher m = Pattern.compile(MbvConstant.NUMBER_REX).matcher(vgde.getQty() + "");
			if(!m.find()){
				return "参数错误！数量必须为大于零的整数！";
			}
			if (!existsProduct(vgde.getProdNum(), entity.getWarehCode())) {
				return "出库商品:" + vgde.getProdNum() + "不存在！请确认商品编码是否正确！";
			}
			if (details.size() == 1) {
				return "";
			}
		}
		Set<VpGdnDtlEntity> detailCodes = new HashSet<VpGdnDtlEntity>(details);
		return details.size() == detailCodes.size() ? null : "出库明细不能重复！";
	}

	private boolean existsProduct(String prodNum, String warehCode) {
		if (prodNum == null || "".equals(prodNum)) {
			throw new MbvException("商品编码不能为空！");
		}
		InventoryBean bean = new InventoryBean();
		bean.setProdNum(prodNum);
		bean.setWarehCode(warehCode);
		int count = inventoryService.queryByParamsCount(bean);
		if (count < 1) {
			return false;
		}
		return true;
	}

	@Override
	public VpGdnBean getVpGdnBeanByEntity(VpGdnEntity entity)
			throws MbvException {
		if (entity == null) {
			throw new MbvException("参数有误！");
		}
		VpGdnEntity vge = null;
		if (entity.getId() != null && entity.getId() > 0) {
			vge = vpGdnEntityDao.selectByPrimaryKey(entity.getId());
		} else {
			vge = vpGdnEntityDao.selectByVpGdnEntity(entity);
		}
		if (vge == null) {
			return null;
		}
		log.info("getVpGdnBeanByEntity vge.id:" + vge.getId());
		VpGdnBean bean = convertToVpGdnBean(vge);
		List<VpGdnDtlEntity> vpGdnDtlEntityList = vpGdnDtlEntityDao.selectVpGdnDtlByVpGdnId(vge.getId());
		log.info("getVpGdnBeanByEntity vpGdnDtlEntityList size:" + vpGdnDtlEntityList.size());
		List<VpGdnDtlBean> vpGdnDtlBeanList = converToVpGdnBeanList(vpGdnDtlEntityList);
		log.info("getVpGdnBeanByEntity vpGdnDtlBeanList size:" + vpGdnDtlBeanList.size());
		bean.setDetailBeans(vpGdnDtlBeanList);
		return bean;
	}
	
	private VpGdnBean convertToVpGdnBean(VpGdnEntity vge) {
		VpGdnBean bean = new VpGdnBean();
		bean.setId(vge.getId());
		bean.setDocCode(vge.getDocCode());
		bean.setDocDate(vge.getDocDate());
		bean.setUnitCode(vge.getUnitCode());
		bean.setWarehCode(vge.getWarehCode());
		bean.setTtlQty(vge.getTtlQty());
		bean.setDispMode(vge.getDispMode());
		bean.setDispTime(vge.getDispTime());
		bean.setDispReason(vge.getDispReason());
		bean.setDocState(vge.getDocState());
		bean.setSrcDocTye(vge.getSrcDocTye());
		bean.setSrcDocCode(vge.getSrcDocCode());
		bean.setRemark(vge.getRemark());
		bean.setVersion(vge.getVersion());
		return bean;
	}

	private List<VpGdnDtlBean> converToVpGdnBeanList(
			List<VpGdnDtlEntity> vpGdnDtlEntityList) {
		List<VpGdnDtlBean> detailBeans = new ArrayList<VpGdnDtlBean>();
		if(org.apache.commons.collections.CollectionUtils.isNotEmpty(vpGdnDtlEntityList)){
			for(VpGdnDtlEntity vgde : vpGdnDtlEntityList){
				VpGdnDtlBean detailBean = convertToVpGdnDtlBean(vgde);
				detailBeans.add(detailBean);
			}
		}
		return detailBeans;
	}
	
	private VpGdnDtlBean convertToVpGdnDtlBean(VpGdnDtlEntity vgde) {
		VpGdnDtlBean detail = new VpGdnDtlBean();
		detail.setId(vgde.getId());
		detail.setVpGdnId(vgde.getVpGdnId());
		detail.setProdNum(vgde.getProdNum());
		detail.setQty(vgde.getQty());
		detail.setRemark(vgde.getRemark());
		
		ResultData<ProductInfo> result = unitProductService.getProductByProdCode(MbvConstant.PRODUCT_SKU_TYPE, vgde.getProdNum());
		if(MbvConstant.RESPONSE_SUCCESS == result.getIsOk()){
			detail.setProdClsNum(result.getData().getProdClsNum());
			detail.setProdName(result.getData().getProdName());
			detail.setColorCode(result.getData().getProductSkuInfos().get(0).getColorCode());
			detail.setColorName(result.getData().getProductSkuInfos().get(0).getColorName());
			detail.setSpecCode(result.getData().getProductSkuInfos().get(0).getSpecCode());
			detail.setSpecName(result.getData().getProductSkuInfos().get(0).getSpecName());
		}else{
			log.info("convertToVpGdnDtlBean 查询商品：" + vgde.getProdNum() + "失败！");
		}
		return detail;
	}
}
