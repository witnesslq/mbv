package com.mbv.common.exception;

public class MbvException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8726907619831132586L;
	
	/**
	 * 异常结果  --jqgrid需要返回result
	 */
	private String status;
	
	/**
	 * 是否异常--前台统一拦截
	 */
	private Boolean error;
	
	/**
	 * 异常原因
	 */
	private String reason;

	public MbvException(String errorCode) {
		super(errorCode);
	}
	
	public MbvException(String errorCode,String errorMsg){
		super(errorCode, errorMsg);
	}
	
	public MbvException(String errorCode, Throwable caused) {
		super(errorCode, caused);
	}

	public MbvException(String errorCode, String errorMsg, Throwable caused) {
		super(errorCode, errorMsg, caused);
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
