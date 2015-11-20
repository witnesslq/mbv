package com.mbv.biz.config.entity;

import java.io.Serializable;
import java.util.Date;

public class DegModifyRecordsEntity implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6284461189101787489L;

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
        this.vpDegCode = vpDegCode == null ? null : vpDegCode.trim();
    }

    public String getModifyCode() {
        return modifyCode;
    }

    public void setModifyCode(String modifyCode) {
        this.modifyCode = modifyCode == null ? null : modifyCode.trim();
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
        this.dealSugst = dealSugst == null ? null : dealSugst.trim();
    }

    public String getOriginalValDesc() {
        return originalValDesc;
    }

    public void setOriginalValDesc(String originalValDesc) {
        this.originalValDesc = originalValDesc == null ? null : originalValDesc.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
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
        this.lastModifiedUser = lastModifiedUser == null ? null : lastModifiedUser.trim();
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}