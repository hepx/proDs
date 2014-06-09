package com.web4j.systemplatform.action.main;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.systemplatform.model.userInfo.UserInfoModel;
import com.web4j.systemplatform.pojo.userInfo.UserInfoPojo;
import com.web4j.util.SysConfig;

/**
 * 登录ACTION
 * 
 * @author hepx
 * @date 2010-11-28
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/main")
@Results( { @Result(name = "page", location = "/main/login.html"),
		@Result(name = "modifyPwd", location = "/main/modifyPwd.html") })
public class LoginAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(LoginAction.class);

	@Autowired
	private UserInfoModel userInfoModel;
	private UserInfoPojo userInfo;
	private String uid;
	private String ein;
	private String pwd;

	@Action("login")
	public String execute() {
		Map<String, Object> session = ServletActionContext.getContext()
				.getSession();
		if (session.get(SysConfig.LOGIN_USER) != null) {
			session.remove(SysConfig.LOGIN_USER);
		}
		return PAGE;
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	@Action("loginIn")
	public String loginIn() {
		try {
			//取得生成的验证码
//			String c=(String)ServletActionContext.getRequest().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
//			if(c!=null&&c.equals(userInfo.getRandCode())){
				userInfoModel.login(userInfo);
//			}else{
//				throw new DoErrorException("验证码输入错误！");
//			}
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 注册
	 * 
	 * @author : hepx
	 * @date : 2011-3-14下午03:37:45
	 * @description : TODO
	 * @return : String
	 */
	@Action("registUser")
	public String registUser() {
		try {
			userInfoModel.saveOrUpdateUserInfo(userInfo);
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-3-15下午12:47:13
	 * @description : 通过填写的用户和邮箱地址。发送一个链接到用户邮箱。供用户修改密码
	 * @return : String
	 */
	@Action("findPwd")
	public String findPwd() {
		try {
			userInfoModel.findPwd(userInfo);
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-3-16上午11:47:26
	 * @description : 用户在进入修改密码页面。点击修改。来修改密码
	 * @return : String
	 */
	@Action("modifyInitPwd")
	public String modifyInitPwd() {
		String temp = JSON;
		try {
			if (uid != null && pwd != null&&ein!=null) {
				userInfoModel.modifyPwd(uid,ein, pwd);
			} else {
				temp = "modifyPwd";
			}
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return temp;
	}

	public UserInfoPojo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoPojo userInfo) {
		this.userInfo = userInfo;
	}


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEin() {
		return ein;
	}

	public void setEin(String ein) {
		this.ein = ein;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
