package com.web4j.sxdplatform.pojo;

import java.util.Date;

/**
 * 佛音
 * @author xixi
 * @date 2012-11-5
 *
 */
public class BuddhaMusic {

	private Long musicId;
	
	private String createUser;
	
	private Date createTime;
	
	private String musicTitle;
	
	private String author;
	
	private String musicPath;
	
	private String lyricPath;
	
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
