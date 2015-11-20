package com.mbv.inventory.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import com.mbv.inventory.bean.VpGrnBean;
import com.mbv.inventory.bean.VpGrnDtlBean;
import com.mbv.inventory.dao.VpGrnDtlEntityDao;
import com.mbv.inventory.dao.VpGrnEntityDao;
import com.mbv.inventory.entity.InventoryEntity;
import com.mbv.inventory.entity.VpGrnDtlEntity;
import com.mbv.inventory.entity.VpGrnEntity;
import com.mbv.inventory.entity.VpWarehProdTranEntity;
import com.mbv.inventory.service.InventoryService;
import com.mbv.inventory.service.VpGrnService;
import com.mbv.inventory.service.VpWarehProdTranService;
import com.metersbonwe.pca.common.ResultData;
import com.metersbonwe.pca.model.ProductInfo;
import com.metersbonwe.pca.service.UnitProductService;

@Service("vpGrnService")
public class VpGrnServiceImpl implements VpGrnService {

	private final static Logger log = LoggerFactory.getLogger(VpGrnServiceImpl.class);

	@Autowired
	private VpGrnEntityDao vpGrnEntityDao;

	@Autowired
	private VpGrnDtlEntityDao vpGrnDtlEntityDao;

	@Resource
	private InventoryService inventoryService;

	@Resource
	private VpWarehProdTranService vpWarehProdTranService;
	
	@Resource
	private UnitProductService unitProductService;

	@Override
	public List<VpGrnEntity> queryByParams(VpGrnBean bean, int offset, int limit)
			throws MbvException {
		if (bean == null) {
			throw new MbvException("查询条件不能为空！");
		}
		return vpGrnEntityDao
				.selectByParams(bean, new RowBounds(offset, limit));
	}

	@Override
	public Integer queryByParamsCount(VpGrnBean bean) throws MbvException {
		if (bean == null) {
			throw new MbvException("查询条件不能为空！");
		}
		return vpGrnEntityDao.selectByParamsCount(bean);
	}

	@Override
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean addVpGrn(VpGrnEntity vpGrnEntity,
			List<VpGrnDtlEntity> details) throws MbvException, Exception {
		if (vpGrnEntity == null || details == null || details.size() < 0) {
			throw new MbvException("参数不能为空！");
		}
		if (existVpGrnEntity(vpGrnEntity)) {
			throw new MbvException("入库单已存在！");
		}
		// 参数验证
		String errMessage = this.validate(vpGrnEntity);
		if (errMessage != null && !"".equals(errMessage)) {
			throw new MbvException(errMessage);
		}
		// 验证明细是否重复
		errMessage = this.validateDetail(vpGrnEntity,details);
		if (errMessage != null && !"".equals(errMessage)) {
			throw new MbvException(errMessage);
		}
		// 计算总数量及设置相关属性值
		vpGrnEntity = setVpGrnValue(vpGrnEntity, details);
		long id = vpGrnEntityDao.insertSelective(vpGrnEntity);
		if (id < 1) {
			throw new MbvException("插入入库单表失败！");
		}
		log.info("插入入库单表成功");
		// 设置入库明细表的grn_id及事务日志对象
		details = setVpGrnDtlEntityId(vpGrnEntity, details);

		// 插入入库明细表
		int j = vpGrnDtlEntityDao.insertVpGrnDtlBatch(details);
		if (j < 1) {
			throw new MbvException("插入入库单明细失败！");
		}
		log.info("插入入库明细表成功");
		// 只有确认状态才记录事务表，并且修改库存数
		if (MbvConstant.DOC_STATE_CONFIRM.equals(vpGrnEntity.getDocState())) {
			insertOrUpdateInventory(vpGrnEntity, details);
			// 插入出入库事务表及更新库存表
			insertProdTran(vpGrnEntity, details);
			log.info("插入事务表成功");
		}
		return true;
	}

	/**
	 * 验证入库单明细正确性
	 * 
	 * @param details
	 * @return
	 */
	@Override
	public String validateDetail(VpGrnEntity vpGrnEntity,List<VpGrnDtlEntity> details) {
		if(details == null || details.size() < 1){
			return "入库明细不能为空！";
		}
		for (VpGrnDtlEntity vgde : details) {
			if (vgde.getProdNum() == null || "".equals(vgde.getProdNum())) {
				return "参数错误！商品编码不能为空！";
			}
			if (vgde.getQty() < 1) {
				return "参数错误！数量不能为空而必须大于0！";
			}
			Matcher m = Pattern.compile(MbvConstant.NUMBER_REX).matcher(vgde.getQty() + "");
			if(!m.find()){
				return "参数错误！数量必须为大于零的整数！";
			}
			if(!existsProduct(vgde.getProdNum(),vpGrnEntity.getWarehCode())){
				return "入库商品不存在！请确认商品编码是否正确！";
			}
			if (details.size() == 1) {
				return "";
			}
		}
		Set<VpGrnDtlEntity> detailCodes = new HashSet<VpGrnDtlEntity>(details);
		return details.size() == detailCodes.size() ? null : "入库明细不能重复！";
	}

