package com.mbv.inventory.dao;

import java.util.List;
import java.util.Map;

import com.mbv.inventory.bean.DeliveryBean;
import com.mbv.inventory.entity.DeliveryEntity;

public interface DeliveryEntityDao {
	
    int deleteByPrimaryKey(Long id);

    int insert(DeliveryEntity record);

    int insertSelective(DeliveryEntity record);

    DeliveryEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeliveryEntity record);

    int updateByPrimaryKey(DeliveryEntity record);
    
    DeliveryEntity selectByDocCode(String docCode);
    
    //更改发货单状态
    public int updateStatusByPrimaryKey(Map<String,Object> parmasMap);
    
    /**
	 * @功能描述：根据条件查询发货单记录信息
	 */
	List<DeliveryBean> selectByParams(Map<String, Object> paramsMap);
	
	/**
     * @功能描述：根据条件查询发货单记录数
     */
    Integer selectByParamsCount(Map<String, Object> paramsMap);
    
    /**
     * @功能描述：更新快递单号
     */
    public int updateExpressCode(Map<String, Object> paramsMap);
    
    public int updateExpress(Map<String, Object> paramsMap);
    
    /**
     * @功能描述：更新拣货单打印次数
     */
   	public int updatePickPrintCount(List<Long> degIds);
   	
   	public DeliveryBean selectDeliveryBeanById(Long id);
   	
}