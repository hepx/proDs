package com.web4j.sxdplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_sxd_user")
public class TbSxdUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column(length=8,nullable=false,unique=true)
	private String xyNo;//香缘号
	
	@Column(length=40,nullable=false,updatable=false)
	private String password;
	
	@Column(length=20)
	private String realName;//真实姓名
	
	@Column(length=1)
	private String sex;//姓别
	
	@Column
	private Integer age;//年龄
	
	@Column(length=30)
	private String city;//城市
	
	@Column(length=11)
	private String mobile;//手机号
	
	@Column(length=15)
	private String qq;//QQ号
	
	@Column(length=30)
	private String email;//邮箱
	
	@Column(length=100)
	private String avatar;//头像
	
	@Column
	private Date createTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getXyNo() {
		return xyNo;
	}

	public void setXyNo(String xyNo) {
		this.xyNo = xyNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
