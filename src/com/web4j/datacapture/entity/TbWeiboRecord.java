package com.web4j.datacapture.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import weibo4j.model.Status;

@Entity
@Table(name="tb_capture_weiborecord")
public class TbWeiboRecord {

	public TbWeiboRecord(){}
	
	public TbWeiboRecord(Status status,String source){
		this.uid=status.getUser().getId();
		this.screenName=status.getUser().getScreenName();
		this.location=status.getUser().getLocation();
		this.createdAt=status.getCreatedAt();
		this.id=status.getId();
		this.mid=status.getMid();
		this.text=cutText(status.getText());
		this.source=status.getSource().getUrl();
		this.thumbnailPic=status.getThumbnailPic();
		this.bmiddlePic=status.getBmiddlePic();
		this.originalPic=status.getOriginalPic();
		//如果转播不为空。就取转播里的图片
		if(status.getRetweetedStatus()!=null){
			if(this.thumbnailPic==null||"".equals(this.thumbnailPic)){
				this.thumbnailPic=status.getRetweetedStatus().getThumbnailPic();
			}
			if(this.bmiddlePic==null||"".equals(this.bmiddlePic)){
				this.bmiddlePic=status.getRetweetedStatus().getBmiddlePic();
			}
			if(this.originalPic==null||"".equals(this.originalPic)){
				this.originalPic=status.getRetweetedStatus().getOriginalPic();
			}
		}
		this.recordSource=source;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long recordId;
	@Column(length=1)
	private String recordType="1";//分类
	@Column(length=30)
	private String recordSource;//抓取来源
	@Column
	private Integer recordStatus=0;//状态0:末发布，1：已编辑，2:已发布
	@Column(length=20)
	private String uid;									//用户ID
	@Column(length=50)
	private String screenName;							//用户名
	@Column(length=30)
	private String location;							//位置
	@Column
	private Date createdAt=new Date();                   //status创建时间
	@Column(length=30)
	private String id;                                   	//status id
	@Column(length=30)
	private String mid;                                  	//微博MID                 
	@Column(length=512)
	private String text;                                 //微博内容
	@Column(length=200)
	private String source;                               //微博来源
	@Column(length=200)
	private String thumbnailPic;                         //微博内容中的图片的缩略地址
	@Column(length=200)
	private String bmiddlePic;                           //中型图片
	@Column(length=200)
	private String originalPic;                          //原始图片	
	
	private String cutText(String str){
		if(str.contains("转发自")){
			return str.substring(0, str.indexOf("转发自"));
		}else{
			return str;
		}
	}
	
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getThumbnailPic() {
		return thumbnailPic;
	}
	public void setThumbnailPic(String thumbnailPic) {
		this.thumbnailPic = thumbnailPic;
	}
	public String getBmiddlePic() {
		return bmiddlePic;
	}
	public void setBmiddlePic(String bmiddlePic) {
		this.bmiddlePic = bmiddlePic;
	}
	public String getOriginalPic() {
		return originalPic;
	}
	public void setOriginalPic(String originalPic) {
		this.originalPic = originalPic;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getRecordSource() {
		return recordSource;
	}

	public void setRecordSource(String recordSource) {
		this.recordSource = recordSource;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
