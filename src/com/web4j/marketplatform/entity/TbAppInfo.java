package com.web4j.marketplatform.entity;

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

import com.web4j.bdgplatform.entity.TbBdgAppWall;

@Entity
@Table(name="tb_market_appinfo")
public class TbAppInfo {

	public TbAppInfo(){}

	public TbAppInfo(Long appId, String appName, String appCategory,
			String appVersionName, String appPic, String createUser,
			Date createTime, String appDesc, Integer appVersionCode,
			String appPath,Integer downloads,String packageName,String sdkSupport,
			String appSize,Float fee) {
		this.appId = appId;
		this.appName = appName;
		this.appCategory = appCategory;
		this.appVersionCode = appVersionCode;
		this.appVersionName = appVersionName;
		this.createUser = createUser;
		this.createTime = createTime;
		this.appDesc = appDesc;
		this.appPic = appPic;
		this.appPath = appPath;
		this.downloads = downloads;
		this.packageName = packageName;
		this.sdkSupport = sdkSupport;
		this.appSize = appSize;
		this.fee = fee;
	}
	
	public TbAppInfo(Long appId, String appName, String appCategory,
			String appVersionName, String appPic, String appDesc, Integer appVersionCode,
			String appPath,Integer downloads,String packageName,String sdkSupport,
			String appSize,Float fee) {
		this.appId = appId;
		this.appName = appName;
		this.appCategory = appCategory;
		this.appVersionCode = appVersionCode;
		this.appVersionName = appVersionName;
		this.appDesc = appDesc;
		this.appPic = appPic;
		this.appPath = appPath;
		this.downloads = downloads;
		this.packageName = packageName;
		this.sdkSupport = sdkSupport;
		this.appSize = appSize;
		this.fee = fee;
	}
	
	public TbAppInfo(Long appId, String appName, String appCategory,
			String appVersionName, String appPic,String appPath,String packageName) {
		this.appId=appId;
		this.appName=appName;
		this.appCategory=appCategory;
		this.appVersionName=appVersionName;
		this.appPic=appPic;
		this.appPath=appPath;
		this.packageName=packageName;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long appId;
	
	@Column(length=100,unique=true)
	private String appName;//应用名
	
	@Column(length=30)
	private String createUser;
	
	@Column(updatable=false)
	private Date createTime;
	
	@Column(length=10)
	private String appCategory;//分类
	
	@Column(length=256)
	private String appDesc;//描述
	
	@Column
	private Integer appVersionCode;//版本（升级）
	
	@Column(length=20)
	private String appVersionName;//版本（展示）
	
	@Column(length=200)
	private String appPic;//icon路径
	
	@Column(length=200)
	private String appPath;//apk路径
	
	@Column
	private Integer downloads=0;//下载次数
	
	@Column(length=50,unique=true)
	private String packageName;//app 唯一包名
	
	@Column(length=20)
	private String sdkSupport;//SDK支持
	
	@Column(length=20)
	private String appSize;//apk大小
	
	@Column
	private Float fee=0f;//资费，默认为0免费
	
	@OneToMany(mappedBy="tbAppInfo",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<TbBdgAppWall> tbBdgAppWalls=new HashSet<TbBdgAppWall>();

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public Integer getAppVersionCode() {
		return appVersionCode;
	}

	public void setAppVersionCode(Integer appVersionCode) {
		this.appVersionCode = appVersionCode;
	}

	public String getAppVersionName() {
		return appVersionName;
	}

	public void setAppVersionName(String appVersionName) {
		this.appVersionName = appVersionName;
	}

	public String getAppPic() {
		return appPic;
	}

	public void setAppPic(String appPic) {
		this.appPic = appPic;
	}

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}

	public Integer getDownloads() {
		return downloads;
	}

	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
	}

	public String getAppCategory() {
		return appCategory;
	}

	public void setAppCategory(String appCategory) {
		this.appCategory = appCategory;
	}

	public Set<TbBdgAppWall> getTbBdgAppWalls() {
		return tbBdgAppWalls;
	}

	public void setTbBdgAppWalls(Set<TbBdgAppWall> tbBdgAppWalls) {
		this.tbBdgAppWalls = tbBdgAppWalls;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getSdkSupport() {
		return sdkSupport;
	}

	public void setSdkSupport(String sdkSupport) {
		this.sdkSupport = sdkSupport;
	}

	public String getAppSize() {
		return appSize;
	}

	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}

	public Float getFee() {
		return fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
	}
	

}
