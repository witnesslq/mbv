package com.mbv.inventory.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DeliveryEntity implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 983413741303704645L;

	private Long id;

    private String docCode;

    private Date docDate;

    private String docState;

    private String unitCode;

    private String warehCode;

    private int ttlQty;

    private double ttlVal;

    private String srcDocType;

    private String srcDocCode;

    private String custName;

    private double receivables;

    private String consignee;

    private String moblie;

    private String country;

    private String province;

    private String city;

    private String district;

    private String delivAddress;

    private String delivPstd;

    private String expressCode;

    private BigDecimal shippingFee;

    private String tspComCode;

    private String createUser;

    private Date createDate;

    private String lastModifiedUser;

    private Date lastModifiedDate;

    private Integer pickingPrintCount;

    private String orderSn;

    private String remark;
    
    private String orderRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode == null ? null : docCode.trim();
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getDocState() {
        return docState;
    }

    public void setDocState(String docState) {
        this.docState = docState == null ? null : docState.trim();
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode == null ? null : unitCode.trim();
    }

    public String getWarehCode() {
        return warehCode;
    }

    public void setWarehCode(String warehCode) {
        this.warehCode = warehCode == null ? null : warehCode.trim();
    }


    public int getTtlQty() {
		return ttlQty;
	}

	public void setTtlQty(int ttlQty) {
		this.ttlQty = ttlQty;
	}

	public double getTtlVal() {
		return ttlVal;
	}

	public void setTtlVal(double ttlVal) {
		this.ttlVal = ttlVal;
	}

	public void setReceivables(double receivables) {
		this.receivables = receivables;
	}

	public String getSrcDocType() {
        return srcDocType;
    }

    public void setSrcDocType(String srcDocType) {
        this.srcDocType = srcDocType == null ? null : srcDocType.trim();
    }

    public String getSrcDocCode() {
        return srcDocCode;
    }

    public void setSrcDocCode(String srcDocCode) {
        this.srcDocCode = srcDocCode == null ? null : srcDocCode.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public double getReceivables() {
		return receivables;
	}

	public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie == null ? null : moblie.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getDelivAddress() {
        return delivAddress;
    }

    public void setDelivAddress(String delivAddress) {
        this.delivAddress = delivAddress == null ? null : delivAddress.trim();
    }

    public String getDelivPstd() {
        return delivPstd;
    }

    public void setDelivPstd(String delivPstd) {
        this.delivPstd = delivPstd == null ? null : delivPstd.trim();
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode == null ? null : expressCode.trim();
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getTspComCode() {
        return tspComCode;
    }

    public void setTspComCode(String tspComCode) {
        this.tspComCode = tspComCode == null ? null : tspComCode.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser == null ? null : lastModifiedUser.trim();
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getPickingPrintCount() {
        return pickingPrintCount;
    }

    public void setPickingPrintCount(Integer pickingPrintCount) {
        this.pickingPrintCount = pickingPrintCount;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
}