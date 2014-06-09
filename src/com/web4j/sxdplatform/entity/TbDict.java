package com.web4j.sxdplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_sxd_dict")
public class TbDict {

	public TbDict(){}
	
	public TbDict(Long dictId){
		this.dictId=dictId;
	}
	
	public TbDict(String dictName,String dictValue){
		this.dictName=dictName;
		this.dictValue=dictValue;
	}
	
	public TbDict(String dictName,String dictValue,String iconPath){
		this.dictName=dictName;
		this.dictValue=dictValue;
		this.iconPath=iconPath;
	}	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long dictId;
	
	@Column(length=30,nullable=false)
	private String dictGroup;
	
	@Column(length=30,nullable=false)
	private String dictName;
	
	@Column(length=30,nullable=false)
	private String dictValue;
	
	@Column
	private String dictDesc;
	
	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	@Column(length=100)
	private String iconPath;
	
	@Column(length=2)
	private Integer sort;

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public String getDictGroup() {
		return dictGroup;
	}

	public void setDictGroup(String dictGroup) {
		this.dictGroup = dictGroup;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getDictDesc() {
		return dictDesc;
	}

	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
