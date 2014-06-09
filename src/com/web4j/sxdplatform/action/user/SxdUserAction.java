package com.web4j.sxdplatform.action.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.sxdplatform.model.SxdUserModel;
import com.web4j.sxdplatform.pojo.SxdUser;

/**
 * 个人中心ACTION
 * @author xixi
 * @date 2012-11-10
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/sxd/user")
public class SxdUserAction extends AbstractActionSupport {
	
	private static Logger log = Logger.getLogger(SxdUserAction.class);
	
	@Autowired
	private SxdUserModel sxdUserModel;
	
	private List<SxdUser> users;
	
	@Action("queryUsers")
	public String querySxdUsers(){
		try{
			users = sxdUserModel.quereySxdUsers(start, limit);
			if(total==null){
				total=sxdUserModel.getSxdUserCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateUsers")
	public String saveOrUpdateSxdUsers(){
		try{
			sxdUserModel.saveOrUpdateSxdUsers(users);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteUsers")
	public String deleteSxdUsers(){
		try{
			sxdUserModel.deleteSxdUsers(users);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	public List<SxdUser> getUsers() {
		return users;
	}
	public void setUsers(List<SxdUser> users) {
		this.users = users;
	}
	
	
}
