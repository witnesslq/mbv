package com.mbv.biz.config.iface.bean;

import java.io.Serializable;

public class OrgResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3590800998889951117L;

	/**
	 * 执行成功标识， true-成功  false-失败
	 */
	private boolean result;
	
	/**
	 * 提示信息   ， 执行成功时为空
	 */
	private String message;
	
	/**
	 * 其他信息
	 */
	private Object data;
	

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
