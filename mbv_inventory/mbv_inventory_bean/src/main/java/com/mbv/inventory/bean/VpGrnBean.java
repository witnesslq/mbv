package com.mbv.inventory.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class VpGrnBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8420021152074002141L;

	private long id;
	
	private String docCode;
	
	private Date docDate;
	
	private String unitCode;
	
	private String warehCode;
	
	private int ttlQty;
	
	private String rcptMode;
	
	private Date rcptTime;
	
	private String rcptReason;
	
	private String docState;
	
	private String remark;
    
    private int version;
	
	private String rcptTime1;
	
	private String rcptTime2;
	
	private String queryDay;
	
	private String queryMonth;
	
	private List<VpGrnDtlBean> detailBeans;
	
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

	public String getRcptMode() {
		return rcptMode;
	}

	public void setRcptMode(String rcptMode) {
		this.rcptMode = rcptMode;
	}

	public Date getRcptTime() {
		return rcptTime;
	}

	public void setRcptTime(Date rcptTime) {
		this.rcptTime = rcptTime;
	}

	public String getRcptReason() {
		return rcptReason;
	}

	public void setRcptReason(String rcptReason) {
		this.rcptReason = rcptReason;
	}

	public String getDocState() {
		return docState;
	}

	public void setDocState(String docState) {
		this.docState = docState;
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

	public List<VpGrnDtlBean> getDetailBeans() {
		return detailBeans;
	}

	public void setDetailBeans(List<VpGrnDtlBean> detailBeans) {
		this.detailBeans = detailBeans;
	}

	public String getQueryDay() {
		return queryDay;
	}

	public void setQueryDay(String queryDay) {
		this.queryDay = queryDay;
	}

	public String getQueryMonth() {
		return queryMonth;
	}

	public void setQueryMonth(String queryMonth) {
		this.queryMonth = queryMonth;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getRcptTime1() {
		return rcptTime1;
	}

	public void setRcptTime1(String rcptTime1) {
		this.rcptTime1 = rcptTime1;
	}

	public String getRcptTime2() {
		return rcptTime2;
	}

	public void setRcptTime2(String rcptTime2) {
		this.rcptTime2 = rcptTime2;
	}

}
