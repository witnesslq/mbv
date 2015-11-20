package com.mbv.inventory.dao;

import com.mbv.inventory.entity.VpSenderEntity;

public interface VpSenderEntityDao {
    
    int deleteByPrimaryKey(Integer id);
    
    int insert(VpSenderEntity record);
    
    int insertSelective(VpSenderEntity record);
    
    VpSenderEntity selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(VpSenderEntity record);

    int updateByPrimaryKey(VpSenderEntity record);
    
    public VpSenderEntity selectByUnitCode(String  unitCode);
    
    public VpSenderEntity selectByWarehCode (String warehCode);
    
    public int getCountByWarehCode(String warehCode);
}