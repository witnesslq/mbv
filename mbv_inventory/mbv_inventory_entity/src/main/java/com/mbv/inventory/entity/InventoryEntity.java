package com.mbv.inventory.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.mbv.common.entity.BaseEntity;

/**
 * 
 * 类描述： 库存
 * 
 */
public class InventoryEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -6940885167854477932L;

	 /**
     * 
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 商品编码
     *
     * @mbggenerated
     */
    private String prodNum;

    /**
     * 仓库编码
     *
     * @mbggenerated
     */
    private String warehCode;

    /**
     * 总库存数
     *
     * @mbggenerated
     */
    private BigDecimal stkOnHand;

    /**
     * 已分配数
     *
     * @mbggenerated
     */
    private BigDecimal qtyCommitted;

    /**
     * 锁定数
     *
     * @mbggenerated
     */
    private BigDecimal qtyOnLock;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_grn.last_modified_user
     *
     * @mbggenerated Wed Sep 09 12:01:02 CST 2015
     */
    private String lastModifiedUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vp_grn.last_modified_date
     *
     * @mbggenerated Wed Sep 09 12:01:02 CST 2015
     */
    private Date lastModifiedDate;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProdNum() {
		return prodNum;
	}

	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}

	public String getWarehCode() {
		return warehCode;
	}

	public void setWarehCode(String warehCode) {
		this.warehCode = warehCode;
	}

	public BigDecimal getStkOnHand() {
		return stkOnHand;
	}

	public void setStkOnHand(BigDecimal stkOnHand) {
		this.stkOnHand = stkOnHand;
	}

	public BigDecimal getQtyCommitted() {
		return qtyCommitted;
	}

	public void setQtyCommitted(BigDecimal qtyCommitted) {
		this.qtyCommitted = qtyCommitted;
	}

	public BigDecimal getQtyOnLock() {
		return qtyOnLock;
	}

	public void setQtyOnLock(BigDecimal qtyOnLock) {
		this.qtyOnLock = qtyOnLock;
	}

}