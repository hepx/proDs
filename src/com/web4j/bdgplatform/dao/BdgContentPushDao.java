package com.web4j.bdgplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.bdgplatform.entity.TbBdgContentPush;
import com.web4j.common.dao.AbstractDao;

@Repository("bdgContentPushDao")
public class BdgContentPushDao extends AbstractDao<TbBdgContentPush> {

	public List<TbBdgContentPush> queryTbBdgPushRecords(Integer start,Integer limit)throws Exception{
		String queryString= "from TbBdgContentPush T order by T.pushId DESC";
		return list(queryString, start, limit);
	}
	
	public TbBdgContentPush queryTbBdgPushRecord()throws Exception{
		String queryString=" from TbBdgContentPush T where T.errcode is null and " +
				"T.errmsg is null order by T.pushId ASC limit 1";
		List<TbBdgContentPush> ls=list(queryString);
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}else{
			return null;
		}
	}
	
}
