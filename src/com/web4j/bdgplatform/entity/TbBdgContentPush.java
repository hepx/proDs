package com.web4j.bdgplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 内容推送
 * @author xixi
 * @date 2013-3-7
 *
 */
@Entity
@Table(name="tb_bdg_contentpush")
public class TbBdgContentPush {

	public TbBdgContentPush(){}
	
	public TbBdgContentPush(String msgContent){
		this.createTime=new Date();
		this.msgContent=msgContent;
	}
	
	public TbBdgContentPush(String msgContent,String errcode,String errmsg){
		this.createTime=new Date();
		this.msgContent=msgContent;
		this.errcode=errcode;
		this.errmsg=errmsg;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pushId;
	
	@Column
	private Date createTime;
	
	@Column
	private Date pushTime;

	@Column(length=50)
	private String msgTitle;
	
	@Column(length=255)
	private String msgContent;
	
	@Column(length=10)
	private String errcode;
	
	@Column(length=100)
	private String errmsg;

	public Long getPushId() {
		return pushId;
	}

	public void setPushId(Long pushId) {
		this.pushId = pushId;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	
	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
}