	private boolean existsProduct(String prodNum, String warehCode) {
		if(prodNum == null || "".equals(prodNum)){
			throw new MbvException("商品编码不能为空！");
		}
		ResultData<ProductInfo> result = unitProductService.getProductByProdCode(MbvConstant.PRODUCT_SKU_TYPE, prodNum);
		if(MbvConstant.RESPONSE_SUCCESS != result.getIsOk()){
			return false;
		}
		return true;
	}

	@Override
	public String validate(VpGrnEntity vpGrnEntity) {
		if (vpGrnEntity.getUnitCode() == null
				|| "".equals(vpGrnEntity.getUnitCode())) {
			return "组织编码不能为空";
		}
		if (vpGrnEntity.getWarehCode() == null
				|| "".equals(vpGrnEntity.getWarehCode())) {
			return "仓库编码不能为空";
		}
		if (vpGrnEntity.getRcptMode() == null
				|| "".equals(vpGrnEntity.getRcptMode())) {
			return "入库方式不能为空";
		}
		if (vpGrnEntity.getRcptReason() == null
				|| "".equals(vpGrnEntity.getRcptReason())) {
			return "入库原因不能为空";
		}
		if (vpGrnEntity.getDocState() == null
				|| "".equals(vpGrnEntity.getDocState())) {
			return "入库进度不能为空";
		}
		return null;
	}

	private void insertOrUpdateInventory(VpGrnEntity vge,
			List<VpGrnDtlEntity> details) throws MbvException, Exception {
		for (VpGrnDtlEntity vgde : details) {
			// 根据商品编码及库存编码查询当前商品库存数量
			List<InventoryEntity> inventoryEntityList = getProdInventoryByGrn(vge, vgde);
			int stockOnHand = 0;
			// 如果该商品库存不存在，则插入该商品库存数据
			if (CollectionUtils.isEmpty(inventoryEntityList)) {
				InventoryEntity entity = new InventoryEntity();
				entity.setProdNum(vgde.getProdNum());
				entity.setWarehCode(vge.getWarehCode());
				stockOnHand = vgde.getQty();
				entity.setStkOnHand(BigDecimal.valueOf(stockOnHand));
				entity.setQtyCommitted(BigDecimal.valueOf(MbvConstant.QTY_COMMITTED_DEFAULT_VALUE));
				entity.setQtyOnLock(BigDecimal.valueOf(MbvConstant.QTY_ON_LOCK_DEFAULT_VALUE));
				entity.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
				entity.setLastModifiedDate(new Date());
				int insertCount = inventoryService.insertInventoryEntity(entity);
				if (insertCount < 1) {
					log.info("插入库存表失败！！");
					throw new MbvException("插入库存表失败！");
				}
				log.info("插入库存表成功");
			} else {
				InventoryEntity ie = inventoryEntityList.get(0);
				stockOnHand = ie.getStkOnHand().intValue() + vgde.getQty();
				ie.setStkOnHand(BigDecimal.valueOf(stockOnHand));
				ie.setLastModifiedDate(new Date());
				ie.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
				int updateCount = 0;
				updateCount = inventoryService.updateInventoryEntity(ie);
				if (updateCount < 1) {
					log.info("更新库存表失败！！");
					throw new MbvException("更新库存表失败！");
				}
				log.info("更新库存表成功");
			}
		}
	}

