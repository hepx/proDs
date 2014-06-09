package com.web4j.bdgplatform.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.bdgplatform.dao.BdgUserDao;
import com.web4j.bdgplatform.entity.TbBdgUser;

@Service("bdgUserModel")
public class BdgUserModel {

	@Autowired
	private BdgUserDao bdgUserDao;
	
	public List<TbBdgUser> queryBdgUsers(Integer start,Integer limit)throws Exception{
		return bdgUserDao.queryTbBdgUsers(start, limit);
	}
	
	public List<TbBdgUser> queryBdgUsers(Integer start,Integer limit,TbBdgUser bdgUser)throws Exception{
		return bdgUserDao.queryTbBdgUsers(start, limit, bdgUser);
	}
	
	public void saveOrUpdageBdgUsers(List<TbBdgUser>bdgUsers)throws Exception{
		bdgUserDao.saveOrUpdateAll(bdgUsers);
	}
	
	public Long getBdgUserCount(){
		return bdgUserDao.counts();
	}
	
	public Long getBdgUserCount(TbBdgUser bdgUser){
		return bdgUserDao.counts(bdgUser);
	}
	
	public void deleteBdgUsers(List<TbBdgUser>bdgUsers)throws Exception{
		bdgUserDao.delete(bdgUsers);
	}
	
	public void createBdgUser(TbBdgUser user)throws Exception{
		bdgUserDao.save(user);
	}
}
