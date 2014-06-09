package com.web4j.qdtgplatform.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_qdtg_channel")
public class TbQdtgChannel {

	public TbQdtgChannel(){}
	
	public TbQdtgChannel(Long channelId,String channelNo,String fileName){
		this.channelId=channelId;
		this.channelNo=channelNo;
		this.fileName=fileName;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long channelId;
	
	@Column(length=30,nullable=false,unique=true)
	private String channelNo;
	
	@Column(length=100)
	private String fileName;
	
	@Column(precision=2)
	private Float unitPrice;
	
	@Column(precision=2)
	private Float proportion;
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="cvsId")
	private TbQdtgCvs tbQdtgCvs;
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="productId")
	private TbQdtgProduct tbQdtgProduct;

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public TbQdtgCvs getTbQdtgCvs() {
		return tbQdtgCvs;
	}

	public void setTbQdtgCvs(TbQdtgCvs tbQdtgCvs) {
		this.tbQdtgCvs = tbQdtgCvs;
	}

	public TbQdtgProduct getTbQdtgProduct() {
		return tbQdtgProduct;
	}

	public void setTbQdtgProduct(TbQdtgProduct tbQdtgProduct) {
		this.tbQdtgProduct = tbQdtgProduct;
	}

	public Float getProportion() {
		return proportion;
	}

	public void setProportion(Float proportion) {
		this.proportion = proportion;
	}
	
}
