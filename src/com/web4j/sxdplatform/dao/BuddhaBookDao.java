package com.web4j.sxdplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.sxdplatform.entity.TbBuddhaBook;

@Repository("buddhaBookDao")
public class BuddhaBookDao extends AbstractDao<TbBuddhaBook> {

	public List<TbBuddhaBook> queryTbBuddhaBooks(Integer start,Integer limit) throws Exception{
		String queryString="from TbBuddhaBook t order by t.bookId DESC";
		return list(queryString, start, limit);
	}
	
	public List<TbBuddhaBook> queryTbBuddhaBooksOfPhone(Integer start,Integer limit)throws Exception{
		String queryString="select new TbBuddhaBook(t.bookTitle,t.bookImagePath,t.bookPath,t.backMusicPath) " +
				"from TbBuddhaBook t order by t.bookId DESC";
		return list(queryString, start, limit);
	}
}
