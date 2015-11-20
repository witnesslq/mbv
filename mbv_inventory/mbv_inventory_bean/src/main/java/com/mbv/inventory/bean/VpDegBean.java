package com.mbv.inventory.bean;

import java.io.Serializable;
import java.util.List;

public class VpDegBean implements Serializable{

	/**
	 *  发货单实体Bean
	 */
	private static final long serialVersionUID = 6429928139933708492L;
    
	private DeliveryBean deliveryBean;
	
	private List<GoodBean> goodBeanList;


	public DeliveryBean getDeliveryBean() {
		return deliveryBean;
	}

	public void setDeliveryBean(DeliveryBean deliveryBean) {
		this.deliveryBean = deliveryBean;
	}

	public List<GoodBean> getGoodBeanList() {
		return goodBeanList;
	}

	public void setGoodBeanList(List<GoodBean> goodBeanList) {
		this.goodBeanList = goodBeanList;
	}

	
	
}
