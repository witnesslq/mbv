package com.mbv.inventory.service;

import java.util.List;
import java.util.Map;

import com.mbv.common.exception.MbvException;
import com.mbv.inventory.bean.DeliveryBean;
import com.mbv.inventory.bean.GoodBean;
import com.mbv.inventory.bean.ResultBean;
import com.mbv.inventory.bean.VpDegBean;
import com.mbv.inventory.entity.DegDtlEntity;
import com.mbv.inventory.entity.DeliveryEntity;
import com.mbv.inventory.entity.VpSenderEntity;


/**
 * 
 * @描述 发货单
 * @date 2015年9月6日
 */
public interface DeliveryService {
	
	/**
	 * @功能描述： 根据条件查询发货记录信息
	 * @2015年9月6日
	 * @param
	 * @version
	 */
	public List<VpDegBean> queryOrderByParams(Map<String, Object> paramsMap) throws MbvException;
	
	/**
	 * 
	 * @功能描述：根据条件查询发货单记录数
	 * @2015年9月6日
	 * @param
	 * @version
	 */
	public Integer queryByParamsCount(Map<String, Object> paramsMap) throws MbvException;
	
	//发货
	public ResultBean send(Long degId) throws MbvException,Exception; 
	
	//查询订单下商品
	public List<GoodBean> queryGoodByParams(Long degId) throws MbvException;
	
	//判断缺货工单是否存在
  	public boolean isExistWn(Map<String, Object> paramsMap) throws MbvException;
	
	//添加缺货工单
	public boolean saveWn(Map<String, Object> paramsMap) throws MbvException,Exception;
	
	//通过主键ID获取原始对象
    public DeliveryEntity getDeliveryById(Long id) throws MbvException;
    
    //通过主键ID获取封装Bean对象
    public DeliveryBean getDeliveryBeanById(Long id) throws MbvException;
	
	//获取多个订单下商品汇总
	public List<GoodBean> queryGoodListByIds(List<Long> degIds) throws MbvException;
	
	//添加发货地址信息
	public boolean saveSender(VpSenderEntity record) throws MbvException;
	
	//修改发货地址信息
  	public boolean updateSender(VpSenderEntity record) throws MbvException;
  	
  	//获取发货地址信息
  	public VpSenderEntity getSendAddressById(int id) throws MbvException;
  	
    //更新发货单收货地址变更状态
  	public boolean updateDelAddressStatus(String docCode) throws MbvException;
  	
    //更新发货单商品变更状态
  	public boolean updateDelGoodStatus(String docCode) throws MbvException;
  	
    //更新收货信息变更状态
  	public boolean isChangeReceiveAddress(String docCode) throws MbvException;
  	
    //通过unitcode获取发货地址信息
  	public VpSenderEntity getSendAddressByUnitCode(String unitCode) throws MbvException;
  	
    //通过warehCode获取发货地址信息
  	public VpSenderEntity getSendAddressByWarehCode(String warehCode) throws MbvException; 
  	
    //判断是否有商品信息变更未处理的数据
  	public boolean isChangeGoodInfo(String docCode) throws MbvException;
  	
    //更新快递单编号
  	public boolean updateExpressCode(Map<String, Object> paramsMap) throws MbvException;
  	
  	//更新快递公司
  	public boolean updateExpress(Map<String, Object> paramsMap) throws MbvException;
  	
  	//更新拣货单打印次数
  	public boolean updatePickPrintCount(List<Long> degIds) throws MbvException;
  	
  	//获取地区编码消息
  	 public String getSystemRegionArea(String region_type, String region_id) throws MbvException;
  	 
  	 //通过warehCode判断是否存在收货信息
 	 public boolean isExistSendAddress(String warehCode) throws MbvException;
 	 
 	public List<DegDtlEntity> getVpDegDtlEntityListByParamMap(Map<String,Object> params) throws MbvException;
	
}
