package com.web4j.systemplatform.pojo.userInfo;

import java.io.Serializable;
import java.util.Date;

public class UserInfoPojo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id=null;
	
	private String userName;
	
	private String password;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String state;
	
	private String roleName;
	
	private Integer roleType;
	
	private Long roleId;
	
	private String phone;
	
	private String email;
	
	private String randCode;//验证码

	public UserInfoPojo(){}
	
	public UserInfoPojo(Long id,String userName,Date createTime,Date updateTime,String state,String roleName,Long roleId,String phone,String email){
		this.id=id;
		this.userName=userName;
		this.createTime=createTime;
		this.updateTime=updateTime;
		this.state=state;
		this.roleName=roleName;
		this.roleId=roleId;
		this.phone=phone;
		this.email=email;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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

	public String getRandCode() {
		return randCode;
	}

	public void setRandCode(String randCode) {
		this.randCode = randCode;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
	
}
