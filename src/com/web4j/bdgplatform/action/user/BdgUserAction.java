package com.web4j.bdgplatform.action.user;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.bdgplatform.entity.TbBdgUser;
import com.web4j.bdgplatform.model.BdgUserModel;
import com.web4j.common.action.AbstractActionSupport;

/**
 * 个人中心ACTION
 * @author xixi
 * @date 2012-11-10
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/bdg/user")
public class BdgUserAction extends AbstractActionSupport {
	
	private static Logger log = Logger.getLogger(BdgUserAction.class);
	
	@Autowired
	private BdgUserModel bdgUserModel;
	
	private List<TbBdgUser> users;
	
	private String channel;
	private String imei;
	private Date createTime;
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Action("queryUsers")
	public String querySxdUsers(){
		try{
			TbBdgUser user=new TbBdgUser(channel,imei,createTime);
			users = bdgUserModel.queryBdgUsers(start, limit, user);
			if(total==null){
				total=bdgUserModel.getBdgUserCount(user);
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
			bdgUserModel.saveOrUpdageBdgUsers(users);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteUsers")
	public String deleteSxdUsers(){
		try{
			bdgUserModel.deleteBdgUsers(users);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<TbBdgUser> getUsers() {
		return users;
	}

	public void setUsers(List<TbBdgUser> users) {
		this.users = users;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}


}
