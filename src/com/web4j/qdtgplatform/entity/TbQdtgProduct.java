package com.web4j.qdtgplatform.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_qdtg_product")
public class TbQdtgProduct {

	public TbQdtgProduct(){}
	
	public TbQdtgProduct(Long productId){
		this.productId=productId;
	}
	
	public TbQdtgProduct(Long productId,String productName){
		this.productId=productId;
		this.productName=productName;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long productId;
	
	@Column(nullable=false,length=50)
	private String productName;
	
	@Column(length=100)
	private String company;
	
	@OneToMany(mappedBy="tbQdtgProduct",cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
	private Set<TbQdtgChannel> tbQdtgChannels=new HashSet<TbQdtgChannel>(0);

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Set<TbQdtgChannel> getTbQdtgChannels() {
		return tbQdtgChannels;
	}

	public void setTbQdtgChannels(Set<TbQdtgChannel> tbQdtgChannels) {
		this.tbQdtgChannels = tbQdtgChannels;
	}
	
}
