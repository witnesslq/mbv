package com.mbv.biz.config.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mbv.biz.config.bean.ChartBean;
import com.mbv.biz.config.bean.DegBean;
import com.mbv.biz.config.bean.DegDtlBean;
import com.mbv.biz.config.bean.OrderBean;
import com.mbv.biz.config.bean.VenRnkBean;
import com.mbv.biz.config.dao.DegDtlEntityDao;
import com.mbv.biz.config.dao.DegEntityDao;
import com.mbv.biz.config.dao.DegModifyRecordsEntityDao;
import com.mbv.biz.config.dao.VenRnkEntityDao;
import com.mbv.biz.config.entity.DegEntity;
import com.mbv.biz.config.entity.DegModifyRecordsEntity;
import com.mbv.biz.config.entity.VenRnkEntity;
import com.mbv.biz.config.service.ReportService;
import com.mbv.common.constant.MbvConstant;
import com.mbv.common.exception.MbvException;
import com.mbv.common.utils.DateUtils;
import com.metersbonwe.oms.api.rsa.service.RSAService;
import com.metersbonwe.pca.common.ResultData;
import com.metersbonwe.pca.model.ProductInfo;
import com.metersbonwe.pca.model.ProductSkuInfo;
import com.metersbonwe.pca.service.UnitProductService;
import com.mtsbw.ump.cache.client.CacheClient;
/**
 * 报表 
 * 1，订单列表
 * @author henry
 *
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {
	
	private  Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);
	
	@Autowired
	private DegEntityDao degDao;
	
	@Autowired
	private DegDtlEntityDao degDtlDao;
	
	@Autowired
	private DegModifyRecordsEntityDao degModifyRecordsEntityDao;
	
	@Autowired
	UnitProductService unitProductService; 
	
	@Autowired
	RSAService rSAService;

	@Autowired
	private VenRnkEntityDao venRnkDao;
	
	@Resource
	private CacheClient cacheClient;
	

	public Integer queryByParamsCount(DegBean bean) throws MbvException {
        return degDao.selectByParamsCount(bean);
	}
	
	public List<OrderBean> queryOrderByParamsForDownload(DegBean bean) throws MbvException{
		List<OrderBean> orderBeanList = new ArrayList<>();
		List<DegBean> degEntityList = degDao.selectByParamsForDownload(bean);
		String skuCode = "";
		
		if(degEntityList != null && degEntityList.size() > 0){
			for(DegBean degBean : degEntityList){
				OrderBean orderBean = new OrderBean();
//				String mobile = degBean.getMoblie();
//				log.info("mobile: " + mobile);
//				mobile = rSAService.decode(mobile);
//				log.info("mobile: " + mobile);
//				if(StringUtils.isNotEmpty(mobile)){
//					degBean.setMoblie(mobile);
//				}
				orderBean.setDegBean(degBean);
				
				List<DegDtlBean> degDtlList = degDtlDao.selectDegDtlByParams(degBean.getId());
				if(degDtlList != null && degDtlList.size() > 0){
					for(DegDtlBean dtl : degDtlList){
						skuCode = dtl.getProdNum();
						
						ResultData<ProductInfo> result = unitProductService.getProductByProdCode("2", skuCode);
						
						if(result != null && result.getData() != null){
							ProductInfo info= result.getData();
							
							dtl.setProductCode(info.getProdClsNum());
							dtl.setProductName(info.getProdName());
							dtl.setBrandCode(info.getProductBrandInfo().getBrandCode());
							dtl.setBrandName(info.getProductBrandInfo().getBrandName());
							dtl.setProductUrl(info.getProductUrl());
							log.info("image url: "+ info.getProductUrl());
							
							List<ProductSkuInfo> skuInfoList = info.getProductSkuInfos();
							if(skuInfoList != null && skuInfoList.size() >0){
								ProductSkuInfo skuInfo = skuInfoList.get(0);
								if(skuInfo != null){
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
				orderBean.setDegDtlBeanList(degDtlList);
				orderBeanList.add(orderBean);
			}
		}
		
		log.info("skuCode: "+ skuCode);
		
		return orderBeanList;
	}
	
	public List<OrderBean> queryOrderByParamsDetail(DegBean bean) throws MbvException{
		List<OrderBean> orderBeanList = new ArrayList<>();
		List<DegBean> degEntityList = degDao.selectByParams(bean);
		String skuCode = "";
		
		if(degEntityList != null && degEntityList.size() > 0){
//			//手机号码密文变成明文
//			List<String> mobileList = new ArrayList<>();
//			for(DegBean degBean : degEntityList){
//				mobileList.add(degBean.getMoblie());
//			}
//			String mobileListStr = rSAService.decode_array(mobileList);
//			log.info("mobile:" + mobileListStr);
			
			for(DegBean degBean : degEntityList){
				OrderBean orderBean = new OrderBean();
				String mobile = degBean.getMoblie();
				log.info("mobile: " + mobile);
				mobile = rSAService.decode(mobile);
				log.info("mobile: " + mobile);
				if(StringUtils.isNotEmpty(mobile)){
					degBean.setMoblie(mobile);
				}
				orderBean.setDegBean(degBean);
				
				List<DegDtlBean> degDtlList = degDtlDao.selectDegDtlByParams(degBean.getId());
				if(degDtlList != null && degDtlList.size() > 0){
					for(DegDtlBean dtl : degDtlList){
						skuCode = dtl.getProdNum();
						
						ResultData<ProductInfo> result = unitProductService.getProductByProdCode("2", skuCode);
						
						if(result != null && result.getData() != null){
							ProductInfo info= result.getData();
							
							
							dtl.setProductCode(info.getProdClsNum());
							dtl.setProductName(info.getProdName());
							dtl.setBrandCode(info.getProductBrandInfo().getBrandCode());
							dtl.setBrandName(info.getProductBrandInfo().getBrandName());
							dtl.setProductUrl(info.getProductUrl());
							log.info("image url: "+ info.getProductUrl());
							
							List<ProductSkuInfo> skuInfoList = info.getProductSkuInfos();
							if(skuInfoList != null && skuInfoList.size() >0){
								ProductSkuInfo skuInfo = skuInfoList.get(0);
								if(skuInfo != null){
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
				orderBean.setDegDtlBeanList(degDtlList);
				orderBeanList.add(orderBean);
			}
		}
		
		log.info("skuCode: "+ skuCode);
		
		return orderBeanList;
	}
	
	public List<OrderBean> queryOrderByParams(DegBean bean) throws MbvException{
		// 开始时间
	    Long beginTime = System.currentTimeMillis();
		List<OrderBean> orderBeanList = new ArrayList<>();
		List<DegBean> degEntityList = degDao.selectByParams(bean);
		String skuCode = "";
		
		if(degEntityList != null && degEntityList.size() > 0){
			for(DegBean degBean : degEntityList){
				OrderBean orderBean = new OrderBean();
				orderBean.setDegBean(degBean);
				
				List<DegDtlBean> degDtlList = degDtlDao.selectDegDtlByParams(degBean.getId());
				if(degDtlList != null && degDtlList.size() > 0){
					for(DegDtlBean dtl : degDtlList){
						skuCode = dtl.getProdNum();
						
						ResultData<ProductInfo> result = unitProductService.getProductByProdCode("2", skuCode);
						
						if(result != null && result.getData() != null){
							ProductInfo info= result.getData();
							
							
							dtl.setProductCode(info.getProdClsNum());
							dtl.setProductName(info.getProdName());
							dtl.setBrandCode(info.getProductBrandInfo().getBrandCode());
							dtl.setBrandName(info.getProductBrandInfo().getBrandName());
							dtl.setProductUrl(info.getProductUrl());
							log.info("image url: "+ info.getProductUrl());
							
							List<ProductSkuInfo> skuInfoList = info.getProductSkuInfos();
							if(skuInfoList != null && skuInfoList.size() >0){
								ProductSkuInfo skuInfo = skuInfoList.get(0);
								if(skuInfo != null){
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
				orderBean.setDegDtlBeanList(degDtlList);
				orderBeanList.add(orderBean);
			}
		}
		
		log.info("skuCode: "+ skuCode);
		// 结束时间
		Long endTime = System.currentTimeMillis();
		log.info("queryOrderByParams 总共：" + (endTime - beginTime) / 1000 + "秒");// 计算时间		
		return orderBeanList;
	}
	
	public DegBean queryByParamsTotal(DegBean bean) throws MbvException{
		log.info("queryByParamsTotal.paramsMap: "+ bean);
		return degDao.selectByParamsTotal(bean);
	}
	
	public OrderBean queryOrderByCode(String docCode) throws MbvException{
		OrderBean orderBean = new OrderBean();
		DegBean degBean = degDao.selectByDocCode(docCode);
		if(degBean != null){
			orderBean.setDegBean(degBean);
			
			List<DegDtlBean> degDtlList = degDtlDao.selectDegDtlByParams(degBean.getId());
			orderBean.setDegDtlBeanList(degDtlList);
		}
		return orderBean;
	}
	
	public List<DegModifyRecordsEntity> queryDegModifyRecordsByCode(String vpDegCode) throws MbvException {
		log.info("queryDegModifyRecordsByCode->vpDegCode: "+ vpDegCode);
		List<DegModifyRecordsEntity> list = degModifyRecordsEntityDao.selectDegModifyRecordByCode(vpDegCode);
		return list;
	}
	
	
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean updateOrderInfo(DegBean bean) throws MbvException{
		degDao.updateDegInfo(bean);
		return true;
	}

	@Override
	public List<VenRnkEntity> queryVenRnkByParams(VenRnkBean bean, int offset, int limit) throws MbvException {
		List<VenRnkEntity> venEntityList = venRnkDao.selectVenkRnkByParams(bean,new RowBounds(offset, limit));
		for(VenRnkEntity entity:venEntityList){
			String prodNum = entity.getProdNum();
			ResultData<ProductInfo> result = unitProductService.getProductByProdCode("2", prodNum);
			
			
			if(result != null && result.getData() != null){
				ProductInfo info= result.getData();
				
				entity.setProdName(info.getProdName());
				entity.setProdUrl(info.getProductUrl());
				log.info("image url: "+ info.getProductUrl());
				
			}
			
			//level:"0", parent:"", isLeaf:false, expanded:false, loaded:true
			entity.setLevel(0);
			entity.getParent();
			entity.setIsLeaf(false);
			entity.setExpanded(false);
			entity.setLoaded(false);
		}
		
		return venEntityList;
	}

	@Override
	public Integer queryRnkByParamsCount(VenRnkBean bean) throws MbvException {
		Integer rankCount =  venRnkDao.selectRnkByParamsCount(bean);
		return rankCount;
	}

	@Override
	public List<ChartBean> queryProByProNumAndDay(VenRnkBean bean) throws MbvException {
		return venRnkDao.selectProByProNumAndDay(bean);
	}
	
	@Override
	public List<ChartBean> queryProByProNumAndDaySKU(VenRnkBean bean) throws MbvException {
		return venRnkDao.selectProByProNumAndDaySKU(bean);
	}

	@Override
	public List<ChartBean> queryProByProNumAndWeek(VenRnkBean bean) throws MbvException {
		return venRnkDao.selectProByProNumAndWeek(bean);
	}
	
	@Override
	public List<ChartBean> queryProByProNumAndWeekSKU(VenRnkBean bean) throws MbvException {
		return venRnkDao.selectProByProNumAndWeekSKU(bean);
	}
	

	@Override
	public List<ChartBean> queryProByProNumAndMonth(VenRnkBean bean) throws MbvException {
		return venRnkDao.selectProByProNumAndMonth(bean);
	}
	
	@Override
	public List<ChartBean> queryProByProNumAndMonthSKU(VenRnkBean bean) throws MbvException {
		return venRnkDao.selectProByProNumAndMonthSKU(bean);
	}
	
	
	

	//先获取明细再聚合
		public List<VenRnkEntity> queryVenRnkListBak(VenRnkBean bean) throws MbvException {
			
			List<VenRnkEntity> venEntityList = new ArrayList<VenRnkEntity>();
			
			List<VenRnkEntity> childList = new ArrayList<VenRnkEntity>();
			
			
			childList = venRnkDao.selectRnkChildByDay(bean);
			
			DateUtils.start();
		
			Map<String,Vector<VenRnkEntity>> allMap = new Hashtable<String,Vector<VenRnkEntity>>();

			for(Iterator<VenRnkEntity> childIterator = childList.iterator();childIterator.hasNext();){
				   
				VenRnkEntity child = childIterator.next();
				
				String prodnum = child.getProdNum().substring(0, 6);
				if(allMap.get(prodnum)!=null&&allMap.get(prodnum).size()!=0){
					allMap.get(prodnum).add(child);
				}else{
					Vector<VenRnkEntity> vector = new Vector<VenRnkEntity>();
					vector.add(child);
					allMap.put(prodnum, vector);
				}
				
			}
			log.info("耗时："+DateUtils.stop()+"s");
		
			Iterator it=allMap.keySet().iterator();  
			int i = 1;
			while(it.hasNext()){    
				double priceTotal = 0;
				double qtyTotal = 0;
				double stockTotal = 0;
				String prodnum = it.next().toString();
				Vector<VenRnkEntity> venVector = allMap.get(prodnum);
				int j = 1;
				for(Iterator<VenRnkEntity> venIterator = venVector.iterator();venIterator.hasNext();){
					VenRnkEntity child = venIterator.next();
					priceTotal +=child.getTtPrice();
					qtyTotal +=child.getTtQty();
					stockTotal +=child.getStkOnHand();
					
					//孩子节点设置
					child.setId(String.valueOf(i)+"_"+(j));
					child.setLevel(1);
					child.setParent(String.valueOf(i));
					child.setIsLeaf(true);
					child.setExpanded(false);
					child.setLoaded(false);
					
					//图片等其余属性设置
					//SKU查询
					String prodNo = child.getProdNum();
					ResultData<ProductInfo> resultChild = unitProductService.getProductByProdCode("2", prodNo);
					
					
					if(resultChild != null && resultChild.getData() != null){
						ProductInfo info= resultChild.getData();
						
						child.setProdName(info.getProdName());
						
						List<ProductSkuInfo> skuInfoList = info.getProductSkuInfos();
						if(skuInfoList != null && skuInfoList.size() >0){
							for(ProductSkuInfo skuInfo : skuInfoList){
								if(prodNo.equals(skuInfo.getProdNum())){
									log.info("color: "+ skuInfo.getColorName());
									log.info("size: "+ skuInfo.getSpecName());
//									child.setColorCode(skuInfo.getColorCode());
									child.setColorName(skuInfo.getColorName());
//									child.setSizeCode(skuInfo.getSpecCode());
									child.setSizeName(skuInfo.getSpecName());
								}
							}
						}
						
						
						child.setProdUrl(info.getProductUrl());
						log.info("12image url: "+ info.getProductUrl());
					}
					
					
					j++;
				}
				
				VenRnkEntity parentEntity = new VenRnkEntity();
				parentEntity.setProdNum(prodnum);
				parentEntity.setTtPrice(priceTotal);
				parentEntity.setTtQty(qtyTotal);
				parentEntity.setStkOnHand(stockTotal);
				
				//父亲节点设置
				parentEntity.setRowId(String.valueOf(i));
				parentEntity.setId(String.valueOf(i));
				
				//level:"0", parent:"", isLeaf:false, expanded:false, loaded:true
				parentEntity.setLevel(0);
				parentEntity.setParent("");
				parentEntity.setIsLeaf(false);
				parentEntity.setExpanded(false);
				parentEntity.setLoaded(true);
				
				//图片等其余属性设置
				//6位编码查询
				ResultData<ProductInfo> result = unitProductService.getProductByProdCode("1", prodnum);
				if(result != null && result.getData() != null){
					ProductInfo info= result.getData();
					parentEntity.setProdName(info.getProdName());
					parentEntity.setProdUrl(info.getProductUrl());
					log.info("6image url: "+ info.getProductUrl());
				}
				
				venEntityList.add(parentEntity);
				venEntityList.addAll(venVector);
				i++;
			}
			

			
			return venEntityList;
		}
		
		@Override
		public List<VenRnkEntity> queryVenRnkList(VenRnkBean bean) throws MbvException {
			log.info("ReportServiceImpl-->queryVenRnkList");
			List<VenRnkEntity> parentList = new ArrayList<VenRnkEntity>();
			
			String dateFlag = bean.getDateFlag();
			DateUtils.start();
				
			parentList = venRnkDao.selectRnkParentByDay(bean);
			log.info("查询父亲节点耗时："+DateUtils.stop()+"毫秒");
			List<VenRnkEntity> venEntityList = new ArrayList<VenRnkEntity>();
			
			if(parentList!=null&&parentList.size()==0){
				return venEntityList;
			}
			
			DateUtils.start();
			List<String> prodCodeList = new ArrayList<String>(); 
			DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			for(Iterator<VenRnkEntity> venIterator = parentList.iterator();venIterator.hasNext();){
				VenRnkEntity entity = venIterator.next();
				prodCodeList.add(entity.getProdNum());
				//format price
				
				if(entity.getTtPrice()==0){
					entity.setTtPriceFormat("0.00");
				}else{
					entity.setTtPriceFormat(decimalFormat.format(entity.getTtPrice()));
				}
			}
			log.info("批量设置父亲节点商品号耗时："+DateUtils.stop()+"毫秒");
			
			DateUtils.start();
			
			
			log.info("cacheClient.getValues(MbvConstant.CACHE_BUSINESS_CODE_RPT, MbvConstant.CACHE_TYPE_RPT...start......");
			List<ProductInfo> parentInfoList = new ArrayList<ProductInfo>();
			try {
				parentInfoList =cacheClient.getValues(MbvConstant.CACHE_BUSINESS_CODE_RPT, MbvConstant.CACHE_TYPE_RPT, prodCodeList,ProductInfo.class);
			} catch (Exception e) {
				log.info(e.getMessage());
				e.printStackTrace();
			}
			
			if(parentInfoList!=null&&parentInfoList.size()==0){
				ResultData<ArrayList<ProductInfo>> resultParent = unitProductService.getProductBatch(MbvConstant.PRODUCT_TYPE_PROD_CLS, prodCodeList);
			if(resultParent!=null&&resultParent.getData()!=null&&resultParent.getData().size()>0){
				parentInfoList = resultParent.getData();
			}
			}
			
			log.info("---------未使用缓存-------------");
			
			
			log.info("批量查询父亲节点耗时："+DateUtils.stop()+"毫秒");
			
			
			DateUtils.start();
			bean.setProdNumList(prodCodeList);
			List<VenRnkEntity> childAllList = venRnkDao.selectRnkChildByDay(bean);
			log.info("批量查询子节点耗时："+DateUtils.stop()+"毫秒");
			
			
			for(int i=0;i<parentList.size();i++){

				VenRnkEntity entity = parentList.get(i);
				
				
				//6位编码查询
				String prodNum = entity.getProdNum();
				DateUtils.start();	
				ProductInfo info= new ProductInfo();
				for(ProductInfo parentInfo:parentInfoList){
					if(prodNum.equals(parentInfo.getProdClsNum())){
						info = parentInfo;
						break;
					}
				}
					
					entity.setProdName(info.getProdName());
					entity.setProdUrl(info.getProductUrl());
					log.info("6image url: "+ info.getProductUrl());
				
				
				entity.setRowId(String.valueOf(i+1));
				entity.setId(String.valueOf(i+1));
				
				//level:"0", parent:"", isLeaf:false, expanded:false, loaded:true
				entity.setLevel(0);
				entity.setParent("");
				entity.setIsLeaf(false);
				entity.setExpanded(false);
				entity.setLoaded(true);
				
				log.info("单个父亲节点其余信息设置耗时："+DateUtils.stop()+"毫秒");
				
				
				venEntityList.add(entity);
				
				DateUtils.start();	
				List<VenRnkEntity> childList = new ArrayList<VenRnkEntity>();
				
				for(VenRnkEntity childVen :childAllList){
					if(prodNum.equals(childVen.getProdNum().substring(0, 6))){
						
						childList.add(childVen);
						//format
						if(childVen.getTtPrice()==0){
							childVen.setTtPriceFormat("0.00");
						}else{
							childVen.setTtPriceFormat(decimalFormat.format(childVen.getTtPrice()));
						}
					}
	
				}
				log.info("遍历获取相关子节点耗时："+DateUtils.stop()+"毫秒");
				
				
				int j = 0;
				for(VenRnkEntity child:childList){
					DateUtils.start();	
					//SKU查询
					String prodNo = child.getProdNum();
					log.info("查询单个子节点productInfo耗时："+DateUtils.stop()+"毫秒");
					DateUtils.start();
						
						child.setProdName(info.getProdName());
						
						List<ProductSkuInfo> skuInfoList = info.getProductSkuInfos();
						if(skuInfoList != null && skuInfoList.size() >0){
							for(ProductSkuInfo skuInfo : skuInfoList){
								if(prodNo.equals(skuInfo.getProdNum())){
									log.info("color: "+ skuInfo.getColorName());
									log.info("size: "+ skuInfo.getSpecName());
//									child.setColorCode(skuInfo.getColorCode());
									child.setColorName(skuInfo.getColorName());
//									child.setSizeCode(skuInfo.getSpecCode());
									child.setSizeName(skuInfo.getSpecName());
								}
							}
						}
						
						
//						child.setProdUrl(info.getProductUrl());
						log.info("12image url: "+ info.getProductUrl());
					
					
					child.setId(String.valueOf(i+1)+"_"+(j+1));
					child.setLevel(1);
					child.setParent(String.valueOf(i+1));
					child.setIsLeaf(true);
					child.setExpanded(false);
					child.setLoaded(false);
					j++;
					
					log.info("查询单个子节点其余信息耗时："+DateUtils.stop()+"毫秒");
				}
				
				venEntityList.addAll(childList);
				
			}
			

			
			return venEntityList;
		}
		


	@Override
	public Integer queryVenkRnkParentCount(VenRnkBean bean) throws MbvException {
		Integer rankCount =  venRnkDao.selectVenkRnkParentCount(bean);
		return rankCount;
	}
	
	public DegEntity selectByPrimaryKey(Long id) throws MbvException{
		return degDao.selectByPrimaryKey(id);
	}

}
