package com.mbv.biz.config.iface.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.biz.config.entity.iface.VpWarehProdEntity;
import com.mbv.biz.config.iface.dao.UDCommWarehProdDao;

@Repository("uDCommWarehProdDao")
public class UDCommWarehProdDaoImpl implements UDCommWarehProdDao {

	/**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.biz.config.iface.dao.impl.UDCommWarehProdDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    
	@Override
	public int updateQtyCommitted(VpWarehProdEntity warehprod) {
		// TODO Auto-generated method stub
		return this.localSqlSession.update(NAMESPACE+"updateQtyCommitted",warehprod);
	}

}
