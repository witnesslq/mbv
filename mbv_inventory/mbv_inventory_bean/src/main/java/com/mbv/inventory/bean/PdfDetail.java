package com.mbv.inventory.bean;

import java.io.Serializable;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PdfDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5646517567928935256L;
	private JRBeanCollectionDataSource table_datas;
	private String doc_code;
	private String ordernum;
	private String consignee;
	private String mobile;
	private String deliv_address;
	private int ttl_qty;
	private String ttl_val;
	private String orderRemark;
	private String sysPhone;
	
	public JRBeanCollectionDataSource getTable_datas() {
		return table_datas;
	}
	public void setTable_datas(JRBeanCollectionDataSource table_datas) {
		this.table_datas = table_datas;
	}
	public String getDoc_code() {
		return doc_code==null?"":doc_code;
	}
	public void setDoc_code(String doc_code) {
		this.doc_code = doc_code;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getMobile() {
		return mobile==null?"":mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDeliv_address() {
		return deliv_address==null?"":deliv_address;
	}
	public void setDeliv_address(String deliv_address) {
		this.deliv_address = deliv_address;
	}
	public int getTtl_qty() {
		return ttl_qty;
	}
	public void setTtl_qty(int ttl_qty) {
		this.ttl_qty = ttl_qty;
	}
	public String getTtl_val() {
		return ttl_val;
	}
	public void setTtl_val(String ttl_val) {
		this.ttl_val = ttl_val;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public String getSysPhone() {
		return sysPhone;
	}
	public void setSysPhone(String sysPhone) {
		this.sysPhone = sysPhone;
	}
	
	
}
