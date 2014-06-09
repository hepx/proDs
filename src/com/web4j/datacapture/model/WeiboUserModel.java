package com.web4j.datacapture.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.datacapture.dao.WeiboUserDao;
import com.web4j.datacapture.entity.TbWeiboUser;

@Service("weiboUserModel")
public class WeiboUserModel {

	@Autowired
	private WeiboUserDao weiboUserDao;
	
	public void saveOrUpdateWeiboUsers(List<TbWeiboUser>weiboUsers) throws Exception{
		weiboUserDao.saveOrUpdateAll(weiboUsers);
	}
	
	public List<TbWeiboUser> queryWeiboUsers(Integer start,Integer limit) throws Exception{
		return weiboUserDao.queryTbWeiboUsers(start, limit);
	}
	
	public List<TbWeiboUser> queryAllWeiboUser() throws Exception{
		return weiboUserDao.queryAllTbWeiboUser();
	}
	
	public Long getWeiboUserCount(){
		return weiboUserDao.counts();
	}
	
	public void deleteWeiboUsers(List<TbWeiboUser> weiboUsers) throws Exception{
		weiboUserDao.delete(weiboUsers);
	}
}
