package com.mbv.biz.config.iface.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbv.biz.config.entity.iface.NewErpOrgEntity;
import com.mbv.biz.config.iface.bean.OrgResponse;
import com.mbv.biz.config.iface.dao.NewErpOrgDao;
import com.mbv.biz.config.iface.service.NewErpOrgService;

@Service("newErpOrgService")
public class NewErpOrgServiceImpl implements NewErpOrgService {

	@Autowired
	private NewErpOrgDao newErpOrgDao;

	@Override
	public OrgResponse getVendorOrgInfoByOwnerCode(String ownerCode) {
		OrgResponse response = new OrgResponse();

		try {
			if (ownerCode != null && !ownerCode.isEmpty()) {
				List<NewErpOrgEntity> lstOrg;
				try {
					lstOrg = newErpOrgDao
							.getVendorOrgInfoByOwnerCode(ownerCode);
					if (lstOrg != null && lstOrg.size() > 0) {
						response.setResult(true);
						response.setData(lstOrg);
					} else {
						response.setResult(true);
						response.setMessage("所属组织为：" + ownerCode
								+ " 的供应商信息在系统中不存在！");
					}
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				response.setResult(false);
				response.setMessage("传入参数 所属组织编码为空！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			response.setResult(false);
			response.setMessage(ex.getMessage());
		}

		return response;
	}

	@Override
	public OrgResponse getWarehInfoByOwnerCode(String ownerCode) {
		OrgResponse response = new OrgResponse();

		try {
			if (ownerCode != null && !ownerCode.isEmpty()) {
				List<NewErpOrgEntity> lstOrg;
				try {
					lstOrg = newErpOrgDao
							.getWarehInfoByOwnerCode(ownerCode);
					if (lstOrg != null && lstOrg.size() > 0) {
						response.setResult(true);
						response.setData(lstOrg);
					} else {
						response.setResult(true);
						response.setMessage("所属组织为：" + ownerCode + " 的仓库信息在系统中不存在！");
					}
				} catch (Throwable e) {
					e.printStackTrace();
				}
			} else {
				response.setResult(false);
				response.setMessage("传入参数 所属组织编码为空！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			response.setResult(false);
			response.setMessage(ex.getMessage());
		}

		return response;
	}

}
