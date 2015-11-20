/*
 * Copyright 2015 Metersbonwe.com All right reserved. This software is the
 * confidential and proprietary information of Metersbonwe.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Metersbonwe.com.
 */
package com.mbv.inventory.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.inventory.bean.DeliveryBean;
import com.mbv.inventory.dao.DeliveryEntityDao;
import com.mbv.inventory.entity.DeliveryEntity;

/**
 * @类描述：发货dao
 * @2015年9月6日
 * @version
 */
@Repository("deliveryEntityDao")
public class DeliveryEntityDaoImpl implements DeliveryEntityDao {
    /**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.inventory.dao.impl.DeliveryEntityDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.localSqlSession.delete(NAMESPACE+"deleteByPrimaryKey",id);
    }

    @Override
    public int insert(DeliveryEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insert",record);
    }

    @Override
    public int insertSelective(DeliveryEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insertSelective",record);
    }

    @Override
    public DeliveryEntity selectByPrimaryKey(Long id) {
        return this.localSqlSession.selectOne(NAMESPACE+"selectByPrimaryKey",id);
    }

    @Override
    public int updateByPrimaryKeySelective(DeliveryEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKeySelective",record);
    }
    
    @Override
    public int updateStatusByPrimaryKey(Map<String,Object> parmasMap) {
        return this.localSqlSession.update(NAMESPACE+"updateStatusByPrimaryKey",parmasMap);
    }

    @Override
    public int updateByPrimaryKey(DeliveryEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKey",record);
    }
    
    @Override
    public List<DeliveryBean> selectByParams(Map<String, Object> paramsMap) {
	    return this.localSqlSession.selectList(NAMESPACE+"selectByParams",paramsMap);
	}
    
    @Override
	public Integer selectByParamsCount(Map<String, Object> paramsMap) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByParamsCount",paramsMap);
	}
    
    @Override
    public DeliveryEntity selectByDocCode(String docCode) {
    	return this.localSqlSession.selectOne(NAMESPACE+"selectByDocCode",docCode);
    }
    
    @Override
	public int updateExpressCode(Map<String, Object> paramsMap) {
		return this.localSqlSession.update(NAMESPACE+"updateExpressCode",paramsMap);
	}
    
    @Override
	public int updateExpress(Map<String, Object> paramsMap) {
		return this.localSqlSession.update(NAMESPACE+"updateExpress",paramsMap);
	}
    
    @Override
	public int updatePickPrintCount(List<Long> degIds) {
		return this.localSqlSession.update(NAMESPACE+"updatePickPrintCount",degIds);
	}

	@Override
	public DeliveryBean selectDeliveryBeanById(Long id) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectDeliveryBeanById",id);
	}

}
