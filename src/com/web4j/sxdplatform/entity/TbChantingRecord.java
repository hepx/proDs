package com.web4j.sxdplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_sxd_chantingrecord")
public class TbChantingRecord {

	public TbChantingRecord(){}
	
	public TbChantingRecord(Long chantRecordId){
		this.chantRecordId=chantRecordId;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long chantRecordId;
	
	@Column
	private Date createTime;
	
	@Column(length=8,nullable=false)
	private String xyNo;
	
	@Column(length=100)
	private String chantingBook;
	
	@Column
	private String meritContent;
	
	@Column(length=1)
	private String restoreStatus;
	
	@Column
	private String restoreContent;

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
