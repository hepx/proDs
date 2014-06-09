package com.web4j.sxdplatform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_sxd_buddhabook")
public class TbBuddhaBook {
	
	public TbBuddhaBook(){}
	
	public TbBuddhaBook(String bookTitle,String bookImagePath,String bookPath,String backMusicPath){
		this.bookTitle=bookTitle;
		this.bookImagePath=bookImagePath;
		this.bookPath=bookPath;
		this.backMusicPath=backMusicPath;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bookId;
	
	@Column(length=100,nullable=false)
	private String bookTitle;
	
	@Column(length=100)
	private String bookImagePath;
	
	@Column(length=100)
	private String bookPath;

	@Column(length=100)
	private String backMusicPath;
	
	@Column(length=20,nullable=false)
	private String createUser;
	
	@Column
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
