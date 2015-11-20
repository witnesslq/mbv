package com.mbv.inventory.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mbv.comm.api.SyncInventoryService;
import com.mbv.inventory.service.InventoryService;

@Service("syncInventoryService")
public class SyncInventoryServiceImpl implements SyncInventoryService {
	
	private Logger log = LoggerFactory.getLogger(SyncInventoryServiceImpl.class);

	@Resource
	private InventoryService inventoryService;
	
	@Override
	public void syncVpWarehProdInventory() {
		boolean flag = inventoryService.setProdStock();
		if(flag){
			log.info("同步库存成功");
		}else{
			log.info("同步库存失败");
		}
	}

}
