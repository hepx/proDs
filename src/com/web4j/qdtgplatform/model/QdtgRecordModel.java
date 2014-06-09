package com.web4j.qdtgplatform.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.qdtgplatform.dao.QdtgChannelDao;
import com.web4j.qdtgplatform.dao.QdtgRecordDao;
import com.web4j.qdtgplatform.entity.TbQdtgChannel;
import com.web4j.qdtgplatform.entity.TbQdtgCvs;
import com.web4j.qdtgplatform.entity.TbQdtgRecord;
import com.web4j.qdtgplatform.pojo.QdtgRecord;

@Service("qdtgRecordModel")
public class QdtgRecordModel {

	@Autowired
	private QdtgRecordDao qdtgRecordDao;
	
	@Autowired 
	private QdtgChannelDao qdtgChannelDao;
	
	public List<TbQdtgRecord> queryQdtgRecords(Integer start,Integer limit,QdtgRecord qdtgRecord) throws Exception{
		return qdtgRecordDao.queryTbQdtgRecord(start, limit,qdtgRecord);
	}
	
	public List<TbQdtgRecord> queryQdtgRecordByInStatus(Integer start,Integer limit) throws Exception{
		return qdtgRecordDao.queryTbQdtgRecordByInStatus(start, limit);
	}

	public List<Map<String,Object>> statQdtgRecordByDay(QdtgRecord statCondition,Integer start,Integer limit) throws Exception{
		return qdtgRecordDao.statTbQdtgRecordByDay(statCondition, start, limit);
	}

	public Long statQdtgRecordByDayCount(QdtgRecord statCondition) throws Exception{
		return qdtgRecordDao.statTbQdtgRecordByDayCount(statCondition);
	}
	
	public List<Map<String,Object>> statQdtgRecordByMonth(QdtgRecord statCondition) throws Exception{
		return qdtgRecordDao.statTbQdtgRecordByMonth(statCondition);
	}
	
	public List<Map<String,Object>> statQdtgRecordByCvsByDay(Long userId,QdtgRecord statCondition,Integer start,Integer limit) throws Exception{
		return qdtgRecordDao.statTbQdtgRecordByCvsByDay(userId, statCondition, start, limit);
	}
	
	public Long statQdtgRecordByCvsByDayCount(Long userId,QdtgRecord statCondition) throws Exception{
		return qdtgRecordDao.statTbQdtgRecordByCvsByDayCount(userId, statCondition);
	}
	
	public List<Map<String,Object>> statQdtgRecordByCvsByMonth(Long userId,QdtgRecord statCondition) throws Exception{
		return qdtgRecordDao.statTbQdtgRecordByCvsByMonth(userId, statCondition);
	}
	
	public Long queryQdtgRecordCount(){
		return qdtgRecordDao.counts();
	}

	public Long queryQdtgRecordCount(QdtgRecord qdtgRecord)throws Exception{
		return qdtgRecordDao.queryTbQdtgRecordCount(qdtgRecord);
	}
	
	public Long queryQdtgRecordByInStatusCount() throws Exception{
		return qdtgRecordDao.queryTbQdtgRecordByInStatusCount();
	}
	
	public void saveOrUpdateQdtgRecord(TbQdtgRecord qdtgRecord) throws Exception{
		TbQdtgChannel tbQdtgChannel=qdtgChannelDao.get(qdtgRecord.getChannelId());
		if(tbQdtgChannel!=null){
			qdtgRecord.setFileName(tbQdtgChannel.getFileName());
			qdtgRecord.setChannelNo(tbQdtgChannel.getChannelNo());
			qdtgRecord.setUnitPrice(tbQdtgChannel.getUnitPrice());
			qdtgRecord.setStatus(false);
			//产品
			qdtgRecord.setProductId(tbQdtgChannel.getTbQdtgProduct().getProductId());
			qdtgRecord.setProductName(tbQdtgChannel.getTbQdtgProduct().getProductName());
			//客户信息
			TbQdtgCvs tbQdtgCvs=tbQdtgChannel.getTbQdtgCvs();
			if(tbQdtgCvs!=null){
				qdtgRecord.setCvsId(tbQdtgCvs.getCvsId());
				qdtgRecord.setCvsName(tbQdtgCvs.getCvsName());
				qdtgRecord.setCreateTime(new Date());
				qdtgRecord.countTotalPrice();
				qdtgRecord.countTotalPriceOfNet(tbQdtgChannel.getProportion());
				//用户信息
				qdtgRecord.setUserId(tbQdtgCvs.getTbUserInfo().getId());
			}
			qdtgRecordDao.save(qdtgRecord);
		}
	}
	
	public void updateQdtgRecordStatus(List<TbQdtgRecord> tbQdtgRecords)throws Exception{
		for(TbQdtgRecord qdtgRecord:tbQdtgRecords){
			TbQdtgRecord r=qdtgRecordDao.get(qdtgRecord.getRecordId());
			r.setStatus(true);
			qdtgRecordDao.update(r);
		}
	}
	
	public void deleteQdtgRecords(List<TbQdtgRecord> qdtgRecords) throws Exception{
		for(TbQdtgRecord record:qdtgRecords){
			if(record.getRecordId()!=null){
				qdtgRecordDao.deleteById(record.getRecordId());
			}
		}
	}
}
