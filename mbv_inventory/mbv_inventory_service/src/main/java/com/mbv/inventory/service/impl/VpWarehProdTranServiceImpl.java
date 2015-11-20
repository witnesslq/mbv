package com.mbv.inventory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbv.common.exception.MbvException;
import com.mbv.inventory.dao.VpWarehProdTranEntityDao;
import com.mbv.inventory.entity.VpWarehProdTranEntity;
import com.mbv.inventory.service.VpWarehProdTranService;

@Service("vpWarehProdTranService")
public class VpWarehProdTranServiceImpl implements VpWarehProdTranService {

	@Autowired
	private VpWarehProdTranEntityDao vpWarehProdTranEntityDao;
	
	@Override
	public int insertSelective(VpWarehProdTranEntity record)
			throws MbvException, Exception {
		if(record == null){
			throw new MbvException("参数对象不能为空！");
		}
		return vpWarehProdTranEntityDao.insertSelective(record);
	}

	@Override
	public int insertVpWarehProdTranBatch(
			List<VpWarehProdTranEntity> prodTranList) throws MbvException,
			Exception {
		if(prodTranList == null || prodTranList.size() <= 0){
			throw new MbvException("参数对象不能为空");
		}
		return vpWarehProdTranEntityDao.insertVpWarehProdTranBatch(prodTranList);
	}

}
