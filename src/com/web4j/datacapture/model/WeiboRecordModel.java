package com.web4j.datacapture.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.datacapture.dao.WeiboRecordDao;
import com.web4j.datacapture.entity.TbWeiboRecord;

@Service("weiboRecordModel")
public class WeiboRecordModel {
	
	@Autowired
	private WeiboRecordDao weiboRecordDao;
	
	public void saveOrUpdateWeiboRecords(List<TbWeiboRecord>tbWeiboRecords)throws Exception{
		weiboRecordDao.saveOrUpdateAll(tbWeiboRecords);
	}
	
	public List<TbWeiboRecord> queryWeiboRecords(Integer start,Integer limit)throws Exception{
		return weiboRecordDao.queryTbWeiboRecords(start, limit);
	}
	
	public Long getTbWeiboRecordCount(){
		return weiboRecordDao.counts();
	}
	
	public void deleteTbWeiboRecords(List<TbWeiboRecord> tbWeiboRecords)throws Exception{
		weiboRecordDao.deleteAll(tbWeiboRecords);
	}
	
	public TbWeiboRecord quereyTbWeiboRecordById(Long recordId)throws Exception{
		return weiboRecordDao.get(recordId);
	}
	
	public void updateTbWeiboRecord(TbWeiboRecord tbWeiboRecord)throws Exception{
		weiboRecordDao.update(tbWeiboRecord);
	}
}
