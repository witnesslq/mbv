package com.mbv.inventory.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.inventory.bean.VpGdnBean;
import com.mbv.inventory.dao.VpGdnDtlEntityDao;
import com.mbv.inventory.entity.VpGdnDtlEntity;

@Repository("vpGdnDtlEntityDao")
public class VpGdnDtlEntityDaoImpl implements VpGdnDtlEntityDao {

	public static final String NAMESPACE = "com.mbv.inventory.dao.VpGdnDtlEntityDao.";
	
    @Resource 
    private SqlSessionTemplate localSqlSession;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return localSqlSession.delete(NAMESPACE + "deleteByPrimaryKey", id);
	}

	@Override
	public int insert(VpGdnDtlEntity record) {
		return localSqlSession.insert(NAMESPACE + "insert", record);
	}

	@Override
	public int insertSelective(VpGdnDtlEntity record) {
		return localSqlSession.insert(NAMESPACE + "insertSelective", record);
	}

	@Override
	public VpGdnDtlEntity selectByPrimaryKey(Long id) {
		return localSqlSession.selectOne(NAMESPACE + "selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(VpGdnDtlEntity record) {
		return localSqlSession.update(NAMESPACE + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(VpGdnDtlEntity record) {
		return localSqlSession.update(NAMESPACE + "updateByPrimaryKey", record);
	}

	@Override
	public int insertVpGdnDtlBatch(List<VpGdnDtlEntity> list) {
		return localSqlSession.insert(NAMESPACE + "insertVpGdnDtlBatch", list);
	}

	@Override
	public List<VpGdnDtlEntity> selectVpGdnDtlByVpGdnId(Long id) {
		return localSqlSession.selectList(NAMESPACE + "selectVpGdnDtlByVpGdnId", id);
	}

	@Override
	public int deleteByVpGdnId(Long id) {
		return localSqlSession.delete(NAMESPACE + "deleteByVpGdnId", id);
	}

	@Override
	public List<VpGdnDtlEntity> selectByVpGdnBeanAndProdNum(VpGdnBean bean) {
		return localSqlSession.selectList(NAMESPACE + "selectByVpGdnBeanAndProdNum", bean);
	}
}
