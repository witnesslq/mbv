package com.mbv.inventory.bean;

import java.io.Serializable;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DeliveryDataSource implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8427807614678948558L;
	private String ordercode;
	private JRBeanCollectionDataSource table_datas;
	
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public JRBeanCollectionDataSource getTable_datas() {
		return table_datas;
	}
	public void setTable_datas(JRBeanCollectionDataSource table_datas) {
		this.table_datas = table_datas;
	}
	  
}
