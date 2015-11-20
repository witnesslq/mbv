package com.mbv.biz.config.service;

import java.util.List;
import java.util.Map;

import com.mbv.biz.config.bean.ChartBean;
import com.mbv.biz.config.bean.DegBean;
import com.mbv.biz.config.bean.OrderBean;
import com.mbv.biz.config.bean.VenRnkBean;
import com.mbv.biz.config.entity.DegEntity;
import com.mbv.biz.config.entity.DegModifyRecordsEntity;
import com.mbv.biz.config.entity.VenRnkEntity;
import com.mbv.common.exception.MbvException;

/**
 * 订单列表
 * @author henry
 *
 */
public interface ReportService {

	
	/**
	 * 
	 * @功能描述：根据条件查询订单记录数
	 * @2015年9月6日
	 * @param
	 * @version
	 */
	public Integer queryByParamsCount(DegBean bean) throws MbvException;
	
	public List<OrderBean> queryOrderByParamsForDownload(DegBean bean) throws MbvException;
	
	/**
	 * @功能描述： 根据条件查询订单信息
	 * @2015年9月6日
	 * @param
	 * @version
	 */
	public List<OrderBean> queryOrderByParams(DegBean bean) throws MbvException;
	
	public List<OrderBean> queryOrderByParamsDetail(DegBean bean) throws MbvException;
	
	public DegBean queryByParamsTotal(DegBean bean) throws MbvException;
	
	public OrderBean queryOrderByCode(String docCode) throws MbvException;
	
	public List<DegModifyRecordsEntity> queryDegModifyRecordsByCode(String vpDegCode) throws MbvException;
	
	public boolean updateOrderInfo(DegBean bean) throws MbvException;
	
	public List<VenRnkEntity> queryVenRnkByParams(VenRnkBean bean, int offset, int limit) throws MbvException;
	
	public List<VenRnkEntity> queryVenRnkList(VenRnkBean bean) throws MbvException;
	
	public Integer queryVenkRnkParentCount(VenRnkBean bean) throws MbvException;
	
	public Integer queryRnkByParamsCount(VenRnkBean bean) throws MbvException;
	
	public List<ChartBean> queryProByProNumAndDay(VenRnkBean bean) throws MbvException;
	
	public List<ChartBean> queryProByProNumAndWeek(VenRnkBean bean) throws MbvException;
	
	public List<ChartBean> queryProByProNumAndMonth(VenRnkBean bean) throws MbvException;
	
	public List<ChartBean> queryProByProNumAndDaySKU(VenRnkBean bean) throws MbvException;
	
	public List<ChartBean> queryProByProNumAndWeekSKU(VenRnkBean bean) throws MbvException;
	
	public List<ChartBean> queryProByProNumAndMonthSKU(VenRnkBean bean) throws MbvException;
	
	public DegEntity selectByPrimaryKey(Long id) throws MbvException;
	
}
