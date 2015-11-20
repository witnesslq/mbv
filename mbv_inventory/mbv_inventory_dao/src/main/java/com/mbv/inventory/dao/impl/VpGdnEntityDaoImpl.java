package com.mbv.inventory.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.inventory.bean.VpGdnBean;
import com.mbv.inventory.dao.VpGdnEntityDao;
import com.mbv.inventory.entity.VpGdnEntity;

/**
 * @类描述：出库单dao实现类
 * @2015年9月6日
 * @version
 */
@Repository("vpGdnEntityDao")
public class VpGdnEntityDaoImpl implements VpGdnEntityDao {

	/**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "mbv.com.mbv.inventory.dao.impl.VpGdnEntityDao.";
	
    @Resource
	private SqlSessionTemplate localSqlSession;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return localSqlSession.delete(NAMESPACE + "deleteByPrimaryKey", id);
	}

	@Override
	public int insert(VpGdnEntity record) {
		return localSqlSession.delete(NAMESPACE + "insert", record);
	}

	@Override
	public int insertSelective(VpGdnEntity record) {
		return localSqlSession.insert(NAMESPACE + "insertSelective", record);
	}

	@Override
	public VpGdnEntity selectByPrimaryKey(Long id) {
		return localSqlSession.selectOne(NAMESPACE + "selectByPrimaryKey", id);
	}

	@Override
	public int updateByPrimaryKeySelective(VpGdnEntity record) {
		return localSqlSession.update(NAMESPACE + "updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(VpGdnEntity record) {
		return localSqlSession.update(NAMESPACE + "updateByPrimaryKey", record);
	}

	@Override
	public List<VpGdnEntity> selectByParams(VpGdnBean bean, RowBounds rb) {
		return localSqlSession.selectList(NAMESPACE + "selectByParams", bean, rb);
	}

	@Override
	public int selectByParamsCount(VpGdnBean bean) {
		return localSqlSession.selectOne(NAMESPACE + "selectByParamsCount", bean);
	}

	@Override
	public VpGdnEntity selectByVpGdnEntity(VpGdnEntity vpGdnEntity) {
		return localSqlSession.selectOne(NAMESPACE + "selectByVpGdnEntity", vpGdnEntity);
	}

	@Override
	public String getMaxDocCode(String seqName) {
		return localSqlSession.selectOne(NAMESPACE + "getMaxDocCode", seqName);
	}

	@Override
	public VpGdnEntity selectByDocCode(String docCode) {
		return localSqlSession.selectOne(NAMESPACE + "selectByDocCode", docCode);
	}

}
