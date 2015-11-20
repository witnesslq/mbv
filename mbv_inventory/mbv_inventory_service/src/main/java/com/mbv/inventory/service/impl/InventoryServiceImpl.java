package com.mbv.inventory.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banggo.stockcenter.client.dataobject.SupplierWarehProdRO;
import com.banggo.stockcenter.client.dataobject.SupplierWarehProdVO;
import com.banggo.stockcenter.client.service.IStockOPService;
import com.mbv.biz.config.entity.SysParameterEntity;
import com.mbv.biz.config.service.SysParameterService;
import com.mbv.common.constant.MbvConstant;
import com.mbv.common.exception.MbvException;
import com.mbv.inventory.bean.InventoryBean;
import com.mbv.inventory.bean.ProductBrandInfo;
import com.mbv.inventory.bean.ProductInfo;
import com.mbv.inventory.bean.ProductSkuInfo;
import com.mbv.inventory.bean.WarehProdTranBean;
import com.mbv.inventory.dao.MbvInventoryEntityDao;
import com.mbv.inventory.dao.VpWarehProdTranEntityDao;
import com.mbv.inventory.entity.InventoryEntity;
import com.mbv.inventory.entity.VpWarehProdTranEntity;
import com.mbv.inventory.service.InventoryService;
import com.metersbonwe.pca.common.ResultData;
import com.metersbonwe.pca.service.UnitProductService;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
	
	private Logger log = LoggerFactory.getLogger(InventoryServiceImpl.class);

	@Resource
	private MbvInventoryEntityDao mbvInventoryEntityDao;

	@Resource
	private VpWarehProdTranEntityDao vpWarehProdTranEntityDao;

	@Resource
	private UnitProductService unitProductService;
	
	@Resource
	private SysParameterService sysParameterService;
	
	@Resource
	private IStockOPService stockService;

	public List<InventoryEntity> queryByParams(InventoryBean bean, int offset,
			int limit) throws MbvException {
		return mbvInventoryEntityDao.selectByParams(bean, new RowBounds(offset,
				limit));
	}
	
	public List<InventoryBean> queryInventoryBeanByParams(InventoryBean bean, int offset,
			int limit) throws MbvException {
		List<InventoryEntity> list = mbvInventoryEntityDao.selectByParams(bean, new RowBounds(offset,limit));
		List<InventoryBean> beanList = new ArrayList<InventoryBean>();
		if(CollectionUtils.isNotEmpty(list)){
			for(InventoryEntity entity : list){
				bean = new InventoryBean();
				bean = convertToInventoryBean(entity);
				beanList.add(bean);
			}
		}
		return beanList;
	}
	
	private InventoryBean convertToInventoryBean(InventoryEntity entity){
		if(entity == null){
			throw new MbvException("参数错误！");
		}
		InventoryBean bean = new InventoryBean();
		bean.setId(entity.getId());
		bean.setProdNum(entity.getProdNum());
		bean.setWarehCode(entity.getWarehCode());
		bean.setStkOnHand(entity.getStkOnHand().intValue());
		bean.setQtyCommitted(entity.getQtyCommitted().intValue());
		bean.setQtyOnLock(entity.getQtyOnLock().intValue());
		bean.setLastUpdateDate(entity.getLastModifiedDate());
		bean.setLastUpdateUser(entity.getLastModifiedUser());
		
		ResultData<com.metersbonwe.pca.model.ProductInfo> result = unitProductService
				.getProductByProdCode(MbvConstant.PRODUCT_SKU_TYPE, entity.getProdNum());
		if (result.getIsOk() != MbvConstant.RESPONSE_SUCCESS) {
			bean.setProdName(MbvConstant.QUERY_PRODUCT_NULL);
			return bean;
		}
		bean.setProdName(result.getData().getProdName());
		bean.setProdClsNum(result.getData().getProdClsNum());
		StringBuffer prodProperty = new StringBuffer("");
		if(result.getData().getProductSkuInfos().get(0).getColorName() != null && !"".equals(result.getData().getProductSkuInfos().get(0).getColorName())){
			prodProperty.append("颜色：").append(result.getData().getProductSkuInfos().get(0).getColorName());
		}
		if(result.getData().getProductSkuInfos().get(0).getSpecName() != null && !"".equals(result.getData().getProductSkuInfos().get(0).getSpecName())){
			prodProperty.append("，尺寸：").append(result.getData().getProductSkuInfos().get(0).getSpecName());
		}
		bean.setProdProperty(prodProperty.toString());
        return bean;
	}
	@Override
	public Integer queryByParamsCount(InventoryBean bean) throws MbvException {
		return mbvInventoryEntityDao.selectByParamsCount(bean);
	}

	@Override
	public List<WarehProdTranBean> queryWarehProdTranByParams(
			WarehProdTranBean bean, int offset, int limit) {
		List<VpWarehProdTranEntity> list = vpWarehProdTranEntityDao.selectWarehProdTranByParams(bean,new RowBounds(offset, limit));
		
		List<WarehProdTranBean> tranList = new ArrayList<WarehProdTranBean>();
		if(CollectionUtils.isNotEmpty(list)){
			tranList = getTranBeanListByTranEntityList(list);
		}
		return tranList;
	}

	private List<WarehProdTranBean> getTranBeanListByTranEntityList(List<VpWarehProdTranEntity> list) {
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		List<WarehProdTranBean> beanList = new ArrayList<WarehProdTranBean>();
		for(VpWarehProdTranEntity entity : list){
			ResultData<com.metersbonwe.pca.model.ProductInfo> result = unitProductService.getProductByProdCode(MbvConstant.PRODUCT_SKU_TYPE, entity.getProdNum());
			if (result.getIsOk() != MbvConstant.RESPONSE_SUCCESS) {
				continue;
			}
			WarehProdTranBean bean = convertToBean(entity);
			beanList.add(bean);
		}
		return beanList;
	}

	@Override
	public Integer queryWarehProdTranByParamsCount(WarehProdTranBean bean)
			throws MbvException {
		return vpWarehProdTranEntityDao.selectWarehProdTranByParamsCount(bean);
	}

	@Override
	@Transactional(value = "local", rollbackFor = Exception.class)
	public Integer insertInventoryEntity(InventoryEntity entity)
			throws MbvException, Exception {
		return mbvInventoryEntityDao.insertSelective(entity);
	}

	@Override
	@Transactional(value = "local", rollbackFor = Exception.class)
	public Integer updateInventoryEntity(InventoryEntity entity)
			throws MbvException, Exception {
		return mbvInventoryEntityDao.updateByPrimaryKeySelective(entity);
	}

	@Override
	public InventoryEntity selectByPrimaryKey(Long id) {
		return mbvInventoryEntityDao.selectByPrimaryKey(id);
	}

	@Override
	public ProductInfo getProductByProdCode(String prodType, String prodCode) throws MbvException {
		// 调用商品接口获取商品信息
		ProductInfo pi = new ProductInfo();
		ProductSkuInfo psi = new ProductSkuInfo();
		ResultData<com.metersbonwe.pca.model.ProductInfo> result = unitProductService
				.getProductByProdCode(MbvConstant.PRODUCT_SKU_TYPE, prodCode);
		if (result.getIsOk() != MbvConstant.RESPONSE_SUCCESS) {
			throw new MbvException("获取商品信息失败！请确认商品编码是否正确！");
		}
		psi.setProdNum(result.getData().getProductSkuInfos().get(0).getProdNum());
		pi.setProdClsNum(result.getData().getProdClsNum());
		pi.setProdName(result.getData().getProdName());
		psi.setColorName(result.getData().getProductSkuInfos().get(0).getColorName());
		psi.setSpecName(result.getData().getProductSkuInfos().get(0).getSpecName());
		List<ProductSkuInfo> psis = new ArrayList<ProductSkuInfo>();
		psis.add(psi);
		pi.setProductSkuInfos(psis) ;
		return pi;
	}

	@Override
	public boolean setProdStock() {
		long startTime = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastModifiedDate = sdf.format(new Date());
		SysParameterEntity entity = new SysParameterEntity();
		entity.setParmCode(MbvConstant.SYS_PARAM_LAST_UPDATE_TIME);
		List<SysParameterEntity> list = sysParameterService.queryByParams(entity, 
				MbvConstant.QUERY_OFFSET_DEFAULT_VALUE, MbvConstant.QUERY_LIMITSET_DEFAULT_VALUE);
		if(CollectionUtils.isEmpty(list)){
			throw new MbvException("查询系统参数失败！");
		}
		entity = list.get(0);
		if(entity.getParaVal() == null || "".equals(entity.getParaVal())){
			throw new MbvException("系统参数值不能为空！");
		}
		InventoryBean bean = new InventoryBean();
		try {
			if(entity.getParaVal().length() == 10){
				entity.setParaVal(entity.getParaVal() + " 00:00:00");
			}
			bean.setLastUpdateDate(sdf.parse(entity.getParaVal()));
			int totalCount = mbvInventoryEntityDao.selectByParamsCount(bean);
			log.info("totalCount:" + totalCount);
			if(totalCount > MbvConstant.Query_MAX_COUNT){
				int maxPage = totalCount % MbvConstant.PER_PAGE == 0 ? totalCount / MbvConstant.PER_PAGE : totalCount / MbvConstant.PER_PAGE + 1;
				for(int i = 1; i <= maxPage; i++){
					int offset = (i - 1) * MbvConstant.PER_PAGE;
					List<InventoryEntity> inventoryList = mbvInventoryEntityDao.selectByParams(bean, new RowBounds(offset, MbvConstant.PER_PAGE));
					if(CollectionUtils.isEmpty(inventoryList)){
						log.info("查询库存为空！");
						continue;
					}
					List<SupplierWarehProdVO> warehProdList = copyList(inventoryList);
					log.info("warehProdList size:" + warehProdList.size());
					SupplierWarehProdRO ro = stockService.setSupplierStock(warehProdList);
					if(MbvConstant.UNIT_STOCK_SUCCESS_CODE.equals(ro.getCode())){
						log.info("第" + i + "页更新成功！");
					}else{
						log.info("第" + i + "页更新失败！");
						throw new MbvException("更新失败");
					}
				}
				entity.setParaVal(lastModifiedDate);
				sysParameterService.updateByPrimaryKey(entity);
			}else{
				int offset = (MbvConstant.FIRST_PAGE - 1) * MbvConstant.PER_PAGE;
				List<InventoryEntity> inventoryList = mbvInventoryEntityDao.selectByParams(bean, new RowBounds(offset, MbvConstant.PER_PAGE));
				if(CollectionUtils.isEmpty(inventoryList)){
					log.info("查询库存为空！");
					return true;
				}
				log.info("需要更新的库存数为：" + inventoryList.size());
				List<SupplierWarehProdVO> warehProdList = copyList(inventoryList);
				SupplierWarehProdRO ro = stockService.setSupplierStock(warehProdList);
				if(MbvConstant.UNIT_STOCK_SUCCESS_CODE.equals(ro.getCode())){
					log.info("库存更新成功！");
					entity.setParaVal(lastModifiedDate);
					sysParameterService.updateByPrimaryKey(entity);
				}else{
					log.info("库存更新失败！");
					return false;
				}
			}
			long endTime = System.currentTimeMillis();
			log.info("共用时：" + (endTime - startTime) + "毫秒");
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return false;
		}
	}

	private List<SupplierWarehProdVO> copyList(List<InventoryEntity> inventoryList) {
		List<SupplierWarehProdVO> list = new ArrayList<SupplierWarehProdVO>();
		for(InventoryEntity entity : inventoryList){
			if(entity.getProdNum() == null || "".equals(entity.getProdNum()) || entity.getWarehCode() == null || "".equals(entity.getWarehCode())
					|| entity.getStkOnHand() == null || entity.getQtyCommitted() == null || entity.getQtyOnLock() == null){
				throw new MbvException("库存商品编码或仓库编码或数量不能为空！");
			}
			SupplierWarehProdVO vo = new SupplierWarehProdVO();
			vo.setSku(entity.getProdNum());
			vo.setWarehcode(entity.getWarehCode());
			int stock = entity.getStkOnHand().intValue() - entity.getQtyCommitted().intValue() - entity.getQtyOnLock().intValue();
			vo.setFreeStock(String.valueOf(stock));
			list.add(vo);
		}
		return list;
	}

	@Override
	public WarehProdTranBean getVpWarehProdTranByBean(WarehProdTranBean bean) {
		if(bean == null){
			throw new MbvException("参数错误！");
		}
		VpWarehProdTranEntity entity = null;
		if(bean.getId() > 0){
			entity = vpWarehProdTranEntityDao.selectByPrimaryKey(bean.getId());
		}
		if(entity == null){
			log.info("事务ID:" + bean.getId() + "查不到相应的记录");
			return null;
		}
		bean = new WarehProdTranBean();
		bean = convertToBean(entity);
		return bean;
	}
	
	private WarehProdTranBean convertToBean(VpWarehProdTranEntity entity){
		if(entity == null){
			throw new MbvException("参数错误！");
		}
		WarehProdTranBean bean = new WarehProdTranBean();
		bean.setId(entity.getId());
		bean.setProdNum(entity.getProdNum());
		bean.setWarehCode(entity.getWarehCode());
		if(entity.getTranDate() != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String tranDate = sdf.format(entity.getTranDate());
			bean.setTranDate1(tranDate);
		}
		bean.setDocType(entity.getDocType());
		bean.setDocNum(entity.getDocNum());
		bean.setTranQty(entity.getTranQty().intValue());
		int stkOnHand = entity.getStkOnHand().intValue();
		bean.setStkOnHand(stkOnHand);
		bean.setQtyCommitted(entity.getQtyCommitted().intValue());
		bean.setQtyOnLock(entity.getQtyOnLock().intValue());
		//设置总库存数为减去已锁定量及已分配量
		bean.setInventoryQty(stkOnHand);
		ResultData<com.metersbonwe.pca.model.ProductInfo> result = unitProductService
				.getProductByProdCode(MbvConstant.PRODUCT_SKU_TYPE, entity.getProdNum());
		if (result.getIsOk() != MbvConstant.RESPONSE_SUCCESS) {
			throw new MbvException("获取商品信息失败！");
		}
		bean.setProdName(result.getData().getProdName());
		bean.setProdClsNum(result.getData().getProdClsNum());
		StringBuffer prodProperty = new StringBuffer("");
		if(result.getData().getProductSkuInfos().get(0).getColorName() != null && !"".equals(result.getData().getProductSkuInfos().get(0).getColorName())){
			prodProperty.append("颜色：").append(result.getData().getProductSkuInfos().get(0).getColorName());
		}
		if(result.getData().getProductSkuInfos().get(0).getSpecName() != null && !"".equals(result.getData().getProductSkuInfos().get(0).getSpecName())){
			prodProperty.append("，尺寸：").append(result.getData().getProductSkuInfos().get(0).getSpecName());
		}
		bean.setProdProperty(prodProperty.toString());
        return bean;
    }
	
	@Override
	public ProductInfo getProdInfoByProdNum(String prodNum) {
		ResultData<com.metersbonwe.pca.model.ProductInfo> result = unitProductService.getProductByProdCode(MbvConstant.PRODUCT_SKU_TYPE, prodNum);
		if(result == null){
			return null;
		}
		com.mbv.inventory.bean.ProductInfo pi = new com.mbv.inventory.bean.ProductInfo();
		if(MbvConstant.RESPONSE_SUCCESS == result.getIsOk()){
			com.metersbonwe.pca.model.ProductInfo productInfo = result.getData();
			pi = convertToBean(productInfo);
			return pi;
		}
		return null;
	}

	private ProductInfo convertToBean(com.metersbonwe.pca.model.ProductInfo productInfo) {
		try{
			com.mbv.inventory.bean.ProductInfo pi = new com.mbv.inventory.bean.ProductInfo();
			pi.setProdClsNum(productInfo.getProdClsNum());
			pi.setProdName(productInfo.getProdName());
			pi.setProductUrl(productInfo.getProductUrl());
			if(productInfo.getProductBrandInfo() != null){
				ProductBrandInfo pbi = new ProductBrandInfo();
				pbi.setBrandCode(productInfo.getProductBrandInfo().getBrandCode());
				pbi.setBrandName(productInfo.getProductBrandInfo().getBrandName());
				pi.setProductBrandInfo(pbi);
			}
			if(org.apache.commons.collections.CollectionUtils.isNotEmpty(productInfo.getProductSkuInfos())){
				List<ProductSkuInfo> psis = new ArrayList<ProductSkuInfo>();
				for(com.metersbonwe.pca.model.ProductSkuInfo productSkuInfo : productInfo.getProductSkuInfos()){
					ProductSkuInfo psi = new ProductSkuInfo();
					psi.setColorCode(productSkuInfo.getColorCode());
					psi.setColorName(productSkuInfo.getColorName());
					psi.setSpecCode(productSkuInfo.getSpecCode());
					psi.setSpecName(productSkuInfo.getSpecName());
					psi.setProdNum(productSkuInfo.getProdNum());
					psis.add(psi);
				}
				pi.setProductSkuInfos(psis);
			}
			return pi;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean validateNum(String num) {
		if(num == null || "".equals(num)){
			return false;
		}
		Pattern pattern = Pattern.compile(MbvConstant.NUMBER_REX);
		Matcher isNum = pattern.matcher(num);
		if(!isNum.matches()){
			return false;
		}
		return true;
	}

}
