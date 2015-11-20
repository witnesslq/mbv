package com.mbv.biz.config.dao;

import com.mbv.biz.config.entity.CodeDtlEntity;

public interface CodeDtlEntityDao {
    
    int deleteByPrimaryKey(Long id);

    int insert(CodeDtlEntity record);

    int insertSelective(CodeDtlEntity record);

    CodeDtlEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CodeDtlEntity record);

    int updateByPrimaryKey(CodeDtlEntity record);
}