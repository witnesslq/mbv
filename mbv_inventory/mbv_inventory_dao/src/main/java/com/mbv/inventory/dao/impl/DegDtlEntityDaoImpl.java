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

import com.mbv.inventory.bean.GoodBean;
import com.mbv.inventory.dao.DegDtlEntityDao;
import com.mbv.inventory.entity.DegDtlEntity;

/**
 * @类描述：发货dao
 * @2015年9月6日
 * @version
 */
@Repository("degDtlEntityDao")
public class DegDtlEntityDaoImpl implements DegDtlEntityDao {
    /**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.inventory.dao.impl.DegDtlEntityDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.localSqlSession.delete(NAMESPACE+"deleteByPrimaryKey",id);
    }

    @Override
    public int insert(DegDtlEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insert",record);
    }

    @Override
    public int insertSelective(DegDtlEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insertSelective",record);
    }

    @Override
    public DegDtlEntity selectByPrimaryKey(Long id) {
        return this.localSqlSession.selectOne(NAMESPACE+"selectByPrimaryKey",id);
    }

    @Override
    public int updateByPrimaryKeySelective(DegDtlEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(DegDtlEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKey",record);
    }
    
    @Override
    public List<GoodBean> selectDegDtlByParams(Long degId) {
    	return this.localSqlSession.selectList(NAMESPACE+"selectDegDtlByParams",degId);
    }
    
    @Override
    public List<GoodBean> findBydegIdsMap(List<Long> degIds) {
    	return this.localSqlSession.selectList(NAMESPACE+"findBydegIdsMap",degIds);
    }

	@Override
	public List<DegDtlEntity> selectByParamMap(Map<String, Object> params) {
		return this.localSqlSession.selectList(NAMESPACE + "selectByParamMap", params);
	}
    
    

}
