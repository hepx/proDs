package com.web4j.sxdplatform.pojo;

import java.util.Date;
/**
 * 经书
 * @author xixi
 * @date 2012-11-1
 *
 */
public class BuddhaBook {

	private Long bookId;
	
	private String bookTitle;
	
	private String bookImagePath;
	
	private String bookPath;

	private String backMusicPath;
	
	private String createUser;
	
	private Date createTime;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookImagePath() {
		return bookImagePath;
	}

	public void setBookImagePath(String bookImagePath) {
		this.bookImagePath = bookImagePath;
	}

	public String getBookPath() {
		return bookPath;
	}

	public void setBookPath(String bookPath) {
		this.bookPath = bookPath;
	}

	public String getBackMusicPath() {
		return backMusicPath;
	}

	public void setBackMusicPath(String backMusicPath) {
		this.backMusicPath = backMusicPath;
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
	
	
}
