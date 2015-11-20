package com.mbv.server.test;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mtsbw.soa.udb.common.model.ResultBean;
import com.mtsbw.soa.udb.dubboservice.VpDegOutService;

public class TestNewErpOrgService {

	private Logger log = LoggerFactory.getLogger(TestNewErpOrgService.class);
	private ApplicationContext ac;

	@Before
	public void init() {
		String[] locations = { "applicationContext.xml" };
		ac = new ClassPathXmlApplicationContext(locations);
	}
	
	@Test
//	@Ignore
	public void testGetGdnMaxDocCode(){
		
		VpDegOutService vpDegOutService = (VpDegOutService)ac.getBean("vpDegOutService");
		ResultBean resultBean = vpDegOutService.checkCanOut(null);
		
    	if(resultBean ==null || !resultBean.isResult()) {
    		System.out.println("判断：不可出库");
    	}else{
    		System.out.println("可以出库");
    	}
	}
}
