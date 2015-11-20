package com.mbv.biz.config.dao;

import java.util.List;
import com.mbv.biz.config.bean.ChartBean;
import com.mbv.biz.config.bean.DegBean;
import com.mbv.biz.config.entity.DegEntity;

public interface DegEntityDao {
    int deleteByPrimaryKey(Long id);

    int insert(DegEntity record);

    int insertSelective(DegEntity record);

    DegEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DegEntity record);

    int updateByPrimaryKey(DegEntity record);
    
    ChartBean selectDegTotalByUnitCode(DegBean bean);
    
    List<ChartBean> selectDegTotalByUnitCodeAndDay(DegBean bean);
    
    public List<ChartBean> selectDegTotalByUnitCodeAndWeek(DegBean bean);
    
    public List<ChartBean> selectDegTotalByUnitCodeAndMonth(DegBean bean);
    
    public List<DegBean> selectByParamsForDownload(DegBean bean);
    
    public List<DegBean> selectByParams(DegBean bean);
    
    public DegBean selectByParamsTotal(DegBean bean);
	
    Integer selectByParamsCount(DegBean bean);
    
    public DegBean selectByDocCode(String docCode);
    
    public DegBean selectBySrcDocCode(String docCode);
    
    int updateDegInfo(DegBean bean); 
    
    public Integer selectDegStandByByUnitCode(String unitCode);
	
	public Integer selectDegDoneByUnitCode(String unitCode);
	
	public Integer selectDegByOrderSn(String orderSn);
	
	public Integer selectDegByOrderSnAndUnitCode(DegBean bean);
	
	public DegBean selectDegBySku(DegBean bean);
    
}