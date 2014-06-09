package com.web4j.systemplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.web4j.common.entity.BaseEntity;

@Entity
@Table(name="tb_billInfo")
public class TbBillInfo extends BaseEntity {
	
	private static final long serialVersionUID = -5459926350401337566L;

	public TbBillInfo(){}
	
	public TbBillInfo(Long id){
		super.setId(id);
	}
	
	@Column(name="mobile",length=11)
	private String mobile;//手机号
	
	@Column(name="service",length=10)
	private String service;//服务商
	
	@Column(name="code",length=30)
	private String code;//指令
	
	@Column(name="cvsName",length=30)
	private String cvsName;//端口
	
	@Column(name="linkId",unique=true,length=30)
	private String linkId;//短信唯一标识
	
	@Column(name="createTime")
	private Date createTime;//创建时间
	
	@Column(name="province",length=20)
	private String province;//省份
	
	@Column(name="city",length=20)
	private String city;//城市
	
	@Column(name="fee")
	private Float fee;//资费
	
	@Column(name="projectCode",length=20)
	private String projectCode;//项目号

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
