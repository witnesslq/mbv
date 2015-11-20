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

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.biz.config.bean.WnBean;
import com.mbv.biz.config.dao.WnEntityDao;
import com.mbv.biz.config.entity.WnEntity;

/**
 * @类描述：库存dao
 * @2015年9月6日
 * @version
 */
@Repository("wnEntityDao")
public class WnEntityDaoImpl implements WnEntityDao {
    /**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.biz.config.dao.impl.WnEntityDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.localSqlSession.delete(NAMESPACE+"deleteByPrimaryKey",id);
    }

    @Override
    public int insert(WnEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insert",record);
    }

    @Override
    public int insertSelective(WnEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insertSelective",record);
    }

    @Override
    public WnEntity selectByPrimaryKey(Long id) {
    	return this.localSqlSession.selectOne(NAMESPACE+"selectByPrimaryKey",id);
    }

    @Override
    public int updateByPrimaryKeySelective(WnEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(WnEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKey",record);
    }
    
    public List<WnBean> selectByParams(WnBean bean, RowBounds rb) {
        return this.localSqlSession.selectList(NAMESPACE+"selectByParams",bean, rb);
    }
    
	public Integer selectByParamsCount(WnBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByParamsCount",bean);
	}
	
	public WnEntity selectByDocCode(String docCode){
		return this.localSqlSession.selectOne(NAMESPACE+"selectByDocCode",docCode);
	}
	
	public String selectMaxDocCode(String type){
		return this.localSqlSession.selectOne(NAMESPACE + "selectMaxDocCode", type);
	}
	
	public Integer selectExitsWnCount(WnBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectExitsWnCount",bean);
	}
}
