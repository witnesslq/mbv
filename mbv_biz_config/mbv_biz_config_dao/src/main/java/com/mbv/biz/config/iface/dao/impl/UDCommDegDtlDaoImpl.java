package com.mbv.biz.config.iface.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.comm.bean.VpDegDtlEntity;
import com.mbv.biz.config.iface.dao.UDCommDegDtlDao;

@Repository("uDCommDegDtlDao")
public class UDCommDegDtlDaoImpl implements UDCommDegDtlDao {

	/**
	 * 定义本地namespace
	 */
	private static final String NAMESPACE = "com.mbv.biz.config.iface.dao.impl.UDCommDegDtlDao.";

	@Resource
	private SqlSessionTemplate localSqlSession;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.localSqlSession
				.delete(NAMESPACE + "deleteByPrimaryKey", id);
	}

	@Override
	public int insert(VpDegDtlEntity record) {
		return this.localSqlSession.insert(NAMESPACE + "insert", record);
	}

	@Override
	public int insertSelective(VpDegDtlEntity record) {
		return this.localSqlSession.insert(NAMESPACE + "insertSelective",
				record);
	}

	@Override
	public VpDegDtlEntity selectByPrimaryKey(Long id) {
		return this.localSqlSession.selectOne(NAMESPACE + "selectByPrimaryKey",
				id);
	}

	@Override
	public int updateByPrimaryKeySelective(VpDegDtlEntity record) {
		return this.localSqlSession.update(NAMESPACE
				+ "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(VpDegDtlEntity record) {
		return this.localSqlSession.update(NAMESPACE + "updateByPrimaryKey",
				record);
	}

	@Override
	public List<VpDegDtlEntity> selectDegDtlByParams(Long id) {
		return this.localSqlSession.selectList(NAMESPACE
				+ "selectDegDtlByParams", id);
	}

	@Override
	public int updateQtyById(VpDegDtlEntity record) {
		// TODO Auto-generated method stub
		return this.localSqlSession.update(NAMESPACE + "updateQtyById", record);
	}
}
