package com.web4j.systemplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.web4j.common.entity.BaseEntity;
import com.web4j.qdtgplatform.entity.TbQdtgCvs;
/**
 * 用户表
 * @author hepx
 * @date 2010-12-5
 */
@Entity
@Table(name="tb_userInfo")
public class TbUserInfo extends BaseEntity {

	private static final long serialVersionUID = 1933270317626717607L;

	public TbUserInfo(){};
	
	public TbUserInfo(Long id){
		super.setId(id);
	}
	
	@Column(name="userName",nullable=true,length=200,unique=true)
	private String userName;
	
	@Column(name="password",nullable=true,length=50)
	private String password;
	
	@Column(name="createTime")
	private Date createTime;
	
	@Column(name="updateTime")
	private Date updateTime;
	
	@Column(name="phone")
	private String phone;//联系方式
	
	@Column(name="email")
	private String email;//邮箱地址
	
	@Column(name="state",length=4)
	private String state;//用户状态
	
	@ManyToOne(targetEntity=TbRole.class,fetch=FetchType.LAZY)
	@JoinColumn(name="roleId")
	private TbRole tbRole;//所属角色
	
	@OneToOne(mappedBy="tbUserInfo",fetch=FetchType.LAZY)
	private TbQdtgCvs tbQdtgCvs;//渠道客户

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public TbRole getTbRole() {
		return tbRole;
	}

	public void setTbRole(TbRole tbRole) {
		this.tbRole = tbRole;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TbQdtgCvs getTbQdtgCvs() {
		return tbQdtgCvs;
	}

	public void setTbQdtgCvs(TbQdtgCvs tbQdtgCvs) {
		this.tbQdtgCvs = tbQdtgCvs;
	}
}
