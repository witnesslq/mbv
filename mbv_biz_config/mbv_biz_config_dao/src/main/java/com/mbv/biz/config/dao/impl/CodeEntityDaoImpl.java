/*
 * Copyright 2015 Metersbonwe.com All right reserved. This software is the
 * confidential and proprietary information of Metersbonwe.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Metersbonwe.com.
 */
package com.mbv.biz.config.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.biz.config.dao.CodeEntityDao;
import com.mbv.biz.config.entity.CodeEntity;

/**
 * @类描述：库存dao
 * @2015年9月6日
 * @version
 */
@Repository("codeDao")
public class CodeEntityDaoImpl implements CodeEntityDao {
    /**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.biz.config.dao.impl.CodeEntityDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.localSqlSession.delete(NAMESPACE+"deleteByPrimaryKey",id);
    }

    @Override
    public int insert(CodeEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insert",record);
    }

    @Override
    public int insertSelective(CodeEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insertSelective",record);
    }

    @Override
    public CodeEntity selectByPrimaryKey(Long id) {
    	return this.localSqlSession.selectOne(NAMESPACE+"selectByPrimaryKey",id);
    }

    @Override
    public int updateByPrimaryKeySelective(CodeEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(CodeEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKey",record);
    }
}
