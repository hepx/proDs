package com.web4j.systemplatform.action.userInfo;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.systemplatform.model.userInfo.UserInfoModel;
import com.web4j.systemplatform.pojo.userInfo.UserInfoPojo;

/**
 * 用户Action
 * 
 * @author hepx
 * 
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/userInfo")
public class UserInfoAction extends AbstractActionSupport {

	protected Logger log = Logger.getLogger(UserInfoAction.class);

	@Autowired
	private UserInfoModel userInfoModel;

	private String oldPwd;
	private String newPwd;

	private UserInfoPojo userInfo;
	private List<UserInfoPojo> userInfoList;

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@Action("modifyPwd")
	public String modifyPwd() {
		try {
			userInfoModel.modifyPwd(oldPwd, newPwd);
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 用户列表
	 * 
	 * @return
	 */
	@Action("queryUserInfoList")
	public String queryUserInfoList() {
		try {
			userInfoList = userInfoModel.queryUserInfoList();
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@Action("deleteUserInfo")
	public String deleteUserInfo() {
		try {
			userInfoModel.deleteUserInfo(userInfo);
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 保存或更新用户
	 * 
	 * @return
	 */
	@Action("saveOrUpdateUserInfo")
	public String saveOrUpdateUserInfo() {
		try {
			userInfoModel.saveOrUpdateUserInfo(userInfo);
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public List<UserInfoPojo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfoPojo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public UserInfoPojo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoPojo userInfo) {
		this.userInfo = userInfo;
	}

}
