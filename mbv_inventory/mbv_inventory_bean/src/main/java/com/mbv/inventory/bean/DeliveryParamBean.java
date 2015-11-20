package com.mbv.inventory.bean;

import java.io.Serializable;
import java.util.List;

public class DeliveryParamBean implements Serializable{

    
	/**
	 *  打包参数
	 */
	private static final long serialVersionUID = 4388222124148092159L;

	private Long degId;
	
	private List<DeliveryGoodBean> delGoodList;

	public Long getDegId() {
		return degId;
	}

	public void setDegId(Long degId) {
		this.degId = degId;
	}

	public List<DeliveryGoodBean> getDelGoodList() {
		return delGoodList;
	}

	public void setDelGoodList(List<DeliveryGoodBean> delGoodList) {
		this.delGoodList = delGoodList;
	}

	
}
