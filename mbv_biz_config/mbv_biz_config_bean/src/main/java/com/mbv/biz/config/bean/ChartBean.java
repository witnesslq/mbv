package com.mbv.biz.config.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 主页图表
 * @author henry
 *
 */
public class ChartBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1927860575518865371L;

	private String unitCode;

    private double ttlQty;

    private double ttlVal;
    
    private Integer amount;
    
    private Date docDate;
    
    private String hour;
    
    private String day;
    
    private String month;
    
    private String weekFrom;
    
    private String weekTo;
    
    /**
     * 已处理订单
     * @return
     */
    private String orderDone;
    
    /**
     * 待处理订单
     * @return
     */
    private String orderStandBy;
    
	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
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

	public String getOrderDone() {
		return orderDone;
	}

	public void setOrderDone(String orderDone) {
		this.orderDone = orderDone;
	}

	public String getOrderStandBy() {
		return orderStandBy;
	}

	public void setOrderStandBy(String orderStandBy) {
		this.orderStandBy = orderStandBy;
	}

	@Override
	public String toString() {
		return "ChartBean [unitCode=" + unitCode + ", ttlQty=" + ttlQty
				+ ", ttlVal=" + ttlVal + ", amount=" + amount + ", docDate="
				+ docDate + ", hour=" + hour + ", day=" + day + ", month="
				+ month + ", weekFrom=" + weekFrom + ", weekTo=" + weekTo
				+ ", orderDone=" + orderDone + ", orderStandBy=" + orderStandBy
				+ "]";
	}
    
}
