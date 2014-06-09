package com.web4j.sxdplatform.action.news;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.sxdplatform.model.NewsModel;
import com.web4j.sxdplatform.pojo.News;

@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/sxd/news")
public class NewsAction extends AbstractActionSupport {

	private static Logger log=Logger.getLogger(NewsAction.class);
	
	@Autowired
	private NewsModel newsModel;
	
	private List<News> newsList;

	@Action("queryNews")
	public String queryNews(){
		try{
			newsList=newsModel.queryNews(start, limit);
			if(total==null){
				total=newsModel.getNewsCounts();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateNews")
	public String saveOrUpdateNews(){
		try{
			newsModel.saveOrUpdateNews(newsList);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("deleteNews")
	public String deleteNews(){
		try{
			newsModel.deleteNews(newsList);
		}catch(Exception e){
			setErrorMessage(e.getClass().getSimpleName());
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	
}
