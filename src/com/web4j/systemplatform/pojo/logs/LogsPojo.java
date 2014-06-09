package com.web4j.systemplatform.pojo.logs;

import java.io.Serializable;
import java.util.Date;

public class LogsPojo implements Serializable {

	private static final long serialVersionUID = -6206988158888862990L;

	private Long id; 
	
	private String user;
	
	private String ip;
	
	private Date createTime;
	
	private String logs;
	
	public LogsPojo(){}
	
	public LogsPojo(Long id,String user,String ip,Date createTime,String logs){
		this.id=id;
		this.user=user;
		this.ip=ip;
		this.createTime=createTime;
		this.logs=logs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
