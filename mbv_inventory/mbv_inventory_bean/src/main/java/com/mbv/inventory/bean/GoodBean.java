package com.mbv.inventory.bean;

import java.io.Serializable;

public class GoodBean implements Serializable{
	
    /**
	 * 商品信息
	 */
	private static final long serialVersionUID = -1816748847016043377L;

	private Long id;

    private Long vpDegId;

    private String prodNum;

    private String intnlCode;
    
	private int qty;

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
	
	private double onBrandPrc;

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
    
    public String getIntnlCode() {
		return intnlCode;
	}

	public void setIntnlCode(String intnlCode) {
		this.intnlCode = intnlCode == null ? null : intnlCode.trim();
	}


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getUnitPrice() {
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        return df.format(this.unitPrice);
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

	public String getOnBrandPrc() {
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		return df.format(this.onBrandPrc);
	}

	public void setOnBrandPrc(double onBrandPrc) {
		this.onBrandPrc = onBrandPrc;
	}
    
}