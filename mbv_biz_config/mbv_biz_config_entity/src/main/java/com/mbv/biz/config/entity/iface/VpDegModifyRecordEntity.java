package com.mbv.biz.config.entity.iface;

import java.io.Serializable;
import java.util.Date;

public class VpDegModifyRecordEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4792882157779571753L;

	/**
	 * id
	 */
	private Long id;
	
	/**
	 * 发货单编码
	 */
	private String vpDegCode;
	
	/**
	 * 变更类型
	 */
	private String modifyCode;
	
	/**
	 * 是否已处理
	 */
	private Byte status;
	
	/**
	 * 创建人
	 */
	private String createUser;
	
	/**
	 * 创建时间
	 */
	private Date createDate; 
	
	/**
	 * 最后更新人
	 */
	private String lastModifiedUser;
	
	/**
	 * 最后更新时间
	 */
	private Date lastModifiedDate;
	
	/**
	 * 原始值json串
	 */
	private String dealSugst;
	
	/**
	 * 原始值描述串
	 */
	private String originalValDesc;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVpDegCode() {
		return vpDegCode;
	}

	public void setVpDegCode(String vpDegCode) {
		this.vpDegCode = vpDegCode;
	}

	public String getModifyCode() {
		return modifyCode;
	}

	public void setModifyCode(String modifyCode) {
		this.modifyCode = modifyCode;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public String getDealSugst() {
		return dealSugst;
	}

	public void setDealSugst(String dealSugst) {
		this.dealSugst = dealSugst;
	}

	public Byte getStatus() {
		return status;
	}

	public String getOriginalValDesc() {
		return originalValDesc;
	}

	public void setOriginalValDesc(String originalValDesc) {
		this.originalValDesc = originalValDesc;
	}
	
	
}
