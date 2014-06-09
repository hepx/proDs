package com.web4j.sxdplatform.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.sxdplatform.dao.SxdUserDao;
import com.web4j.sxdplatform.entity.TbSxdUser;
import com.web4j.sxdplatform.pojo.SxdUser;

@Service("sxdUserModel")
public class SxdUserModel {

	@Autowired
	private SxdUserDao sxdUserDao;
	
	public void saveOrUpdateSxdUsers(List<SxdUser>users)throws Exception{
		List<TbSxdUser> tbSxdUsers =new ArrayList<TbSxdUser>();
		for(SxdUser source :users){
			TbSxdUser target=new TbSxdUser();
			BeanUtils.copyProperties(source, target);
			tbSxdUsers.add(target);
		}
		sxdUserDao.saveOrUpdateAll(tbSxdUsers);
	}
	
	public List<SxdUser> quereySxdUsers(Integer start,Integer limit)throws Exception{
		List<TbSxdUser> tbSxdUsers=sxdUserDao.quereyTbSxdUsers(start, limit);
		List<SxdUser> sxdUsers=new ArrayList<SxdUser>();
		for(TbSxdUser source : tbSxdUsers){
			SxdUser target =new SxdUser();
			BeanUtils.copyProperties(source, target);
			sxdUsers.add(target);
		}
		return sxdUsers;
	}
	
	public Long getSxdUserCount(){
		return sxdUserDao.counts();
	}
	
	public void deleteSxdUsers(List<SxdUser>users)throws Exception{
		for(SxdUser user : users){
			if(user.getUserId()!=null&&!"".equals(user.getUserId())){
				sxdUserDao.deleteById(user.getUserId());
			}
		}
	}
	
	public void createSxdUser(SxdUser user)throws Exception{
		TbSxdUser tbSxdUser=new TbSxdUser();
		BeanUtils.copyProperties(user, tbSxdUser);
		sxdUserDao.save(tbSxdUser);
	}
	
	public boolean checkXyNoIsExist(String xyNo)throws Exception{
		return sxdUserDao.checkXyNoIsExist(xyNo);
	}
	
	public TbSxdUser quereyTbSxdUserById(Long userId)throws Exception{
		return sxdUserDao.get(userId);
	}
	
	public void updateTbSxdUser(TbSxdUser tbSxdUser)throws Exception{
		sxdUserDao.update(tbSxdUser);
	}
}
