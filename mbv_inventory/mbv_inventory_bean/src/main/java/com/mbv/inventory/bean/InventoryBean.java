/*
 * Copyright 2015 Metersbonwe.com All right reserved. This software is the
 * confidential and proprietary information of Metersbonwe.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Metersbonwe.com.
 */
package com.mbv.inventory.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @类描述：库存查询bean
 * @2015年9月6日
 * @version
 */
public class InventoryBean implements Serializable{

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -8892677953684737553L;
    
    private Long id;
    
    /**
     * 商品编码
     */
    private String prodNum;
    
    /**
     * 仓库编号
     */
    private String warehCode;
    
    private int stkOnHand;
    
    /**
     * 查询条件库存数1
     */
    private String stkOnHand1;
    
    /**
     * 查询条件库存数2
     */
    private String stkOnHand2;
    
    /**
     * 已分配数
     */
    private int qtyCommitted;
    
    /**
     *  锁定数
     */
    private int qtyOnLock;
    
    private String lastUpdateUser;
    
    private Date lastUpdateDate;
    
    private String prodClsNum;
    
    private String prodName;
    
    private String prodProperty;
    
	public Long getId() {
		return id;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStkOnHand() {
		return stkOnHand;
	}

	public void setStkOnHand(int stkOnHand) {
		this.stkOnHand = stkOnHand;
	}

	public String getProdClsNum() {
		return prodClsNum;
	}

	public void setProdClsNum(String prodClsNum) {
		this.prodClsNum = prodClsNum;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdProperty() {
		return prodProperty;
	}

	public void setProdProperty(String prodProperty) {
		this.prodProperty = prodProperty;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
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

	public String getStkOnHand1() {
		return stkOnHand1;
	}

	public void setStkOnHand1(String stkOnHand1) {
		this.stkOnHand1 = stkOnHand1;
	}

	public String getStkOnHand2() {
		return stkOnHand2;
	}

	public void setStkOnHand2(String stkOnHand2) {
		this.stkOnHand2 = stkOnHand2;
	}

	public int getQtyCommitted() {
		return qtyCommitted;
	}

	public void setQtyCommitted(int qtyCommitted) {
		this.qtyCommitted = qtyCommitted;
	}

	public int getQtyOnLock() {
		return qtyOnLock;
	}

	public void setQtyOnLock(int qtyOnLock) {
		this.qtyOnLock = qtyOnLock;
	}

}
