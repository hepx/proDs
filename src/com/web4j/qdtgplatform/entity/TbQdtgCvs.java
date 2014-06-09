package com.web4j.qdtgplatform.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.web4j.systemplatform.entity.TbUserInfo;

@Entity
@Table(name="tb_qdtb_cvs")
public class TbQdtgCvs {

	public TbQdtgCvs(){}
	
	public TbQdtgCvs(Long cvsId){
		this.cvsId=cvsId;
	}
	
	public TbQdtgCvs(Long cvsId,String cvsName){
		this.cvsId=cvsId;
		this.cvsName=cvsName;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long cvsId;
	
	@Column(length=100,nullable=false,unique=true)
	private String cvsName;
	
	@OneToOne(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
	@JoinColumn(name="userId",unique=true)
	private TbUserInfo tbUserInfo;
	
	@OneToMany(mappedBy="tbQdtgCvs",fetch=FetchType.LAZY)
	private List<TbQdtgChannel> tbQdtgChannels=new ArrayList<TbQdtgChannel>(0); 

	public Long getCvsId() {
		return cvsId;
	}

	public void setCvsId(Long cvsId) {
		this.cvsId = cvsId;
	}

	public String getCvsName() {
		return cvsName;
	}

	public void setCvsName(String cvsName) {
		this.cvsName = cvsName;
	}

	public TbUserInfo getTbUserInfo() {
		return tbUserInfo;
	}

	public void setTbUserInfo(TbUserInfo tbUserInfo) {
		this.tbUserInfo = tbUserInfo;
	}

	public List<TbQdtgChannel> getTbQdtgChannels() {
		return tbQdtgChannels;
	}

	public void setTbQdtgChannels(List<TbQdtgChannel> tbQdtgChannels) {
		this.tbQdtgChannels = tbQdtgChannels;
	}
	
}
