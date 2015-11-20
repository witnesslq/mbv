package com.mbv.biz.config.entity.iface;

import java.io.Serializable;
import java.util.Date;

public class VpWarehProdEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5818677340098682351L;

	/**
	 * 仓库编码
	 */
	private String warehCode;
	
	/**
	 * 商品编码
	 */
	private String prodNum;
	
	/**
	 * 商品总库存
	 */
	private float stkOnHand;
	
	/**
	 * 已分配库存
	 */
	private float qtyCommitted;
	
	/**
	 * 已锁定量
	 */
	private float qtyOnLock;
	
	private String lastModifiedUser;
	
	private Date lastModifiedDate;

	public String getWarehCode() {
		return warehCode;
	}

	public void setWarehCode(String warehCode) {
		this.warehCode = warehCode;
	}

	public String getProdNum() {
		return prodNum;
	}

	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}

	public float getStkOnHand() {
		return stkOnHand;
	}

	public void setStkOnHand(float stkOnHand) {
		this.stkOnHand = stkOnHand;
	}

	public float getQtyCommitted() {
		return qtyCommitted;
	}

	public void setQtyCommitted(float qtyCommitted) {
		this.qtyCommitted = qtyCommitted;
	}

	public float getQtyOnLock() {
		return qtyOnLock;
	}

	public void setQtyOnLock(float qtyOnLock) {
		this.qtyOnLock = qtyOnLock;
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
	
}