	private void insertProdTran(VpGrnEntity vge, List<VpGrnDtlEntity> details)
			throws MbvException, Exception {
		List<VpWarehProdTranEntity> prodTranList = new ArrayList<VpWarehProdTranEntity>();
		for (VpGrnDtlEntity vgde : details) {
			List<InventoryEntity> inventoryEntityList = getProdInventoryByGrn(vge, vgde);
			if(CollectionUtils.isEmpty(inventoryEntityList)){
				log.info("当前商品库存不存在，入库失败！");
				throw new MbvException("当前商品库存不存在，入库失败！");
			}
			InventoryEntity ie = inventoryEntityList.get(0);
			int stockOnHand = 0;
			// 根据商品编码及库存编码查询当前商品库存数量
			VpWarehProdTranEntity tranEntity = new VpWarehProdTranEntity();
			tranEntity.setWarehCode(vge.getWarehCode());
			tranEntity.setProdNum(vgde.getProdNum());
			tranEntity.setTranDate(new Date());
			tranEntity.setTranTime(new Date());
			tranEntity.setDocType(MbvConstant.DOC_TYPE_GRN);
			tranEntity.setDocNum(vge.getDocCode());
			tranEntity.setTranQty(BigDecimal.valueOf(vgde.getQty()));
			if(MbvConstant.DOC_STATE_CONFIRM.equals(vge.getDocState())){
				stockOnHand = ie.getStkOnHand().intValue() - ie.getQtyCommitted().intValue() - ie.getQtyOnLock().intValue();
			}else if(MbvConstant.DOC_STATE_UNDO.equals(vge.getDocState())){
				stockOnHand = ie.getStkOnHand().intValue() - ie.getQtyCommitted().intValue() - ie.getQtyOnLock().intValue();
				if(stockOnHand < 0){
					throw new MbvException("修改事务表库存失败！当前库存小于撤消库存！");
				}
			}
			tranEntity.setStkOnHand(BigDecimal.valueOf(stockOnHand));
			tranEntity.setQtyCommitted(ie.getQtyCommitted());
			tranEntity.setQtyOnLock(ie.getQtyOnLock());
			tranEntity.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
			tranEntity.setLastModifiedDate(new Date());
			prodTranList.add(tranEntity);
		}
		int k = vpWarehProdTranService.insertVpWarehProdTranBatch(prodTranList);
		if (k < 1) {
			throw new MbvException("插入事务表失败");
		}
	}

	private VpGrnEntity setVpGrnValue(VpGrnEntity vpGrnEntity,
			List<VpGrnDtlEntity> details) {
		int ttlQty = 0;
		for (VpGrnDtlEntity vgde : details) {
			ttlQty += vgde.getQty();
		}
		vpGrnEntity.setTtlQty(ttlQty);
		vpGrnEntity.setDocDate(new Date());
		vpGrnEntity.setRcptTime(new Date());
		vpGrnEntity.setCreateUser(MbvConstant.CREATE_USER);
		vpGrnEntity.setCreateDate(new Date());
		vpGrnEntity.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
		vpGrnEntity.setLastModifiedDate(new Date());
		return vpGrnEntity;
	}

	private List<VpGrnDtlEntity> setVpGrnDtlEntityId(VpGrnEntity vge,
			List<VpGrnDtlEntity> details) {
		for (VpGrnDtlEntity vgde : details) {
			vgde.setVpGrnId(vge.getId());
		}
		return details;
	}

	private List<InventoryEntity> getProdInventoryByGrn(VpGrnEntity vge,
			VpGrnDtlEntity vgde) {
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

	private boolean existVpGrnEntity(VpGrnEntity vpGrnEntity) {
		if (vpGrnEntityDao.selectByVpGrnEntity(vpGrnEntity) != null) {
			return true;
		}
		return false;
	}

	@Override
	public String getVpGrnMaxDocCode(String seqName) throws MbvException {
		String docCode = vpGrnEntityDao.getMaxDocCode(seqName);
		log.info("result docCode:" + docCode);
		return docCode;
	}

	@Override
	public VpGrnBean getVpGrnBeanByEntity(VpGrnEntity entity) throws MbvException {
		if (entity == null) {
			throw new MbvException("参数有误！");
		}
		VpGrnEntity vge = null;
		if (entity.getId() != null && entity.getId() > 0) {
			vge = vpGrnEntityDao.selectByPrimaryKey(entity.getId());
		} else {
			vge = vpGrnEntityDao.selectByVpGrnEntity(entity);
		}
		if (vge == null) {
			return null;
		}
		log.info("getVpGrnBeanByEntity vge.id:" + vge.getId());
		VpGrnBean bean = convertToVpGrnBean(vge);
		List<VpGrnDtlEntity> vpGrnDtlEntityList = vpGrnDtlEntityDao.selectVpGrnDtlByVpGrnId(vge.getId());
		log.info("getVpGrnBeanByEntity vpGrnDtlEntityList size:" + vpGrnDtlEntityList.size());
		List<VpGrnDtlBean> vpGrnDtlBeanList = converToVpGrnBeanList(vpGrnDtlEntityList);
		log.info("getVpGrnBeanByEntity vpGrnDtlBeanList size:" + vpGrnDtlBeanList.size());
		bean.setDetailBeans(vpGrnDtlBeanList);
		return bean;
	}
	
	@Override
	public VpGrnEntity getVpGrnByEntity(VpGrnEntity entity) throws MbvException {
		if (entity == null) {
			throw new MbvException("参数有误！");
		}
		VpGrnEntity vge = null;
		if (entity.getId() != null && entity.getId() > 0) {
			vge = vpGrnEntityDao.selectByPrimaryKey(entity.getId());
		} else {
			vge = vpGrnEntityDao.selectByVpGrnEntity(entity);
		}
		if (vge == null) {
			return null;
		}
		List<VpGrnDtlEntity> vpGrnDtlEntityList = vpGrnDtlEntityDao.selectVpGrnDtlByVpGrnId(vge.getId());
		vge.setDetails(vpGrnDtlEntityList);
		return vge;
	}

	private List<VpGrnDtlBean> converToVpGrnBeanList(
			List<VpGrnDtlEntity> vpGrnDtlEntityList) {
		List<VpGrnDtlBean> detailBeans = new ArrayList<VpGrnDtlBean>();
		if(org.apache.commons.collections.CollectionUtils.isNotEmpty(vpGrnDtlEntityList)){
			for(VpGrnDtlEntity vgde : vpGrnDtlEntityList){
				VpGrnDtlBean detailBean = convertToVpGrnDtlBean(vgde);
				detailBeans.add(detailBean);
			}
		}
		return detailBeans;
	}
	
	private VpGrnDtlBean convertToVpGrnDtlBean(VpGrnDtlEntity vgde) {
		VpGrnDtlBean detail = new VpGrnDtlBean();
		detail.setId(vgde.getId());
		detail.setVpGrnId(vgde.getVpGrnId());
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
			log.info("convertToVpGrnDtlBean 查询商品：" + vgde.getProdNum() + "失败！");
		}
		return detail;
	}

