package com.web4j.sxdplatform.pojo;

import java.util.Date;
/**
 * 佛学新闻
 * @author xixi
 * @date 2012-11-1
 *
 */
public class News {

	private Long newsId;

	private String newsTitle;
	
	private String newsDesc;
	
	private Date createTime;
	
	private String createUser;
	
	private String newsUrl;
	
	private String newsType;

	public News (){};
	
	public News(String newsTitle,String newsType){
		this.newsTitle=newsTitle;
		this.newsType=newsType;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsDesc() {
		return newsDesc;
	}

	public void setNewsDesc(String newsDesc) {
		this.newsDesc = newsDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	
	
}
