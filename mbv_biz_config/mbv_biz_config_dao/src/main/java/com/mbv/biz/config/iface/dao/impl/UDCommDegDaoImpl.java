package com.mbv.biz.config.iface.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.comm.bean.VpDegEntity;
import com.mbv.comm.bean.VpDegModifyEntity;
import com.mbv.biz.config.iface.dao.UDCommDegDao;

@Repository("uDCommDegDao")
public class UDCommDegDaoImpl implements UDCommDegDao {

	/**
	 * 定义本地namespace
	 */
	private static final String NAMESPACE = "com.mbv.biz.config.iface.dao.impl.UDCommDegDao.";

	@Resource
	private SqlSessionTemplate localSqlSession;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.localSqlSession
				.delete(NAMESPACE + "deleteByPrimaryKey", id);
	}

	@Override
	public int insert(VpDegEntity record) {
		return this.localSqlSession.insert(NAMESPACE + "insert", record);
	}

	@Override
	public int insertSelective(VpDegEntity record) {
		return this.localSqlSession.insert(NAMESPACE + "insertSelective",
				record);
	}

	@Override
	public VpDegEntity selectByPrimaryKey(Long id) {
		return this.localSqlSession.selectOne(NAMESPACE + "selectByPrimaryKey",
				id);
	}

	@Override
	public int updateByPrimaryKeySelective(VpDegEntity record) {
		return this.localSqlSession.update(NAMESPACE
				+ "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(VpDegEntity record) {
		return this.localSqlSession.update(NAMESPACE + "updateByPrimaryKey",
				record);
	}

	@Override
	public Integer selectByParamsCount(Map<String, Object> paramsMap) {
		return this.localSqlSession.selectOne(
				NAMESPACE + "selectByParamsCount", paramsMap);
	}

	@Override
	public VpDegEntity selectByDocCode(String docCode) {
		return this.localSqlSession.selectOne(NAMESPACE + "selectByDocCode",
				docCode);
	}

	@Override
	public int updateDegInfo(Map<String, Object> paramsMap) {
		return this.localSqlSession.update(NAMESPACE + "updateDegInfo",
				paramsMap);
	}

	@Override
	public int updateDegRcvInfoByOrderSn(VpDegModifyEntity modifyInfo) {
		// TODO Auto-generated method stub
		return this.localSqlSession.update(NAMESPACE
				+ "updateDegRcvInfoByOrderSn", modifyInfo);
	}

	@Override
	public List<VpDegEntity> selectByOrderSn(String orderSn) {
		// TODO Auto-generated method stub
		return this.localSqlSession.selectList(NAMESPACE + "selectByOrderSn",
				orderSn);
	}

	@Override
	public int updateDetTtlQtyById(VpDegEntity degInfo) {
		// TODO Auto-generated method stub
		return this.localSqlSession.update(NAMESPACE + "updateDetTtlQtyById",
				degInfo);
	}
}
