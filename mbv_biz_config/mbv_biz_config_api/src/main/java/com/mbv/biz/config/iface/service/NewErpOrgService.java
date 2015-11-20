package com.mbv.biz.config.iface.service;

import com.mbv.biz.config.iface.bean.OrgResponse;

public interface NewErpOrgService {

	public OrgResponse getVendorOrgInfoByOwnerCode(String ownerCode);
	
	public OrgResponse getWarehInfoByOwnerCode(String ownerCode);
	
}
