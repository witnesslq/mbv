package com.mbv.biz.config.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 发货单变更记录
 * @author henry
 *
 */
public class DegModifyBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8071129424405079113L;

	private Long id;

    private String vpDegCode;

    private String modifyCode;

    private Byte status;

    private String dealSugst;

    private String originalValDesc;

    private String createUser;

    private Date createDate;

    private String lastModifiedUser;

    private Date lastModifiedDate;

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

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getDealSugst() {
		return dealSugst;
	}

	public void setDealSugst(String dealSugst) {
		this.dealSugst = dealSugst;
	}

	public String getOriginalValDesc() {
		return originalValDesc;
	}

	public void setOriginalValDesc(String originalValDesc) {
		this.originalValDesc = originalValDesc;
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

	@Override
	public String toString() {
		return "DegModifyBean [id=" + id + ", vpDegCode=" + vpDegCode
				+ ", modifyCode=" + modifyCode + ", status=" + status
				+ ", dealSugst=" + dealSugst + ", originalValDesc="
				+ originalValDesc + ", createUser=" + createUser
				+ ", createDate=" + createDate + ", lastModifiedUser="
				+ lastModifiedUser + ", lastModifiedDate=" + lastModifiedDate
				+ "]";
	}
    
}
