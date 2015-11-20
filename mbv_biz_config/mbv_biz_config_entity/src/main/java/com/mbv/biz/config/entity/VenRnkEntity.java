package com.mbv.biz.config.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VenRnkEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2409722304064014228L;
	
	private String rowId;//伪列用于显示
	private String id;
	private String prodNum;
	private String prodName;
	private String prodUrl;
	private String colorName;
	private String sizeName; 
	private Double ttPrice;
	private String ttPriceFormat;//format
	private Double ttQty;
	private Double stkOnHand;
	private Integer level;
	private String parent;
	private Boolean isLeaf;
	private Boolean expanded;
	private Boolean loaded;
	
	
	
	
	public String getTtPriceFormat() {
		return ttPriceFormat;
	}
	public void setTtPriceFormat(String ttPriceFormat) {
		this.ttPriceFormat = ttPriceFormat;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getProdUrl() {
		return prodUrl;
	}
	public void setProdUrl(String prodUrl) {
		this.prodUrl = prodUrl;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Boolean getExpanded() {
		return expanded;
	}
	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}
	public Boolean getLoaded() {
		return loaded;
	}
	public void setLoaded(Boolean loaded) {
		this.loaded = loaded;
	}

	
	
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}

	
	
	
	
	public Double getTtPrice() {
		return ttPrice;
	}
	public void setTtPrice(Double ttPrice) {
		this.ttPrice = ttPrice;
	}
	public Double getTtQty() {
		return ttQty;
	}
	public void setTtQty(Double ttQty) {
		this.ttQty = ttQty;
	}
	public Double getStkOnHand() {
		return stkOnHand;
	}
	public void setStkOnHand(Double stkOnHand) {
		this.stkOnHand = stkOnHand;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getProdNum() {
		return prodNum;
	}
	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
}