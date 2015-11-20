/*
 * Copyright 2015 Metersbonwe.com All right reserved. This software is the
 * confidential and proprietary information of Metersbonwe.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Metersbonwe.com.
 */
package com.mbv.biz.config.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.biz.config.bean.UserBean;
import com.mbv.biz.config.dao.UserEntityDao;
import com.mbv.biz.config.entity.UserEntity;

/**
 * @类描述：库存dao
 * @2015年9月6日
 * @version
 */
@Repository("userDao")
public class UserEntityDaoImpl implements UserEntityDao {
    /**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.biz.config.dao.impl.UserEntityDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.localSqlSession.delete(NAMESPACE+"deleteByPrimaryKey",id);
    }

    @Override
    public int insert(UserEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insert",record);
    }

    @Override
    public int insertSelective(UserEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insertSelective",record);
    }

    @Override
    public UserEntity selectByPrimaryKey(Long id) {
    	return this.localSqlSession.selectOne(NAMESPACE+"selectByPrimaryKey",id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(UserEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKey",record);
    }

	@Override
	public UserEntity selectByUserCodeAndPassword(Map<String, Object> map) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByUserCodeAndPassword",map);
	}

	@Override
	public UserEntity selectByUserCodeAndMobile(Map<String, Object> map) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByUserCodeAndMobile",map);
	}

	@Override
	public UserEntity selectByUserCode(String userCode) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByUserCode",userCode);
	}

	@Override
	public int updateByUserCode(UserEntity record) {
		return this.localSqlSession.update(NAMESPACE+"updateByUserCode",record);
	}
	
	public UserEntity selectByMobile(String mobile){
		return this.localSqlSession.selectOne(NAMESPACE+"selectByMobile",mobile);
	}
	
	public List<UserEntity> selectByParams(UserBean bean, RowBounds rb) {
        return this.localSqlSession.selectList(NAMESPACE+"selectByParams",bean,rb);
    }
    
	public Integer selectByParamsCount(UserBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByParamsCount",bean);
	}
	
	public int updateLoginTimeByUserCode(UserEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateLoginTimeByUserCode",record);
    }
}
