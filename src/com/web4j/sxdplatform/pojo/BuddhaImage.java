package com.web4j.sxdplatform.pojo;

import java.util.Date;

/**
 * 佛像
 * @author xixi
 * @date 2012-11-7
 *
 */
public class BuddhaImage {
	
	private Long imageId;
	
	private String createUser;
	
	private Date createTime;
	
	private String buddhaImageName;
	
	private String buddhaImageDesc;
	
	private String buddhaImagePath;

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
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

	public String getBuddhaImageName() {
		return buddhaImageName;
	}

	public void setBuddhaImageName(String buddhaImageName) {
		this.buddhaImageName = buddhaImageName;
	}

	public String getBuddhaImageDesc() {
		return buddhaImageDesc;
	}

	public void setBuddhaImageDesc(String buddhaImageDesc) {
		this.buddhaImageDesc = buddhaImageDesc;
	}

	public String getBuddhaImagePath() {
		return buddhaImagePath;
	}

	public void setBuddhaImagePath(String buddhaImagePath) {
		this.buddhaImagePath = buddhaImagePath;
	}
	
}
