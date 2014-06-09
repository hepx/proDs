package com.web4j.sxdplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.sxdplatform.entity.TbBuddhaMusic;

@Repository("buddhaMusicDao")
public class BuddhaMusicDao extends AbstractDao<TbBuddhaMusic> {

	public List<TbBuddhaMusic>queryTbBuddhaMusics(Integer start,Integer limit) throws Exception{
		String queryString = "from TbBuddhaMusic t order by t.musicId DESC";
		return list(queryString, start, limit);
	}
	
	/**
	 * 分类查询佛音
	 * @param musicType
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<TbBuddhaMusic> queryTbBuddhaMusicByType(String musicType,Integer start,Integer limit)throws Exception{
		String queryString = "select new TbBuddhaMusic(musicTitle,author,musicPath,lyricPath,musicType) " +
				"from TbBuddhaMusic t where t.musicType=? order by t.musicId DESC";
		return list(queryString, musicType, start, limit);
	}
	
	/**
	 * 分组统计佛音
	 * @return
	 * @throws Exception
	 */
	public List<TbBuddhaMusic> statTbBuddhaMusicByType() throws Exception{
		String queryString = "select new Map(t.musicType,sum(t.musicType))from TbBuddhaMusic t group by t.musicType order by t.musicType";
		return list(queryString);
	}
}
