package com.web4j.bdgplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.bdgplatform.entity.TbBdgAppPush;
import com.web4j.common.dao.AbstractDao;

@Repository("bdgAppPushDao")
public class BdgAppPushDao extends AbstractDao<TbBdgAppPush> {

	public List<TbBdgAppPush> queryTbBdgPushApps(Integer start,Integer limit)throws Exception{
		String queryString= "from TbBdgAppPush T order by T.pushId DESC";
		return list(queryString, start, limit);
	}
	
	public TbBdgAppPush queryTbBdgPushApp()throws Exception{
		String queryString=" from TbBdgAppPush T where T.errcode is null and " +
				"T.errmsg is null order by T.pushId ASC limit 1";
		List<TbBdgAppPush> ls=list(queryString);
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}else{
			return null;
		}
	}
	
}
