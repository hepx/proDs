package com.web4j.sxdplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_sxd_version")
public class TbSxdVersion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long versionId;
	
	@Column(length=20,nullable=false)
	private String createUser;
	
	@Column
	private Date createTime;
	
	@Column(nullable=false,unique=true)
	private Integer internalVersion;
	
	@Column(length=20)
	private String version;
	
	@Column(length=500)
	private String versionDesc;
	
	@Column(length=100)
	private String apkPath;
	
	@Column
	private Integer downLoads;

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
