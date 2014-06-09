package com.web4j.qdtgplatform.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_qdtg_record")
public class TbQdtgRecord {
	
	public TbQdtgRecord(){}
	
	
	public TbQdtgRecord(Long productId,Long channelId,Long cvsId,Date recordTime){
		this.productId=productId;
		this.channelId=channelId;
		this.cvsId=cvsId;
		this.recordTime=recordTime;
	}
	
	//by 运营人看到的信息
	public TbQdtgRecord(Long recordId,String productName,String channelNo,
			String fileName,Integer activateQt,Date createTime,Date recordTime){
		this.recordId=recordId;
		this.productName=productName;
		this.channelNo=channelNo;
		this.fileName=fileName;
		this.activateQt=activateQt;
		this.createTime=createTime;
		this.recordTime=recordTime;
	}
	
	//by 客户看到的信息
	public TbQdtgRecord(Long recordId,String productName,String channelNo,String fileName,
			Float unitPrice,Integer activateQt_Net,Float totalPrice_Net,Date recordTime){
		this.recordId=recordId;
		this.productName=productName;
		this.channelNo=channelNo;
		this.fileName=fileName;
		this.unitPrice=unitPrice;
		this.activateQt_Net=activateQt_Net;
		this.totalPrice_Net=totalPrice_Net;
		this.recordTime=recordTime;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long recordId;
	
	@Column
	private Long userId;

	@Column
	private Long productId;
	
	@Column(length=50)
	private String productName;
	
	@Column
	private Long channelId;
	
	@Column(length=30)
	private String channelNo;
	
	@Column(length=100)
	private String fileName;
	
	@Column
	private Long cvsId;
	
	@Column(length=100)
	private String cvsName;
	
	@Column(precision=2)
	private Float unitPrice;
	
	@Column
	private Integer activateQt;
	
	@Column(precision=2)
	private Float totalPrice;
	
	@Column
	private Date createTime;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date recordTime;
	
	@Column
	private Integer activateQt_Net;
	
	@Column(precision=2)
	private Float totalPrice_Net;
	
	@Column(columnDefinition="bit default 0")
	private Boolean status;

	//计算原生总价
	public void countTotalPrice(){
		this.totalPrice=new BigDecimal(this.unitPrice*this.activateQt).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	//计算扣量后的总价
	public void countTotalPriceOfNet(Float proportion){
		if(proportion!=null&&proportion>0){//如果有扣量，就用原生的量减去扣量比率量得到扣量
			this.activateQt_Net=this.activateQt-(new BigDecimal(Math.floor(this.activateQt*proportion)).intValue());
			this.totalPrice_Net=new BigDecimal(this.unitPrice*this.activateQt_Net).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		}else{//如果不扣量，刚等于原生量
			this.activateQt_Net=this.activateQt;
			this.totalPrice_Net=this.totalPrice;
		}
	}
	
	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
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

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getActivateQt() {
		return activateQt;
	}

	public void setActivateQt(Integer activateQt) {
		this.activateQt = activateQt;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
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


	public Date getRecordTime() {
		return recordTime;
	}


	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}


	public Integer getActivateQt_Net() {
		return activateQt_Net;
	}


	public void setActivateQt_Net(Integer activateQt_Net) {
		this.activateQt_Net = activateQt_Net;
	}


	public Float getTotalPrice_Net() {
		return totalPrice_Net;
	}


	public void setTotalPrice_Net(Float totalPrice_Net) {
		this.totalPrice_Net = totalPrice_Net;
	}

}
