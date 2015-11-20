package com.mbv.biz.config.service;

import java.util.List;
import com.mbv.biz.config.bean.DegBean;
import com.mbv.biz.config.bean.WnBean;
import com.mbv.biz.config.entity.WnEntity;
import com.mbv.common.exception.MbvException;

public interface WnService {

	public List<WnBean> queryByParams(WnBean bean,int offset,int limit) throws MbvException;
	
	public Integer queryByParamsCount(WnBean bean) throws MbvException;
	
	public WnEntity selectByDocCode(String docCode) throws MbvException;
	
	public boolean insertSelective(WnEntity record) throws MbvException;
	
	public WnEntity selectByPrimaryKey(Long id) throws MbvException;
	
	public boolean deleteDataDirectory(List<WnEntity> dataWnEntities) throws MbvException,Exception;
	
	public boolean updateByPrimaryKey(WnEntity record) throws MbvException,Exception;
	
	public boolean updateByPrimaryKeySelective(WnEntity record) throws MbvException,Exception;
	
	public DegBean selectDegByDocCode(String docCode) throws MbvException,Exception;
	
	public DegBean selectDegBySrcDocCode(String docCode) throws MbvException,Exception;
	
	public Integer selectDegByOrderSn(String orderSn) throws MbvException;
	
	public Integer selectDegByOrderSnAndUnitCode(DegBean bean) throws MbvException;
	
	public String selectDocCodeSeq(String type) throws MbvException,Exception;
	
	public void sendOrder(WnEntity wnEntity) throws MbvException,Exception;
	
	public Integer queryExitsWnCount(WnBean bean) throws MbvException,Exception;
	
	public DegBean selectDegBySku(DegBean bean) throws MbvException;
	
}