	private VpGrnBean convertToVpGrnBean(VpGrnEntity vge) {
		VpGrnBean bean = new VpGrnBean();
		bean.setId(vge.getId());
		bean.setDocCode(vge.getDocCode());
		bean.setDocDate(vge.getDocDate());
		bean.setUnitCode(vge.getUnitCode());
		bean.setWarehCode(vge.getWarehCode());
		bean.setTtlQty(vge.getTtlQty());
		bean.setRcptMode(vge.getRcptMode());
		bean.setRcptTime(vge.getRcptTime());
		bean.setRcptReason(vge.getRcptReason());
		bean.setDocState(vge.getDocState());
		bean.setRemark(vge.getRemark());
		bean.setVersion(vge.getVersion());
		return bean;
	}

	@Override
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean updateVpGrn(VpGrnEntity entity, List<VpGrnDtlEntity> details)
			throws MbvException, Exception {
		if (entity == null || details == null || details.size() < 0) {
			throw new MbvException("参数不能为空！");
		}
		// 参数验证
		String errMessage = this.validate(entity);
		if(errMessage != null && !"".equals(errMessage)){
			throw new MbvException(errMessage);
		}
		// 验证入库明细有效性
		errMessage = this.validateDetail(entity,details);
		if(errMessage != null && !"".equals(errMessage)){
			throw new MbvException(errMessage);
		}
		VpGrnEntity vge = vpGrnEntityDao.selectByDocCode(entity.getDocCode());
		if(vge == null || vge.getId() == null){
			throw new MbvException("数据错误！查不到相应的入库单！");
		}
		
		int ttlQty = 0;
		for (VpGrnDtlEntity vgde : details) {
			ttlQty += vgde.getQty();
		}
		vge.setTtlQty(ttlQty);
		vge.setRcptMode(entity.getRcptMode());
		vge.setRcptReason(entity.getRcptReason());
		vge.setDocState(entity.getDocState());
		vge.setRemark(entity.getRemark());
		vge.setLastModifiedDate(new Date());
		vge.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
		// 更新失败则抛出异常
		if (vpGrnEntityDao.updateByPrimaryKeySelective(vge) < 1) {
			throw new MbvException("更新入库单失败！版本号已改变！请重新尝试！");
		}
		log.info("更新入库单成功!");
		// 删除原来的明细信息，重新批量插入明细
		if (vpGrnDtlEntityDao.deleteByVpGrnId(vge.getId()) < 1) {
			throw new MbvException("删除入库单明细失败！");
		}
		log.info("删除入库单明细成功！");
		
		//插入明细前赋值vp_grn_id值
		details = setVpGrnDtlEntityId(vge,details);
		if (vpGrnDtlEntityDao.insertVpGrnDtlBatch(details) < 1) {
			throw new MbvException("插入入库单明细失败！");
		}
		log.info("插入入库单明细成功！");
		// 状态为已确认的情况下插入事务表并更新库存
		if (MbvConstant.DOC_STATE_CONFIRM.equals(entity.getDocState())) {
			insertOrUpdateInventory(entity, details);
			log.info("插入或更新库存表成功！");
			insertProdTran(entity, details);
			log.info("插入事务表成功！");
		}
		return true;
	}

