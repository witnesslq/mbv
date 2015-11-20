/*
 * Copyright 2015 Metersbonwe.com All right reserved. This software is the
 * confidential and proprietary information of Metersbonwe.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Metersbonwe.com.
 */
package com.mbv.inventory.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.inventory.bean.InventoryBean;
import com.mbv.inventory.dao.MbvInventoryEntityDao;
import com.mbv.inventory.entity.InventoryEntity;

/**
 * @类描述：库存dao
 * @2015年9月6日
 * @version
 */
@Repository("mbvInventoryEntityDao")
public class MbvInventoryEntityDaoImpl implements MbvInventoryEntityDao {
    /**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "mbv.com.mbv.inventory.dao.impl.MbvInventoryEntityDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.localSqlSession.delete(NAMESPACE+"deleteByPrimaryKey",id);
    }

    @Override
    public int insert(InventoryEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insert",record);
    }

    @Override
    public int insertSelective(InventoryEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insertSelective",record);
    }

    @Override
    public InventoryEntity selectByPrimaryKey(Long id) {
        return this.localSqlSession.selectOne(NAMESPACE+"selectByPrimaryKey",id);
    }

    @Override
    public int updateByPrimaryKeySelective(InventoryEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(InventoryEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKey",record);
    }

    /**
     * @功能描述：根据条件查询多货主库存记录信息
     * @2015年9月6日
     * @param
     * @version
     */
    public List<InventoryEntity> selectByParams(InventoryBean bean, RowBounds rb) {
        return this.localSqlSession.selectList(NAMESPACE+"selectByParams",bean,rb);
    }
    
    @Override
	public Integer selectByParamsCount(InventoryBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByParamsCount",bean);
	}

	@Override
	public String getLastModifiedDate(String date) {
		return this.localSqlSession.selectOne(NAMESPACE+"getLastModifiedDate",date);
	}

}
