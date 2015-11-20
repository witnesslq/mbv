package com.mbv.inventory.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.inventory.dao.VpSenderEntityDao;
import com.mbv.inventory.entity.VpSenderEntity;

/**
 * @类描述：发货地址dao实现类
 * @2015年9月28日
 * @version
 */
@Repository("vpSenderEntityDao")
public class VpSenderEntityDaoImpl implements VpSenderEntityDao {

	/**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.inventory.dao.impl.VpSenderEntityDao.";
	
    @Resource
	private SqlSessionTemplate localSqlSession;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.localSqlSession.delete(NAMESPACE+"deleteByPrimaryKey",id);
	}

	@Override
	public int insert(VpSenderEntity record) {
		return this.localSqlSession.insert(NAMESPACE+"insert",record);
	}

	@Override
	public int insertSelective(VpSenderEntity record) {
		return this.localSqlSession.insert(NAMESPACE+"insertSelective",record);
    }

	@Override
	public VpSenderEntity selectByPrimaryKey(Integer id) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByPrimaryKey",id);
    }

	@Override
	public int updateByPrimaryKeySelective(VpSenderEntity record) {
		return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKeySelective",record);
    }

	@Override
	public int updateByPrimaryKey(VpSenderEntity record) {
		return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKey",record);
    }
	
	@Override
	public VpSenderEntity selectByUnitCode(String  unitCode) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByUnitCode",unitCode);
    }

	@Override
	public VpSenderEntity selectByWarehCode(String warehCode) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByWarehCode",warehCode);
	}

	@Override
	public int getCountByWarehCode(String warehCode) {
		return this.localSqlSession.selectOne(NAMESPACE+"getCountByWarehCode",warehCode);
	}
	

}
