package com.mbv.biz.config.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class VenRnkBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1291962974096095415L;

	private List<String> prodNumList;
	private String prodNumQuery;
	private String prodNum;
	private String date1;
	private String date2;
	private String dateFlag;
	private boolean prodFlag;//6 true 11 false
	//wareh_code
	private String warehCode;
	//unit_code
	private String unitCode;
	
	private String orderId;//1 price 2 qty 3 stock
	private String orderFlag;//0 升序 1 降序
	
	private Integer rankNum;//排行前位数

	public Integer getRankNum() {
		return rankNum;
	}

	public void setRankNum(Integer rankNum) {
		this.rankNum = rankNum;
	}

	public String getWarehCode() {
		return warehCode;
	}

	public void setWarehCode(String warehCode) {
		this.warehCode = warehCode;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getProdNumQuery() {
		return prodNumQuery;
	}

	public void setProdNumQuery(String prodNumQuery) {
		this.prodNumQuery = prodNumQuery;
	}

	public String getProdNum() {
		return prodNum;
	}

	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}

	public List<String> getProdNumList() {
		return prodNumList;
	}

	public void setProdNumList(List<String> prodNumList) {
		this.prodNumList = prodNumList;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	public boolean isProdFlag() {
		return prodFlag;
	}

	public void setProdFlag(boolean prodFlag) {
		this.prodFlag = prodFlag;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}
	
	
	

}