package com.mbv.inventory.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.mbv.inventory.bean.VpGdnBean;
import com.mbv.inventory.entity.VpGdnEntity;

public interface VpGdnEntityDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vp_gdn
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vp_gdn
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    int insert(VpGdnEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vp_gdn
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    int insertSelective(VpGdnEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vp_gdn
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    VpGdnEntity selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vp_gdn
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    int updateByPrimaryKeySelective(VpGdnEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vp_gdn
     *
     * @mbggenerated Thu Sep 10 11:02:49 CST 2015
     */
    int updateByPrimaryKey(VpGdnEntity record);
    
    public List<VpGdnEntity> selectByParams(VpGdnBean bean, RowBounds rb);
    
    public int selectByParamsCount(VpGdnBean bean);
    
    public VpGdnEntity selectByVpGdnEntity(VpGdnEntity vpGdnEntity);
    
    public String getMaxDocCode(String seqName);

	VpGdnEntity selectByDocCode(String docCode);
}