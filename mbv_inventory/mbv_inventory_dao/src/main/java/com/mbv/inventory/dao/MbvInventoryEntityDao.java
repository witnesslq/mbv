package com.mbv.inventory.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.mbv.inventory.bean.InventoryBean;
import com.mbv.inventory.entity.InventoryEntity;
/**
 * @类描述：库存接口
 * @2015年9月6日
 * @version
 */
public interface MbvInventoryEntityDao {
   
    int deleteByPrimaryKey(Long id);

    int insert(InventoryEntity record);

    int insertSelective(InventoryEntity record);

    InventoryEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InventoryEntity record);

    int updateByPrimaryKey(InventoryEntity record);

	/**
	 * @功能描述：根据条件查询多货主库存记录信息
	 * @2015年9月6日
	 * @param
	 * @version
	 */
	List<InventoryEntity> selectByParams(InventoryBean bean,RowBounds rb);
	
	/**
     * @功能描述：根据条件查询库存记录数
     * @2015年9月6日
     * @param
     * @version
     */
    Integer selectByParamsCount(InventoryBean bean);
    
    String getLastModifiedDate(String date);

}