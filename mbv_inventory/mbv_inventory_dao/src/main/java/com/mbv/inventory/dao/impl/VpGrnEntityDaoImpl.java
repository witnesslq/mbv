/*
 * Copyright 2015 Metersbonwe.com All right reserved. This software is the
 * confidential and proprietary information of Metersbonwe.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Metersbonwe.com.
 */
package com.mbv.inventory.dao.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.inventory.bean.VpGrnBean;
import com.mbv.inventory.dao.VpGrnEntityDao;
import com.mbv.inventory.entity.VpGrnEntity;

/**
 * @类描述：入库单dao实现类
 * @2015年9月6日
 * @version
 */
@Repository("vpGrnEntityDao")
public class VpGrnEntityDaoImpl implements VpGrnEntityDao {

	/**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "mbv.com.mbv.inventory.dao.impl.VpGrnEntityDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    
	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.localSqlSession.delete(NAMESPACE + "deleteByPrimaryKey", id);
	}

	@Override
	public int insert(VpGrnEntity record) {
		return this.localSqlSession.insert(NAMESPACE + "insert", record);
	}

	@Override
	public int insertSelective(VpGrnEntity record) {
		return this.localSqlSession.insert(NAMESPACE + "insertSelective", record);
	}

	@Override
	public VpGrnEntity selectByPrimaryKey(Long id) {
		return this.localSqlSession.selectOne(NAMESPACE + "selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(VpGrnEntity record) {
		return this.localSqlSession.update(NAMESPACE + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(VpGrnEntity record) {
		return this.localSqlSession.update(NAMESPACE + "updateByPrimaryKey", record);
	}

	@Override
	public List<VpGrnEntity> selectByParams(VpGrnBean bean, RowBounds rb) {
		return this.localSqlSession.selectList(NAMESPACE + "selectByParams", bean, rb);
	}

	@Override
	public int selectByParamsCount(VpGrnBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE + "selectByParamsCount", bean);
	}

	@Override
	public VpGrnEntity selectByVpGrnEntity(VpGrnEntity vpGrnEntity) {
		return this.localSqlSession.selectOne(NAMESPACE + "selectByVpGrnEntity", vpGrnEntity);
	}

	@Override
	public String getMaxDocCode(String seqName) {
		return this.localSqlSession.selectOne(NAMESPACE + "getMaxDocCode", seqName);
	}

	@Override
	public VpGrnEntity selectByDocCode(String docCode) {
		return this.localSqlSession.selectOne(NAMESPACE + "selectByDocCode", docCode);
	}
	
	

}
