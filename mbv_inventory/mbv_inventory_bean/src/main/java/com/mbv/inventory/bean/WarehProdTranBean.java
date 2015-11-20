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
 * @类描述：出入库事务查询bean
 * @2015年9月6日
 * @version
 */
public class WarehProdTranBean implements Serializable{

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -8892677953684737553L;
    
    /**
     * 商品编码
     */
    private String prodNum;
    
    /**
     * 仓库编号
     */
    private String warehCode;
    
    /**
     * 总库存数
     */
    private int stkOnHand;
    
    /**
     * 已分配数
     */
    private int qtyCommitted;
    
    /**
     *  锁定数
     */
    private int qtyOnLock;
    
    private String docType;
    
    private String tranDate1;
    
    private String tranDate2;
    
    private long id;
    
    private String prodName;
    
    private String prodProperty;
    
    private int tranQty;
    
    private int inventoryQty;
    
    private String prodClsNum;
    
    private Date tranDate;
    
    private Date tranTime;
    
    private String docNum;
    
    private String queryDay;
    
    private String queryMonth;
    
	public String getQueryDay() {
		return queryDay;
	}

	public void setQueryDay(String queryDay) {
		this.queryDay = queryDay;
	}

	public String getQueryMonth() {
		return queryMonth;
	}

	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}

	public String getDocNum() {
		return docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	public Date getTranTime() {
		return tranTime;
	}

	public void setTranTime(Date tranTime) {
		this.tranTime = tranTime;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getProdClsNum() {
		return prodClsNum;
	}

	public void setProdClsNum(String prodClsNum) {
		this.prodClsNum = prodClsNum;
	}

	public int getInventoryQty() {
		return inventoryQty;
	}

	public void setInventoryQty(int inventoryQty) {
		this.inventoryQty = inventoryQty;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTranDate1() {
		return tranDate1;
	}

	public void setTranDate1(String tranDate1) {
		this.tranDate1 = tranDate1;
	}

	public String getTranDate2() {
		return tranDate2;
	}

	public void setTranDate2(String tranDate2) {
		this.tranDate2 = tranDate2;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
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

	public int getStkOnHand() {
		return stkOnHand;
	}

	public void setStkOnHand(int stkOnHand) {
		this.stkOnHand = stkOnHand;
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

	public int getTranQty() {
		return tranQty;
	}

	public void setTranQty(int tranQty) {
		this.tranQty = tranQty;
	}

}
