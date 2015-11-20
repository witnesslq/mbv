package com.mbv.biz.config.iface.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.mbv.biz.config.entity.iface.NewErpOrgEntity;
import com.mbv.biz.config.iface.dao.NewErpOrgDao;
import com.mtsbw.dal.template.impl.MyBatisTemplate;

@Repository("newErpOrgDao")
public class NewErpOrgDaoImpl implements NewErpOrgDao {

	/**
	 * 定义本地namespace
	 */
	private static final String NAMESPACE = "newerp.com.mbv.biz.config.iface.dao.impl.NewErpOrgDao.";

	@Resource
	private MyBatisTemplate sqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<NewErpOrgEntity> getVendorOrgInfoByOwnerCode(String ownerCode) throws Throwable {
		return this.sqlMapClientTemplate.selectList(NAMESPACE
				+ "getVendorOrgInfoByOwnerCode", ownerCode);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewErpOrgEntity> getWarehInfoByOwnerCode(String ownerCode) throws Throwable {
		return this.sqlMapClientTemplate.selectList(NAMESPACE
				+ "getWarehInfoByOwnerCode", ownerCode);
	}

}
