package com.mbv.inventory.dao;

import java.util.Map;

import com.mbv.inventory.entity.VpDegModifyRecordEntity;

public interface VpDegModifyRecordEntityDao {
    int deleteByPrimaryKey(Long id);

    int insert(VpDegModifyRecordEntity record);

    int insertSelective(VpDegModifyRecordEntity record);

    VpDegModifyRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VpDegModifyRecordEntity record);

    int updateByPrimaryKey(VpDegModifyRecordEntity record);
    
    public int updateModifyStatus(Map<String,Object> parametersMap);
    
    public Integer getModifyCount(Map<String,Object> parametersMap);
}