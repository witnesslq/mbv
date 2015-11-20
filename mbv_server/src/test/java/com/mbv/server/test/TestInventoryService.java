package com.mbv.server.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import com.mbv.inventory.bean.InventoryBean;
import com.mbv.inventory.bean.VpGdnBean;
import com.mbv.inventory.bean.VpGrnBean;
import com.mbv.inventory.entity.InventoryEntity;
import com.mbv.inventory.entity.VpGdnDtlEntity;
import com.mbv.inventory.entity.VpGdnEntity;
import com.mbv.inventory.entity.VpGrnDtlEntity;
import com.mbv.inventory.entity.VpGrnEntity;
import com.mbv.inventory.service.InventoryService;
import com.mbv.inventory.service.VpGdnService;
import com.mbv.inventory.service.VpGrnService;

public class TestInventoryService {

	private ApplicationContext ac;

	@Before
	public void init() {
		String[] locations = { "applicationContext.xml" };
		ac = new ClassPathXmlApplicationContext(locations);
	}
	
	@Test
	@Ignore
	public void testInsertVpGdnDtlEntityBatch(){
		VpGdnService vpGdnService = (VpGdnService) ac.getBean("vpGdnService");
		VpGdnEntity vge = new VpGdnEntity();
		vge.setDocCode("123333");
		vge.setUnitCode("123");
		vge.setWarehCode("123");
		
		List<VpGdnDtlEntity> details = new ArrayList<VpGdnDtlEntity>();
		
		VpGdnDtlEntity vgde = new VpGdnDtlEntity();
		vgde.setProdNum("11111111111");
		vgde.setQty(new BigDecimal(10));
		
		VpGdnDtlEntity vgde2 = new VpGdnDtlEntity();
		vgde2.setProdNum("22222222222");
		vgde2.setQty(new BigDecimal(10));
		
		details.add(vgde);
		details.add(vgde2);
		
		boolean flag = vpGdnService.addVpGdn(vge, details);
		if(flag){
			System.out.println("OK");
		}else{
			System.out.println("Failed");
		}
	}
	
	@Test
	@Ignore
	public void testAddVpGdn(){
		VpGdnService vpGdnService = (VpGdnService) ac.getBean("vpGdnService");
		VpGdnEntity vge = new VpGdnEntity();
		vge.setDocCode("123333");
		vge.setUnitCode("123");
		vge.setWarehCode("123");
		
		List<VpGdnDtlEntity> details = new ArrayList<VpGdnDtlEntity>();
		
		VpGdnDtlEntity vgde = new VpGdnDtlEntity();
		vgde.setProdNum("11111111111");
		vgde.setQty(new BigDecimal(10));
		
		VpGdnDtlEntity vgde2 = new VpGdnDtlEntity();
		vgde2.setProdNum("22222222222");
		vgde2.setQty(new BigDecimal(10));
		
		details.add(vgde);
		details.add(vgde2);
		
		boolean flag = vpGdnService.addVpGdn(vge, details);
		if(flag){
			System.out.println("OK");
		}else{
			System.out.println("Failed");
		}
	}
	
	@Test
	@Ignore
	public void testGetGdnMaxDocCode(){
		VpGdnService vpGdnService = (VpGdnService) ac.getBean("vpGdnService");
		String docCode = vpGdnService.getVpGdnMaxDocCode();
		System.out.println("docCode:" + docCode);
	}
	
	@Test
//	@Ignore
	public void testGetMaxDocCode(){
		VpGrnService vpGrnService = (VpGrnService) ac.getBean("vpGrnService");
		String docCode = vpGrnService.getVpGrnMaxDocCode();
		System.out.println("docCode:" + docCode);
	}
	
