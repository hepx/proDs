package com.web4j.sxdplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.sxdplatform.entity.TbPrayRecord;

@Repository("prayRecordDao")
public class PrayRecordDao extends AbstractDao<TbPrayRecord> {

	public List<TbPrayRecord> quereyTbPrayRecords(Integer start,Integer limit) throws Exception{
		String queryString="from TbPrayRecord t order by t.prayId DESC";
		return list(queryString, start, limit);
	}
	
	public List<TbPrayRecord> quereyTbPrayRecordsByUser(String xyNo,String prayType,Integer start,Integer limit) throws Exception{
		String queryString="from TbPrayRecord t where t.xyNo=? and t.prayType=? order by t.prayId DESC";
		return list(queryString, new String[]{xyNo,prayType}, start, limit);
	}
	
	public List statByPrayType(String xyNo)throws Exception {
		String queryString = "select t.prayType as prayType,count(*) as total"
							+ " from TbPrayRecord t where t.xyNo=?"
							+ " group by t.prayType";
		return list(queryString, xyNo);
	}
}