package com.mbv.biz.config.bean;

import java.io.Serializable;
import java.util.List;

public class OrderBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6429928139933708492L;
    
	private DegBean degBean;
	
	private List<DegDtlBean> degDtlBeanList;

	public DegBean getDegBean() {
		return degBean;
	}

	public void setDegBean(DegBean degBean) {
		this.degBean = degBean;
	}

	public List<DegDtlBean> getDegDtlBeanList() {
		return degDtlBeanList;
	}

	public void setDegDtlBeanList(List<DegDtlBean> degDtlBeanList) {
		this.degDtlBeanList = degDtlBeanList;
	}
	
	
	
}
