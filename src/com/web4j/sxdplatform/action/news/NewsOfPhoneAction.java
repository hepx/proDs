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
import com.web4j.util.SysConfig;
/**
 * 
 * 手机端调用新闻Action
 * @author xixi
 * @date 2012-11-2
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/sxd/phone/news")
public class NewsOfPhoneAction extends AbstractActionSupport {
	
	private static Logger log =Logger.getLogger(NewsOfPhoneAction.class);
	
	@Autowired
	private NewsModel newsModel;
	
	private List news;
	
	private String newsTitle;
	
	private String newsType;
	
	@Action("queryNews")
	public String queryNews(){
		try{
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			News newsvo=new News(newsTitle,newsType);
			news=newsModel.queryNewsOfPhone(newsvo, start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List getNews() {
		return news;
	}

	public void setNews(List news) {
		this.news = news;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	
}
