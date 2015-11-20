package com.mbv.biz.config.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbv.biz.config.bean.ChartBean;
import com.mbv.biz.config.bean.DegBean;
import com.mbv.biz.config.dao.DegEntityDao;
import com.mbv.biz.config.service.ChartService;

/**
 * 主页报表
 * @author henry
 *
 */
@Service("chartService")
public class ChartServiceImpl implements ChartService {
	
	private  Logger log = LoggerFactory.getLogger(ChartServiceImpl.class);
	
	@Autowired
	private DegEntityDao degDao;

	public ChartBean selectDegTotalByUnitCode(DegBean bean) {
		log.info("selectDegTotalByUnitCode.map:"+bean);
		return degDao.selectDegTotalByUnitCode(bean);
	}
	
	public List<ChartBean> selectDegTotalByUnitCodeAndDay(DegBean bean){
		log.info("selectDegTotalByUnitCodeAndDay.map:"+bean);
		return degDao.selectDegTotalByUnitCodeAndDay(bean);
	}
	
	public List<ChartBean> selectDegTotalByUnitCodeAndWeek(DegBean bean){
		log.info("selectDegTotalByUnitCodeAndWeek.map:"+bean);
		return degDao.selectDegTotalByUnitCodeAndWeek(bean);
	}
	
	public List<ChartBean> selectDegTotalByUnitCodeAndMonth(DegBean bean){
		log.info("selectDegTotalByUnitCodeAndMonth.map:"+bean);
		return degDao.selectDegTotalByUnitCodeAndMonth(bean);
	}
	
	public Integer selectDegStandByByUnitCode(String unitCode) {
		log.info("selectDegStandByByUnitCode.unitCode:"+unitCode);
		return degDao.selectDegStandByByUnitCode(unitCode);
	}
	
	public Integer selectDegDoneByUnitCode(String unitCode) {
		log.info("selectDegDoneByUnitCode.unitCode:"+unitCode);
		return degDao.selectDegDoneByUnitCode(unitCode);
	}

}