	@Override
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean deleteVpGrnBatch(List<VpGrnEntity> vpGrnEntityList) {
		if (CollectionUtils.isEmpty(vpGrnEntityList)) {
			throw new MbvException("参数为空！");
		}
		for (VpGrnEntity vge : vpGrnEntityList) {
			if (vge.getId() == null || vge.getId() < 1) {
				throw new MbvException("入库单Id不存在，参数错误！");
			}
			if (vpGrnEntityDao.deleteByPrimaryKey(vge.getId()) < 0) {
				throw new MbvException("删除入库单失败！");
			}
			if (vpGrnDtlEntityDao.deleteByVpGrnId(vge.getId()) < 0) {
				throw new MbvException("删除入库单明细失败！");
			}
		}
		return true;
	}

	@Override
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean deleteVpGrnById(VpGrnEntity entity) throws MbvException,
			Exception {
		if (entity == null || entity.getId() < 1
				|| entity.getDocState() == null
				|| "".equals(entity.getDocState())) {
			log.info("参数错误！");
			throw new MbvException("参数错误！");
		}
		String docState = entity.getDocState();
		entity = vpGrnEntityDao.selectByPrimaryKey(entity.getId());
		if (entity == null) {
			throw new MbvException("查询当前出库单失败！");
		}
		if(MbvConstant.DOC_STATE_INPUTING.equals(docState)){
			entity.setDocState(MbvConstant.DOC_STATE_DELETE);
		}else if(MbvConstant.DOC_STATE_CONFIRM.equals(docState)){
			entity.setDocState(MbvConstant.DOC_STATE_UNDO);
		}else{
			throw new MbvException("未知的入库状态！");
		}
		entity.setLastModifiedDate(new Date());
		entity.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
		// 更新入库表
		int updateCount = vpGrnEntityDao.updateByPrimaryKey(entity);
		if (updateCount < 1) {
			log.info("更新成功！");
		}

		//根据grn_id获取入库明细
		List<VpGrnDtlEntity> details = new ArrayList<VpGrnDtlEntity>();
		details = vpGrnDtlEntityDao.selectVpGrnDtlByVpGrnId(entity.getId());
		if (CollectionUtils.isEmpty(details)) {
			throw new MbvException("数据错误！出库单明细不存在！");
		}
		//如果撤消前状态是已入库的话，需要记录事务表
		if (MbvConstant.DOC_STATE_CONFIRM.equals(docState)) {
			// 根据入库明细查询对应库存，并重新计算库存数
			for (VpGrnDtlEntity detail : details) {
				InventoryBean bean = new InventoryBean();
				bean.setWarehCode(entity.getWarehCode());
				bean.setProdNum(detail.getProdNum());
				List<InventoryEntity> invEntityList = inventoryService
						.queryByParams(bean,
								MbvConstant.QUERY_OFFSET_DEFAULT_VALUE,
								MbvConstant.QUERY_LIMITSET_DEFAULT_VALUE);
				if (CollectionUtils.isEmpty(invEntityList)) {
					throw new MbvException("查不到库存信息！");
				}
				InventoryEntity invEntity = invEntityList.get(0);
				//撤消入库单时判断如果当前实际可用库存小于撤消库存，则不能撤消
				int stkOnHand = invEntity.getStkOnHand().intValue() - invEntity.getQtyCommitted().intValue() - invEntity.getQtyOnLock().intValue() - detail.getQty();
				if(stkOnHand < 0){
					log.info("撤消入库失败！当前库存小于撤消入库库存！");
					throw new MbvException("撤消入库失败！当前库存小于撤消入库库存！");
				}
				invEntity.setStkOnHand(BigDecimal.valueOf(stkOnHand));
				invEntity.setLastModifiedDate(new Date());
				invEntity.setLastModifiedUser(MbvConstant.LAST_UPDATE_USER);
				int count = inventoryService.updateInventoryEntity(invEntity);
				if (count > 0) {
					log.info("单据" + entity.getDocCode() + "撤单成功");
				} else {
					log.info("单据" + entity.getDocCode() + "撤单失败");
				}
			}
			insertProdTran(entity, details);
			log.info("插入事务表成功");
		}
		return true;
	}

}
