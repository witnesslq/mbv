package com.mbv.inventory.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class VpGdnEntity implements Serializable{
    
	private static final long serialVersionUID = 4876199657143620550L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.id
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.doc_code
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private String docCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.doc_date
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private Date docDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.unit_code
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private String unitCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.wareh_code
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private String warehCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.ttl_qty
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private int ttlQty;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.disp_mode
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private String dispMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.disp_time
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private Date dispTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.disp_reason
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private String dispReason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.doc_state
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private String docState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.src_doc_tye
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private String srcDocTye;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.src_doc_code
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private String srcDocCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.remark
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private String remark;
    
    private int version;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.create_user
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.create_date
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.last_modified_user
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private String lastModifiedUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_gdn.last_modified_date
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    private Date lastModifiedDate;
    
    private List<VpGdnDtlEntity> details;
    
    public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<VpGdnDtlEntity> getDetails() {
		return details;
	}

	public void setDetails(List<VpGdnDtlEntity> details) {
		this.details = details;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.id
     *
     * @return the value of vp_gdn.id
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.id
     *
     * @param id the value for vp_gdn.id
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.doc_code
     *
     * @return the value of vp_gdn.doc_code
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public String getDocCode() {
        return docCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.doc_code
     *
     * @param docCode the value for vp_gdn.doc_code
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setDocCode(String docCode) {
        this.docCode = docCode == null ? null : docCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.doc_date
     *
     * @return the value of vp_gdn.doc_date
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public Date getDocDate() {
        return docDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.doc_date
     *
     * @param docDate the value for vp_gdn.doc_date
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.unit_code
     *
     * @return the value of vp_gdn.unit_code
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.unit_code
     *
     * @param unitCode the value for vp_gdn.unit_code
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode == null ? null : unitCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.wareh_code
     *
     * @return the value of vp_gdn.wareh_code
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public String getWarehCode() {
        return warehCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.wareh_code
     *
     * @param warehCode the value for vp_gdn.wareh_code
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setWarehCode(String warehCode) {
        this.warehCode = warehCode == null ? null : warehCode.trim();
    }

    public int getTtlQty() {
		return ttlQty;
	}

	public void setTtlQty(int ttlQty) {
		this.ttlQty = ttlQty;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.disp_mode
     *
     * @return the value of vp_gdn.disp_mode
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public String getDispMode() {
        return dispMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.disp_mode
     *
     * @param dispMode the value for vp_gdn.disp_mode
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setDispMode(String dispMode) {
        this.dispMode = dispMode == null ? null : dispMode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.disp_time
     *
     * @return the value of vp_gdn.disp_time
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public Date getDispTime() {
        return dispTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.disp_time
     *
     * @param dispTime the value for vp_gdn.disp_time
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setDispTime(Date dispTime) {
        this.dispTime = dispTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.disp_reason
     *
     * @return the value of vp_gdn.disp_reason
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public String getDispReason() {
        return dispReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.disp_reason
     *
     * @param dispReason the value for vp_gdn.disp_reason
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setDispReason(String dispReason) {
        this.dispReason = dispReason == null ? null : dispReason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.doc_state
     *
     * @return the value of vp_gdn.doc_state
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public String getDocState() {
        return docState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.doc_state
     *
     * @param docState the value for vp_gdn.doc_state
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setDocState(String docState) {
        this.docState = docState == null ? null : docState.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.src_doc_tye
     *
     * @return the value of vp_gdn.src_doc_tye
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public String getSrcDocTye() {
        return srcDocTye;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.src_doc_tye
     *
     * @param srcDocTye the value for vp_gdn.src_doc_tye
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setSrcDocTye(String srcDocTye) {
        this.srcDocTye = srcDocTye == null ? null : srcDocTye.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.src_doc_code
     *
     * @return the value of vp_gdn.src_doc_code
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public String getSrcDocCode() {
        return srcDocCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.src_doc_code
     *
     * @param srcDocCode the value for vp_gdn.src_doc_code
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setSrcDocCode(String srcDocCode) {
        this.srcDocCode = srcDocCode == null ? null : srcDocCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.remark
     *
     * @return the value of vp_gdn.remark
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.remark
     *
     * @param remark the value for vp_gdn.remark
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.create_user
     *
     * @return the value of vp_gdn.create_user
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.create_user
     *
     * @param createUser the value for vp_gdn.create_user
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.create_date
     *
     * @return the value of vp_gdn.create_date
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.create_date
     *
     * @param createDate the value for vp_gdn.create_date
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.last_modified_user
     *
     * @return the value of vp_gdn.last_modified_user
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.last_modified_user
     *
     * @param lastModifiedUser the value for vp_gdn.last_modified_user
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser == null ? null : lastModifiedUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_gdn.last_modified_date
     *
     * @return the value of vp_gdn.last_modified_date
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_gdn.last_modified_date
     *
     * @param lastModifiedDate the value for vp_gdn.last_modified_date
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}