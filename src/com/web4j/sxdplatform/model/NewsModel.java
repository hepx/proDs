package com.web4j.sxdplatform.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.sxdplatform.dao.NewsDao;
import com.web4j.sxdplatform.entity.TbNews;
import com.web4j.sxdplatform.pojo.News;

@Service("newsModel")
public class NewsModel {

	@Autowired
	private NewsDao newsDao;
	
	public void saveOrUpdateNews(List<News>newsList)throws Exception{
		List<TbNews>tbNewsList=new ArrayList<TbNews>();
		for(News news:newsList){
			TbNews tbNews=new TbNews();
			BeanUtils.copyProperties(news, tbNews);
			tbNewsList.add(tbNews);
		}
		newsDao.saveOrUpdateAll(tbNewsList);
	}
	
	public List<News> queryNews(Integer start,Integer limit)throws Exception{
		List<TbNews> tbNewsList=newsDao.queryTbNews(start, limit);
		List<News> newsList =new ArrayList<News>();
		for(TbNews tbNews :tbNewsList){
			News news=new News();
			BeanUtils.copyProperties(tbNews, news);
			newsList.add(news);
		}
		return newsList;
	}
	
	public Long getNewsCounts(){
		return newsDao.counts();
	}
	
	public void deleteNews(List<News>newsList)throws Exception{
		for(News news : newsList){
			if(news.getNewsId()!=null&&!"".equals(news.getNewsId())){
				newsDao.deleteById(news.getNewsId());
			}
		}
	}
	
	public List<TbNews> queryNewsOfPhone(News news,Integer start,Integer limit) throws Exception{
		TbNews tbNews=new TbNews();
		BeanUtils.copyProperties(news, tbNews);
		return newsDao.quereyTbNewsOfPhone(tbNews, start, limit);
	}
}
