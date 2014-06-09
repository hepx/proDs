package com.web4j.bdgplatform.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.bdgplatform.dao.BdgArRecordDao;
import com.web4j.bdgplatform.entity.TbBdgArRecord;

@Service("bdgArRecordModel")
public class BdgArRecordModel {
	
	@Autowired
	private BdgArRecordDao bdgArRecordDao;
	
	public void saveOrUpdateBdgArRecords(List<TbBdgArRecord>tbBdgArRecords)throws Exception{
		bdgArRecordDao.saveOrUpdateAll(tbBdgArRecords);
	}
	
	public List<TbBdgArRecord> queryBdgArRecords(Integer start,Integer limit)throws Exception{
		return bdgArRecordDao.queryTbBdgArRecords(start, limit);
	}
	
	public Long getBdgArRecordCount(){
		return bdgArRecordDao.counts();
	}
	
	public void deleteBdgArRecords(List<TbBdgArRecord> tbBdgArRecords)throws Exception{
		bdgArRecordDao.deleteAll(tbBdgArRecords);
	}
	
	public TbBdgArRecord quereyBdgArRecordById(Long recordId)throws Exception{
		return bdgArRecordDao.get(recordId);
	}
	
	public void updateBdgArRecord(TbBdgArRecord tbWeiboRecord)throws Exception{
		bdgArRecordDao.update(tbWeiboRecord);
	}
	
	public void saveBdgArRecord(TbBdgArRecord bdgArRecord)throws Exception{
		bdgArRecordDao.save(bdgArRecord);
	}
	
	/*********************手机调用*************************/
	public List<TbBdgArRecord> quereyBdgArRecordsOfPhone(Integer start,Integer limit)throws Exception{
		return bdgArRecordDao.queryTbBdgArRecordsOfPhone(start, limit);
	}
	
	public List<TbBdgArRecord> quereyPreBdgArRecordsOfPhone(Integer start,Integer limit,Long since_id)throws Exception{
		return bdgArRecordDao.queryPreTbBdgArRecordsOfPhone(start, limit,since_id);
	}

	public List<TbBdgArRecord> quereNextBdgArRecordsOfPhone(Integer start,Integer limit,Long max_id)throws Exception{
		return bdgArRecordDao.queryNextTbBdgArRecordsOfPhone(start, limit,max_id);
	}
	
	public List<TbBdgArRecord> orderTop(Integer start,Integer limit)throws Exception{
		return bdgArRecordDao.orderTopOfPhone(start, limit);
	}
	
	public List<TbBdgArRecord> orderTread(Integer start,Integer limit)throws Exception{
		return bdgArRecordDao.orderTreadOfPhone(start, limit);
	}
	
	public List<TbBdgArRecord> orderCollect(Integer start,Integer limit)throws Exception{
		return bdgArRecordDao.orderCollectOfPhone(start, limit);
	}
	
	public List<TbBdgArRecord> orderShare(Integer start,Integer limit)throws Exception{
		return bdgArRecordDao.orderShareOfPhone(start, limit);
	}
}
