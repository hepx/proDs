package com.web4j.sxdplatform.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_sxd_article")
public class TbArticle {

	public TbArticle(){}
	
	public TbArticle(Long articleId){
		this.articleId=articleId;
	}
	
	public TbArticle(Long articleId,String articleName,String articleDesc,String articlePath,String articleType){
		this.articleId=articleId;
		this.articleName=articleName;
		this.articleDesc=articleDesc;
		this.articlePath=articlePath;
		this.articleType=articleType;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long articleId;
	
	@Column(length=20,nullable=false)
	private String createUser;
	
	@Column
	private Date createTime;
	
	@Column(length=100,nullable=false)
	private String articleName;
	
	@Column
	private String articleDesc;
	
	@Column(length=100)
	private String articlePath;
	
	@Column(length=30)
	private String articleType;
	
	@OneToMany(mappedBy="tbArticle",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<TbPrayRecord> tbPrayRecords=new HashSet<TbPrayRecord>();

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getArticleDesc() {
		return articleDesc;
	}

	public void setArticleDesc(String articleDesc) {
		this.articleDesc = articleDesc;
	}

	public String getArticlePath() {
		return articlePath;
	}

	public void setArticlePath(String articlePath) {
		this.articlePath = articlePath;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public Set<TbPrayRecord> getTbPrayRecords() {
		return tbPrayRecords;
	}

	public void setTbPrayRecords(Set<TbPrayRecord> tbPrayRecords) {
		this.tbPrayRecords = tbPrayRecords;
	}
	
}
