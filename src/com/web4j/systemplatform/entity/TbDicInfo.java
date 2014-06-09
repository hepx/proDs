package com.web4j.systemplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.web4j.common.entity.BaseEntity;

/**
 * 
 * @author : hepx
 * @date : 2011-3-11上午11:26:59
 * @classname : TbDicInfo
 * @description : 字典表
 *
 */
@Entity
@Table(name="tb_dicInfo")
public class TbDicInfo extends BaseEntity {

	private static final long serialVersionUID = 1797934584569327553L;
	
	public TbDicInfo(){}
	
	public TbDicInfo(Long id){
		super.setId(id);
	}

	@Column(name="dicGroup",length=50)
	private String dicGroup;//组别
	
	@Column(name="dicCode",length=50)
	private String dicCode;//代码
	
	@Column(name="dicValue",length=20)
	private String dicValue;//值
	
	@Column(name="dicDesc",length=200)
	private String dicDesc;//描述

	public String getDicGroup() {
		return dicGroup;
	}

	public void setDicGroup(String dicGroup) {
		this.dicGroup = dicGroup;
	}

	public String getDicCode() {
		return dicCode;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}

	public String getDicValue() {
		return dicValue;
	}

	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}

	public String getDicDesc() {
		return dicDesc;
	}

	public void setDicDesc(String dicDesc) {
		this.dicDesc = dicDesc;
	}
	
	
	
}
