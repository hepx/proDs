package com.web4j.qdtgplatform.pojo;


public class QdtgChannel {

	private Long channelId;
	
	private String channelNo;
	
	private String fileName;
	
	private Long cvsId;
	
	private String cvsName;
	
	private Long productId;
	
	private String productName;
	
	private Float unitPrice;
	
	private Float proportion;

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

	public String getCvsName() {
		return cvsName;
	}

	public void setCvsName(String cvsName) {
		this.cvsName = cvsName;
	}

	public Long getCvsId() {
		return cvsId;
	}

	public void setCvsId(Long cvsId) {
		this.cvsId = cvsId;
	}

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

	public Float getProportion() {
		return proportion;
	}

	public void setProportion(Float proportion) {
		this.proportion = proportion;
	}
	
}
