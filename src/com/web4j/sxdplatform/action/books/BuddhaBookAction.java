package com.web4j.sxdplatform.action.books;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.sxdplatform.entity.TbBuddhaBook;
import com.web4j.sxdplatform.model.BuddhaBookModel;
import com.web4j.sxdplatform.pojo.BuddhaBook;
import com.web4j.util.FileUtil;

/**
 * 经书ACTION
 * @author xixi
 * @date 2012-10-29
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/sxd/books")
public class BuddhaBookAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(BuddhaBookAction.class);
	
	@Autowired
	private BuddhaBookModel buddhaBookModel;
	
	private List<BuddhaBook> buddhaBooks;
	
	private BuddhaBook buddhaBook;
	
	private Long bookId;
	
	private File bookFile;
	
	private String bookFileContentType;
	
	private String bookFileFileName;
	
	private Map<String,Object>result;

	@Action("queryBooks")
	public String queryBooks(){
		try{
			buddhaBooks=buddhaBookModel.queryBooks(start, limit);
			if(total==null){
				total=buddhaBookModel.getBookCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("createBook")
	public String createBook(){
		try{
			buddhaBookModel.createBook(buddhaBook);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("deleteBooks")
	public String deleteBooks(){
		try{
			buddhaBookModel.deleteBooks(buddhaBooks);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("uploadImage")
	public String uploadImage() {
		HttpServletResponse response=ServletActionContext.getResponse();
		result=new HashMap<String,Object>();
		try{
			response.setContentType("text/html;charset=UTF-8");
			String []types={"image/jpeg","image/pjpeg","image/gif","image/png","image/x-png"};
			int maxSize=102400;
			//验证文件
			FileUtil.validate(bookFile, bookFileContentType, bookFileFileName,types,maxSize);
			//需要保存的路径
			String relaPath="/attachments/sxd/books/images";
			String realPath = FileUtil.getTomcatWebAppPath()+relaPath;
			FileUtil.writeFile(bookFile, bookFileFileName, realPath);
			TbBuddhaBook tbBuddhaBook=buddhaBookModel.getTbBuddhaBookById(bookId);
			if(tbBuddhaBook!=null){
				tbBuddhaBook.setBookImagePath(relaPath+"/"+bookFileFileName);
				buddhaBookModel.updateTbBuddhaBook(tbBuddhaBook);
			}
			result.put("success", true);
			result.put("msg", bookFileFileName+"上传成功！");
		}catch(Exception e){
			result.put("success", false);
			result.put("msg", e.getMessage());
			log.error(e.getMessage(), e);
		}
		try {
			response.getWriter().write(JSONUtil.serialize(result));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 
		return null;
	}
	
	@Action("uploadTxt")
	public String uploadTxt(){
		result=new HashMap<String,Object>();
		try{
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			String []types={"text/plain"};
			int maxSize=102400;
			FileUtil.validate(bookFile, bookFileContentType, bookFileFileName,types,maxSize);
			String relaPath="/attachments/sxd/books/books";
			String realPath = FileUtil.getTomcatWebAppPath()+relaPath;
			FileUtil.writeFile(bookFile, bookFileFileName, realPath);
			TbBuddhaBook tbBuddhaBook=buddhaBookModel.getTbBuddhaBookById(bookId);
			if(tbBuddhaBook!=null){
				tbBuddhaBook.setBookPath(relaPath+"/"+bookFileFileName);
				buddhaBookModel.updateTbBuddhaBook(tbBuddhaBook);
			}
			result.put("success", true);
			result.put("msg", bookFileFileName+"上传成功！");			
		}catch(Exception e){
			result.put("success", false);
			result.put("msg", e.getMessage());
			log.error(e.getMessage(), e);
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONUtil.serialize(result));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 		
		return null;
	}
	
	@Action("uploadMusic")
	public String uploadMusic(){
		result=new HashMap<String,Object>();
		try{
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			String []types={"audio/mp3","audio/mpeg"};
			int maxSize=10485760;
			FileUtil.validate(bookFile, bookFileContentType, bookFileFileName,types,maxSize);
			String relaPath="/attachments/sxd/books/musics";
			String realPath = FileUtil.getTomcatWebAppPath()+relaPath;
			FileUtil.writeFile(bookFile, bookFileFileName, realPath);
			TbBuddhaBook tbBuddhaBook=buddhaBookModel.getTbBuddhaBookById(bookId);
			if(tbBuddhaBook!=null){
				tbBuddhaBook.setBackMusicPath(relaPath+"/"+bookFileFileName);
				buddhaBookModel.updateTbBuddhaBook(tbBuddhaBook);
			}
			result.put("success", true);
			result.put("msg", bookFileFileName+"上传成功！");	
		}catch(Exception e){
			result.put("success", false);
			result.put("msg", e.getMessage());
			log.error(e.getMessage(),e);
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONUtil.serialize(result));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 
		return null;
	}

	public List<BuddhaBook> getBuddhaBooks() {
		return buddhaBooks;
	}

	public void setBuddhaBooks(List<BuddhaBook> buddhaBooks) {
		this.buddhaBooks = buddhaBooks;
	}

	public File getBookFile() {
		return bookFile;
	}

	public void setBookFile(File bookFile) {
		this.bookFile = bookFile;
	}

	public String getBookFileContentType() {
		return bookFileContentType;
	}

	public void setBookFileContentType(String bookFileContentType) {
		this.bookFileContentType = bookFileContentType;
	}

	public String getBookFileFileName() {
		return bookFileFileName;
	}

	public void setBookFileFileName(String bookFileFileName) {
		this.bookFileFileName = bookFileFileName;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public BuddhaBook getBuddhaBook() {
		return buddhaBook;
	}

	public void setBuddhaBook(BuddhaBook buddhaBook) {
		this.buddhaBook = buddhaBook;
	}
	
	
}
