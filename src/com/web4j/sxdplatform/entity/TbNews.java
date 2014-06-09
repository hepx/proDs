package com.web4j.sxdplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_sxd_news")
public class TbNews {

	public TbNews(){}
	
	public TbNews(Long newsId){
		this.newsId=newsId;
	}
	
	public TbNews(String newsTitle,String newsDesc,String newsUrl,String newsType,Date createTime){
		this.newsTitle=newsTitle;
		this.newsDesc=newsDesc;
		this.newsUrl=newsUrl;
		this.newsType=newsType;
		this.createTime=createTime;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long newsId;
	
	@Column(length=100,nullable=false)
	private String newsTitle;
	
	@Column
	private String newsDesc;
	
	@Column
	private Date createTime;
	
	@Column(length=20,nullable=false)
	private String createUser;
	
	@Column(length=100,nullable=false)
	private String newsUrl;
	
	@Column(length=30,nullable=false)
	private String newsType;

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

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	
}
