package com.mbv.biz.config.dao;

import java.util.List;

import com.mbv.biz.config.entity.DegModifyRecordsEntity;

public interface DegModifyRecordsEntityDao {
    int deleteByPrimaryKey(Long id);

    int insert(DegModifyRecordsEntity record);

    int insertSelective(DegModifyRecordsEntity record);

    DegModifyRecordsEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DegModifyRecordsEntity record);

    int updateByPrimaryKey(DegModifyRecordsEntity record);
    
    List<DegModifyRecordsEntity> selectDegModifyRecordByCode(String vpDegCode);
}