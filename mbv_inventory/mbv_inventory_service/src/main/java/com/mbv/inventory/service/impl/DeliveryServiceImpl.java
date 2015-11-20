package com.mbv.inventory.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbv.biz.config.bean.WnBean;
import com.mbv.biz.config.entity.WnEntity;
import com.mbv.biz.config.service.WnService;
import com.mbv.common.constant.MbvConstant;
import com.mbv.common.exception.MbvException;
import com.mbv.inventory.bean.DeliveryBean;
import com.mbv.inventory.bean.GoodBean;
import com.mbv.inventory.bean.ResultBean;
import com.mbv.inventory.bean.VpDegBean;
import com.mbv.inventory.dao.DegDtlEntityDao;
import com.mbv.inventory.dao.DeliveryEntityDao;
import com.mbv.inventory.dao.VpDegModifyRecordEntityDao;
import com.mbv.inventory.dao.VpSenderEntityDao;
import com.mbv.inventory.entity.DegDtlEntity;
import com.mbv.inventory.entity.DeliveryEntity;
import com.mbv.inventory.entity.VpGdnDtlEntity;
import com.mbv.inventory.entity.VpGdnEntity;
import com.mbv.inventory.entity.VpSenderEntity;
import com.mbv.inventory.service.DeliveryService;
import com.mbv.inventory.service.VpGdnService;
import com.metersbonwe.oms.api.region.service.SystemRegionService;
import com.metersbonwe.oms.api.rsa.service.RSAService;
import com.metersbonwe.pca.common.ResultData;
import com.metersbonwe.pca.model.ProductInfo;
import com.metersbonwe.pca.model.ProductSkuInfo;
import com.metersbonwe.pca.service.UnitProductService;
import com.mtsbw.soa.udb.common.model.VpDegEntity;
import com.mtsbw.soa.udb.common.model.param.VpDegDtlInfo;
import com.mtsbw.soa.udb.common.model.param.VpDegInfo;
import com.mtsbw.soa.udb.common.model.param.VpDegRequestInfo;
import com.mtsbw.soa.udb.dubboservice.VpDegOutService;

@Service("deliveryService")
public class DeliveryServiceImpl implements DeliveryService {
	private  Logger log = LoggerFactory.getLogger(DeliveryServiceImpl.class);
	
	@Resource
	DeliveryEntityDao deliveryEntityDao;
	
	@Resource
	private DegDtlEntityDao degDtlEntityDao;
	
	@Resource
	private VpSenderEntityDao vpSenderEntityDao;
	
	@Resource
	private VpDegModifyRecordEntityDao vpDegModifyRecordEntityDao;
	
	@Autowired
	UnitProductService unitProductService;
	
	@Autowired
	VpDegOutService vpDegOutService;
	
	@Autowired
	VpGdnService vpGdnService;
	
	@Autowired
    WnService wnService;
	
	@Autowired 
	SystemRegionService systemRegionService;
	
	@Autowired
	RSAService rSAService;
	
	//查询发货单列表
    public List<VpDegBean> queryOrderByParams(Map<String, Object> paramsMap) throws MbvException{
    	List<VpDegBean> deliveryBeanList = new ArrayList<VpDegBean>();
		List<DeliveryBean> orderList = deliveryEntityDao.selectByParams(paramsMap);
		for(DeliveryBean order : orderList){
			VpDegBean deliveryBean = new VpDegBean();
			deliveryBean.setDeliveryBean(order);
			
			List<GoodBean> goodList = queryGoodByParams(order.getId());
			deliveryBean.setGoodBeanList(goodList);
			deliveryBeanList.add(deliveryBean);
		}
		
        return deliveryBeanList;
    }

    //查询发货单数量
    @Override
	public Integer queryByParamsCount(Map<String, Object> paramsMap) throws MbvException {
        return deliveryEntityDao.selectByParamsCount(paramsMap);
	}

