package com.web4j.sxdplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.sxdplatform.entity.TbSxdUser;

@Repository("sxdUserDao")
public class SxdUserDao extends AbstractDao<TbSxdUser> {
	
	public List<TbSxdUser> quereyTbSxdUsers(Integer start,Integer limit)throws Exception{
		String queryString = "from TbSxdUser t order by t.userId DESC";
		return list(queryString, start, limit);
	}
	
	public boolean checkXyNoIsExist(String xyNo)throws Exception{
		String queryString="from TbSxdUser t where t.xyNo=?";
		List<TbSxdUser>ls=list(queryString, xyNo);
		if(ls!=null&&ls.size()>0){
			return true;
		}else{
			return false;
		}
	}

}
