package com.mbv.inventory.entity;

import java.io.Serializable;
import java.util.Date;

public class VpDegModifyRecordEntity implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3623541588582468807L;


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

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_deg_modify_records.deal_sugst
     *
     * @return the value of vp_deg_modify_records.deal_sugst
     *
     * @mbggenerated Mon Sep 28 15:44:01 CST 2015
     */
    public String getDealSugst() {
        return dealSugst;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_deg_modify_records.deal_sugst
     *
     * @param dealSugst the value for vp_deg_modify_records.deal_sugst
     *
     * @mbggenerated Mon Sep 28 15:44:01 CST 2015
     */
    public void setDealSugst(String dealSugst) {
        this.dealSugst = dealSugst == null ? null : dealSugst.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_deg_modify_records.original_val_desc
     *
     * @return the value of vp_deg_modify_records.original_val_desc
     *
     * @mbggenerated Mon Sep 28 15:44:01 CST 2015
     */
    public String getOriginalValDesc() {
        return originalValDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_deg_modify_records.original_val_desc
     *
     * @param originalValDesc the value for vp_deg_modify_records.original_val_desc
     *
     * @mbggenerated Mon Sep 28 15:44:01 CST 2015
     */
    public void setOriginalValDesc(String originalValDesc) {
        this.originalValDesc = originalValDesc == null ? null : originalValDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_deg_modify_records.create_user
     *
     * @return the value of vp_deg_modify_records.create_user
     *
     * @mbggenerated Mon Sep 28 15:44:01 CST 2015
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_deg_modify_records.create_user
     *
     * @param createUser the value for vp_deg_modify_records.create_user
     *
     * @mbggenerated Mon Sep 28 15:44:01 CST 2015
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_deg_modify_records.create_date
     *
     * @return the value of vp_deg_modify_records.create_date
     *
     * @mbggenerated Mon Sep 28 15:44:01 CST 2015
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_deg_modify_records.create_date
     *
     * @param createDate the value for vp_deg_modify_records.create_date
     *
     * @mbggenerated Mon Sep 28 15:44:01 CST 2015
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_deg_modify_records.last_modified_user
     *
     * @return the value of vp_deg_modify_records.last_modified_user
     *
     * @mbggenerated Mon Sep 28 15:44:01 CST 2015
     */
    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_deg_modify_records.last_modified_user
     *
     * @param lastModifiedUser the value for vp_deg_modify_records.last_modified_user
     *
     * @mbggenerated Mon Sep 28 15:44:01 CST 2015
     */
    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser == null ? null : lastModifiedUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_deg_modify_records.last_modified_date
     *
     * @return the value of vp_deg_modify_records.last_modified_date
     *
     * @mbggenerated Mon Sep 28 15:44:01 CST 2015
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_deg_modify_records.last_modified_date
     *
     * @param lastModifiedDate the value for vp_deg_modify_records.last_modified_date
     *
     * @mbggenerated Mon Sep 28 15:44:01 CST 2015
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}