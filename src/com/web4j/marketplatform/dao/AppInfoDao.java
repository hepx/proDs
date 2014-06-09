package com.web4j.marketplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.marketplatform.entity.TbAppInfo;

@Repository("appInfoDao")
public class AppInfoDao extends AbstractDao<TbAppInfo> {

	/**
	 * 列表里的数据
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<TbAppInfo> queryTbAppInfos(Integer start,Integer limit) throws Exception{
		String queryString = "select new TbAppInfo(appId,appName,appCategory,appVersionName," +
				"appPic,createUser,createTime,appDesc,appVersionCode,appPath,downloads,packageName," +
				"sdkSupport,appSize,fee) from TbAppInfo " +
				"t order by t.appId DESC";
		return list(queryString, start, limit);
	}
	/**
	 * 弹出选择窗口数据
	 * @param appCategory
	 * @return
	 * @throws Exception
	 */
	public List<TbAppInfo> queryAllTbAppInfo(String appCategory)throws Exception{
		String queryString ="select new TbAppInfo(appId,appName,appCategory,appVersionName,appPic,appPath,packageName)" +
				" from TbAppInfo t";
		if(appCategory!=null&&!"".equals(appCategory)){
			queryString+=" where t.appCategory='"+appCategory+"'";
		}
		queryString+=" order by t.appId DESC";
		return list(queryString);
	}
	
	public TbAppInfo queryTbAppInfo(String packageName)throws Exception{
		String queryString = "select new TbAppInfo(appId,appName,appCategory,appVersionName," +
				"appPic,appDesc,appVersionCode,appPath,downloads,packageName," +
				"sdkSupport,appSize,fee) from TbAppInfo where packageName='"+packageName+"'";
		List<TbAppInfo> ls=list(queryString);
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}else{
			return null;
		}
	}
}