    //发货
    @Override
	@Transactional(value = "local",rollbackFor = Exception.class)
    public ResultBean send(Long degId) throws MbvException,Exception {
    	log.info("进入发货service");
    	boolean flag = true;
    	ResultBean result = new ResultBean();
    	DeliveryEntity deliveryEntity = deliveryEntityDao.selectByPrimaryKey(degId);
    	List<GoodBean> goodList = degDtlEntityDao.selectDegDtlByParams(degId);
    	
    	//判断是否可出库
    	VpDegRequestInfo vpDegRequest = new VpDegRequestInfo();
    	VpDegInfo degInfo = new VpDegInfo();
    	degInfo.setDoc_code(deliveryEntity.getDocCode());
    	degInfo.setDoc_state(deliveryEntity.getDocState());
    	List<VpDegDtlInfo> degDtlInfo = new ArrayList<VpDegDtlInfo>();
    	for(GoodBean good:goodList) {
    		VpDegDtlInfo dtlInfo = new VpDegDtlInfo();
    		dtlInfo.setProd_num(good.getProdNum());
    		dtlInfo.setQty(good.getQty());
    		degDtlInfo.add(dtlInfo);
    	}
    	vpDegRequest.setDegInfo(degInfo);
    	vpDegRequest.setDegDtlInfo(degDtlInfo);
    	try{
    		com.mtsbw.soa.udb.common.model.ResultBean resultBean = vpDegOutService.checkCanOut(vpDegRequest);
    		if(resultBean ==null) {
    			log.info("判断不可出库原因===返回对象NULL");
    			throw new MbvException("不可出库");
    		}else{
    			flag = resultBean.isResult();
    			if(!flag) {
    				log.info("判断不可出库原因==="+resultBean.getMessage());
    				if(resultBean.getMessage() == null){
    					throw new MbvException("不可出库");
    				}else{
    					throw new MbvException(resultBean.getMessage());
    				}
    			}
    		}
    	}catch(MbvException e) {
    		throw new MbvException(e.getMessage());
    	}
    	
    	
    	//接口调用，更改库存
    	VpGdnEntity vpGdn = new VpGdnEntity();
    	vpGdn.setDocCode(vpGdnService.getVpGdnMaxDocCode(MbvConstant.SEQ_NAME_VPGDN));
    	vpGdn.setDocDate(deliveryEntity.getDocDate());
    	vpGdn.setUnitCode(deliveryEntity.getUnitCode());
    	vpGdn.setWarehCode(deliveryEntity.getWarehCode());
    	vpGdn.setTtlQty(deliveryEntity.getTtlQty());
    	vpGdn.setDispMode(MbvConstant.INVENTORY_ADD_TYPE_GDN);
    	vpGdn.setDispReason("1");
    	vpGdn.setDocState(MbvConstant.DOC_STATE_CONFIRM);
    	vpGdn.setSrcDocTye(MbvConstant.SRC_DOC_TYPE);
    	vpGdn.setSrcDocCode(deliveryEntity.getDocCode());
    	vpGdn.setCreateDate(deliveryEntity.getCreateDate());
    	vpGdn.setCreateUser(deliveryEntity.getCreateUser());
    	vpGdn.setLastModifiedDate(deliveryEntity.getLastModifiedDate());
    	vpGdn.setLastModifiedUser(deliveryEntity.getLastModifiedUser());
    	List<VpGdnDtlEntity> details = new ArrayList<VpGdnDtlEntity>();
    	for(GoodBean good:goodList) {
    		VpGdnDtlEntity vpGdnDtl = new VpGdnDtlEntity();
    		vpGdnDtl.setProdNum(good.getProdNum());
    		log.info("发货单传递商品数量："+good.getQty());
    		vpGdnDtl.setQty(good.getQty());
    		vpGdnDtl.setRemark(good.getRemark());
    		details.add(vpGdnDtl);
    	}
    	log.info("details商品数量size="+details.size());
    	try{
    		flag = vpGdnService.addVpGdn(vpGdn, details, "1");
    		if(!flag) {
    			log.info("更改库存失败");
    			throw new MbvException("更改库存失败");
    		}
    	}catch(MbvException e) {
    		throw new MbvException(e.getMessage());
    	}
		
    	
    	//出库结果回写
    	VpDegEntity degOutResult = new VpDegEntity();
    	degOutResult.setDegCode(deliveryEntity.getDocCode());
    	degOutResult.setExpressCode(deliveryEntity.getExpressCode());
    	degOutResult.setTspComCode(deliveryEntity.getTspComCode());
    	log.info("发货单编号："+deliveryEntity.getDocCode()+"  快递单编号："+deliveryEntity.getExpressCode()+"  承运商编码："+deliveryEntity.getTspComCode());
    	try{
    		com.mtsbw.soa.udb.common.model.ResultBean vpDegResponse = vpDegOutService.writeDegOutResult(degOutResult);
    		if(vpDegResponse == null){
    			log.info("出库回写失败原因vpDegResponse==="+vpDegResponse);
    			throw new MbvException("出库回写失败");
    		}else{
    			flag = vpDegResponse.isResult();
    			if(!flag) {
    				log.info("出库回写失败原因vpDegResponse-->Message==="+vpDegResponse.getMessage());
    				if(vpDegResponse.getMessage() == null) {
    					throw new MbvException("出库回写失败");
    				}else{
    					throw new MbvException(vpDegResponse.getMessage());
    				}
    			}
    		}
    	}catch(MbvException e) {
    		throw new MbvException(e.getMessage());
    	}
    	
    	//更改发货状态
    	Map<String,Object> paramsMap = new HashMap<String,Object>();
    	paramsMap.put("degId", degId);
    	paramsMap.put("docState", 2);
    	try{
    		if(deliveryEntityDao.updateStatusByPrimaryKey(paramsMap) < 1){
    			throw new MbvException("更新发货单状态失败");
    		}
    	}catch(MbvException e) {
    		throw new MbvException("更新发货单状态失败");
    	}
    	
    	result.setFlag(true);
    	return result;
    }
    
