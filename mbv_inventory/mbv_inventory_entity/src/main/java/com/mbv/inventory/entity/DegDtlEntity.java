package com.mbv.inventory.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class DegDtlEntity implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1816748847016043377L;

	private Long id;

    private Long vpDegId;

    private String prodNum;

    private BigDecimal qty;

    private BigDecimal unitPrice;

    private String remark;

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

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}