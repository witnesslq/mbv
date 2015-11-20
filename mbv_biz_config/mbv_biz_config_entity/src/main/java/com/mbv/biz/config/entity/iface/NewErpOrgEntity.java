package com.mbv.biz.config.entity.iface;

import java.io.Serializable;

public class NewErpOrgEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4346063975695756905L;
	
	/**
	 * 组织编码
	 */
	private String orgCode;
	
	/**
	 * 组织名称
	 */
	private String orgName;
	
	/**
	 * 组织类型编码
	 */
	private String orgTypeCode;
	
	/**
	 * 组织类型名称
	 */
	private String orgTypeName;
	
	/**
	 * 所属组织编码
	 */
	private String ownerCode;
	
	/**
	 * 所属组织名称
	 */
	private String ownerName;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgTypeCode() {
		return orgTypeCode;
	}

	public void setOrgTypeCode(String orgTypeCode) {
		this.orgTypeCode = orgTypeCode;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public String getOwnerCode() {
		return ownerCode;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}
