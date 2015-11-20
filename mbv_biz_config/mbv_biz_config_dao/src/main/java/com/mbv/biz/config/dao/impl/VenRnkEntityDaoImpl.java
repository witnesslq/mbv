/*
 * Copyright 2015 Metersbonwe.com All right reserved. This software is the
 * confidential and proprietary information of Metersbonwe.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Metersbonwe.com.
 */
package com.mbv.biz.config.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.biz.config.bean.ChartBean;
import com.mbv.biz.config.bean.VenRnkBean;
import com.mbv.biz.config.dao.VenRnkEntityDao;
import com.mbv.biz.config.entity.VenRnkEntity;

/**
 * @类描述：库存dao
 * @2015年9月6日
 * @version
 */
@Repository("venRnkDao")
public class VenRnkEntityDaoImpl implements VenRnkEntityDao{
    /**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.biz.config.dao.impl.VenRnkEntityDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    


	@Override
	public List<VenRnkEntity> selectVenkRnkByParams(VenRnkBean bean,RowBounds rb) {
		return this.localSqlSession.selectList(NAMESPACE+"selectVenkRnkByParams", bean, rb);
	}



	@Override
	public Integer selectRnkByParamsCount(VenRnkBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectRnkByParamsCount", bean);
	}



	@Override
	public List<ChartBean> selectProByProNumAndDay(VenRnkBean bean) {
		return this.localSqlSession.selectList(NAMESPACE+"selectProByProNumAndDay", bean);
	}



	@Override
	public List<ChartBean> selectProByProNumAndWeek(VenRnkBean bean) {
		return this.localSqlSession.selectList(NAMESPACE+"selectProByProNumAndWeek", bean);
	}



	@Override
	public List<ChartBean> selectProByProNumAndMonth(VenRnkBean bean) {
		return this.localSqlSession.selectList(NAMESPACE+"selectProByProNumAndMonth", bean);
	}
	


	@Override
	public List<VenRnkEntity> selectVenkRnkParent(VenRnkBean bean) {
		return this.localSqlSession.selectList(NAMESPACE+"selectVenkRnkParent", bean);
	}



	@Override
	public List<VenRnkEntity> selectVenkRnkChild(VenRnkBean bean) {
		return this.localSqlSession.selectList(NAMESPACE+"selectVenkRnkChild", bean);
	}



	@Override
	public Integer selectVenkRnkParentCount(VenRnkBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectVenkRnkParentCount", bean);
	}



	@Override
	public List<VenRnkEntity> selectRnkParentByDay(VenRnkBean bean) {
		return this.localSqlSession.selectList(NAMESPACE+"selectRnkParentByDay", bean);
	}




	@Override
	public List<VenRnkEntity> selectRnkChildByDay(VenRnkBean bean) {
		return this.localSqlSession.selectList(NAMESPACE+"selectRnkChildByDay", bean);
	}



	@Override
	public List<ChartBean> selectProByProNumAndDaySKU(VenRnkBean bean) {
		return this.localSqlSession.selectList(NAMESPACE+"selectProByProNumAndDaySKU", bean);
	}



	@Override
	public List<ChartBean> selectProByProNumAndWeekSKU(VenRnkBean bean) {
		return this.localSqlSession.selectList(NAMESPACE+"selectProByProNumAndWeekSKU", bean);
	}



	@Override
	public List<ChartBean> selectProByProNumAndMonthSKU(VenRnkBean bean) {
		return this.localSqlSession.selectList(NAMESPACE+"selectProByProNumAndMonthSKU", bean);
	}



	@Override
	public List<String> selectProNumClsList(RowBounds rb) {
		return this.localSqlSession.selectList(NAMESPACE+"selectProNumClsList", rb);
	}



	@Override
	public Integer selectProNumClsListCount() {
		return this.localSqlSession.selectOne(NAMESPACE+"selectProNumClsListCount");
	}





	
}
