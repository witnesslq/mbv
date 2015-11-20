package com.mbv.biz.config.iface.dao;

import com.mbv.biz.config.entity.iface.VpDegModifyRecordEntity;

public interface UDCommDegModifyRecordDao {

	int insert(VpDegModifyRecordEntity record);

    int insertSelective(VpDegModifyRecordEntity record);

    VpDegModifyRecordEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VpDegModifyRecordEntity record);

    int updateByPrimaryKey(VpDegModifyRecordEntity record);
}
