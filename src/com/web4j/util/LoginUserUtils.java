package com.web4j.util;

import com.opensymphony.xwork2.ActionContext;
import com.web4j.systemplatform.pojo.userInfo.UserInfoPojo;

public class LoginUserUtils {

	/**
	 * 获得登录用户
	 * @author : hepx 
	 * @date : 2011-3-10下午06:15:54
	 * @description : TODO
	 * @return : UserInfoPojo
	 */
	public static UserInfoPojo getLoginUserInfo(){
		return (UserInfoPojo) ActionContext.getContext().getSession().get(SysConfig.LOGIN_USER);
	}
	
	public static Integer getRoleType(){
		return getLoginUserInfo().getRoleType();
	}
	
	public static Long getUserId(){
		return getLoginUserInfo().getId();
	}
}
