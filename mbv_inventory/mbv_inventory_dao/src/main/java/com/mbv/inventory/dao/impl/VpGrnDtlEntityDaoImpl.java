package com.mbv.inventory.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.inventory.dao.VpGrnDtlEntityDao;
import com.mbv.inventory.entity.VpGrnDtlEntity;

@Repository("vpGrnDtlEntityDao")
public class VpGrnDtlEntityDaoImpl implements VpGrnDtlEntityDao {

	public static final String NAMESPACE = "com.mbv.biz.config.dao.impl.VpGrnDtlEntityDao.";
	
    @Resource 
    private SqlSessionTemplate localSqlSession;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return localSqlSession.delete(NAMESPACE + "deleteByPrimaryKey", id);
	}

	@Override
	public int insert(VpGrnDtlEntity record) {
		return localSqlSession.insert(NAMESPACE + "insert", record);
	}

	@Override
	public int insertSelective(VpGrnDtlEntity record) {
		return localSqlSession.insert(NAMESPACE + "insertSelective", record);
	}

	@Override
	public VpGrnDtlEntity selectByPrimaryKey(Long id) {
		return localSqlSession.selectOne(NAMESPACE + "selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(VpGrnDtlEntity record) {
		return localSqlSession.update(NAMESPACE + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(VpGrnDtlEntity record) {
		return localSqlSession.update(NAMESPACE + "updateByPrimaryKey", record);
	}

	@Override
	public int insertVpGrnDtlBatch(List<VpGrnDtlEntity> list) {
		return localSqlSession.insert(NAMESPACE + "insertVpGrnDtlBatch", list);
	}

	@Override
	public List<VpGrnDtlEntity> selectVpGrnDtlByVpGrnId(Long id) {
		return localSqlSession.selectList(NAMESPACE + "selectVpGrnDtlByVpGrnId", id);
	}

	@Override
	public int deleteByVpGrnId(Long id) {
		return localSqlSession.delete(NAMESPACE + "deleteByVpGrnId", id);
	}

}
