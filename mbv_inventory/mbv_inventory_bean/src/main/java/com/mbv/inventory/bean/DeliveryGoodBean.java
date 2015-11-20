package com.mbv.inventory.bean;

import java.io.Serializable;

public class DeliveryGoodBean implements Serializable{
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 8997677423788471958L;

	private String prodNum;

    private int qty;

    public String getProdNum() {
        return prodNum;
    }

    public void setProdNum(String prodNum) {
        this.prodNum = prodNum;
    }

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	
	@Override
	public boolean equals(Object obj){  
		if(!(obj instanceof DeliveryGoodBean)) 
			return false;
		DeliveryGoodBean goodBean = (DeliveryGoodBean)obj;
	    if(goodBean !=null && prodNum.equals(goodBean.getProdNum()) && qty == goodBean.getQty())  
	    {  
	      return true;  
	    }  
	    return false;  
	  }  
    
}