	@Test
	@Ignore
	public void testAddVpGrn(){
		VpGrnService vpGrnService = (VpGrnService) ac.getBean("vpGrnService");
		VpGrnEntity vge = new VpGrnEntity();
		vge.setDocCode("123333");
		vge.setUnitCode("123");
		vge.setWarehCode("123");
		
		List<VpGrnDtlEntity> details = new ArrayList<VpGrnDtlEntity>();
		
		VpGrnDtlEntity vgde = new VpGrnDtlEntity();
		vgde.setProdNum("11111111111");
		vgde.setQty(new BigDecimal(10));
		
		VpGrnDtlEntity vgde2 = new VpGrnDtlEntity();
		vgde2.setProdNum("22222222222");
		vgde2.setQty(new BigDecimal(10));
		
		details.add(vgde);
		details.add(vgde2);
		
		boolean flag = vpGrnService.addVpGrn(vge, details);
		if(flag){
			System.out.println("OK");
		}else{
			System.out.println("Failed");
		}
	}
	
	@Test
	@Ignore
	public void testGetVpGdnByParams(){
		VpGdnService vpGdnService = (VpGdnService) ac.getBean("vpGdnService");
		VpGdnBean bean = new VpGdnBean();
		bean.setDocCode("123333");
		List<VpGdnEntity> list = vpGdnService.queryByParams(bean, 1, 1);
		if(CollectionUtils.isEmpty(list)){
			System.out.println("null");
		}else{
			System.out.println(list.get(0).getDocCode() + "," + list.get(0).getDocDate());
		}
	}
	
	@Test
	@Ignore
	public void testGetVpGdnByParamsCount(){
		VpGdnService vpGdnService = (VpGdnService) ac.getBean("vpGdnService");
		VpGdnBean bean = new VpGdnBean();
		bean.setDocCode("654321");
		int count = vpGdnService.queryByParamsCount(bean);
		System.out.println("count:" + count);
	}
	
	@Test
	@Ignore
	public void testGetVpGrnByParams(){
		VpGrnService vpGrnService = (VpGrnService) ac.getBean("vpGrnService");
		VpGrnBean bean = new VpGrnBean();
		bean.setDocCode("123456");
		List<VpGrnEntity> list = vpGrnService.queryByParams(bean, 10, 10);
		if(CollectionUtils.isEmpty(list)){
			System.out.println("null");
		}else{
			System.out.println(list.get(0).getDocCode() + "," + list.get(0).getDocDate());
		}
	}
	
	@Test
	@Ignore
	public void testGetVpGrnByParamsCount(){
		VpGrnService vpGrnService = (VpGrnService) ac.getBean("vpGrnService");
		VpGrnBean bean = new VpGrnBean();
		bean.setDocCode("123456");
		int count = vpGrnService.queryByParamsCount(bean);
		System.out.println("count:" + count);
	}
	
	@Test
	@Ignore
	public void testQueryByParams(){
		InventoryService inventoryService = (InventoryService) ac.getBean("inventoryService");
		InventoryBean inventoryBean = new InventoryBean();
		inventoryBean.setProdNum("12345678901");
		inventoryBean.setWarehCode("001");
		List<InventoryEntity> inventoryEntityList = inventoryService.queryByParams(inventoryBean, 10, 10);
		if(CollectionUtils.isEmpty(inventoryEntityList)){
			System.out.println("null");
		}else{
			System.out.println(inventoryEntityList.get(0).getProdNum() + "," + inventoryEntityList.get(0).getStkOnHand() + "," + inventoryEntityList.get(0).getLastModifiedDate());
		}
	}
	
	@Test
	@Ignore
	public void testQueryByParamsCount(){
		InventoryService inventoryService = (InventoryService) ac.getBean("inventoryService");
		InventoryBean inventoryBean = new InventoryBean();
		inventoryBean.setProdNum("12345678901");
		inventoryBean.setWarehCode("001");
		List<InventoryEntity> inventoryEntityList = inventoryService.queryByParams(inventoryBean, 10, 10);
		if(CollectionUtils.isEmpty(inventoryEntityList)){
			System.out.println("null");
		}else{
			System.out.println(inventoryEntityList.size());
		}
	}
}
