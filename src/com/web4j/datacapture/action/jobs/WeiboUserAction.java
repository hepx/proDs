package com.web4j.datacapture.action.jobs;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.datacapture.entity.TbWeiboUser;
import com.web4j.datacapture.jobs.WeiboUserBean;
import com.web4j.datacapture.model.WeiboUserModel;
/**
 * 微博用户Action
 * @author xixi
 * @date 2012-12-6
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/captrue/wbuser")
public class WeiboUserAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(WeiboUserAction.class);
	
	@Autowired
	private WeiboUserModel weiboUserModel;
	
	@Autowired
	private WeiboUserBean weiboUserBean;
	
	private List<TbWeiboUser> wbUsers;
	
	@Action("queryWbUsers")
	public String queryWbUsers(){
		try{
			wbUsers=weiboUserModel.queryWeiboUsers(start, limit);
			if(total==null){
				total=weiboUserModel.getWeiboUserCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateWbUsers")
	public String saveOrUpdateWbUsers(){
		try{
			weiboUserModel.saveOrUpdateWeiboUsers(wbUsers);
			weiboUserBean.clearWeiboUsers();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteWbUsers")
	public String deleteWbUsers(){
		try{
			weiboUserModel.deleteWeiboUsers(wbUsers);
			weiboUserBean.clearWeiboUsers();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<TbWeiboUser> getWbUsers() {
		return wbUsers;
	}

	public void setWbUsers(List<TbWeiboUser> wbUsers) {
		this.wbUsers = wbUsers;
	}
	
}
