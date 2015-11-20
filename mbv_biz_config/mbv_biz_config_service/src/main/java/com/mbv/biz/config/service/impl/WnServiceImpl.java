package com.mbv.biz.config.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.mbv.biz.config.bean.DegBean;
import com.mbv.biz.config.bean.WnBean;
import com.mbv.biz.config.bean.WnJsonParamBean;
import com.mbv.biz.config.bean.WnOrderBean;
import com.mbv.biz.config.dao.DegEntityDao;
import com.mbv.biz.config.dao.WnEntityDao;
import com.mbv.biz.config.entity.WnEntity;
import com.mbv.biz.config.mq.service.MqWnService;
import com.mbv.biz.config.service.WnService;
import com.mbv.common.constant.MbvConstant;
import com.mbv.common.exception.MbvException;

/**
 * 工单服务
 * @author henry
 *
 */
@Service("wnService")
public class WnServiceImpl implements WnService {
	
	private  Logger log = LoggerFactory.getLogger(WnServiceImpl.class);
	
	@Autowired
	WnEntityDao wnEntityDao;
	
	@Autowired
	private DegEntityDao degDao;
	
	@Autowired 
	MqWnService mqWnService;
	
    public List<WnBean> queryByParams(WnBean bean, int offset,
            int limit) throws MbvException{
        return wnEntityDao.selectByParams(bean,new RowBounds(offset,limit));
    }

	public Integer queryByParamsCount(WnBean bean) throws MbvException {
        return wnEntityDao.selectByParamsCount(bean);
	}
	
	public WnEntity selectByDocCode(String docCode){
		return wnEntityDao.selectByDocCode(docCode);
	}
	
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean insertSelective(WnEntity record) throws MbvException{
		if(StringUtils.isEmpty(record.getContent())){
			throw new MbvException("工单内容不能为空！");
		}
		if(StringUtils.isEmpty(record.getDocCode()) || record.getDocCode().length() != 8){
			throw new MbvException("工单编号不合法！");
		}
		if(StringUtils.isNotEmpty(record.getContent()) && record.getContent().length() > 2000){
			throw new MbvException("工单内容过长！");
		}
		if(StringUtils.isNotEmpty(record.getRemark()) && record.getRemark().length() > 200){
			throw new MbvException("备注内容过长！");
		}
		wnEntityDao.insertSelective(record);
		return true;
	}
	
	public WnEntity selectByPrimaryKey(Long id) throws MbvException{
    	return wnEntityDao.selectByPrimaryKey(id);
    }
	
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean deleteDataDirectory(List<WnEntity> dataWnEntities) throws MbvException,Exception {
		for (WnEntity wnEntity : dataWnEntities) {
			// 判断主键或者code是否存在
			if (wnEntity.getId() == null) {
				throw new MbvException("主键不存在 无法删除");
			}
			// 删除数据字典
			if (wnEntityDao.deleteByPrimaryKey(wnEntity
					.getId()) < 0) {
				throw new MbvException("删除工单错误");
			}
			//删除在缓存中心的数据
			//cacheClient.delete(MbaCacheConstant.BUSINESSCODE, MbaCacheConstant.BUSINESSTYPE_DATA, Arrays.asList(key));
		}
		return true;
	}
	
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean updateByPrimaryKey(WnEntity record) {
        wnEntityDao.updateByPrimaryKeySelective(record);
        return true;
    }
    
	public void sendOrder(WnEntity wnEntity) throws MbvException,Exception{
		if(wnEntity == null || wnEntity.getId() == null){
			log.info("工单为空");
			throw new MbvException("工单数据不完整");
		}
		if(!MbvConstant.MBV_WN_DOC_STATE.equals(wnEntity.getDocState())){
			log.info("wn->docSate: " + wnEntity.getDocState());
			throw new MbvException("工单状态非处理中状态");
		}
		if(!MbvConstant.MBV_WN_DOC_TYPE.equals(wnEntity.getDocType())){
			log.info("wn->docSate: " + wnEntity.getDocType());
			throw new MbvException("工单属于非缺货类型");
		}
		if(MbvConstant.MBV_WN_DOC_STATE.equals(wnEntity.getDocState())){
			mqWnService.sendOrder(wnJsonStr(wnEntity));
		}
	}
	
	/**
	 * mq消息提供者
	 * @param entity
	 * @return
	 */
	public String wnJsonStr(WnEntity entity){
		WnOrderBean bean = new WnOrderBean();
		bean.setActionUser(entity.getLastModifiedUser());
		bean.setCode(MbvConstant.MBV_WN_MQ_TYPE);
		bean.setLogType(MbvConstant.MBV_WN_MQ_LOG_TYPE);
		bean.setNote(jsonToObject(entity.getContent()));
		bean.setOrderSn(entity.getOrderSn());
		bean.setOutId(String.valueOf(entity.getId()));
		String result = JSON.toJSONString(bean);
		log.info("wn->result:"+result);
		return result;
	}
	
	public String jsonToObject(String content){
		log.info("wn->jsonToObject:"+content);
		String result = "";
		List<WnJsonParamBean> list = (List<WnJsonParamBean>) JSON.parseArray(content, WnJsonParamBean.class);
		for(WnJsonParamBean  bean : list){
			String tmp = "缺货商品:"+bean.getProdNum()+";发货仓编码:"+bean.getWarehCode()+";缺货数量:"+bean.getQty(); 
			result = result + tmp + "\n";
		}
		return result;
	}
	
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean updateByPrimaryKeySelective(WnEntity record) throws MbvException,Exception{
		wnEntityDao.updateByPrimaryKeySelective(record);
        return true;
	}
	
	public DegBean selectDegByDocCode(String docCode) {
		return degDao.selectByDocCode(docCode);
	}
	
	public DegBean selectDegBySrcDocCode(String docCode){
		return degDao.selectBySrcDocCode(docCode);
	}
	
	public Integer selectDegByOrderSn(String orderSn) throws MbvException{
		return degDao.selectDegByOrderSn(orderSn);
	}
	
	public Integer selectDegByOrderSnAndUnitCode(DegBean bean) throws MbvException{
		return degDao.selectDegByOrderSnAndUnitCode(bean);
	}
	
	/**
	 * 工单编号 8位 从00000001开始
	 */
	public String selectDocCodeSeq(String type) throws MbvException,Exception{
		String result = "";
		int length = 8;
		String tempDocCode = wnEntityDao.selectMaxDocCode(type);
		if(StringUtils.isNotEmpty(tempDocCode)){
			result = getBeforeZero(length - tempDocCode.length()) + tempDocCode;
		}else{
			result = MbvConstant.WN_DOC_CODE;
		}
		return result;
	}
	
	private String getBeforeZero(int length){
		String result = "";
		for(int i=1;i<=length;i++){
			result = result + "0";
		}
		return result;
	}
	
	public Integer queryExitsWnCount(WnBean bean) throws MbvException {
        return wnEntityDao.selectExitsWnCount(bean);
	}
	
	public DegBean selectDegBySku(DegBean bean) throws MbvException{
		return degDao.selectDegBySku(bean);
	}

}
