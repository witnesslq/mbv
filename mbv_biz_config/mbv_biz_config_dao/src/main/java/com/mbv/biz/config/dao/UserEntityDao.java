package com.mbv.biz.config.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.mbv.biz.config.bean.UserBean;
import com.mbv.biz.config.entity.UserEntity;

public interface UserEntityDao {
	int deleteByPrimaryKey(Long id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);
    
    UserEntity selectByUserCodeAndPassword(Map<String, Object> map);
    
    UserEntity selectByUserCodeAndMobile(Map<String, Object> map);
    
    UserEntity selectByUserCode(String userCode);
    
    UserEntity selectByMobile(String mobile);
    
    int updateByUserCode(UserEntity record);
    
	List<UserEntity> selectByParams(UserBean bean,RowBounds rb);
	
    Integer selectByParamsCount(UserBean bean);
    
    public int updateLoginTimeByUserCode(UserEntity record);
    
}