package com.web4j.systemplatform.pojo.bill;

import java.util.Date;

/**
 * 
 * @author : hepx
 * @date : 2011-5-11下午05:51:13
 * @classname : BillInfo
 * @description : 帐单
 *
 */
public class BillInfo {
	private Long id;
	
	private String mobile;//手机号
	
	private String service;//服务商
	
	private String code;//指令
	
	private String cvsName;//客户
	
	private String linkId;//短信唯一标识
	
	private Date createTime;//创建时间
	
	private String province;//省份
	
	private String city;//城市
	
	private Float fee;//资费
	
	private String projectCode;//项目号

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCvsName() {
		return cvsName;
	}

	public void setCvsName(String cvsName) {
		this.cvsName = cvsName;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Float getFee() {
		return fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
}
