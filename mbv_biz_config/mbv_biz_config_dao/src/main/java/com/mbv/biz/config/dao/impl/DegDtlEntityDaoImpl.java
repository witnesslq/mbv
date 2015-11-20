/*
 * Copyright 2015 Metersbonwe.com All right reserved. This software is the
 * confidential and proprietary information of Metersbonwe.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Metersbonwe.com.
 */
package com.mbv.biz.config.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.biz.config.bean.DegDtlBean;
import com.mbv.biz.config.dao.DegDtlEntityDao;
import com.mbv.biz.config.entity.DegDtlEntity;

/**
 * @类描述：库存dao
 * @2015年9月6日
 * @version
 */
@Repository("degDtlDao")
public class DegDtlEntityDaoImpl implements DegDtlEntityDao {
    /**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.biz.config.dao.impl.DegDtlEntityDao.";
    
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
    public List<DegDtlBean> selectDegDtlByParams(Long id) {
    	return this.localSqlSession.selectList(NAMESPACE+"selectDegDtlByParams",id);
    }
}
