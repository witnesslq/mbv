package com.mbv.biz.config.mq.service.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.mbv.biz.config.mq.service.MqWnService;

/**
 * mq服务接口
 * @author henry
 *
 */
@Component
public class MqWnServiceImpl implements MqWnService{
	
	private  Logger log = LoggerFactory.getLogger(MqWnServiceImpl.class);
	
	private JmsTemplate jmsTemplate;  
    private Destination destination; 
	
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public void sendOrder(final String content){
		log.info("content: " + content);
		jmsTemplate.send(  
	            destination,  
	            new MessageCreator(){  
	                public Message createMessage(Session session) throws JMSException {  
	                    return session.createTextMessage(content);  
	                }  
	            }  
	        );  
	}
}
