package com.mbv.biz.config.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.mbv.biz.config.entity.SysParameterEntity;


public interface SysParameterEntityDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysParameterEntity record);

    int insertSelective(SysParameterEntity record);

    SysParameterEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysParameterEntity record);

    int updateByPrimaryKey(SysParameterEntity record);
    
    public List<SysParameterEntity> selectByParams(SysParameterEntity entity, RowBounds rb);
    
    public SysParameterEntity selectByPramCode(String parmCode);
}