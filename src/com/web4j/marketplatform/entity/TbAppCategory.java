package com.web4j.marketplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="tb_market_appcategory",uniqueConstraints=@UniqueConstraint(columnNames={"categoryName","categoryValue"}))
public class TbAppCategory {

	public TbAppCategory(){}
	public TbAppCategory(String categoryName,String categoryValue){
		this.categoryName=categoryName;
		this.categoryValue=categoryValue;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long categoryId;
	
	@Column(length=30,nullable=false)
	public String categoryName;
	
	@Column(length=10,nullable=false)
	public String categoryValue;

	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryValue() {
		return categoryValue;
	}
	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}
	
}
