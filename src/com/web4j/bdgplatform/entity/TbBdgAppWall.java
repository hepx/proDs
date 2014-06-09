package com.web4j.bdgplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.web4j.marketplatform.entity.TbAppInfo;

@Entity
@Table(name="tb_bdg_appwall")
public class TbBdgAppWall {
	
	public TbBdgAppWall(){}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long appWallId;

	@Column(length=30)
	private String createUser;
	
	@Column(updatable=false)
	private Date createTime;
	
	@Column
	private Integer downloads=0;
	
	@Column
	private Integer sort;
	
	@Column(columnDefinition="bit default 0")
	private Boolean isPush;
	
	@ManyToOne(targetEntity=TbAppInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="appId",unique=true)
	private TbAppInfo tbAppInfo;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getAppWallId() {
		return appWallId;
	}

	public void setAppWallId(Long appWallId) {
		this.appWallId = appWallId;
	}

	public TbAppInfo getTbAppInfo() {
		return tbAppInfo;
	}

	public void setTbAppInfo(TbAppInfo tbAppInfo) {
		this.tbAppInfo = tbAppInfo;
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

	public Integer getDownloads() {
		return downloads;
	}

	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}

	public Boolean getIsPush() {
		return isPush;
	}

	public void setIsPush(Boolean isPush) {
		this.isPush = isPush;
	}
}
