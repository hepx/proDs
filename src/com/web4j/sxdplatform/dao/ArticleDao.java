package com.web4j.sxdplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.sxdplatform.entity.TbArticle;

@Repository("tbArticleDao")
public class ArticleDao extends AbstractDao<TbArticle> {

	public List<TbArticle> queryTbArticles(Integer start,Integer limit)throws Exception{
		String queryString="from TbArticle t order by t.articleId DESC";
		return list(queryString, start, limit);
	}
	
	public List<TbArticle> queryTbArticlesOfPhone(Integer start,Integer limit)throws Exception{
		String queryString = "select new TbArticle(articleId,articleName,articleDesc,articlePath,articleType)" +
				"from TbArticle t order by t.articleType,t.articleId DESC";
		return list(queryString,start,limit);
	}
}
