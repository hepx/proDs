package com.web4j.sxdplatform.pojo;

import java.util.Date;

/**
 * 祈福记录
 * @author xixi
 * @date 2012-11-9
 *
 */
public class PrayRecord {

	private Long prayId;
	
	private Date createTime;
	
	private String xyNo;
	
	private String prayType;//祈福类型
	
	private String meritContent;//功德内容
	
	//private String buddhaImageIdId;//祈福佛像ID
	
	private String buddhaName;//祈福佛像名
	
	private String buddhaImagePath;//祈福佛像路径
	
	//private String articleId;//供奉物品ID
	
	private String articleName;//供奉物品名
	
	private String articlePath;//供奉物品图像路径
	
	private String restoreStatus="0";//还原状态 0和1表示 0表示未还原
	
	public PrayRecord(){}
	
	public PrayRecord(String xyNo,String prayType,String meritContent){
		this.xyNo = xyNo;
		this.prayType = prayType;
		this.meritContent = meritContent;
		this.createTime  = new Date();
 	}

	public Long getPrayId() {
		return prayId;
	}

	public void setPrayId(Long prayId) {
		this.prayId = prayId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getXyNo() {
		return xyNo;
	}

	public void setXyNo(String xyNo) {
		this.xyNo = xyNo;
	}

	public String getPrayType() {
		return prayType;
	}

	public void setPrayType(String prayType) {
		this.prayType = prayType;
	}

	public String getMeritContent() {
		return meritContent;
	}

	public void setMeritContent(String meritContent) {
		this.meritContent = meritContent;
	}

	public String getRestoreStatus() {
		return restoreStatus;
	}

	public void setRestoreStatus(String restoreStatus) {
		this.restoreStatus = restoreStatus;
	}

	public String getBuddhaName() {
		return buddhaName;
	}

	public void setBuddhaName(String buddhaName) {
		this.buddhaName = buddhaName;
	}

	public String getBuddhaImagePath() {
		return buddhaImagePath;
	}

	public void setBuddhaImagePath(String buddhaImagePath) {
		this.buddhaImagePath = buddhaImagePath;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getArticlePath() {
		return articlePath;
	}

	public void setArticlePath(String articlePath) {
		this.articlePath = articlePath;
	}
	
}
