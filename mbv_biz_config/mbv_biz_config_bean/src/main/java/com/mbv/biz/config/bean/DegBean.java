package com.mbv.biz.config.bean;

import java.io.Serializable;
import java.util.Date;

public class DegBean implements Serializable{
	
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

    private double ttlQty;

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

    private double shippingFee;

    private String tspComCode;

    private String createUser;

    private Date createDate;

    private String lastModifiedUser;

    private Date lastModifiedDate;

    private Integer pickingPrintCount;

    private String orderSn;

    private String remark;
    
    private String orderRemark;
    
    private double totalTtlVal;
    
    private double totalTtlQty;
    
    private int firstPage;
    
    private int length;
    
    private String weekFrom;
    
    private String weekTo;
    
    private String day;
    
    private String month;
    
    private int flag;
    
    private String prodNum;

    private double qty;

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
    
    public double getTtlQty() {
		return ttlQty;
	}

	public void setTtlQty(double ttlQty) {
		this.ttlQty = ttlQty;
	}

	public double getTtlVal() {
		return ttlVal;
	}

	public void setTtlVal(double ttlVal) {
		this.ttlVal = ttlVal;
	}

	public double getReceivables() {
		return receivables;
	}

	public void setReceivables(double receivables) {
		this.receivables = receivables;
	}

	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
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

	public double getTotalTtlVal() {
		return totalTtlVal;
	}

	public void setTotalTtlVal(double totalTtlVal) {
		this.totalTtlVal = totalTtlVal;
	}

	public double getTotalTtlQty() {
		return totalTtlQty;
	}

	public void setTotalTtlQty(double totalTtlQty) {
		this.totalTtlQty = totalTtlQty;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getWeekFrom() {
		return weekFrom;
	}

	public void setWeekFrom(String weekFrom) {
		this.weekFrom = weekFrom;
	}

	public String getWeekTo() {
		return weekTo;
	}

	public void setWeekTo(String weekTo) {
		this.weekTo = weekTo;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getProdNum() {
		return prodNum;
	}

	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "DegBean [id=" + id + ", docCode=" + docCode + ", docDate="
				+ docDate + ", docState=" + docState + ", unitCode=" + unitCode
				+ ", warehCode=" + warehCode + ", ttlQty=" + ttlQty
				+ ", ttlVal=" + ttlVal + ", srcDocType=" + srcDocType
				+ ", srcDocCode=" + srcDocCode + ", custName=" + custName
				+ ", receivables=" + receivables + ", consignee=" + consignee
				+ ", moblie=" + moblie + ", country=" + country + ", province="
				+ province + ", city=" + city + ", district=" + district
				+ ", delivAddress=" + delivAddress + ", delivPstd=" + delivPstd
				+ ", expressCode=" + expressCode + ", shippingFee="
				+ shippingFee + ", tspComCode=" + tspComCode + ", createUser="
				+ createUser + ", createDate=" + createDate
				+ ", lastModifiedUser=" + lastModifiedUser
				+ ", lastModifiedDate=" + lastModifiedDate
				+ ", pickingPrintCount=" + pickingPrintCount + ", orderSn="
				+ orderSn + ", remark=" + remark + ", orderRemark="
				+ orderRemark + ", totalTtlVal=" + totalTtlVal
				+ ", totalTtlQty=" + totalTtlQty + ", firstPage=" + firstPage
				+ ", length=" + length + ", weekFrom=" + weekFrom + ", weekTo="
				+ weekTo + ", day=" + day + ", month=" + month + ", flag="
				+ flag + ", prodNum=" + prodNum + ", qty=" + qty + "]";
	}
	
}