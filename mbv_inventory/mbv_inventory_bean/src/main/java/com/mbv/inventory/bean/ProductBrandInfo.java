package com.mbv.inventory.bean;

import java.io.Serializable;

public class ProductBrandInfo implements Serializable {

	private static final long serialVersionUID = -1972032729609363055L;

	private String brandId;                      //品牌ID
	private String brandCode;                    //品牌编码
	private String brandName;                    //品牌名称
	private String srcBrandId;                   //原品牌ID
	private String srcBrandCode;                 //原品牌编码
	private String srcBrandName;                 //原品牌名称
	private String isTP;                         //是否第三方品牌   1：第三方品牌   0：非第三方品牌
	
	public String getSrcBrandId() {
		return srcBrandId;
	}
	public void setSrcBrandId(String srcBrandId) {
		this.srcBrandId = srcBrandId;
	}
	public String getSrcBrandCode() {
		return srcBrandCode;
	}
	public void setSrcBrandCode(String srcBrandCode) {
		this.srcBrandCode = srcBrandCode;
	}
	public String getSrcBrandName() {
		return srcBrandName;
	}
	public void setSrcBrandName(String srcBrandName) {
		this.srcBrandName = srcBrandName;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getIsTP() {
		return isTP;
	}
	public void setIsTP(String isTP) {
		this.isTP = isTP;
	}
	
}
