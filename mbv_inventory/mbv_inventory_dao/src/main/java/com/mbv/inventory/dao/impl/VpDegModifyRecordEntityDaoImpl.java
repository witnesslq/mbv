package com.mbv.inventory.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.inventory.dao.VpDegModifyRecordEntityDao;
import com.mbv.inventory.entity.VpDegModifyRecordEntity;

/**
 * @类描述：发货单变更dao实现类
 * @2015年9月28日
 * @version
 */
@Repository("vpDegModifyRecordEntityDao")
public class VpDegModifyRecordEntityDaoImpl implements VpDegModifyRecordEntityDao {

	/**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.inventory.dao.impl.VpDegModifyRecordEntityDao.";
	
    @Resource
	private SqlSessionTemplate localSqlSession;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.localSqlSession.delete(NAMESPACE+"deleteByPrimaryKey",id);
	}

	@Override
	public int insert(VpDegModifyRecordEntity record) {
		return this.localSqlSession.insert(NAMESPACE+"insert",record);
	}

	@Override
	public int insertSelective(VpDegModifyRecordEntity record) {
		return this.localSqlSession.insert(NAMESPACE+"insertSelective",record);
    }

	@Override
	public VpDegModifyRecordEntity selectByPrimaryKey(Long id) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByPrimaryKey",id);
    }

	@Override
	public int updateByPrimaryKeySelective(VpDegModifyRecordEntity record) {
		return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKeySelective",record);
    }

	@Override
	public int updateByPrimaryKey(VpDegModifyRecordEntity record) {
		return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKey",record);
    }
	
	@Override
	public int updateModifyStatus(Map<String,Object> parametersMap) {
		return this.localSqlSession.update(NAMESPACE+"updateModifyStatus", parametersMap);
	}
	
	@Override
	public Integer getModifyCount(Map<String,Object> parametersMap) {
		return this.localSqlSession.selectOne(NAMESPACE+"getModifyCount", parametersMap);
	}


}
