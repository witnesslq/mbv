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

import com.mbv.inventory.bean.WarehProdTranBean;
import com.mbv.inventory.dao.VpWarehProdTranEntityDao;
import com.mbv.inventory.entity.VpWarehProdTranEntity;

/**
 * @类描述：出入库事务dao
 * @2015年9月6日
 * @version
 */
@Repository("warehProdTranEntityDao")
public class VpWarehProdTranEntityDaoImpl implements VpWarehProdTranEntityDao {
    /**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "mbv.com.mbv.inventory.dao.VpWarehProdTranEntityDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.localSqlSession.delete(NAMESPACE+"deleteByPrimaryKey",id);
    }

    @Override
    public int insert(VpWarehProdTranEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insert",record);
    }

    @Override
    public int insertSelective(VpWarehProdTranEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insertSelective",record);
    }

    @Override
    public VpWarehProdTranEntity selectByPrimaryKey(Long id) {
        return this.localSqlSession.selectOne(NAMESPACE+"selectByPrimaryKey",id);
    }

    @Override
    public int updateByPrimaryKeySelective(VpWarehProdTranEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(VpWarehProdTranEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKey",record);
    }

	@Override
	public List<VpWarehProdTranEntity> selectWarehProdTranByParams(
			WarehProdTranBean bean, RowBounds rowBounds) {
		return this.localSqlSession.selectList(NAMESPACE + "selectWarehProdTranByParams",bean, rowBounds);
	}

	@Override
	public Integer selectWarehProdTranByParamsCount(WarehProdTranBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE + "selectWarehProdTranByParamsCount",bean);
	}

	@Override
	public int insertVpWarehProdTranBatch(
			List<VpWarehProdTranEntity> list) {
		return this.localSqlSession.insert(NAMESPACE + "insertVpWarehProdTranBatch",list);
	}
    
}
