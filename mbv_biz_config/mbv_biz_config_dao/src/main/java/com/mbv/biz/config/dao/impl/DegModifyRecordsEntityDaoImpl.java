package com.mbv.biz.config.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mbv.biz.config.dao.DegModifyRecordsEntityDao;
import com.mbv.biz.config.entity.DegModifyRecordsEntity;

@Repository("degModifyRecordsEntityDao")
public class DegModifyRecordsEntityDaoImpl implements DegModifyRecordsEntityDao {
	
	private  Logger log = LoggerFactory.getLogger(DegModifyRecordsEntityDaoImpl.class);

	 /**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.biz.config.dao.impl.DegModifyRecordsEntityDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.localSqlSession.delete(NAMESPACE+"deleteByPrimaryKey",id);
    }

    @Override
    public int insert(DegModifyRecordsEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insert",record);
    }

    @Override
    public int insertSelective(DegModifyRecordsEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insertSelective",record);
    }

    @Override
    public DegModifyRecordsEntity selectByPrimaryKey(Long id) {
    	return this.localSqlSession.selectOne(NAMESPACE+"selectByPrimaryKey",id);
    }

    @Override
    public int updateByPrimaryKeySelective(DegModifyRecordsEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKeySelective",record);
    }

    @Override
    public int updateByPrimaryKey(DegModifyRecordsEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKey",record);
    }
    
    public List<DegModifyRecordsEntity> selectDegModifyRecordByCode(String vpDegCode) {
    	log.info("selectDegModifyRecordByCode->vpDegCode: "+ vpDegCode);
        return this.localSqlSession.selectList(NAMESPACE+"selectDegModifyRecordByCode",vpDegCode);
    }

}
