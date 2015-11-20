package com.mbv.inventory.dao;

import java.util.List;
import java.util.Map;

import com.mbv.inventory.bean.GoodBean;
import com.mbv.inventory.entity.DegDtlEntity;

public interface DegDtlEntityDao {
    int deleteByPrimaryKey(Long id);

    int insert(DegDtlEntity record);

    int insertSelective(DegDtlEntity record);

    DegDtlEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DegDtlEntity record);

    int updateByPrimaryKey(DegDtlEntity record);
    
    public List<GoodBean> selectDegDtlByParams(Long degId);
    
    public List<GoodBean> findBydegIdsMap(List<Long> degIds);

	List<DegDtlEntity> selectByParamMap(Map<String, Object> params);
}