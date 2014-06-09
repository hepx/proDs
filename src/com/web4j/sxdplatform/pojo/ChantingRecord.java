package com.web4j.sxdplatform.pojo;

import java.util.Date;

/**
 * 诵经记录
 * @author xixi
 * @date 2012-11-1
 *
 */
public class ChantingRecord {

	private Long chantRecordId;
	
	private Date createTime;
	
	private String xyNo;//香缘号
	
	private String chantingBook;//诵经的经书。
	
	private String meritContent;//功德内容
	
	private String restoreStatus="0";//还原状态 0和1表示 0表示未还原
	
	private String restoreContent;//还原内容
	
	public ChantingRecord(){}
	
	public ChantingRecord(String xyNo,String chantingBook,String meritContent){
		this.xyNo = xyNo;
		this.chantingBook = chantingBook;
		this.meritContent=meritContent;
	}

	public Long getChantRecordId() {
		return chantRecordId;
	}

	public void setChantRecordId(Long chantRecordId) {
		this.chantRecordId = chantRecordId;
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

	public String getChantingBook() {
		return chantingBook;
	}

	public void setChantingBook(String chantingBook) {
		this.chantingBook = chantingBook;
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

	public String getRestoreContent() {
		return restoreContent;
	}

	public void setRestoreContent(String restoreContent) {
		this.restoreContent = restoreContent;
	}
	
	
}
