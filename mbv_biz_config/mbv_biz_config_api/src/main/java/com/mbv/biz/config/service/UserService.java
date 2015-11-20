package com.mbv.biz.config.service;

import java.util.List;
import java.util.Map;

import com.mbv.biz.config.bean.UserBean;
import com.mbv.biz.config.entity.UserEntity;
import com.mbv.common.exception.MbvException;

public interface UserService {

	public UserEntity selectByUserCodeAndPassword(Map<String, Object> map);
	
	public UserEntity selectByUserCodeAndMobile(Map<String, Object> map);
	
	public UserEntity selectByUserCode(String userCode);
	
	public UserEntity selectByMobile(String mobile);
	
	public int updateByUserCode(UserEntity record);
	
	public List<UserEntity> queryByParams(UserBean bean, int offset, int limit) throws MbvException;

	public Integer queryByParamsCount(UserBean bean) throws MbvException;
	
	public boolean insertSelective(UserEntity record) throws MbvException;
	
	public UserEntity selectByPrimaryKey(Long id) throws MbvException;
	
	public boolean deleteDataDirectory(List<UserEntity> dataWnEntities) throws MbvException,Exception;
	
	public boolean updateByPrimaryKey(UserEntity record);
	
	public boolean updateLoginTimeByUserCode(UserEntity record);
}
