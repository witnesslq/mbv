package com.mbv.biz.config.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 工单
 * @author henry
 *
 */
public class WnBean implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2248209933342457597L;

	/**
	 * 
	 */
	

	private Long id;

    private String docCode;

    private Date docDate;

    private String docState;

    private String docType;

    private String content;

    private String remark;

    private Date cancelDate;

    private String replyContent;

    private String createUser;

    private Date createDate;

    private String lastModifiedUser;

    private Date lastModifiedDate;

    private String srcDocType;

    private String srcDocCode;

    private String orderSn;
    
    private String unitCode;
    
    private String day;
    
    private String month;
    
    private String weekFrom;
    
    private String weekTo;

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

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType == null ? null : docType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
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

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }
    
	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
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

	@Override
	public String toString() {
		return "WnBean [id=" + id + ", docCode=" + docCode + ", docDate="
				+ docDate + ", docState=" + docState + ", docType=" + docType
				+ ", content=" + content + ", remark=" + remark
				+ ", cancelDate=" + cancelDate + ", replyContent="
				+ replyContent + ", createUser=" + createUser + ", createDate="
				+ createDate + ", lastModifiedUser=" + lastModifiedUser
				+ ", lastModifiedDate=" + lastModifiedDate + ", srcDocType="
				+ srcDocType + ", srcDocCode=" + srcDocCode + ", orderSn="
				+ orderSn + ", unitCode=" + unitCode + ", day=" + day
				+ ", month=" + month + ", weekFrom=" + weekFrom + ", weekTo="
				+ weekTo + "]";
	}
    
}