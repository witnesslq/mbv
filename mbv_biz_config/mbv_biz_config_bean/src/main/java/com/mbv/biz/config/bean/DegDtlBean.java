package com.mbv.biz.config.bean;

import java.io.Serializable;

/**
 * 发货单明细
 * @author henry
 *
 */
public class DegDtlBean implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1816748847016043377L;

	private Long id;

    private Long vpDegId;

    private String prodNum;

    private double qty;

    private double unitPrice;

    private String remark;
    
    private String brandCode;
	
	private String brandName;
	
	private String productCode;
	
	private String productName;
	
	private String colorCode;
	
	private String colorName;
	
	private String sizeCode;
	
	private String sizeName;
	
	private String productUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVpDegId() {
        return vpDegId;
    }

    public void setVpDegId(Long vpDegId) {
        this.vpDegId = vpDegId;
    }

    public String getProdNum() {
        return prodNum;
    }

    public void setProdNum(String prodNum) {
        this.prodNum = prodNum == null ? null : prodNum.trim();
    }

    public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	@Override
	public String toString() {
		return "DegDtlBean [id=" + id + ", vpDegId=" + vpDegId + ", prodNum="
				+ prodNum + ", qty=" + qty + ", unitPrice=" + unitPrice
				+ ", remark=" + remark + ", brandCode=" + brandCode
				+ ", brandName=" + brandName + ", productCode=" + productCode
				+ ", productName=" + productName + ", colorCode=" + colorCode
				+ ", colorName=" + colorName + ", sizeCode=" + sizeCode
				+ ", sizeName=" + sizeName + ", productUrl=" + productUrl + "]";
	}
    
}