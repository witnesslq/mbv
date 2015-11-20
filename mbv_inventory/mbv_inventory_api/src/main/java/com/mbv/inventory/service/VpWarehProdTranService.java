package com.mbv.inventory.service;

import java.util.List;

import com.mbv.common.exception.MbvException;
import com.mbv.inventory.entity.VpWarehProdTranEntity;

public interface VpWarehProdTranService {
	
	/**
	 * 插入出入库日志表
	 * @param record
	 * @return
	 * @throws MbvException
	 * @throws Exception
	 */
	public int insertSelective(VpWarehProdTranEntity record) throws MbvException,Exception;

	/**
	 * 批量插入事务日志表
	 * @param prodTranList
	 * @return
	 * @throws MbvException
	 * @throws Exception
	 */
	public int insertVpWarehProdTranBatch(
			List<VpWarehProdTranEntity> prodTranList) throws MbvException,Exception;
}
