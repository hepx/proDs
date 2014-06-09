package com.web4j.bdgplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_bdg_user")
public class TbBdgUser {

	public TbBdgUser(){}

	public TbBdgUser(String imei, String mobile, String simOperator,
			String networkType, String sdk, String screenSize, String province,
			String city,String channel) {
		this.imei = imei;
		this.mobile = mobile;
		this.simOperator = simOperator;
		this.networkType = networkType;
		this.sdk = sdk;
		this.screenSize = screenSize;
		this.province = province;
		this.city = city;
		this.channel= channel;
		this.createTime = new Date();// 初始时间
	}
	
	public TbBdgUser(String channel,String imei,Date createTime){
		this.channel=channel;
		this.imei=imei;
		this.createTime=createTime;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column(length=15)
	private String imei;

	@Column(length=11)
	private String mobile;//手机号
	
	@Column(length=10)
	private String simOperator;//SIM运营商
	
	@Column(length=10)
	private String networkType;//网络类型
	
	@Column(length=30)
	private String province;//省份
	
	@Column(length=30)
	private String city;//城市
	
	@Column
	private Date createTime;//创建时间
	
	@Column(length=2)
	private String sdk;//SDK版本信息
	
	@Column(length=10)
	private String screenSize;//屏幕尺寸
	
	@Column(length=20)
	private String channel;//渠道号

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getSimOperator() {
		return simOperator;
	}

	public void setSimOperator(String simOperator) {
		this.simOperator = simOperator;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getSdk() {
		return sdk;
	}

	public void setSdk(String sdk) {
		this.sdk = sdk;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
