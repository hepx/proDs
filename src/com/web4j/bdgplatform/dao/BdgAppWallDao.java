package com.web4j.bdgplatform.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.web4j.bdgplatform.entity.TbBdgAppWall;
import com.web4j.common.dao.AbstractDao;

@Repository("bdgAppWallDao")
public class BdgAppWallDao extends AbstractDao<TbBdgAppWall> {

	public List<Map<String,?>> queryTbBdgAppWalls(Integer start,Integer limit) throws Exception{
		String queryString="select new map(A.appWallId as appWallId,A.createUser as createUser," +
				"A.createTime as createTime,A.downloads as downloads,A.sort as sort,A.isPush as isPush,B.appId as appId," +
				"B.appName as appName,B.appVersionCode as appVersionCode,B.appVersionName as appVersionName," +
				"B.appPic as appPic) from TbBdgAppWall A left join A.tbAppInfo B order by A.sort DESC";
		return (List<Map<String,?>>)map(queryString, start, limit);
	}
	
	public List<Map<String,?>> queryTbBdgAppWallsOfPhone(Integer start, Integer limit)throws Exception{
		String queryString="select new map(A.appWallId as appWallId,A.downloads as downloads,A.sort as sort,A.isPush as isPush," +
				"B.appName as appName,B.appVersionCode as appVersionCode,B.appVersionName as appVersionName," +
				"B.appDesc as appDesc,B.appPic as appPic,B.appPath as appPath) " +
				"from TbBdgAppWall A  left join A.tbAppInfo B order by A.sort DESC";
		return (List<Map<String,?>>)map(queryString, start, limit);
	}
	
}
