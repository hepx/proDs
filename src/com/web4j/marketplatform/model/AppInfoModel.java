package com.web4j.marketplatform.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.marketplatform.dao.AppInfoDao;
import com.web4j.marketplatform.entity.TbAppInfo;

@Service("appInfoModel")
public class AppInfoModel {

	@Autowired
	private AppInfoDao appInfoDao;
	
	public List<TbAppInfo> queryAppInfos(Integer start,Integer limit) throws Exception{
		return appInfoDao.queryTbAppInfos(start, limit);
	}
	
	public List<TbAppInfo> queryAllAppInfo(String appCategory) throws Exception{
		return appInfoDao.queryAllTbAppInfo(appCategory);
	}
	
	public void saveAppInfo(TbAppInfo appInfo)throws Exception{
		appInfoDao.save(appInfo);
	}
	
	public void updateAppInfo(TbAppInfo appInfo)throws Exception{
		appInfoDao.update(appInfo);
	}
	
	public Long getAppInfoCount(){
		return appInfoDao.counts();
	}
	
	public TbAppInfo getAppInfoById(Long appId) throws Exception{
		return appInfoDao.get(appId);
	}
	
	public void deleteAppInfos(List<TbAppInfo> appInfos)throws Exception{
		for(TbAppInfo app:appInfos){
			if(app.getAppId()!=null){
				appInfoDao.deleteById(app.getAppId());
			}
		}
	}
	
	public TbAppInfo queryAppInfo(String packageName) throws Exception{
		return appInfoDao.queryTbAppInfo(packageName);
	}

}
