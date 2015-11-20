package com.mbv.inventory.bean;

import java.io.Serializable;

public class ResultBean implements Serializable {

	/**
	 *  发货结果
	 */
	private static final long serialVersionUID = -4403987071947141598L;
   
	private boolean flag;
	
	private String message;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
