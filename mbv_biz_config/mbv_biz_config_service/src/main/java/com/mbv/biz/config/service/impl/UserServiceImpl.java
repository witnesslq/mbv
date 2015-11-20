package com.mbv.biz.config.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mbv.biz.config.bean.UserBean;
import com.mbv.biz.config.dao.UserEntityDao;
import com.mbv.biz.config.entity.UserEntity;
import com.mbv.biz.config.service.UserService;
import com.mbv.common.exception.MbvException;
import com.mtsbw.ump.cache.client.CacheClient;

/**
 * 用户管理服务
 * @author henry
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserEntityDao userDao;
	
	@Resource
	private CacheClient cacheClient;

	public UserEntity selectByUserCodeAndPassword(Map<String, Object> map) {
		return userDao.selectByUserCodeAndPassword(map);
	}
	
	public UserEntity selectByUserCodeAndMobile(Map<String, Object> map) {
		return userDao.selectByUserCodeAndMobile(map);
	}
	
	public UserEntity selectByUserCode(String userCode) {
		return userDao.selectByUserCode(userCode);
	}
	
	public UserEntity selectByMobile(String mobile){
		return userDao.selectByMobile(mobile);
	}
	
	@Transactional(value = "local", rollbackFor = Exception.class)
	public int updateByUserCode(UserEntity record){
		return userDao.updateByUserCode(record);
	}
	
	/**
	 * 用戶的RDUC
	 */
	public List<UserEntity> queryByParams(UserBean bean, int offset,
            int limit) throws MbvException{
        return userDao.selectByParams(bean,new RowBounds(offset,limit));
    }

	public Integer queryByParamsCount(UserBean bean) throws MbvException {
        return userDao.selectByParamsCount(bean);
	}
	
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean insertSelective(UserEntity record) throws MbvException{
		userDao.insertSelective(record);
		return true;
	}
	
	public UserEntity selectByPrimaryKey(Long id) throws MbvException{
    	return userDao.selectByPrimaryKey(id);
    }
	
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean deleteDataDirectory(List<UserEntity> dataWnEntities) throws MbvException,Exception {
		for (UserEntity wnEntity : dataWnEntities) {
			// 判断主键或者code是否存在
			if (wnEntity.getId() == null) {
				throw new MbvException("主键不存在 无法删除");
			}
			// 删除数据字典
			long id = wnEntity.getId();
			if (userDao.deleteByPrimaryKey(id) < 0) {
				throw new MbvException("删除工单错误");
			}
			//删除在缓存中心的数据
			//cacheClient.delete(MbaCacheConstant.BUSINESSCODE, MbaCacheConstant.BUSINESSTYPE_DATA, Arrays.asList(key));
		}
		return true;
	}
	
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean updateByPrimaryKey(UserEntity record) {
		userDao.updateByPrimaryKeySelective(record);
        return true;
    }
	
	@Transactional(value = "local", rollbackFor = Exception.class)
	public boolean updateLoginTimeByUserCode(UserEntity record) {
		userDao.updateLoginTimeByUserCode(record);
        return true;
    }

}
