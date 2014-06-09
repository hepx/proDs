package com.web4j.bdgplatform.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jpush.api.MessageResult;

import com.web4j.bdgplatform.dao.BdgAppPushDao;
import com.web4j.bdgplatform.entity.TbBdgAppPush;
import com.web4j.exception.DoErrorException;
import com.web4j.jpush.JpushErrorCodeConfig;

@Service("bdgAppPushModel")
public class BdgAppPushModel {

	@Autowired
	private BdgAppPushDao bdgAppPushDao;
	
	@Autowired
	private BdgPushImptModel bdgPushImptModel;
	
	public List<TbBdgAppPush>queryTbBdgPushApps(Integer start,Integer limit)throws Exception{
		return bdgAppPushDao.queryTbBdgPushApps(start, limit);
	}
	
	public void saveOrUpdateBdgPushApp(TbBdgAppPush bdgAppPush)throws Exception{
		if(bdgAppPush.getCreateTime()==null){
			bdgAppPush.setCreateTime(new Date());
		}
		bdgAppPushDao.saveOrUpdate(bdgAppPush);
	}
	
	public void saveBdgPushApps(List<TbBdgAppPush> bdgAppPushs)throws Exception{
		bdgAppPushDao.save(bdgAppPushs);
	}
	
	public void updateBdgPushApp(TbBdgAppPush bdgAppPush)throws Exception{
		bdgAppPushDao.update(bdgAppPush);
	}
	
	public Long getBdgPushAppCount(){
		return bdgAppPushDao.counts();
	}
	
	public TbBdgAppPush getBdgPushAppById(Long id)throws Exception{
		return bdgAppPushDao.get(id);
	}
	
	public void deleteBdgPushApps(List<TbBdgAppPush> bdgAppPushs)throws Exception{
		for(TbBdgAppPush appPush:bdgAppPushs){
			if(appPush.getPushId()!=null){
				bdgAppPushDao.deleteById(appPush.getPushId());
			}
		}
	}
	
	public TbBdgAppPush queryTbBdgPushAppOfUnPush()throws Exception{
		return bdgAppPushDao.queryTbBdgPushApp();
	}
	
	/**
	 * 推送APP
	 * @param bdgPushApp
	 * @throws Exception
	 */
	public void pushBdgApp(TbBdgAppPush bdgPushApp) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appPath", bdgPushApp.getAppPath());
		map.put("packageName", bdgPushApp.getPackageName());
		MessageResult msgResult=bdgPushImptModel.sendNotificationWithAppKey(bdgPushApp.getPushId().intValue(),
				bdgPushApp.getMsgTitle(), bdgPushApp.getMsgContent(), 1, map);
		updateMsgResultHandler(msgResult);
	}
	
	public void pushRecord()throws Exception{
		//获得一条未推送的记录
		TbBdgAppPush bdgAppPush=queryTbBdgPushAppOfUnPush();
		if(bdgAppPush!=null){
			pushBdgApp(bdgAppPush);
		}
	}
	
	public void updateMsgResultHandler(MessageResult msgResult)throws Exception{
		if (null != msgResult) {
			TbBdgAppPush bdgAppPush=getBdgPushAppById(Long.valueOf(msgResult.getSendno()));
			bdgAppPush.setPushTime(new Date());
			bdgAppPush.setErrcode(String.valueOf(msgResult.getErrcode()));
			bdgAppPush.setErrmsg(JpushErrorCodeConfig.getErrorMsg(msgResult.getErrcode()));
			updateBdgPushApp(bdgAppPush);
		} else {
		    throw new DoErrorException("BDG应用推送异常*****无法获取数据");
		}
	}
}
