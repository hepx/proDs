package com.web4j.systemplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.web4j.common.entity.BaseEntity;

/**
 * 日志
 * @author hepx
 *
 */
@Entity
@Table(name="tb_logs")
public class TbLogs extends BaseEntity {

	private static final long serialVersionUID = -7246990180707139948L;
	
	@Column(name="user",length=50)
	private String user;
	
	@Column(name="ip",length=20)
	private String ip;
	
	@Column(name="createTime")
	private Date createTime;
	
	@Column(name="logs",length=255)
	private String logs;
	
	public TbLogs(){}
	public TbLogs(Long id){
		super.setId(id);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}
	
	
}
