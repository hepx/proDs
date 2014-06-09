package com.web4j.bdgplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.bdgplatform.entity.TbBdgBrRecord;
import com.web4j.common.dao.AbstractDao;

@Repository("bdgBrRecordDao")
public class BdgBrRecordDao extends AbstractDao<TbBdgBrRecord> {

	public List<TbBdgBrRecord> queryTbBdgBrRecords(Integer start,Integer limit)throws Exception{
		String queryString ="from TbBdgBrRecord t order by t.recordId DESC";
		return list(queryString, start, limit);
	}
	
	public TbBdgBrRecord getBdgBrRecord(Integer recordStatus) throws Exception{
		String queryString = "from TbBdgBrRecord t where t.recordStatus="+recordStatus
							+ " order by t.recordId ASC limit 1";
		List<TbBdgBrRecord> ls=list(queryString);
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}else{
			return null;
		}
	}
	
}
