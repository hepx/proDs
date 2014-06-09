package com.web4j.sxdplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.sxdplatform.dao.ArticleDao;
import com.web4j.sxdplatform.entity.TbArticle;
import com.web4j.sxdplatform.pojo.Article;

@Service("articleModel")
public class ArticleModel {

	@Autowired
	private ArticleDao articleDao;
	
	public void saveOrUpdateArticles(List<Article>articles)throws Exception{
		List<TbArticle> tbArticles=new ArrayList<TbArticle>();
		for(Article source : articles){
			TbArticle target =new TbArticle();
			BeanUtils.copyProperties(source, target);
			if(target.getCreateTime()==null){
				target.setCreateTime(new Date());
			}
			tbArticles.add(target);
		}
		articleDao.saveOrUpdateAll(tbArticles);
	}
	
	public List<Article> quereyArticles(Integer start,Integer limit)throws Exception{
		List<TbArticle> tbArticles=articleDao.queryTbArticles(start, limit);
		List<Article> articles =new ArrayList<Article>();
		for(TbArticle source : tbArticles){
			Article target = new Article();
			BeanUtils.copyProperties(source, target);
			articles.add(target);
		}
		return articles;
	}
	
	public Long getArticleCount(){
		return articleDao.counts(); 
	}
	
	public void deleteArticles(List<Article>articles) throws Exception{
		for(Article article : articles){
			if(article.getArticleId()!=null&&!"".equals(article.getArticleId())){
				articleDao.deleteById(article.getArticleId());
			}
		}
	}
	
	public TbArticle quereyTbArticleById(Long articleId)throws Exception{
		return articleDao.get(articleId);
	}
	
	public void updateTbArticle(TbArticle tbArticle)throws Exception{
		articleDao.update(tbArticle);
	}
	
	/***********************手机调用****************************/
	public List<TbArticle> queryArticlesOfPhone(Integer start,Integer limit)throws Exception{
		return articleDao.queryTbArticlesOfPhone(start, limit);
	}
}
