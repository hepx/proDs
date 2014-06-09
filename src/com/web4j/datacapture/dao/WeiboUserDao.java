package com.web4j.datacapture.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.datacapture.entity.TbWeiboUser;

@Repository("weiboUserDao")
public class WeiboUserDao extends AbstractDao<TbWeiboUser> {
	
	public List<TbWeiboUser> queryTbWeiboUsers(Integer start,Integer limit) throws Exception{
		String queryString = "from TbWeiboUser t order by t.userId DESC";
		return list(queryString, start, limit);
	}
	
	public List<TbWeiboUser> queryAllTbWeiboUser() throws Exception{
		String queryString = "from TbWeiboUser t where t.status=1 order by t.type";
		return list(queryString);
	}
}
