package com.web4j.sxdplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.sxdplatform.entity.TbNews;

@Repository("newsDao")
public class NewsDao extends AbstractDao<TbNews> {

	public List<TbNews> queryTbNews(Integer start,Integer limit) throws Exception{
		String queryString="from TbNews t order by t.newsId DESC";
		return list(queryString, start, limit);
	}
	
	public List<TbNews> quereyTbNewsOfPhone(TbNews tbNews,Integer start,Integer limit) throws Exception{
		StringBuffer queryString=new StringBuffer("select new TbNews(t.newsTitle,t.newsDesc,t.newsUrl,t.newsType,t.createTime) from TbNews t");
		queryString.append(" where 1=1");
		if(tbNews.getNewsTitle()!=null){
			queryString.append(" and t.newsTitle like '%"+tbNews.getNewsTitle()+"%'");
		}
		if(tbNews.getNewsType()!=null){
			queryString.append(" and t.newsType='"+tbNews.getNewsType()+"'");
		}
		queryString.append(" order by t.newsId DESC");
		
		return list(queryString.toString(), start, limit);
	}
}
