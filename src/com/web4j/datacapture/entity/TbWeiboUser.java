package com.web4j.datacapture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_captrue_weibouser")
public class TbWeiboUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;//自增
	
	@Column(length=30,nullable=false,unique=true)
	private String uid;//微博ID
	
	@Column(length=30)
	private String screenName;//微博名
	
	@Column(nullable=false)
	private Integer feature=0;//过滤特征
	
	@Column(length=30)
	private String maxRecordId="0";//最大记录ID
	
	@Column
	private Integer limits=20;//拉取数量
	
	@Column(length=1)
	private String type;//类型，比如 1：sina 2: tx
	
	@Column(length=1)
	private String appId;//指定为那个APP的数据
	
	@Column
	private Boolean status=false;//是否启动

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Integer getFeature() {
		return feature;
	}

	public void setFeature(Integer feature) {
		this.feature = feature;
	}
	
	public Integer getLimits() {
		return limits;
	}

	public void setLimits(Integer limits) {
		this.limits = limits;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMaxRecordId() {
		return maxRecordId;
	}

	public void setMaxRecordId(String maxRecordId) {
		this.maxRecordId = maxRecordId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
