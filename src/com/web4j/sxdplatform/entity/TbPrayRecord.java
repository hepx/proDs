package com.web4j.sxdplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_sxd_prayrecord")
public class TbPrayRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long prayId;
	
	@Column
	private Date createTime;
	
	@Column(length=20,nullable=false)
	private String xyNo;
	
	@Column(length=30)
	private String prayType;//祈福类型
	
	@Column(length=500)
	private String meritContent;//功德内容
	
	@Column(length=1)
	private String restoreStatus="0";//还原状态 0和1表示 0表示未还原
	
	@ManyToOne(targetEntity=TbBuddhaImage.class,fetch=FetchType.LAZY)
	@JoinColumn(name="buddhaImageIdId")
	private TbBuddhaImage tbBuddhaImage;//祈福佛像
	
	@ManyToOne(targetEntity=TbArticle.class,fetch=FetchType.LAZY)
	@JoinColumn(name="articleId")
	private TbArticle tbArticle;//供奉物品

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

	public TbBuddhaImage getTbBuddhaImage() {
		return tbBuddhaImage;
	}

	public void setTbBuddhaImage(TbBuddhaImage tbBuddhaImage) {
		this.tbBuddhaImage = tbBuddhaImage;
	}

	public TbArticle getTbArticle() {
		return tbArticle;
	}

	public void setTbArticle(TbArticle tbArticle) {
		this.tbArticle = tbArticle;
	}
	
	
}
