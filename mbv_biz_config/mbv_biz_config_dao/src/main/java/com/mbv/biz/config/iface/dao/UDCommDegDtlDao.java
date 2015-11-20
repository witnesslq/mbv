package com.mbv.biz.config.iface.dao;

import java.util.List;

import com.mbv.comm.bean.VpDegDtlEntity;

public interface UDCommDegDtlDao {

	int deleteByPrimaryKey(Long id);

    int insert(VpDegDtlEntity record);

    int insertSelective(VpDegDtlEntity record);

    VpDegDtlEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VpDegDtlEntity record);

    int updateByPrimaryKey(VpDegDtlEntity record);
    
    List<VpDegDtlEntity> selectDegDtlByParams(Long id);
    
    int updateQtyById(VpDegDtlEntity record);
	
}
