package com.web4j.bdgplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.bdgplatform.entity.TbBdgArRecord;
import com.web4j.common.dao.AbstractDao;

@Repository("bdgArRecordDao")
public class BdgArRecordDao extends AbstractDao<TbBdgArRecord> {

	private static String phone_sql ="select new TbBdgArRecord(recordId,recordType,screenName,text,"
			+ "pic,tops,treads,collects,shares,releaseTime) from TbBdgArRecord t";
	
	public List<TbBdgArRecord> queryTbBdgArRecords(Integer start,Integer limit)throws Exception{
		String queryString ="from TbBdgArRecord t order by t.recordId DESC";
		return list(queryString, start, limit);
	}
	
	public List<TbBdgArRecord>queryTbBdgArRecordsOfPhone(Integer start,Integer limit)throws Exception{
		String queryString=phone_sql+" order by t.recordId DESC";
		return list(queryString,start,limit);
	}
	
	public List<TbBdgArRecord>queryPreTbBdgArRecordsOfPhone(Integer start,Integer limit,Long since_id)throws Exception{
		String queryString=phone_sql;
		if(since_id!=null&&since_id>0){
			queryString+=" where t.recordId>"+since_id;
		}
		queryString+=" order by t.recordId DESC";
		return list(queryString,start,limit);
	}
	
	public List<TbBdgArRecord>queryNextTbBdgArRecordsOfPhone(Integer start,Integer limit,Long max_id)throws Exception{
		String queryString=phone_sql;
		if(max_id!=null&&max_id>0){
			queryString+=" where t.recordId<="+max_id;
		}
		queryString+=" order by t.recordId DESC";
		return list(queryString,start,limit);
	}
	
	public List<TbBdgArRecord> orderTopOfPhone(Integer start,Integer limit) throws Exception{
		String queryString=phone_sql+" order by tops DESC,recordId DESC";
		return list(queryString,start,limit);
	}
	
	public List<TbBdgArRecord> orderTreadOfPhone(Integer start,Integer limit) throws Exception{
		String queryString=phone_sql+" order by treads DESC,recordId DESC";
		return list(queryString,start,limit);
	}
	
	public List<TbBdgArRecord> orderCollectOfPhone(Integer start,Integer limit) throws Exception{
		String queryString=phone_sql+" order by collects DESC,recordId DESC";
		return list(queryString,start,limit);
	}
	
	public List<TbBdgArRecord> orderShareOfPhone(Integer start,Integer limit) throws Exception{
		String queryString=phone_sql+" order by shares DESC,recordId DESC";
		return list(queryString,start,limit);
	}
	
}
