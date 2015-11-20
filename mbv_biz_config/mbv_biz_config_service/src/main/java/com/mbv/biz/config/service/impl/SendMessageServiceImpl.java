package com.mbv.biz.config.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbv.biz.config.service.SendMessageService;
import com.mbv.common.utils.CommonConfig;
import com.metersbonwe.sms.bean.Message;
import com.metersbonwe.sms.bean.State;
import com.metersbonwe.sms.send.api.SMSService;

/**
 * 手机发送短信
 * @author henry
 *
 */
@Service("sendMessageService")
public class SendMessageServiceImpl implements SendMessageService {
	
	private  Logger log = LoggerFactory.getLogger(SendMessageServiceImpl.class);
	
	@Autowired
	SMSService smsService;
	
	@Autowired
	CommonConfig config;

	@Override
	public void sendMessage(String mobile, String msgContent) {
	  log.info("mobile: "+mobile+",content:"+msgContent);
	  log.info("mobile.username: " + config.getMobileUserName()+",mobile.password: "+config.getMobilePassword()+"," +
	  		"mobile.channel.code:"+config.getMobileChannelCode()+",mobile.send.type:"+config.getMobileSendType());
      com.metersbonwe.sms.bean.User user = new com.metersbonwe.sms.bean.User();
//      user.setUsername("mobile001");
//      user.setPassword("mobile001");
      user.setUsername(config.getMobileUserName());
      user.setPassword(config.getMobilePassword());
      Message sendMessage = new Message();
//      sendMessage.setChannelCode("A02588S001");
//      sendMessage.setSendType("MobileRegister");
      sendMessage.setChannelCode(config.getMobileChannelCode());
      sendMessage.setSendType(config.getMobileSendType());
      sendMessage.setMsgContent(msgContent);
      sendMessage.setPhoneNO(mobile);
      State state_bean = smsService.send(user, sendMessage);
      state_bean.getState();
      log.info("status: " + state_bean.getState());
	}

}
