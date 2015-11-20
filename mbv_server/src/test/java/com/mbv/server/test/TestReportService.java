package com.mbv.server.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mbv.biz.config.service.impl.SyncReportServiceImpl;

public class TestReportService {

	private ApplicationContext ac;

	@Before
	public void init() {
		String[] locations = { "applicationContext.xml" };
		ac = new ClassPathXmlApplicationContext(locations);
	}
	
	@Test
	public void test(){
		SyncReportServiceImpl syncReportService = (SyncReportServiceImpl) ac.getBean("syncReportService");
		syncReportService.syncProductDetailInfo();
		
		
		
	}
	
}
