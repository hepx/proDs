package com.web4j.systemplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.web4j.common.entity.BaseEntity;

/**
 * 手机用户
 * @author : hepx
 * @date : 2011-4-8上午10:27:14
 * @classname : TbMobileInfo
 * @description : TODO
 *
 */
@Entity
@Table(name="tb_mobileInfo")
public class TbMobileInfo extends BaseEntity {

	private static final long serialVersionUID = 8006035500082398820L;

	public TbMobileInfo(){}
	
	public TbMobileInfo(Long id){
		super.setId(id);
	}
	
	@Column(name="mobile",nullable=true,length=11)
	private String mobile;
	
	@Column(name="imsi",nullable=true,length=16)
	private String imsi;
	
	@Column(name="createTime")
	private Date createTime;
	
	@Column(name="service",length=20)
	private String service;
	
	@Column(name="cardType",length=50)
	private String cardType;
	
	@Column(name="province",length=10)
	private String province;
	
	@Column(name="city",length=50)
	private String city;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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
	
	
}
