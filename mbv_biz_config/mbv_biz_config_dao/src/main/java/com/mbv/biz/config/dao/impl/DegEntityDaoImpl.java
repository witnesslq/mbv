package com.mbv.biz.config.dao.impl;

import java.util.List;
import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.biz.config.bean.ChartBean;
import com.mbv.biz.config.bean.DegBean;
import com.mbv.biz.config.dao.DegEntityDao;
import com.mbv.biz.config.entity.DegEntity;

/**
 * @类描述：库存dao
 * @2015年9月6日
 * @version
 */
@Repository("degDao")
public class DegEntityDaoImpl implements DegEntityDao {
    /**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "com.mbv.biz.config.dao.impl.DegEntityDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
    
    public int deleteByPrimaryKey(Long id) {
        return this.localSqlSession.delete(NAMESPACE+"deleteByPrimaryKey",id);
    }

    public int insert(DegEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insert",record);
    }

    public int insertSelective(DegEntity record) {
        return this.localSqlSession.insert(NAMESPACE+"insertSelective",record);
    }

    public DegEntity selectByPrimaryKey(Long id) {
    	return this.localSqlSession.selectOne(NAMESPACE+"selectByPrimaryKey",id);
    }

    public int updateByPrimaryKeySelective(DegEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKeySelective",record);
    }

    public int updateByPrimaryKey(DegEntity record) {
        return this.localSqlSession.update(NAMESPACE+"updateByPrimaryKey",record);
    }

	public ChartBean selectDegTotalByUnitCode(DegBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectDegTotalByUnitCode",bean);
	}
	
	public List<ChartBean> selectDegTotalByUnitCodeAndDay(DegBean bean){
		return this.localSqlSession.selectList(NAMESPACE+"selectDegTotalByUnitCodeAndDay",bean);
	}
	
	public List<ChartBean> selectDegTotalByUnitCodeAndWeek(DegBean bean){
		return this.localSqlSession.selectList(NAMESPACE+"selectDegTotalByUnitCodeAndWeek",bean);
	}
	
	public List<ChartBean> selectDegTotalByUnitCodeAndMonth(DegBean bean){
		return this.localSqlSession.selectList(NAMESPACE+"selectDegTotalByUnitCodeAndMonth",bean);
	}

	public List<DegBean> selectByParamsForDownload(DegBean bean) {
		return this.localSqlSession.selectList(NAMESPACE+"selectByParamsForDownload", bean);
	}
	
	public List<DegBean> selectByParams(DegBean bean) {
		return this.localSqlSession.selectList(NAMESPACE+"selectByParams", bean);
	}
	
	public DegBean selectByParamsTotal(DegBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByParamsTotal", bean);
	}

	@Override
	public Integer selectByParamsCount(DegBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByParamsCount",bean);
	}
	
	@Override
	public DegBean selectByDocCode(String docCode) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectByDocCode", docCode);
	}
	
	public DegBean selectBySrcDocCode(String docCode) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectBySrcDocCode", docCode);
	}
	
	public int updateDegInfo(DegBean bean){
		return this.localSqlSession.update(NAMESPACE+"updateDegInfo",bean);
	}
	
	public Integer selectDegStandByByUnitCode(String unitCode) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectDegStandByByUnitCode",unitCode);
	}
	
	public Integer selectDegDoneByUnitCode(String unitCode) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectDegDoneByUnitCode",unitCode);
	}
	
	public Integer selectDegByOrderSn(String orderSn) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectDegByOrderSn",orderSn);
	}
	
	public Integer selectDegByOrderSnAndUnitCode(DegBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectDegByOrderSnAndUnitCode",bean);
	}
	
	public DegBean selectDegBySku(DegBean bean) {
		return this.localSqlSession.selectOne(NAMESPACE+"selectDegBySku", bean);
	}
	
}
