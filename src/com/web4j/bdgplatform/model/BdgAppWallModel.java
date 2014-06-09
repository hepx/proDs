package com.web4j.bdgplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.bdgplatform.dao.BdgAppWallDao;
import com.web4j.bdgplatform.entity.TbBdgAppWall;
import com.web4j.marketplatform.entity.TbAppInfo;
import com.web4j.util.LoginUserUtils;

@Service("bdgAppWallModel")
public class BdgAppWallModel {

	@Autowired
	private BdgAppWallDao bdgAppWallDao;
	
	public void addBdgAppWalls(List<TbAppInfo> tbAppInfos) throws Exception{
		List<TbBdgAppWall>tbBdgAppWalls=new ArrayList<TbBdgAppWall>();
		for(TbAppInfo appInfo:tbAppInfos){
			TbBdgAppWall bdgAppWall=new TbBdgAppWall();
			bdgAppWall.setCreateUser(LoginUserUtils.getLoginUserInfo().getUserName());
			bdgAppWall.setCreateTime(new Date());
			bdgAppWall.setTbAppInfo(appInfo);
			tbBdgAppWalls.add(bdgAppWall);
		}
		bdgAppWallDao.save(tbBdgAppWalls);
	}
	
//	public List<TbBdgAppWall> queryBdgAppWalls(Integer start,Integer limit)throws Exception{
//		return bdgAppWallDao.queryTbBdgAppWalls(start, limit);
//	}
	
	public List<Map<String,?>> queryBdgAppWalls(Integer start,Integer limit)throws Exception{
		return bdgAppWallDao.queryTbBdgAppWalls(start, limit);
	}
	
	public Long getBdgAppWallCount(){
		return bdgAppWallDao.counts();
	}
	
	public void deleteBdgAppWalls(List<TbBdgAppWall>tbBdgAppWalls)throws Exception{
		for(TbBdgAppWall bdgAppWall:tbBdgAppWalls){
			if(bdgAppWall.getAppWallId()!=null){
				bdgAppWallDao.deleteById(bdgAppWall.getAppWallId());
			}
		}
	}
	
	public TbBdgAppWall queryBdgAppWallById(Long appWallId)throws Exception{
		return bdgAppWallDao.get(appWallId);
	}
	
	public void updateBdgAppWall(TbBdgAppWall bdgAppWall)throws Exception{
		bdgAppWallDao.update(bdgAppWall);
	}
	
	/****************手机调用****************/
	public List<Map<String,?>> queryBdgAppWallsOfPhone(Integer start,Integer limit)throws Exception{
		return bdgAppWallDao.queryTbBdgAppWallsOfPhone(start, limit);
	}
	
	public void updateDownloads(Long appWallId)throws Exception{
		TbBdgAppWall appWall=bdgAppWallDao.get(appWallId);
		if(appWall!=null){
			appWall.setDownloads(appWall.getDownloads()+1);
			appWall.getTbAppInfo().setDownloads(appWall.getTbAppInfo().getDownloads()+1);
			bdgAppWallDao.update(appWall);
		}
	}
}
