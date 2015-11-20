package com.mbv.biz.config.entity.sys;

import com.mbv.common.entity.BaseEntity;

import java.util.Date;
import java.util.List;

public class DataDirectoryEntity extends BaseEntity {
    /**
	 * TODO
	 */ 
	private static final long serialVersionUID = -30761577240173427L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_directory.id
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_directory.CODE
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_directory.NAME
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_directory.descno
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    private Integer descno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_directory.isvalid
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    private String isvalid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_directory.create_oper
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    private String createOper;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_directory.create_time
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_directory.modify_oper
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    private String modifyOper;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_directory.modify_time
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    private Date modifyTime;

    /**
     * @mbggenerated 数据字典的明细集合
     */
    private List<DataDirectoryDetailEntity> details;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_directory.id
     *
     * @return the value of data_directory.id
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_directory.id
     *
     * @param id the value for data_directory.id
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_directory.CODE
     *
     * @return the value of data_directory.CODE
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_directory.CODE
     *
     * @param code the value for data_directory.CODE
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public void setCode(String code) {
        this.code = code == null||"".equals(code) ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_directory.NAME
     *
     * @return the value of data_directory.NAME
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_directory.NAME
     *
     * @param name the value for data_directory.NAME
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public void setName(String name) {
        this.name = name == null||"".equals(name) ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_directory.descno
     *
     * @return the value of data_directory.descno
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public Integer getDescno() {
        return descno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_directory.descno
     *
     * @param descno the value for data_directory.descno
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public void setDescno(Integer descno) {
        this.descno = descno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_directory.isvalid
     *
     * @return the value of data_directory.isvalid
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public String getIsvalid() {
        return isvalid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_directory.isvalid
     *
     * @param isvalid the value for data_directory.isvalid
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_directory.create_oper
     *
     * @return the value of data_directory.create_oper
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public String getCreateOper() {
        return createOper;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_directory.create_oper
     *
     * @param createOper the value for data_directory.create_oper
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public void setCreateOper(String createOper) {
        this.createOper = createOper == null ? null : createOper.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_directory.create_time
     *
     * @return the value of data_directory.create_time
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_directory.create_time
     *
     * @param createTime the value for data_directory.create_time
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_directory.modify_oper
     *
     * @return the value of data_directory.modify_oper
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public String getModifyOper() {
        return modifyOper;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_directory.modify_oper
     *
     * @param modifyOper the value for data_directory.modify_oper
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public void setModifyOper(String modifyOper) {
        this.modifyOper = modifyOper == null ? null : modifyOper.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_directory.modify_time
     *
     * @return the value of data_directory.modify_time
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_directory.modify_time
     *
     * @param modifyTime the value for data_directory.modify_time
     *
     * @mbggenerated Mon Aug 24 09:51:46 CST 2015
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 
     * TODO 返回数据字典明细集合
     *
     * @return
     */
	public List<DataDirectoryDetailEntity> getDetails() {
		return details;
	}

	/**
	 * 
	 * TODO 设定数据字典明细集合
	 *
	 * @param details
	 */
	public void setDetails(List<DataDirectoryDetailEntity> details) {
		this.details = details;
	}
    
    
}