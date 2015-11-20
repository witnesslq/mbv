package com.mbv.biz.config.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.mbv.biz.config.bean.WnBean;
import com.mbv.biz.config.entity.WnEntity;

public interface WnEntityDao {
    int deleteByPrimaryKey(Long id);

    int insert(WnEntity record);

    int insertSelective(WnEntity record);

    WnEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WnEntity record);

    int updateByPrimaryKey(WnEntity record);
    
    /**
	 * @功能描述：根据条件查询多货主库存记录信息
	 * @2015年9月6日
	 * @param
	 * @version
	 */
	List<WnBean> selectByParams(WnBean bean,RowBounds rb);
	
	/**
     * @功能描述：根据条件查询库存记录数
     * @2015年9月6日
     * @param
     * @version
     */
    Integer selectByParamsCount(WnBean bean);
    
    WnEntity selectByDocCode(String docCode);
    
    String selectMaxDocCode(String type);
    
    public Integer selectExitsWnCount(WnBean bean);
    
}