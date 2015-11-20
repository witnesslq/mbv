package com.mbv.biz.config.iface.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.biz.config.iface.dao.UDCommWnDao;
import com.mbv.comm.bean.VpWnEntity;

@Repository("uDCommWnDao")
public class UDCommWnDaoImpl implements UDCommWnDao {

	/**
	 * 定义本地namespace
	 */
	private static final String NAMESPACE = "com.mbv.biz.config.iface.dao.impl.UDCommWnDao.";
	
	@Resource
	private SqlSessionTemplate localSqlSession;

	@Override
	public VpWnEntity selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return this.localSqlSession.selectOne(NAMESPACE + "selectByPrimaryKey",
				id);
	}
	
	@Override
	public int updateTradeResult(VpWnEntity wninfo) {
		// TODO Auto-generated method stub
		return this.localSqlSession.update(NAMESPACE + "updateTradeResult",
				wninfo);
	}
}
