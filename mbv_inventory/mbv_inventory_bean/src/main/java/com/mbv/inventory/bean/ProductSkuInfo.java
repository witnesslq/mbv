package com.mbv.inventory.bean;

import java.io.Serializable;

public class ProductSkuInfo implements Serializable {

	private static final long serialVersionUID = 3422463899420998493L;

	private String prodNum;           //商品11位SKU码
	private String colorCode;         //颜色编码
	private String colorName;         //颜色名称
	private String specCode;          //尺寸编码
	private String specName;          //尺寸名称
	private String srcColorCode;       //源颜色编码
	private String srcColorName;       //源颜色名称
	private String srcSpecCode;        //源尺寸编码
	private String srcSpecName;        //源尺寸名称
	private String intnlCode;         //国际码
	private String innerCode;         //店内码
	private String prodStatus;        //商品状态
	private String prodGrid;          //网格值
	
	public String getProdStatus() {
		return prodStatus;
	}
	public void setProdStatus(String prodStatus) {
		this.prodStatus = prodStatus;
	}
	public String getProdGrid() {
		return prodGrid;
	}
	public void setProdGrid(String prodGrid) {
		this.prodGrid = prodGrid;
	}
	public String getSrcColorCode() {
		return srcColorCode;
	}
	public void setSrcColorCode(String srcColorCode) {
		this.srcColorCode = srcColorCode;
	}
	public String getSrcColorName() {
		return srcColorName;
	}
	public void setSrcColorName(String srcColorName) {
		this.srcColorName = srcColorName;
	}
	public String getSrcSpecCode() {
		return srcSpecCode;
	}
	public void setSrcSpecCode(String srcSpecCode) {
		this.srcSpecCode = srcSpecCode;
	}
	public String getSrcSpecName() {
		return srcSpecName;
	}
	public void setSrcSpecName(String srcSpecName) {
		this.srcSpecName = srcSpecName;
	}
	public String getProdNum() {
		return prodNum;
	}
	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getSpecCode() {
		return specCode;
	}
	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public String getIntnlCode() {
		return intnlCode;
	}
	public void setIntnlCode(String intnlCode) {
		this.intnlCode = intnlCode;
	}
	public String getInnerCode() {
		return innerCode;
	}
	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		sb.append(" prodNum = ").append(this.getProdNum()).append(",colorCode = ").append(this.getColorCode())
		  .append(",colorName = ").append(this.getColorName()).append(",specCode = ").append(this.getSpecCode()).append(",specName = ").append(this.getSpecName())
		  .append(",srcColorCode = ").append(this.srcColorCode).append(",srcColorName = ").append(this.getSrcColorName()).append(",srcSpecCode = ")
		  .append(this.srcSpecCode).append(",srcSpecName = ").append(this.srcSpecName)
		  .append(",intnlCode = ").append(this.intnlCode).append(",innerCode = ").append(this.innerCode);
		return sb.toString();
	}
	
}
