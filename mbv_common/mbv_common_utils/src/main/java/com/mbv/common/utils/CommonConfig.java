package com.mbv.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommonConfig {

	@Value("${mobile.username}")
    private String mobileUserName;
	
	@Value("${mobile.password}")
    private String mobilePassword;
	
	@Value("${mobile.channel.code}")
    private String mobileChannelCode;
	
	@Value("${mobile.send.type}")
    private String mobileSendType;

	public String getMobileUserName() {
		return mobileUserName;
	}

	public void setMobileUserName(String mobileUserName) {
		this.mobileUserName = mobileUserName;
	}

	public String getMobilePassword() {
		return mobilePassword;
	}

	public void setMobilePassword(String mobilePassword) {
		this.mobilePassword = mobilePassword;
	}

	public String getMobileChannelCode() {
		return mobileChannelCode;
	}

	public void setMobileChannelCode(String mobileChannelCode) {
		this.mobileChannelCode = mobileChannelCode;
	}

	public String getMobileSendType() {
		return mobileSendType;
	}

	public void setMobileSendType(String mobileSendType) {
		this.mobileSendType = mobileSendType;
	}
	
	

}
