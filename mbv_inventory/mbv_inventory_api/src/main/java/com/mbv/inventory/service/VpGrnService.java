package com.mbv.inventory.service;

import java.util.List;

import com.mbv.common.exception.MbvException;
import com.mbv.inventory.bean.ProductInfo;
import com.mbv.inventory.bean.VpGrnBean;
import com.mbv.inventory.entity.VpGrnDtlEntity;
import com.mbv.inventory.entity.VpGrnEntity;

/**
 * @描述  入库单接口
 * @author Administrator
 * @Date 2015年9月9日
 *
 */
public interface VpGrnService {
	/**
	 * @功能描述： 根据条件查询入库单信息
	 * @2015年9月9日
	 * @param
	 * @version
	 */
	public List<VpGrnEntity> queryByParams(VpGrnBean bean,int offset,int limit) throws MbvException;
	
	/**
	 * 
	 * @功能描述：根据条件查询入库单数
	 * @2015年9月9日
	 * @param
	 * @version
	 */
	public Integer queryByParamsCount(VpGrnBean bean) throws MbvException;
	
	/**
	 * @功能描述：插入入库单及入库单明细
	 * @2015年9月9日
	 * @param vpGrnEntity
	 * @param details
	 * @return
	 * @throws MbvException,Exception
	 */
	public boolean addVpGrn(VpGrnEntity vpGrnEntity,List<VpGrnDtlEntity> details) throws MbvException,Exception;
	
	/**
	 * @功能描述：获取最大入库单编码
	 * @return
	 */
	public String getVpGrnMaxDocCode(String seqName) throws MbvException;
	
	/**
	 * 根据entity参数获取入库单
	 * @param entity
	 * @return
	 * @throws MbvException
	 */
	public VpGrnBean getVpGrnBeanByEntity(VpGrnEntity entity) throws MbvException;

	/**
	 * 根据entity参数获取入库单
	 * @param entity
	 * @return
	 * @throws MbvException
	 */
	public VpGrnEntity getVpGrnByEntity(VpGrnEntity entity) throws MbvException;
	
	/**
	 * 更新入库单
	 * @param entity     入库单
	 * @param details    入库单明细
	 * @return
	 * @throws MbvException
	 * @throws Exception
	 */
	public boolean updateVpGrn(VpGrnEntity entity, List<VpGrnDtlEntity> details) throws MbvException,Exception;

	/**
	 * 根据入库单id批量删除入库单
	 * @param vpGrnEntityList
	 * @return
	 */
	public boolean deleteVpGrnBatch(List<VpGrnEntity> vpGrnEntityList) throws MbvException,Exception;
	
	/**
	 * 根据入库单id删除入库单
	 * @param id
	 * @return
	 */
	public boolean deleteVpGrnById(VpGrnEntity entity) throws MbvException,Exception;
	
	/**
	 * 验证出库单
	 * @param entity
	 * @return
	 * @throws MbvException
	 */
	public String validate(VpGrnEntity entity) throws MbvException;
	
	/**
	 * 验证出库单明细
	 * @param entity
	 * @param details
	 * @return
	 * @throws MbvException
	 */
	public String validateDetail(VpGrnEntity entity,List<VpGrnDtlEntity> details) throws MbvException;

}
