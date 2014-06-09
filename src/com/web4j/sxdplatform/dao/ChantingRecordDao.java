package com.web4j.sxdplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.sxdplatform.entity.TbChantingRecord;

@Repository("chantingRecordDao")
public class ChantingRecordDao extends AbstractDao<TbChantingRecord> {

	public List<TbChantingRecord> queryTbChantingRecords(Integer start,Integer limit) throws Exception{
		String queryString = "from TbChantingRecord t order by t.chantRecordId DESC";
		return list(queryString, start, limit);
	}
	
	/**
	 * 按月统计诵经计录
	 * @param xyNo
	 * @return
	 * @throws Exception
	 */
	public List statByMonth(String xyNo)throws Exception{
		String queryString = "select date_format(t.createTime,'%Y-%m') as months,t.chantingBook as chantingBook,count(*) as total"
							+ " from TbChantingRecord t where t.xyNo=?" 
							+ " group by date_format(t.createTime,'%Y-%m'),t.chantingBook"
							+ " order by date_format(t.createTime,'%Y-%m') DESC,t.chantingBook";
		return list(queryString,xyNo);
	}
	/**
	 * 按月，香缘号，书，查询详细诵经信息
	 * @param xyNo
	 * @param month
	 * @param chantingBook
	 * @return
	 * @throws Exception
	 */
	public List<TbChantingRecord>queryDetails(String xyNo,String month,String chantingBook)throws Exception{
		String queryString = "from TbChantingRecord t" 
							+ " where t.xyNo=? and t.chantingBook=? and date_format(t.createTime,'%Y-%m')=?"
							+ " order by t.chantRecordId";
		return list(queryString, new String[]{xyNo,chantingBook,month});
	}
}
