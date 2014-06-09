package com.web4j.sxdplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.sxdplatform.entity.TbBuddhaWeibo;

@Repository("buddhaWeiboDao")
public class BuddhaWeiboDao extends AbstractDao<TbBuddhaWeibo> {

	public List<TbBuddhaWeibo> quereyTbBuddhaWeibos(Integer start,Integer limit)throws Exception{
		String queryString="from TbBuddhaWeibo t order by t.wbId DESC";
		return list(queryString, start, limit);
	}
	
	public List<TbBuddhaWeibo> quereyTbBuddhaWeibeOfPhone(Integer start,Integer limit)throws Exception{
		String queryString = "select new TbBuddhaWeibo(wbName,wbDesc,wbUrl) " +
				"from TbBuddhaWeibo t order by t.wbId DESC";
		return list(queryString,start,limit);
	}
}
