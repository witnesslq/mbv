package com.mbv.inventory.service;

import java.util.List;

import com.mbv.common.exception.MbvException;
import com.mbv.inventory.bean.VpGdnBean;
import com.mbv.inventory.entity.VpGdnEntity;
import com.mbv.inventory.entity.VpGdnDtlEntity;

/**
 * @描述  出库单接口
 * @author Administrator
 * @Date 2015年9月9日
 *
 */
public interface VpGdnService {
	
	/**
	 * @功能描述： 根据条件查询入库单信息
	 * @2015年9月9日
	 * @param
	 * @version
	 */
	public List<VpGdnEntity> queryByParams(VpGdnBean bean,int offset,int limit) throws MbvException;
	
	/**
	 * 
	 * @功能描述：根据条件查询入库单数
	 * @2015年9月9日
	 * @param
	 * @version
	 */
	public Integer queryByParamsCount(VpGdnBean bean) throws MbvException;
	
	/**
	 * @功能描述：插入出库单及出库单明细
	 * @2015年9月9日
	 * @param VpGdnEntity
	 * @param details
	 * @return
	 * @throws MbvException
	 */
	public boolean addVpGdn(VpGdnEntity VpGdnEntity,List<VpGdnDtlEntity> details, String mode) throws MbvException,Exception;
	
	/**
	 * @功能描述：获取最大入库单编码
	 * @return
	 */
	public String getVpGdnMaxDocCode(String seqName) throws MbvException;

	/**
	 * @功能描述：根据id或者doc_code获取出库单
	 * @param entity
	 * @return
	 */
	public VpGdnEntity getVpGdnByEntity(VpGdnEntity entity) throws MbvException;

	/**
	 * 根据entity参数获取入库单
	 * @param entity
	 * @return
	 * @throws MbvException
	 */
	public VpGdnBean getVpGdnBeanByEntity(VpGdnEntity entity) throws MbvException;
	
	/**
	 * 更新出库单
	 * @param entity
	 * @param details
	 * @return
	 */
	public boolean updateVpGdn(VpGdnEntity entity, List<VpGdnDtlEntity> details) throws MbvException,Exception;

	/**
	 * 删除出库单
	 * @param vpGdnEntityList
	 * @return
	 */
	public boolean deleteVpGdnBatch(List<VpGdnEntity> vpGdnEntityList) throws MbvException,Exception;
	
	/**
	 * 删除出库单
	 * @param vpGdnEntity
	 * @return
	 */
	public boolean deleteVpGdnByEntity(VpGdnEntity vpGdnEntity) throws MbvException,Exception;
	
	public String validate(VpGdnEntity entity) throws MbvException;
	
	public String validateDetail(VpGdnEntity entity, List<VpGdnDtlEntity> details) throws MbvException;
}