    //判断缺货工单是否存在
  	@Override
  	public boolean isExistWn(Map<String, Object> paramsMap) throws MbvException {
  		String docCode = (String)paramsMap.get("docCode");
  		String orderSn = (String)paramsMap.get("orderSn");
  		String unitCode = (String)paramsMap.get("unitCode");
  		WnBean wnBean = new WnBean();
  		wnBean.setSrcDocCode(docCode);
  		wnBean.setOrderSn(orderSn);
  		wnBean.setUnitCode(unitCode);
  		wnBean.setDocState("1");
		try {
			if(wnService.queryExitsWnCount(wnBean)<1) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
  		return true;
  	}
    
    //新增缺货工单
    @Override
	@Transactional(value = "local",rollbackFor = Exception.class)
    public boolean saveWn(Map<String, Object> paramsMap) throws MbvException,Exception{
    	Long degId = (Long)paramsMap.get("degId");
    	String content = (String)paramsMap.get("content");
    	String remark = (String)paramsMap.get("remark");
    	String type = (String)paramsMap.get("type");
    	String user = (String)paramsMap.get("user");
    	String unitCode = (String)paramsMap.get("unitCode");
    	DeliveryEntity deliveryEntity = deliveryEntityDao.selectByPrimaryKey(degId);
    	Date now = new Date();
    	WnEntity entity = new WnEntity();
    	entity.setDocDate(now);
    	entity.setDocType(MbvConstant.MBV_WN_DOC_TYPE);
    	String docCode = wnService.selectDocCodeSeq(MbvConstant.MBV_WN_SEQ_NAME);
    	entity.setDocCode(docCode);
    	entity.setDocState(type);
    	entity.setContent(content);
    	entity.setRemark(remark);
    	//entity.setSrcDocType(MbvConstant.MBV_WN_SRC_DOC_TYPE);//缺货类型
    	//entity.setSrcDocCode(deliveryEntity.getDocCode());
    	entity.setOrderSn(deliveryEntity.getOrderSn());
    	entity.setCreateDate(now);
    	entity.setCreateUser(user);
    	entity.setLastModifiedDate(now);
    	entity.setLastModifiedUser(user);
    	entity.setUnitCode(unitCode);
    	try {
    		boolean flag = wnService.insertSelective(entity);
    		
            log.info("DeliveryServiceImpl.sendOrder -> docType: " + entity.getDocType() + ", docState: " + entity.getDocState());
			
			//0缺货 1提交状态
			if(MbvConstant.MBV_WN_DOC_STATE.equals(entity.getDocState())){
				/**
				 * 调用消息队列
				 */
				WnEntity wnEntity = wnService.selectByDocCode(docCode);
				wnService.sendOrder(wnEntity);
			}	
    		if(!flag) {
    		   throw new MbvException("添加缺货工单失败！");
    		}
    	}catch(MbvException e){
    		throw new MbvException("添加缺货工单失败！");
    	}
    	return true;
    } 
    
    //获取订单下商品
    public List<GoodBean> queryGoodByParams(Long degId) throws MbvException{
    	List<GoodBean> goodList = degDtlEntityDao.selectDegDtlByParams(degId);
		if(goodList != null && goodList.size() > 0){
			for(GoodBean dtl : goodList){
				String skuCode = dtl.getProdNum();
				
				ResultData<ProductInfo> result = unitProductService.getProductByProdCode("2", skuCode);
				
				if(result != null && result.getData() != null){
					ProductInfo info= result.getData();
					
					dtl.setProductCode(info.getProdClsNum());
					dtl.setProductName(info.getProdName());
					dtl.setBrandCode(info.getProductBrandInfo().getBrandCode());
					dtl.setBrandName(info.getProductBrandInfo().getBrandName());
					dtl.setProductUrl(info.getProductUrl());
					dtl.setOnBrandPrc(info.getOnBrandPrc());
					
					List<ProductSkuInfo> skuInfoList = info.getProductSkuInfos();
					if(skuInfoList != null && skuInfoList.size() >0){
						for(ProductSkuInfo skuInfo : skuInfoList){
							if(skuCode.equals(skuInfo.getProdNum())){
								log.info("color: "+ skuInfo.getColorName());
								log.info("size: "+ skuInfo.getSpecName());
								dtl.setColorCode(skuInfo.getColorCode());
								dtl.setColorName(skuInfo.getColorName());
								dtl.setSizeCode(skuInfo.getSpecCode());
								dtl.setSizeName(skuInfo.getSpecName());
								dtl.setIntnlCode(skuInfo.getIntnlCode());
							}
						}
					}
				}
			}
		}
		
		return goodList;
    }
    
    //通过主键ID获取原始对象
    public DeliveryEntity getDeliveryById(Long id) throws MbvException {
    	return deliveryEntityDao.selectByPrimaryKey(id);
    }
    
    //通过主键ID获取封装Bean对象
    public DeliveryBean getDeliveryBeanById(Long id) throws MbvException {
    	DeliveryBean deliveryBean = deliveryEntityDao.selectDeliveryBeanById(id);
    	if(deliveryBean != null && deliveryBean.getMoblie()!=null){
    		String mobile = deliveryBean.getMoblie();
    		mobile = rSAService.decode(deliveryBean.getMoblie());
    		deliveryBean.setMoblie(mobile);
    	}
    	
    	return deliveryBean;
    }
    
    //获取多个订单下商品汇总
    public List<GoodBean> queryGoodListByIds(List<Long> degIds) throws MbvException{
    	List<GoodBean> goodList = degDtlEntityDao.findBydegIdsMap(degIds);
		if(goodList != null && goodList.size() > 0){
			for(GoodBean dtl : goodList){
				String skuCode = dtl.getProdNum();
				
				ResultData<ProductInfo> result = unitProductService.getProductByProdCode("2", skuCode);
				
				if(result != null && result.getData() != null){
					ProductInfo info= result.getData();
					
					dtl.setProductCode(info.getProdClsNum());
					dtl.setProductName(info.getProdName());
					dtl.setBrandCode(info.getProductBrandInfo().getBrandCode());
					dtl.setBrandName(info.getProductBrandInfo().getBrandName());
					dtl.setProductUrl(info.getProductUrl());
					dtl.setOnBrandPrc(info.getOnBrandPrc());
					
					List<ProductSkuInfo> skuInfoList = info.getProductSkuInfos();
					if(skuInfoList != null && skuInfoList.size() >0){
						for(ProductSkuInfo skuInfo : skuInfoList){
							if(skuCode.equals(skuInfo.getProdNum())){
								log.info("color: "+ skuInfo.getColorName());
								log.info("size: "+ skuInfo.getSpecName());
								dtl.setColorCode(skuInfo.getColorCode());
								dtl.setColorName(skuInfo.getColorName());
								dtl.setSizeCode(skuInfo.getSpecCode());
								dtl.setSizeName(skuInfo.getSpecName());
							}
						}
					}
				}
			}
		}
		
		return goodList;
    }
    
    //添加发货地址信息
    @Transactional(value = "local",rollbackFor = Exception.class)
  	public boolean saveSender(VpSenderEntity record) throws MbvException {
    	try{
    		vpSenderEntityDao.insertSelective(record);
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new MbvException("新增发货地址失败！");
    	}
  		return true;
  	}
    
    //修改发货地址信息
    @Transactional(value = "local",rollbackFor = Exception.class)
  	public boolean updateSender(VpSenderEntity record) throws MbvException {
    	try{
    		vpSenderEntityDao.updateByPrimaryKeySelective(record);
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new MbvException("更新发货地址失败！");
    	}
  		return true;
  	}

    //获取发货地址信息
	@Override
	public VpSenderEntity getSendAddressById(int id) throws MbvException {
  		return vpSenderEntityDao.selectByPrimaryKey(id);
	}
	
	//通过unitcode获取发货地址信息
	@Override
	public VpSenderEntity getSendAddressByUnitCode(String unitCode) throws MbvException {
  		return vpSenderEntityDao.selectByUnitCode(unitCode);
	}
	
	//通过warehCode获取发货地址信息
	@Override
	public VpSenderEntity getSendAddressByWarehCode(String warehCode) throws MbvException {
  		return vpSenderEntityDao.selectByWarehCode(warehCode);
	}
	
	//通过warehCode获取发货地址信息
	@Override
	public boolean isExistSendAddress(String warehCode) throws MbvException {
  		if(vpSenderEntityDao.getCountByWarehCode(warehCode)<1) {
  			return false;
  		}
		return true;
	}
	
	//更新收货信息变更状态
	@Override
	@Transactional(value = "local",rollbackFor = Exception.class)
	public boolean updateDelAddressStatus(String docCode) throws MbvException {
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("vpDegCode", docCode);
		paramsMap.put("status", 1);
		paramsMap.put("modifyCode", "变更收货信息");
		
		try{
			vpDegModifyRecordEntityDao.updateModifyStatus(paramsMap);
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new MbvException("更新收货信息失败！");
    	}
  		return true;
	}
	
	//更新收货信息变更状态
	@Override
	@Transactional(value = "local",rollbackFor = Exception.class)
	public boolean updateDelGoodStatus(String docCode) throws MbvException {
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("vpDegCode", docCode);
		paramsMap.put("status", 1);
		paramsMap.put("modifyCode", "商品变更");
		
		try{
			vpDegModifyRecordEntityDao.updateModifyStatus(paramsMap);
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new MbvException("更新商品信息失败！");
    	}
  		return true;
	}
	
	//判断是否有收货信息变更未处理的数据
	@Override
	public boolean isChangeReceiveAddress(String docCode) throws MbvException {
		boolean flag = true;
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("vpDegCode", docCode);
		paramsMap.put("status", 0);
		paramsMap.put("modifyCode", "变更收货信息");
		
		if(vpDegModifyRecordEntityDao.getModifyCount(paramsMap) < 1){
			flag = false;
		}
  		return flag;
	}
	
	//判断是否有商品信息变更未处理的数据
	@Override
	public boolean isChangeGoodInfo(String docCode) throws MbvException {
		boolean flag = true;
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("vpDegCode", docCode);
		paramsMap.put("status", 0);
		paramsMap.put("modifyCode", "商品变更");
		
		if(vpDegModifyRecordEntityDao.getModifyCount(paramsMap) < 1){
			flag = false;
		}
  		return flag;
	}
	
	//更新快递单编号
	@Override
	@Transactional(value = "local",rollbackFor = Exception.class)
	public boolean updateExpressCode(Map<String, Object> paramsMap) throws MbvException {
		try{
			deliveryEntityDao.updateExpressCode(paramsMap);
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new MbvException("更新快递单号失败！");
    	}
  		return true;
	}
	
	//更新快递公司
	@Override
	@Transactional(value = "local",rollbackFor = Exception.class)
	public boolean updateExpress(Map<String, Object> paramsMap) throws MbvException {
		try{
			deliveryEntityDao.updateExpress(paramsMap);
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new MbvException("更新快递公司失败！");
    	}
  		return true;
	}
	
	//更新拣货单打印次数
	@Override
	@Transactional(value = "local",rollbackFor = Exception.class)
	public boolean updatePickPrintCount(List<Long> degIds) throws MbvException {
		try{
			deliveryEntityDao.updatePickPrintCount(degIds);
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new MbvException("更新拣货单打印次数失败！");
    	}
  		return true;
	}

	/* (non-Javadoc)
	 * @see com.mbv.inventory.service.DeliveryService#getSystemRegionArea(java.lang.String, java.lang.String)
	 * region_type 地区级别
	 * region_id   地区id
	 * region_type 和 region_id 为互斥只可填写一个
	 * 传region_type表示获取各等级的全部地区消息，“0”为国家 “1”为省 “2”市 “3”为地区 “4”为街道
	 * 传 region_id表示获取所传地区id下的所有子集地区消息
	 */
	public String getSystemRegionArea(String region_type, String region_id) throws MbvException {
		String systemRegionArea = systemRegionService.getSystemRegionArea(region_type, region_id);
		return systemRegionArea;
		
	}
	
	@Override
	public List<DegDtlEntity> getVpDegDtlEntityListByParamMap(Map<String,Object> params) throws MbvException{
		if(params == null || params.size() <= 0){
			throw new MbvException("获取发货明细失败！参数为空");
		}
		return degDtlEntityDao.selectByParamMap(params);
	}
}
