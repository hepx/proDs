package com.web4j.sxdplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.exception.DoErrorException;
import com.web4j.sxdplatform.dao.ChantingRecordDao;
import com.web4j.sxdplatform.entity.TbChantingRecord;
import com.web4j.sxdplatform.pojo.ChantingRecord;

@Service("chantingRecordModel")
public class ChantingRecordModel {

	@Autowired
	private ChantingRecordDao chantingRecordDao;
	
	public List<ChantingRecord> queryChantingRecords(Integer start,Integer limit) throws Exception{
		List<ChantingRecord> chantingRecords = new ArrayList<ChantingRecord>();
		List<TbChantingRecord> tbChantingRecords=chantingRecordDao.queryTbChantingRecords(start, limit);
		for(TbChantingRecord tbChantingRecord:tbChantingRecords){
			ChantingRecord chantingRecord = new ChantingRecord();
			BeanUtils.copyProperties(tbChantingRecord, chantingRecord);
			chantingRecords.add(chantingRecord);
		}
		return chantingRecords;
	}
	
	public Long getChantingRecordCount(){
		return chantingRecordDao.counts();
	}
	
	public void saveChantingRecord(ChantingRecord chantingRecord) throws Exception{
		TbChantingRecord tbChantingRecord=new TbChantingRecord();
		BeanUtils.copyProperties(chantingRecord, tbChantingRecord);
		tbChantingRecord.setCreateTime(new Date());
		chantingRecordDao.save(tbChantingRecord);
	}
	
	public void deleteChantingRecord(List<ChantingRecord> chantingRecords) throws Exception{
		for(ChantingRecord chantingRecord : chantingRecords){
			if(chantingRecord.getChantRecordId()!=null&&!"".equals(chantingRecord.getChantRecordId())){
				chantingRecordDao.deleteById(chantingRecord.getChantRecordId());
			}
		}
	}
	
	public void updateChantingRecord(Long chantRecordId,String restoreStatus,String restoreContent)throws Exception{
		if(chantRecordId!=null&&!"".equals(chantRecordId)){
			TbChantingRecord tbChantingRecord=chantingRecordDao.get(chantRecordId);
			if(tbChantingRecord!=null){
				tbChantingRecord.setRestoreStatus(restoreStatus);
				tbChantingRecord.setRestoreContent(restoreContent);
			}else{
				throw new DoErrorException("诵经记录不存在！");
			}
		}else{
			throw new DoErrorException("数据格式不完整！");
		}
	}
	
	public List statByMonth(String xyNo)throws Exception{
		return chantingRecordDao.statByMonth(xyNo);
	}
	
	public List queryDetails(String xyNo,String month,String chantingBook)throws Exception{
		return chantingRecordDao.queryDetails(xyNo, month, chantingBook);
	}
}
