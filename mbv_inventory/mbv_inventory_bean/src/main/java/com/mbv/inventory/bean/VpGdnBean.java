package com.mbv.inventory.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class VpGdnBean implements Serializable {

	private static final long serialVersionUID = 8420021152074002141L;

	private Long id;
	
	private String docCode;
	
	private Date docDate;
	
	private String unitCode;
	
	private String warehCode;
	
	private int ttlQty;
	
	private String dispMode;
	
	private Date dispTime;
	
	private String dispReason;
	
	private String docState;
	
	private String srcDocTye;
	
	private String srcDocCode;
	
	private String remark;
    
    private int version;
    
    private List<VpGdnDtlBean> detailBeans;
	
	private String dispTime1;
	
	private String dispTime2;
	
	private String queryMonth;
	
	private String queryDay;
	
	private String prodNum;

	public String getProdNum() {
		return prodNum;
	}

	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public int getTtlQty() {
		return ttlQty;
	}

	public void setTtlQty(int ttlQty) {
		this.ttlQty = ttlQty;
	}

	public String getDispMode() {
		return dispMode;
	}

	public void setDispMode(String dispMode) {
		this.dispMode = dispMode;
	}

	public Date getDispTime() {
		return dispTime;
	}

	public void setDispTime(Date dispTime) {
		this.dispTime = dispTime;
	}

	public String getDispReason() {
		return dispReason;
	}

	public void setDispReason(String dispReason) {
		this.dispReason = dispReason;
	}

	public String getDocState() {
		return docState;
	}

	public void setDocState(String docState) {
		this.docState = docState;
	}

	public String getSrcDocTye() {
		return srcDocTye;
	}

	public void setSrcDocTye(String srcDocTye) {
		this.srcDocTye = srcDocTye;
	}

	public String getSrcDocCode() {
		return srcDocCode;
	}

	public void setSrcDocCode(String srcDocCode) {
		this.srcDocCode = srcDocCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<VpGdnDtlBean> getDetailBeans() {
		return detailBeans;
	}

	public void setDetailBeans(List<VpGdnDtlBean> detailBeans) {
		this.detailBeans = detailBeans;
	}

	public String getQueryMonth() {
		return queryMonth;
	}

	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}

	public String getQueryDay() {
		return queryDay;
	}

	public void setQueryDay(String queryDay) {
		this.queryDay = queryDay;
	}

	public String getDocCode() {
		return docCode;
	}

	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getWarehCode() {
		return warehCode;
	}

	public void setWarehCode(String warehCode) {
		this.warehCode = warehCode;
	}

	public String getDispTime1() {
		return dispTime1;
	}

	public void setDispTime1(String dispTime1) {
		this.dispTime1 = dispTime1;
	}

	public String getDispTime2() {
		return dispTime2;
	}

	public void setDispTime2(String dispTime2) {
		this.dispTime2 = dispTime2;
	}
	
}
