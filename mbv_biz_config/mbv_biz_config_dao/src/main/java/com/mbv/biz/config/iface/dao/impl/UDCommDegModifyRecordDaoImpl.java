package com.mbv.biz.config.iface.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.biz.config.entity.iface.VpDegModifyRecordEntity;
import com.mbv.biz.config.iface.dao.UDCommDegModifyRecordDao;

@Repository("uDCommDegModifyRecordDao")
public class UDCommDegModifyRecordDaoImpl implements UDCommDegModifyRecordDao {

	/**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.biz.config.iface.dao.impl.UDCommDegModifyRecordDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    

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

}
