package com.mbv.biz.config.bean;

import java.io.Serializable;

public class WnOrderBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2125675980467884108L;

	private String actionUser;
	
	private String code;
	
	private String logType;
	
	private String note;
	
	private String orderSn;
	
	private String outId;

	public String getActionUser() {
		return actionUser;
	}

	public void setActionUser(String actionUser) {
		this.actionUser = actionUser;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}
	
	
}
