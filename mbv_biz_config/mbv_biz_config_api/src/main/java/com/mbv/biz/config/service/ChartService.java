package com.mbv.biz.config.service;

import java.util.List;

import com.mbv.biz.config.bean.ChartBean;
import com.mbv.biz.config.bean.DegBean;

public interface ChartService {

	public ChartBean selectDegTotalByUnitCode(DegBean bean); 
	
	public List<ChartBean> selectDegTotalByUnitCodeAndDay(DegBean bean); 
	
	public List<ChartBean> selectDegTotalByUnitCodeAndWeek(DegBean bean); 
	
	public List<ChartBean> selectDegTotalByUnitCodeAndMonth(DegBean bean); 
	
	public Integer selectDegStandByByUnitCode(String unitCode);
	
	public Integer selectDegDoneByUnitCode(String unitCode);
}
