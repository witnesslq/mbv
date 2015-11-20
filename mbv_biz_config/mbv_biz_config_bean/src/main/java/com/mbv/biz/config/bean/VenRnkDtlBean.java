package com.mbv.biz.config.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VenRnkDtlBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4092350871867870630L;
	
	private String prodNum;
	private String prodName;
	private BigDecimal ttPrice;
	private BigDecimal ttQty;
	private BigDecimal stkOnHand;
	public String getProdNum() {
		return prodNum;
	}
	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public BigDecimal getTtPrice() {
		return ttPrice;
	}
	public void setTtPrice(BigDecimal ttPrice) {
		this.ttPrice = ttPrice;
	}
	public BigDecimal getTtQty() {
		return ttQty;
	}
	public void setTtQty(BigDecimal ttQty) {
		this.ttQty = ttQty;
	}
	public BigDecimal getStkOnHand() {
		return stkOnHand;
	}
	public void setStkOnHand(BigDecimal stkOnHand) {
		this.stkOnHand = stkOnHand;
	}
	
}