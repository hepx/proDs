package com.web4j.systemplatform.model.bill;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.systemplatform.dao.bill.BillInfoDao;
import com.web4j.systemplatform.entity.TbBillInfo;
import com.web4j.systemplatform.pojo.bill.BillInfo;

@Service("billInfoModel")
public class BillInfoModel {

	@Autowired
	private BillInfoDao billInfoDao;
	
	public List<BillInfo>queryBillInfos()throws Exception{
		List<TbBillInfo> tbBillInfos=billInfoDao.queryBillInfos();
		List<BillInfo>billInfos=null;
		if(tbBillInfos!=null&&tbBillInfos.size()>0){
			billInfos=new ArrayList<BillInfo>();
			for(TbBillInfo tbBillInfo:tbBillInfos){
				BillInfo billInfo=new BillInfo();
				BeanUtils.copyProperties(tbBillInfo,billInfo);
				billInfos.add(billInfo);
			}
		}
		return billInfos;
	}
}
