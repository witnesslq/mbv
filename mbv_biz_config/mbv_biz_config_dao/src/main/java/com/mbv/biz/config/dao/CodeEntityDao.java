package com.mbv.biz.config.dao;


import com.mbv.biz.config.entity.CodeEntity;

public interface CodeEntityDao {
    
    int deleteByPrimaryKey(Long id);

    int insert(CodeEntity record);

    int insertSelective(CodeEntity record);

    CodeEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CodeEntity record);

    int updateByPrimaryKey(CodeEntity record);
    
    
}