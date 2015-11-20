package com.mbv.biz.config.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mbv.biz.config.dao.SysParameterEntityDao;
import com.mbv.biz.config.entity.SysParameterEntity;

@Repository("sysParameterEntityDao")
public class SysParameterEntityDaoImpl implements SysParameterEntityDao {

	 /**
     * 定义本地namespace
     */
    private static final String NAMESPACE = "mbv.com.mbv.biz.config.dao.impl.SysParameterEntityDao.";
    
    @Resource 
    private SqlSessionTemplate localSqlSession;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		 return this.localSqlSession.delete(NAMESPACE + "deleteByPrimaryKey",id);
	}

	@Override
	public int insert(SysParameterEntity record) {
		return this.localSqlSession.insert(NAMESPACE + "insert",record);
	}

	@Override
	public int insertSelective(SysParameterEntity record) {
		return this.localSqlSession.insert(NAMESPACE + "insertSelective",record);
	}

	@Override
	public SysParameterEntity selectByPrimaryKey(Integer id) {
		return this.localSqlSession.selectOne(NAMESPACE + "selectByPrimaryKey",id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysParameterEntity record) {
		return this.localSqlSession.update(NAMESPACE + "updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(SysParameterEntity record) {
		return this.localSqlSession.update(NAMESPACE + "updateByPrimaryKey",record);
	}

	@Override
	public List<SysParameterEntity> selectByParams(SysParameterEntity entity, RowBounds rb) {
		return this.localSqlSession.selectList(NAMESPACE + "selectByParams", entity, rb);
	}
	
	@Override
	public SysParameterEntity selectByPramCode(String parmCode) {
		return this.localSqlSession.selectOne(NAMESPACE + "selectByPramCode",parmCode);
	}

}
