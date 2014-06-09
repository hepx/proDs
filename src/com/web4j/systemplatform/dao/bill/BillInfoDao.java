package com.web4j.systemplatform.dao.bill;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.systemplatform.entity.TbBillInfo;

@Repository("billInfoDao")
public class BillInfoDao extends AbstractDao<TbBillInfo> {
	
	public List<TbBillInfo> queryBillInfos()throws Exception{
		return listAll();
	}
}
