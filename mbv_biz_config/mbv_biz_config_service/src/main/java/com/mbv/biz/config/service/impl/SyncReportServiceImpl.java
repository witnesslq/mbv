package com.mbv.biz.config.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.mbv.biz.config.dao.VenRnkEntityDao;
import com.mbv.biz.config.service.SyncReportService;
import com.mbv.common.constant.MbvConstant;
import com.metersbonwe.pca.common.ResultData;
import com.metersbonwe.pca.model.ProductInfo;
import com.metersbonwe.pca.service.UnitProductService;
import com.mtsbw.ump.cache.client.CacheClient;


@Service("syncReportService")
public class SyncReportServiceImpl implements SyncReportService {
	
	private Logger log = LoggerFactory.getLogger(SyncReportServiceImpl.class);

	@Autowired
	UnitProductService unitProductService; 
	
	@Autowired
	private VenRnkEntityDao venRnkDao;
	
	@Resource
	private CacheClient cacheClient;
	
	/*
	 * 查询商品详细信息，有效时间为1天
	 * */
	public void syncProductDetailInfo() {
		
		log.info("SyncReportServiceImpl-->syncProductDetailInfo  开始运行。。。。。");
		List<String> prodCodeLists = new ArrayList<String>(); 
		
		int totalCount = venRnkDao.selectProNumClsListCount();
		log.info("product num cls totalCount:" + totalCount);
		if(totalCount > MbvConstant.Query_MAX_COUNT){
			int maxPage = totalCount % MbvConstant.PER_PAGE == 0 ? totalCount / MbvConstant.PER_PAGE : totalCount / MbvConstant.PER_PAGE + 1;
			for(int i = 1; i <= maxPage; i++){
				int offset = (i - 1) * MbvConstant.PER_PAGE;
				List<String> prodCodeList = venRnkDao.selectProNumClsList(new RowBounds(offset, MbvConstant.PER_PAGE));
				if(CollectionUtils.isEmpty(prodCodeList)){
					log.info("查询库存为空！");
					continue;
				}
				log.info("prodCodeClsList size:" + prodCodeList.size());
				
				log.info("第" + i + "页更新成功！");
				prodCodeLists.addAll(prodCodeList);
			}
		}else{
			int offset = (MbvConstant.FIRST_PAGE - 1) * MbvConstant.PER_PAGE;
			List<String> prodCodeList = venRnkDao.selectProNumClsList(new RowBounds(offset, MbvConstant.PER_PAGE));
			if(CollectionUtils.isEmpty(prodCodeList)){
				log.info("查询库存为空！");
			}
			log.info("需要更新的库存数为：" + prodCodeList.size());
			log.info("库存更新成功！");
			prodCodeLists.addAll(prodCodeList);
		}
		
		
//		try {
//			ArrayList<ProductInfo> parentInfoList = (ArrayList<ProductInfo>) cacheClient.getValues(MbvConstant.CACHE_BUSINESS_CODE_RPT, MbvConstant.CACHE_TYPE_RPT, prodCodeLists, ProductInfo.class);
//			log.info("商品详细信息量："+parentInfoList.size());
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
//		try {
//			cacheClient.deleteByType(MbvConstant.CACHE_BUSINESS_CODE_RPT, MbvConstant.CACHE_TYPE_RPT);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			log.info(e1.getMessage());
//		}
		
		
		
		ArrayList<ProductInfo> productInfos = new ArrayList<ProductInfo>();
		ResultData<ArrayList<ProductInfo>> resultParent = unitProductService.getProductBatch(MbvConstant.PRODUCT_TYPE_PROD_CLS, prodCodeLists);
		if(resultParent.getIsOk()==1){
			productInfos = resultParent.getData();
		}else{
			log.info("unitProductService.getProductBatch中获取缓存失败！");
		}
		
		if(productInfos!=null&&productInfos.size()>0){
			log.info("unitProductService.getProductBatch成功获取缓存中数据且不为空！");
			Map<String,String> map = new HashMap<String, String>();
			for(ProductInfo pi : productInfos){
				String key = "";
				String value = "";
				key = pi.getProdClsNum();
				value = JSON.toJSONString(pi);
				map.put(key, value);
			}
			try {
				cacheClient.setJsonValues(MbvConstant.CACHE_BUSINESS_CODE_RPT, MbvConstant.CACHE_TYPE_RPT, map);
			} catch (Exception e) {
				e.printStackTrace();
				
				log.info(e.getMessage());
			}
			
			
		}else{
			log.info("unitProductService.getProductBatch获取缓存中数据为空！");
		}
		
		
	}
	
	
}
