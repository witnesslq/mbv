package com.mbv.biz.config.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.mbv.biz.config.bean.ChartBean;
import com.mbv.biz.config.bean.VenRnkBean;
import com.mbv.biz.config.entity.VenRnkEntity;

public interface VenRnkEntityDao {
	
    public List<VenRnkEntity> selectVenkRnkByParams(VenRnkBean bean,RowBounds rb);
    
    public Integer selectRnkByParamsCount(VenRnkBean bean);
    
    public List<ChartBean> selectProByProNumAndDay(VenRnkBean bean);
    
    public List<ChartBean> selectProByProNumAndWeek(VenRnkBean bean);
    
    public List<ChartBean> selectProByProNumAndMonth(VenRnkBean bean);
    
    public List<VenRnkEntity> selectVenkRnkParent(VenRnkBean bean);
	
    public List<VenRnkEntity> selectVenkRnkChild(VenRnkBean bean);
    
    public Integer selectVenkRnkParentCount(VenRnkBean bean);
    
    public List<VenRnkEntity> selectRnkParentByDay(VenRnkBean bean);
	
    public List<VenRnkEntity> selectRnkChildByDay(VenRnkBean bean);
    
    
    public List<ChartBean> selectProByProNumAndDaySKU(VenRnkBean bean);
    
    public List<ChartBean> selectProByProNumAndWeekSKU(VenRnkBean bean);
    
    public List<ChartBean> selectProByProNumAndMonthSKU(VenRnkBean bean);
    
    public List<String> selectProNumClsList(RowBounds rb);
    
    public Integer selectProNumClsListCount();
	
    
}