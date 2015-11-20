package com.mbv.biz.config.iface.service.impl;

import java.util.List;





import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mbv.comm.bean.VpDegDtlEntity;
import com.mbv.comm.bean.VpDegEntity;
import com.mbv.comm.bean.VpDegModifyEntity;
import com.mbv.comm.bean.IfaceResponse;
import com.mbv.comm.bean.VpDegRequest;
import com.mbv.comm.bean.VpWnEntity;
import com.mbv.comm.api.UDCommService;

@Service("uDCommService")
public class UDCommServiceImpl implements UDCommService {

	private  Logger log = LoggerFactory.getLogger(UDCommServiceImpl.class);
	
	@Autowired
	private UDCommServiceBLL uDCommServiceBLL;
	
	@Override
	public IfaceResponse saveNewVpDeg(String lstVpDegReq) {
		// TODO Auto-generated method stub

		IfaceResponse res = new IfaceResponse();

		try {
			
			log.info("saveNewVpDeg.json:"+lstVpDegReq);
			
			List<VpDegRequest> lstReq = JSON.parseArray(lstVpDegReq, VpDegRequest.class);
			
			String strRe = uDCommServiceBLL.saveNewVpDeg(lstReq);
			if (StringUtils.isEmpty(strRe)) {
				res.setResult(true);
			} else {
				res.setResult(false);
				res.setMessage(strRe);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			res.setResult(false);
			res.setMessage("发生异常，异常信息：" + e.getMessage());
		}

		return res;
	}

	@Override
	public IfaceResponse cancelVpDeg(String lstCancelVpDeg) {
		// TODO Auto-generated method stub
		
		IfaceResponse res = new IfaceResponse();
		
		try {
			
			log.info("cancelVpDeg.json:"+lstCancelVpDeg);
			
			List<VpDegEntity> lstVpDeg = JSON.parseArray(lstCancelVpDeg, VpDegEntity.class);
			
			res = uDCommServiceBLL.cancelVpDeg(lstVpDeg);
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			
			res.setResult(false);
			res.setMessage("发生异常，异常信息："+ex.getMessage());
		}
		
		return res;
	}

	@Override
	public IfaceResponse cancelVpDegProduct(
			String lstCancelProduct) {
		// TODO Auto-generated method stub
		
		IfaceResponse res = new IfaceResponse();
		
		try
		{
			log.info("cancelVpDegProduct.json:"+lstCancelProduct);
			
			List<VpDegDtlEntity> lstCancelDtl = JSON.parseArray(lstCancelProduct,VpDegDtlEntity.class);
			String strRe = uDCommServiceBLL.cancelVpDegProduct(lstCancelDtl);
			if(StringUtils.isEmpty(strRe))
			{
				res.setResult(true);
			}
			else
			{
				res.setResult(false);
				res.setMessage(strRe);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			res.setResult(false);
			res.setMessage("发生异常，异常信息："+ex.getMessage());
		}
		
		return res;
	}

	@Override
	public IfaceResponse modifyVpDeg(VpDegModifyEntity modifyInfo) {
		// TODO Auto-generated method stub
		
		IfaceResponse res = new IfaceResponse();
		
		try
		{
			log.info("modifyVpDeg.json:"+JSON.toJSONString(modifyInfo));
			
			String strRe = uDCommServiceBLL.modifyVpDeg(modifyInfo);
			
			if(StringUtils.isEmpty(strRe))
			{
				res.setResult(true);
			}
			else
			{
				res.setResult(false);
				res.setMessage(strRe);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			res.setResult(false);
			res.setMessage("发生异常，异常信息："+ex.getMessage());
		}
		
		return res;
	}
	
	
	@Override
	public IfaceResponse writeVpWnTradeResult(VpWnEntity wnInfo) {
		// TODO Auto-generated method stub
		IfaceResponse res = new IfaceResponse();
		
		try
		{
			log.info("writeVpWnTradeResult.json:"+JSON.toJSONString(wnInfo));
			
			String strRe = uDCommServiceBLL.writeVpWnResult(wnInfo);
			
			if(StringUtils.isEmpty(strRe))
			{
				res.setResult(true);
			}
			else
			{
				res.setResult(false);
				res.setMessage(strRe);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			res.setResult(false);
			res.setMessage("发生异常，异常信息："+ex.getMessage());
		}
		
		return res;
	}

}
