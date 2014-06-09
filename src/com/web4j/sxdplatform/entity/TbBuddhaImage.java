package com.web4j.sxdplatform.entity;

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

@Entity
@Table(name="tb_sxd_buddhaimage")
public class TbBuddhaImage {

	public TbBuddhaImage(){}
	
	public TbBuddhaImage(Long imageId){
		this.imageId=imageId;
	}
	
	public TbBuddhaImage(Long imageId,String buddhaImageName,String buddhaImageDesc,String buddhaImagePath){
		this.imageId=imageId;
		this.buddhaImageName=buddhaImageName;
		this.buddhaImageDesc=buddhaImageDesc;
		this.buddhaImagePath=buddhaImagePath;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long imageId;
	
	@Column(length=20,nullable=false)
	private String createUser;
	
	@Column
	private Date createTime;
	
	@Column(length=50,nullable=false)
	private String buddhaImageName;
	
	@Column
	private String buddhaImageDesc;
	
	@Column(length=100)
	private String buddhaImagePath;
	
	@OneToMany(mappedBy="tbBuddhaImage",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<TbPrayRecord> tbPrayRecords=new HashSet<TbPrayRecord>(0);

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

	public Set<TbPrayRecord> getTbPrayRecords() {
		return tbPrayRecords;
	}

	public void setTbPrayRecords(Set<TbPrayRecord> tbPrayRecords) {
		this.tbPrayRecords = tbPrayRecords;
	}
	
}
