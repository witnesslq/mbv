package com.mbv.biz.config.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbv.biz.config.dao.SysParameterEntityDao;
import com.mbv.biz.config.entity.SysParameterEntity;
import com.mbv.biz.config.service.SysParameterService;
import com.mbv.common.exception.MbvException;

@Service("sysParameterService")
public class SysParameterServiceImpl implements SysParameterService {

	@Autowired
	private SysParameterEntityDao sysParameterDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) throws MbvException {
		return sysParameterDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysParameterEntity record) throws MbvException {
		return sysParameterDao.insert(record);
	}

	@Override
	public int insertSelective(SysParameterEntity record) throws MbvException {
		return sysParameterDao.insertSelective(record);
	}

	@Override
	public SysParameterEntity selectByPrimaryKey(Integer id)
			throws MbvException {
		return sysParameterDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysParameterEntity record)
			throws MbvException {
		return sysParameterDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysParameterEntity record)
			throws MbvException {
		return sysParameterDao.updateByPrimaryKey(record);
	}

	@Override
	public List<SysParameterEntity> queryByParams(SysParameterEntity entity,int offset,int limit)
			throws MbvException {
		return sysParameterDao.selectByParams(entity, new RowBounds(offset, limit));
	}
	
	@Override
	public SysParameterEntity selectByPramCode(String paramCode) throws MbvException {
		return sysParameterDao.selectByPramCode(paramCode);
	}

}
