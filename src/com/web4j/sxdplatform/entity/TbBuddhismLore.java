package com.web4j.sxdplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_sxd_lore")
public class TbBuddhismLore {

	public TbBuddhismLore(){}
	
	public TbBuddhismLore(Long loreId){
		this.loreId=loreId;
	}
	
	public TbBuddhismLore(Long loreId,String loreTitle,String loreType){
		this.loreId=loreId;
		this.loreTitle=loreTitle;
		this.loreType=loreType;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long loreId;
	
	@Column(length=20,nullable=false)
	private String createUser;
	
	@Column
	private Date createTime;
	
	@Column(length=100,nullable=false)
	private String loreTitle;
	
	@Column(columnDefinition="text")
	private String loreContent;
	
	@Column(length=30,nullable=false)
	private String loreType;

	public Long getLoreId() {
		return loreId;
	}

	public void setLoreId(Long loreId) {
		this.loreId = loreId;
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

	public String getLoreTitle() {
		return loreTitle;
	}

	public void setLoreTitle(String loreTitle) {
		this.loreTitle = loreTitle;
	}

	public String getLoreContent() {
		return loreContent;
	}

	public void setLoreContent(String loreContent) {
		this.loreContent = loreContent;
	}

	public String getLoreType() {
		return loreType;
	}

	public void setLoreType(String loreType) {
		this.loreType = loreType;
	}
	
}
