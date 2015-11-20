package com.mbv.biz.config.dao;

import java.util.List;

import com.mbv.biz.config.bean.DegDtlBean;
import com.mbv.biz.config.entity.DegDtlEntity;
/**
 * @类描述：库存接口
 * @2015年9月6日
 * @version
 */
public interface DegDtlEntityDao {
   
    int deleteByPrimaryKey(Long id);

    int insert(DegDtlEntity record);

    int insertSelective(DegDtlEntity record);

    DegDtlEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DegDtlEntity record);

    int updateByPrimaryKey(DegDtlEntity record);
    
    List<DegDtlBean> selectDegDtlByParams(Long id);
	
}