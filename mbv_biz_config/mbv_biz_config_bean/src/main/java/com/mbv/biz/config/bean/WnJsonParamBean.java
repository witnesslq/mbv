package com.mbv.biz.config.bean;

import java.io.Serializable;

public class WnJsonParamBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6299150689205829891L;

	private String prodNum;
	
	private int degQty;
	
	private String warehCode;
	
	private int qty;

	public String getProdNum() {
		return prodNum;
	}

	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}

	public int getDegQty() {
		return degQty;
	}

	public void setDegQty(int degQty) {
		this.degQty = degQty;
	}

	public String getWarehCode() {
		return warehCode;
	}

	public void setWarehCode(String warehCode) {
		this.warehCode = warehCode;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
