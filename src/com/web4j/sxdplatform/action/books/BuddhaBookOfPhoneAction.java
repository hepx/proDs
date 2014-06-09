package com.web4j.sxdplatform.action.books;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.sxdplatform.model.BuddhaBookModel;
import com.web4j.util.SysConfig;
/**
 * 诵经手机调用ACTION
 * @author xixi
 * @date 2012-11-2
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/sxd/phone/books")
public class BuddhaBookOfPhoneAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(BuddhaBookOfPhoneAction.class);
	
	@Autowired
	private BuddhaBookModel buddhaBookModel;
	
	private List books;
	
	@Action("queryBooks")
	public String queryBooks(){
		try{
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			books=buddhaBookModel.queryBooksOfPhone(start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List getBooks() {
		return books;
	}

	public void setBooks(List books) {
		this.books = books;
	}
	
}
