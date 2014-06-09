package com.web4j.sxdplatform.pojo;

import java.util.Date;

public class SxdVersion {

	private Long versionId;
	
	private String createUser;
	
	private Date createTime;
	
	private Integer internalVersion;
	
	private String version;
	
	private String versionDesc;
	
	private String apkPath;
	
	private Integer downLoads=0;

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
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

	public Integer getInternalVersion() {
		return internalVersion;
	}

	public void setInternalVersion(Integer internalVersion) {
		this.internalVersion = internalVersion;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersionDesc() {
		return versionDesc;
	}

	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}

	public String getApkPath() {
		return apkPath;
	}

	public void setApkPath(String apkPath) {
		this.apkPath = apkPath;
	}

	public Integer getDownLoads() {
		return downLoads;
	}

	public void setDownLoads(Integer downLoads) {
		this.downLoads = downLoads;
	}
	
	
}
