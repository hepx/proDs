package com.web4j.datacapture.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.datacapture.entity.TbWeiboRecord;

@Repository("weiboRecordDao")
public class WeiboRecordDao extends AbstractDao<TbWeiboRecord> {

	public List<TbWeiboRecord> queryTbWeiboRecords(Integer start,Integer limit)throws Exception{
		String queryString ="from TbWeiboRecord t order by t.recordId DESC";
		return list(queryString, start, limit);
	}

}
