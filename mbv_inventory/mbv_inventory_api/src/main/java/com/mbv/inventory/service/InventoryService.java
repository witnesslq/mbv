package com.mbv.inventory.service;

import java.util.List;

import com.mbv.common.exception.MbvException;
import com.mbv.inventory.bean.InventoryBean;
import com.mbv.inventory.bean.ProductInfo;
import com.mbv.inventory.bean.WarehProdTranBean;
import com.mbv.inventory.entity.InventoryEntity;


/**
 * 
 * @描述 库存增减
 * @date 2015年9月6日
 */
public interface InventoryService {
	
	/**
	 * @功能描述： 根据条件查询多库存信息
	 * @2015年9月6日
	 * @param
	 * @version
	 */
	public List<InventoryEntity> queryByParams(InventoryBean bean,int offset,int limit) throws MbvException;
	
	/**
	 * @功能描述： 根据条件查询库存信息带商品信息
	 * @2015年9月6日
	 * @param
	 * @version
	 */
	public List<InventoryBean> queryInventoryBeanByParams(InventoryBean bean,int offset,int limit) throws MbvException;
	
	/**
	 * 
	 * @功能描述：根据条件查询库存记录数
	 * @2015年9月6日
	 * @param
	 * @version
	 */
	public Integer queryByParamsCount(InventoryBean bean) throws MbvException;

	/**
	 * @功能描述： 根据条件查询出入库事务记录信息
	 * @2015年9月6日
	 * @param
	 * @version
	 */
	public List<WarehProdTranBean> queryWarehProdTranByParams(
			WarehProdTranBean bean, int offset, int limit);
	
	/**
	 * 
	 * @功能描述：根据条件查询出入库事务记录数
	 * @2015年9月6日
	 * @param
	 * @version
	 */
	public Integer queryWarehProdTranByParamsCount(WarehProdTranBean bean) throws MbvException;

	/**
	 * 插入库存表
	 * @param entity
	 * @return
	 * @throws MbvException
	 * @throws Exception
	 */
	public Integer insertInventoryEntity(InventoryEntity entity) throws MbvException,Exception;
	
	/**
	 * 更新库存表
	 * @param entity
	 * @return
	 * @throws MbvException
	 * @throws Exception
	 */
	public Integer updateInventoryEntity(InventoryEntity entity) throws MbvException,Exception;
	
	/**
	 * 根据主键ID获取库存信息
	 * @param id
	 * @return
	 */
	public InventoryEntity selectByPrimaryKey(Long id);
	
	/**
	 * 根据商品编码获取商品信息
	 * @param prodType
	 * @param prodCode
	 * @return
	 */
	public ProductInfo getProductByProdCode(String prodType, String prodCode) throws MbvException;
	
	/**
	 * 更新统一库存系统商品库存数
	 * @return
	 */
	public boolean setProdStock();

	/**
	 * 根据参数获取事务信息
	 * @param bean
	 * @return
	 */
	public WarehProdTranBean getVpWarehProdTranByBean(WarehProdTranBean bean);
	
	/**
	 * 根据SKU编码查询商品信息
	 * @param prodNum
	 * @return
	 */
	public ProductInfo getProdInfoByProdNum(String prodNum);
	
	/**
	 * 判断字符串是否为数字
	 * @param num
	 * @return
	 */
	public boolean validateNum(String num);
	
}
