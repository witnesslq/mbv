package com.mbv.inventory.bean;

import java.io.Serializable;

public class VpGrnDtlBean implements Serializable {

	private static final long serialVersionUID = -4721272596255806438L;
	
    private Long id;

    private Long vpGrnId;

    private String prodNum;

    private int qty;

    private String remark;
    
    private String prodClsNum;
    
    private String prodName;
    
    private String colorName;
    
    private String colorCode;
    
    private String specName;
    
    private String specCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVpGrnId() {
		return vpGrnId;
	}

	public void setVpGrnId(Long vpGrnId) {
		this.vpGrnId = vpGrnId;
	}

	public String getProdNum() {
		return prodNum;
	}

	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProdClsNum() {
		return prodClsNum;
	}

	public void setProdClsNum(String prodClsNum) {
		this.prodClsNum = prodClsNum;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSpecCode() {
		return specCode;
	}

	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}
    
}
