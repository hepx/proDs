package com.web4j.bdgplatform.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.bdgplatform.dao.BdgBrRecordDao;
import com.web4j.bdgplatform.entity.TbBdgArRecord;
import com.web4j.bdgplatform.entity.TbBdgBrRecord;
import com.web4j.util.SysConfig;

@Service("bdgBrRecordModel")
public class BdgBrRecordModel {
	
	@Autowired
	private BdgBrRecordDao bdgBrRecordDao;
	
	@Autowired
	private BdgArRecordModel bdgArRecordModel; 
	
	public void saveOrUpdateBdgBrRecords(List<TbBdgBrRecord>tbBdgBrRecords)throws Exception{
		bdgBrRecordDao.saveOrUpdateAll(tbBdgBrRecords);
	}
	
	public List<TbBdgBrRecord> queryBdgBrRecords(Integer start,Integer limit)throws Exception{
		return bdgBrRecordDao.queryTbBdgBrRecords(start, limit);
	}
	
	public Long getTbBdgBrRecordCount(){
		return bdgBrRecordDao.counts();
	}
	
	public TbBdgBrRecord getBdgBrRecord(Integer recordStatus) throws Exception{
		return bdgBrRecordDao.getBdgBrRecord(recordStatus);
	}
	
	public void deleteBdgBrRecords(List<TbBdgBrRecord> tbBdgBrRecords)throws Exception{
		bdgBrRecordDao.deleteAll(tbBdgBrRecords);
	}
	
	public TbBdgBrRecord quereyBdgBrRecordById(Long recordId)throws Exception{
		return bdgBrRecordDao.get(recordId);
	}
	
	public void updateBdgBrRecord(TbBdgBrRecord tbBdgBrRecord)throws Exception{
		bdgBrRecordDao.update(tbBdgBrRecord);
	}
	
	//更新发布
	public void updateReleaseBdgBrRecords(List<TbBdgBrRecord> brRecords) throws Exception{
		List<TbBdgArRecord> arRecords=new ArrayList<TbBdgArRecord>();
		for(TbBdgBrRecord source : brRecords){
			if(source.getRecordStatus()!=SysConfig.RECORD_STATUS_RELEASE){
				source.setRecordStatus(SysConfig.RECORD_STATUS_RELEASE);
				TbBdgArRecord target =new TbBdgArRecord(source);
				arRecords.add(target);
			}
		}
		//批量插入到发布后的表中
		bdgArRecordModel.saveOrUpdateBdgArRecords(arRecords);
		//更新发布状态
		saveOrUpdateBdgBrRecords(brRecords);
	}
	
	/**
	 * 定时更新调用
	 * @throws Exception
	 */
	public void updateReleaseBdgBrRecord() throws Exception{
		//取得一条已编辑好的记录
		TbBdgBrRecord brRecord = getBdgBrRecord(SysConfig.RECORD_STATUS_EDITED);
		if(brRecord!=null){
			brRecord.setRecordStatus(SysConfig.RECORD_STATUS_RELEASE);
			TbBdgArRecord arRecord =new TbBdgArRecord(brRecord);
			//批量插入到发布后的表中
			bdgArRecordModel.saveBdgArRecord(arRecord);
			//更新发布状态
			updateBdgBrRecord(brRecord);
		}
	}
}
