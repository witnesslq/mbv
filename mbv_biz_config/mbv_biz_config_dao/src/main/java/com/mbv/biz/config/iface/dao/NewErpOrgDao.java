package com.mbv.biz.config.iface.dao;

import java.util.List;

import com.mbv.biz.config.entity.iface.NewErpOrgEntity;

public interface NewErpOrgDao {

	public List<NewErpOrgEntity> getVendorOrgInfoByOwnerCode(String ownerCode) throws Throwable;
	
	public List<NewErpOrgEntity> getWarehInfoByOwnerCode(String ownerCode) throws Throwable;
	
}
