package com.web4j.sxdplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_sxd_buddhamusic")
public class TbBuddhaMusic {

	public TbBuddhaMusic (){}
	
	public TbBuddhaMusic(String musicTitle,String author,String musicPath,String lyricPath,String musicType){
		this.musicTitle=musicTitle;
		this.author=author;
		this.musicPath=musicPath;
		this.lyricPath=lyricPath;
		this.musicType=musicType;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long musicId;
	
	@Column(length=20,nullable=false)
	private String createUser;
	
	@Column
	private Date createTime;
	
	@Column(length=100,nullable=false)
	private String musicTitle;
	
	@Column(length=20)
	private String author;
	
	@Column(length=100)
	private String musicPath;
	
	@Column(length=100)
	private String lyricPath;
	
	@Column(length=30)
	private String musicType;

	public Long getMusicId() {
		return musicId;
	}

	public void setMusicId(Long musicId) {
		this.musicId = musicId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMusicTitle() {
		return musicTitle;
	}

	public void setMusicTitle(String musicTitle) {
		this.musicTitle = musicTitle;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	public String getLyricPath() {
		return lyricPath;
	}

	public void setLyricPath(String lyricPath) {
		this.lyricPath = lyricPath;
	}

	public String getMusicType() {
		return musicType;
	}

	public void setMusicType(String musicType) {
		this.musicType = musicType;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
