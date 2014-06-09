package com.web4j.sxdplatform.action.user;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.RandomStringUtils;
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
import com.web4j.sxdplatform.entity.TbSxdUser;
import com.web4j.sxdplatform.model.SxdUserModel;
import com.web4j.sxdplatform.pojo.SxdUser;
import com.web4j.util.FileUtil;
/**
 * 手机调用用户中心ACTION
 * @author xixi
 * @date 2012-11-10
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/sxd/phone/user")
public class SxdUserOfPhoneAction extends AbstractActionSupport {

	private Logger log =  Logger.getLogger(SxdUserOfPhoneAction.class);
	
	@Autowired
	private SxdUserModel sxdUserModel;
	
	private SxdUser user;
	
	private Long userId;
	
	private File auatarFile;
	
	private String auatarFileContentType;
	
	private String auatarFileFileName;
	
	private Map<String,Object>result;
	
	@Action("register")
	public String register(){
		try{
			//默认注册用户密码
			String defPwd="888888";
			//随机生成一个8位数的香缘号
			String xyNo=generateXyNo();
			user=new SxdUser(xyNo,defPwd);
			sxdUserModel.createSxdUser(user);
			//把默认不加密的密码返回给手机
			user.setPassword(defPwd);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("update")
	public String update(){
		try{
			
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("uploadAuatar")
	public String uploadAuatar(){
		HttpServletResponse response=ServletActionContext.getResponse();
		try{
			response.setContentType("text/html;charset=UTF-8");
			result=new HashMap<String, Object>();
			String []types={"image/jpeg","image/gif","image/png"};
			Integer maxSize=20480;
			FileUtil.validate(auatarFile, auatarFileContentType, auatarFileFileName, types, maxSize);
			String relaPath="/attachments/sxd/user/auatar";
			String realPath = FileUtil.getTomcatWebAppPath()+relaPath;
			FileUtil.writeFile(auatarFile, auatarFileFileName, realPath);
			TbSxdUser sxdUser = sxdUserModel.quereyTbSxdUserById(userId);
			if(sxdUser!=null){
				sxdUser.setAvatar(relaPath+"/"+auatarFileFileName);
				sxdUserModel.updateTbSxdUser(sxdUser);
			}
			result.put("success", true);
			result.put("msg", auatarFileFileName+"上传成功！");
		}catch(Exception e){
			result.put("success", false);
			result.put("msg", e.getMessage());
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		try {
			response.getWriter().write(JSONUtil.serialize(result));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 
		return null;
	}
	
	/**
	 * 生成一个长度为8位的号码
	 * @return
	 */
	private String generateXyNo()throws Exception{
		int len=8;
		String xyNo=null;
		while(true){
			xyNo=RandomStringUtils.randomNumeric(len);
			if(!sxdUserModel.checkXyNoIsExist(xyNo)){
				break;
			}
		}
		return xyNo;
	}

	public SxdUser getUser() {
		return user;
	}

	public void setUser(SxdUser user) {
		this.user = user;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public File getAuatarFile() {
		return auatarFile;
	}

	public void setAuatarFile(File auatarFile) {
		this.auatarFile = auatarFile;
	}

	public String getAuatarFileContentType() {
		return auatarFileContentType;
	}

	public void setAuatarFileContentType(String auatarFileContentType) {
		this.auatarFileContentType = auatarFileContentType;
	}

	public String getAuatarFileFileName() {
		return auatarFileFileName;
	}

	public void setAuatarFileFileName(String auatarFileFileName) {
		this.auatarFileFileName = auatarFileFileName;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
}
