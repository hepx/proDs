package com.web4j.marketplatform.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import android.apk.parser.APK;
import android.apk.parser.APKParser;
import android.apk.utils.IconUtil;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.exception.DoErrorException;
import com.web4j.marketplatform.entity.TbAppInfo;
import com.web4j.marketplatform.model.AppInfoModel;
import com.web4j.util.FileUtil;

/**
 * 应用管理ACTION
 * @author xixi
 * @date 2013-1-22
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/market/appmgr")
public class AppMgrAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(AppMgrAction.class);
	
	private static String APP_DIR="/attachments/market/apps/";
	
	@Autowired
	private AppInfoModel appInfoModel;
	
	private List<TbAppInfo>appInfos;
	
	private TbAppInfo appInfo;
	
	private APK apk;
	
	private String appCategory;
	
	private String appName;
	
	private Long appId;
	
	private File app;
	
	private String appFileName;
	
	private String appContentType;
	
	private Map<String,Object>result;
	
	@Action("queryAppInfos")
	public String queryAppInfos(){
		try{
			appInfos=appInfoModel.queryAppInfos(start, limit);
			if(total==null){
				total=appInfoModel.getAppInfoCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("queryAllAppInfo")
	public String queryAllAppInfo(){
		try{
			appInfos=appInfoModel.queryAllAppInfo(appCategory);
			if(total==null){
				total=appInfoModel.getAppInfoCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("saveAppInfo")
	public String saveAppInfo(){
		try{
			appInfo.setCreateTime(new Date());
			appInfoModel.saveAppInfo(appInfo);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("deleteAppInfos")
	public String deleteAppInfos(){
		try{
			//删除数据库记录
			appInfoModel.deleteAppInfos(appInfos);
			//删除文件
			for(TbAppInfo appInfo:appInfos){
				FileUtil.removeFile(FileUtil.getTomcatWebAppPath()+APP_DIR+appInfo.getPackageName());
			}
			appInfos=null;
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	/**
	 * 上传APP包
	 * @return
	 */
	@Action("uploadApp")
	public String uploadApp(){
		result=new HashMap<String,Object>();
		try{
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			String []types={"application/x-zip-compressed","application/octet-stream"};
			String picName="icon_72.png";
			int maxSize=31457280;//30M
			FileUtil.validate(app, appContentType, appFileName,types,maxSize);
			apk=APKParser.getApk(app);
			//判断APP是否已经创建过。防止重复创建
			if(appInfoModel.queryAppInfo(apk.getPackageName())!=null){
				throw new DoErrorException(appFileName+"已经创建不能重复创建,如需更新APP请前往APP列表进行更新。");
			}
			String relaPath=APP_DIR+apk.getPackageName();
			String realPath = FileUtil.getTomcatWebAppPath()+relaPath;
			FileUtil.writeFile(app, appFileName, realPath);
			IconUtil.getIcon(app, "icon.png", realPath+"/"+picName);
			apk.setAppPic(relaPath+"/"+picName);
			apk.setAppPath(relaPath+"/"+appFileName);
			result.put("success", true);
			result.put("msg", appFileName+"上传成功！");	
			result.put("apk", apk);
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
	
	@Action("updateApp")
	public String updateApp(){
		result=new HashMap<String,Object>();
		try{
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			String []types={"application/x-zip-compressed","application/octet-stream"};
			String picName="icon_72.png";
			int maxSize=31457280;//30M
			//验证文件
			FileUtil.validate(app, appContentType, appFileName,types,maxSize);
			TbAppInfo tbAppInfo=appInfoModel.getAppInfoById(appId);
			apk=APKParser.getApk(app);
			//如果上传的APP的唯一包名不一样，视为不是同一款APP
			if(!apk.getPackageName().equals(tbAppInfo.getPackageName())){
				throw new DoErrorException("上传的APP与原有APP信息不符！");
			}
			if(apk.getAppVersionCode()<=tbAppInfo.getAppVersionCode()){
				throw new DoErrorException("当前版本已是最新版！");
			}
			String relaPath=APP_DIR+apk.getPackageName();
			String realPath = FileUtil.getTomcatWebAppPath()+relaPath;
			FileUtil.writeFile(app, appFileName, realPath);
			IconUtil.getIcon(app, "icon.png", realPath+"/"+picName);
			apk.setAppPic(relaPath+"/"+picName);
			apk.setAppPath(relaPath+"/"+appFileName);
			//更新APP记录
			tbAppInfo.setAppVersionCode(apk.getAppVersionCode());
			tbAppInfo.setAppVersionName(apk.getAppVersionName());
			tbAppInfo.setAppSize(apk.getAppSize());
			tbAppInfo.setSdkSupport(apk.getSdkSupport());
			tbAppInfo.setAppPath(relaPath+"/"+appFileName);
			appInfoModel.updateAppInfo(tbAppInfo);
			
			result.put("success", true);
			result.put("msg", appFileName+"更新成功！");
			result.put("apk", apk);
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

	public List<TbAppInfo> getAppInfos() {
		return appInfos;
	}

	public void setAppInfos(List<TbAppInfo> appInfos) {
		this.appInfos = appInfos;
	}

	public TbAppInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(TbAppInfo appInfo) {
		this.appInfo = appInfo;
	}

	public String getAppCategory() {
		return appCategory;
	}

	public void setAppCategory(String appCategory) {
		this.appCategory = appCategory;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public File getApp() {
		return app;
	}

	public void setApp(File app) {
		this.app = app;
	}

	public String getAppFileName() {
		return appFileName;
	}

	public void setAppFileName(String appFileName) {
		this.appFileName = appFileName;
	}

	public String getAppContentType() {
		return appContentType;
	}

	public void setAppContentType(String appContentType) {
		this.appContentType = appContentType;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public APK getApk() {
		return apk;
	}

	public void setApk(APK apk) {
		this.apk = apk;
	}
}
