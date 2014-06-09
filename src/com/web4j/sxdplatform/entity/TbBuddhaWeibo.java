package com.web4j.sxdplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_sxd_buddhaweibo")
public class TbBuddhaWeibo {
	
	public TbBuddhaWeibo(){}
	
	public TbBuddhaWeibo(String wbName,String wbDesc,String wbUrl){
		this.wbName=wbName;
		this.wbDesc=wbDesc;
		this.wbUrl=wbUrl;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long wbId;
	
	@Column(length=50,nullable=false)
	private String wbName;
	
	@Column
	private String wbDesc;
	
	@Column(length=100,nullable=false)
	private String wbUrl;
	
	@Column(length=20,nullable=false)
	private String createUser;
	
	@Column
	private Date createTime;

	public Long getWbId() {
		return wbId;
	}

	public void setWbId(Long wbId) {
		this.wbId = wbId;
	}

	public String getWbName() {
		return wbName;
	}

	public void setWbName(String wbName) {
		this.wbName = wbName;
	}

	public String getWbDesc() {
		return wbDesc;
	}

	public void setWbDesc(String wbDesc) {
		this.wbDesc = wbDesc;
	}

	public String getWbUrl() {
		return wbUrl;
	}

	public void setWbUrl(String wbUrl) {
		this.wbUrl = wbUrl;
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
	
	
}
