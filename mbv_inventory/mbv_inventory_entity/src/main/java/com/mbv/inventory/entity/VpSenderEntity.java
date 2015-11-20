package com.mbv.inventory.entity;

import java.io.Serializable;
import java.util.Date;

public class VpSenderEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2524978550441102185L;

	private Integer id;

    private String unitCode;

    private String warehCode;

    private String consignor;

    private String mobile;

    private String telephone;

    private String country;

    private String province;

    private String city;

    private String district;

    private String zip;

    private String address;

    private Byte defaultStatus;

    private String shortName;

    private String remark;

    private String createUser;

    private Date createDate;

    private String lastModifiedUser;

    private Date lastModifiedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.unit_code
     *
     * @return the value of vp_sender.unit_code
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.unit_code
     *
     * @param unitCode the value for vp_sender.unit_code
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode == null ? "" : unitCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.wareh_code
     *
     * @return the value of vp_sender.wareh_code
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getWarehCode() {
        return warehCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.wareh_code
     *
     * @param warehCode the value for vp_sender.wareh_code
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setWarehCode(String warehCode) {
        this.warehCode = warehCode == null ? "" : warehCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.consignor
     *
     * @return the value of vp_sender.consignor
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getConsignor() {
        return consignor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.consignor
     *
     * @param consignor the value for vp_sender.consignor
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setConsignor(String consignor) {
        this.consignor = consignor == null ? "" : consignor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.mobile
     *
     * @return the value of vp_sender.mobile
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.mobile
     *
     * @param mobile the value for vp_sender.mobile
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? "" : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.telephone
     *
     * @return the value of vp_sender.telephone
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.telephone
     *
     * @param telephone the value for vp_sender.telephone
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? "" : telephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.country
     *
     * @return the value of vp_sender.country
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.country
     *
     * @param country the value for vp_sender.country
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setCountry(String country) {
        this.country = country == null ? "" : country.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.province
     *
     * @return the value of vp_sender.province
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.province
     *
     * @param province the value for vp_sender.province
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setProvince(String province) {
        this.province = province == null ? "" : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.city
     *
     * @return the value of vp_sender.city
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.city
     *
     * @param city the value for vp_sender.city
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setCity(String city) {
        this.city = city == null ? "" : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.district
     *
     * @return the value of vp_sender.district
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getDistrict() {
        return district;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.district
     *
     * @param district the value for vp_sender.district
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setDistrict(String district) {
        this.district = district == null ? "" : district.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.zip
     *
     * @return the value of vp_sender.zip
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getZip() {
        return zip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.zip
     *
     * @param zip the value for vp_sender.zip
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setZip(String zip) {
        this.zip = zip == null ? "" : zip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.address
     *
     * @return the value of vp_sender.address
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.address
     *
     * @param address the value for vp_sender.address
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setAddress(String address) {
        this.address = address == null ? "" : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.default_status
     *
     * @return the value of vp_sender.default_status
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public Byte getDefaultStatus() {
        return defaultStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.default_status
     *
     * @param defaultStatus the value for vp_sender.default_status
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setDefaultStatus(Byte defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.short_name
     *
     * @return the value of vp_sender.short_name
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.short_name
     *
     * @param shortName the value for vp_sender.short_name
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setShortName(String shortName) {
        this.shortName = shortName == null ? "" : shortName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.remark
     *
     * @return the value of vp_sender.remark
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.remark
     *
     * @param remark the value for vp_sender.remark
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? "" : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.create_user
     *
     * @return the value of vp_sender.create_user
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.create_user
     *
     * @param createUser the value for vp_sender.create_user
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? "" : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.create_date
     *
     * @return the value of vp_sender.create_date
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.create_date
     *
     * @param createDate the value for vp_sender.create_date
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.last_modified_user
     *
     * @return the value of vp_sender.last_modified_user
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.last_modified_user
     *
     * @param lastModifiedUser the value for vp_sender.last_modified_user
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser == null ? "" : lastModifiedUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vp_sender.last_modified_date
     *
     * @return the value of vp_sender.last_modified_date
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vp_sender.last_modified_date
     *
     * @param lastModifiedDate the value for vp_sender.last_modified_date
     *
     * @mbggenerated Mon Sep 28 14:32:40 CST 2015
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}