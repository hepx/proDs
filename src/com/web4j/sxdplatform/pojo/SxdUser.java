package com.web4j.sxdplatform.pojo;

import java.util.Date;

import com.web4j.util.MD5Util;


/**
 * 神香道用户信息
 * @author xixi
 * @date 2012-11-10
 *
 */
public class SxdUser {

	private Long userId;
	
	private String xyNo;//香缘号
	
	private String password;
	
	private String realName;//真实姓名
	
	private String sex;//姓别
	
	private Integer age;//年龄
	
	private String city;//城市
	
	private String mobile;//手机号
	
	private String qq;//QQ号
	
	private String email;//邮箱
	
	private String avatar;//头像
	
	private Date createTime;
	
	public SxdUser(){}
	
	public SxdUser(String xyNo,String password){
		this.xyNo=xyNo;
		this.password=MD5Util.MD5(password);
		this.createTime=new Date();
	}

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
