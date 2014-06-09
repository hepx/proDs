package com.web4j.sxdplatform.pojo;

import java.util.Date;

/**
 * 佛学常识
 * @author xixi
 * @date 2012-11-1
 *
 */
public class BuddhismLore {

	private Long loreId;
	
	private String createUser;
	
	private Date createTime;
	
	private String loreTitle;
	
	private String loreContent;
	
	private String loreType;
	
	public BuddhismLore(){}
	
	public BuddhismLore(String loreTitle,String loreType){
		this.loreTitle=loreTitle;
		this.loreType=loreType;
	}

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
