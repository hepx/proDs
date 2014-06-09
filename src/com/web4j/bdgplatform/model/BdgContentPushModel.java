package com.web4j.bdgplatform.model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jpush.api.MessageResult;

import com.web4j.bdgplatform.dao.BdgContentPushDao;
import com.web4j.bdgplatform.entity.TbBdgContentPush;
import com.web4j.exception.DoErrorException;
import com.web4j.jpush.JpushErrorCodeConfig;

@Service("bdgContentPushModel")
public class BdgContentPushModel {

	@Autowired
	private BdgContentPushDao bdgContentPushDao;
	
	@Autowired
	private BdgPushImptModel bdgPushImptModel;
	
	public List<TbBdgContentPush>queryBdgPushHistories(Integer start,Integer limit)throws Exception{
		return bdgContentPushDao.queryTbBdgPushRecords(start, limit);
	}
	
	public void saveOrUpdatePushRecord(TbBdgContentPush bdgPushRecord)throws Exception{
		if(bdgPushRecord.getCreateTime()==null){
			bdgPushRecord.setCreateTime(new Date());
		}
		bdgContentPushDao.saveOrUpdate(bdgPushRecord);
	}
	
	public void saveBdgPushRecords(List<TbBdgContentPush> bdgPushRecords)throws Exception{
		bdgContentPushDao.saveOrUpdateAll(bdgPushRecords);
	}
	
	public void updateBdgPushRecord(TbBdgContentPush bdgPushRecord)throws Exception{
		bdgContentPushDao.update(bdgPushRecord);
	}
	
	public Long getBdgPushRecordCount(){
		return bdgContentPushDao.counts();
	}
	
	public TbBdgContentPush getBdgPushRecordById(Long id)throws Exception{
		return bdgContentPushDao.get(id);
	}
	
	public void deleteBdgPushRecords(List<TbBdgContentPush> bdgPushRecords)throws Exception{
		for(TbBdgContentPush bdgPushRecord:bdgPushRecords){
			if(bdgPushRecord.getPushId()!=null){
				bdgContentPushDao.deleteById(bdgPushRecord.getPushId());
			}
		}
	}
	
	public TbBdgContentPush queryTbBdgPushRecordOfUnPush()throws Exception{
		return bdgContentPushDao.queryTbBdgPushRecord();
	}
	
	/**
	 * 推送信息
	 * @param bdgPushRecord
	 * @throws Exception
	 */
	public void pushBdgRecord(TbBdgContentPush bdgPushRecord)throws Exception{
		MessageResult msgResult=bdgPushImptModel.sendNotificationWithAppKey(bdgPushRecord.getPushId().intValue(), bdgPushRecord.getMsgTitle(), bdgPushRecord.getMsgContent());
		updateMsgResultHandler(msgResult);
	}
	
	public void pushRecord()throws Exception{
		//获得一条未推送的记录
		TbBdgContentPush bdgPushRecord=queryTbBdgPushRecordOfUnPush();
		if(bdgPushRecord!=null){
			pushBdgRecord(bdgPushRecord);
		}
	}
	
	public void updateMsgResultHandler(MessageResult msgResult)throws Exception{
		if (null != msgResult) {
			TbBdgContentPush bdgPushRecord=getBdgPushRecordById(Long.valueOf(msgResult.getSendno()));
			bdgPushRecord.setPushTime(new Date());
			bdgPushRecord.setErrcode(String.valueOf(msgResult.getErrcode()));
			bdgPushRecord.setErrmsg(JpushErrorCodeConfig.getErrorMsg(msgResult.getErrcode()));
			updateBdgPushRecord(bdgPushRecord);
		} else {
			throw new DoErrorException("BDG内容推送异常*****无法获取数据");
		}
	}
}
