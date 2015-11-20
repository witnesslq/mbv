package com.mbv.server.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mtsbw.soa.udb.common.model.ResultBean;
import com.mtsbw.soa.udb.common.model.param.VpDegDtlInfo;
import com.mtsbw.soa.udb.common.model.param.VpDegInfo;
import com.mtsbw.soa.udb.common.model.param.VpDegRequestInfo;
import com.mtsbw.soa.udb.dubboservice.VpDegOutService;

public class TestDeliveryService {

	private ApplicationContext ac;

	@Before
	public void init() {
		String[] locations = { "applicationContext.xml" };
		ac = new ClassPathXmlApplicationContext(locations);
	}
	
	
	@Test
	//@Ignore
	public void testSend(){
		VpDegOutService vpDegOutService = (VpDegOutService)ac.getBean("vpDegOutService");

		VpDegRequestInfo degRequestInfo = new VpDegRequestInfo();
    	VpDegInfo degInfo = new VpDegInfo();
    	degInfo.setDoc_code("0000001");
    	degInfo.setDoc_state("1");
    	List<VpDegDtlInfo> degDtlInfo = new ArrayList<VpDegDtlInfo>();
		VpDegDtlInfo dtlInfo = new VpDegDtlInfo();
		dtlInfo.setProd_num("11019901047");
		dtlInfo.setQty(1.0d);
		degDtlInfo.add(dtlInfo);
		degRequestInfo.setDegInfo(degInfo);
		degRequestInfo.setDegDtlInfo(degDtlInfo);
		long startTime=System.currentTimeMillis();
		//执行方法
		try{
			ResultBean resultBean = vpDegOutService.checkCanOut(degRequestInfo);
			long endTime=System.currentTimeMillis();
			float excTime=(float)(endTime-startTime)/1000;
			System.out.println("执行时间："+excTime+"s");
			if(resultBean ==null || !resultBean.isResult()) {
				System.out.println("判断：不可出库");
			}else{
				System.out.println("可以出库");
			}
		}catch(Exception e){
			long endTime=System.currentTimeMillis();
			float excTime=(float)(endTime-startTime)/1000;
			System.out.println("执行时间："+excTime+"s");
		}
	}
	
}
