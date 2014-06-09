package com.web4j.systemplatform.dao.userInfo;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.systemplatform.entity.TbUserInfo;

@Repository("userInfoDao")
public class UserInfoDao extends AbstractDao<TbUserInfo> {

	public void saveOrUpdateUserInfo(TbUserInfo tbUserInfo)throws Exception{
		saveOrUpdate(tbUserInfo);
	}
	
	public void updateUserInfo(TbUserInfo tbUserInfo)throws Exception{
		update(tbUserInfo);
	}
	
	public TbUserInfo findTbUserInfo(TbUserInfo tbUserInfo){
		List<TbUserInfo> list=list(tbUserInfo);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public void deleteUserInfo(Long id)throws Exception{
		deleteById(id);
	}
	
	public List<TbUserInfo> queryUserInfoList()throws Exception{
		return listAll();
	}
	/**
	 * 检查用户名是否以注册
	 * @author : hepx 
	 * @date : 2011-3-15上午11:12:02
	 * @description : TODO
	 * @param userName
	 * @return
	 * @throws Exception : Boolean
	 */
	public Boolean isExist(String userName)throws Exception{
		Boolean isE=false;
		String hql="from TbUserInfo T where T.userName=?";
		List<TbUserInfo>lists=list(hql, new String[]{userName});
		if(lists!=null&&lists.size()>0){
			isE=true;
		}
		return isE;
	}
	
	public void modifyPwd(Long userId,String pwd)throws Exception{
		String sql="update tb_userInfo T set T.password=? where T.id=?";
		SQLQuery sqlQuery=getSession().createSQLQuery(sql);
		sqlQuery.setString(0, pwd);
		sqlQuery.setLong(1,userId);
		sqlQuery.executeUpdate();
	}
}
