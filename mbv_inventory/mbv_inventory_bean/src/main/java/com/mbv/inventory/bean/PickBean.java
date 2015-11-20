package com.mbv.inventory.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class PickBean implements Serializable{
    /**
	 * 商品数据
	 */
	private static final long serialVersionUID = -2710147648637042086L;
	
	private int id;
	private String name;
    private String code;
    private int num;
    private String productName;
    private String color;
    private String unitPrice;
    private String onBrandPrc;
    private String total;
    private String desc;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getOnBrandPrc() {
		return onBrandPrc;
	}
	public void setOnBrandPrc(String onBrandPrc) {
		this.onBrandPrc = onBrandPrc;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
    
}
