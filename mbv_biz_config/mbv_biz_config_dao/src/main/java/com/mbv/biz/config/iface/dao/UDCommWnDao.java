package com.mbv.biz.config.iface.dao;

import com.mbv.comm.bean.VpWnEntity;

public interface UDCommWnDao {
	
	VpWnEntity selectByPrimaryKey(Long id);
	
	int updateTradeResult(VpWnEntity wninfo);
}
