package com.web4j.bdgplatform.entity;

import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.xwork.math.RandomUtils;

import com.web4j.util.RandomUtil;

/**
 * 发布后的记录
 * @author xixi
 * @date 2012-12-7
 *
 */
@Entity
@Table(name="tb_bdg_ar_record")
public class TbBdgArRecord {

	public TbBdgArRecord(){}
	
	public TbBdgArRecord(Long recordId, String recordType, String screenName,String text,
			String pic,Integer tops,Integer treads,Integer collects,Integer shares,Date releaseTime) {
		this.recordId=recordId;
		this.recordType=recordType;
		this.screenName=screenName;
		this.text=text;
		this.pic=pic;
		this.tops=tops;
		this.treads=treads;
		this.collects=collects;
		this.shares=shares;
		this.releaseTime=releaseTime;
	}
	
	public TbBdgArRecord(TbBdgBrRecord brRecord){
		this.recordSource=brRecord.getRecordSource();
		this.uid=brRecord.getUid();
		this.screenName=brRecord.getScreenName();
		this.location=brRecord.getLocation();
		this.createdAt=brRecord.getCreatedAt();
		this.id=brRecord.getId();
		this.text=brRecord.getText();
		this.source=brRecord.getSource();
		this.pic=brRecord.getBmiddlePic();
		this.releaseTime=new Date();
		//随机生成以下数
		this.tops=RandomUtil.getRandom4Int();
		this.treads=RandomUtil.getRandom4Int();
		this.collects=RandomUtil.getRandom4Int();
		this.shares=RandomUtil.getRandom4Int();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long recordId;
	@Column(length=1)
	private String recordType="1";//分类
	@Column(length=30)
	private String recordSource;//抓取来源
	@Column
	private Integer tops=0;			//顶次数
	@Column
	private Integer treads=0;			//踩次数
	@Column
	private Integer collects=0;		//收藏次数
	@Column
	private Integer shares=0;		//分享
	@Column
	private Date releaseTime;		//分布时间
	@Column(length=20)
	private String uid;									//用户ID
	@Column(length=50)
	private String screenName;							//用户名
	@Column(length=30)
	private String location;							//位置
	@Column
	private Date createdAt;			                   //status创建时间
	@Column(length=30)
	private String id;                                   //status id                
	@Column(length=512)
	private String text;                                 //微博内容
	@Column(length=200)
	private String source;                               //微博来源
	@Column(length=200)
	private String pic;                          //原始图片	
	
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

	public Integer getTops() {
		return tops;
	}

	public void setTops(Integer tops) {
		this.tops = tops;
	}

	public Integer getTreads() {
		return treads;
	}

	public void setTreads(Integer treads) {
		this.treads = treads;
	}

	public Integer getCollects() {
		return collects;
	}

	public void setCollects(Integer collects) {
		this.collects = collects;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getShares() {
		return shares;
	}

	public void setShares(Integer shares) {
		this.shares = shares;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
}
