package com.mbv.biz.config.iface.dao;

import java.util.List;
import java.util.Map;

import com.mbv.comm.bean.VpDegEntity;
import com.mbv.comm.bean.VpDegModifyEntity;


public interface UDCommDegDao {

	int deleteByPrimaryKey(Long id);

    int insert(VpDegEntity record);

    int insertSelective(VpDegEntity record);

    VpDegEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VpDegEntity record);

    int updateByPrimaryKey(VpDegEntity record);
    
    Integer selectByParamsCount(Map<String, Object> paramsMap);
    
    int updateDegInfo(Map<String, Object> paramsMap); 
    
    VpDegEntity selectByDocCode(String docCode); 
    
    int updateDegRcvInfoByOrderSn(VpDegModifyEntity modifyInfo);
    
    List<VpDegEntity> selectByOrderSn(String orderSn);
    
    int updateDetTtlQtyById(VpDegEntity degInfo);
}
