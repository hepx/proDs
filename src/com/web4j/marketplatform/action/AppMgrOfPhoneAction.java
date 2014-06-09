package com.web4j.marketplatform.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.exception.DoErrorException;
import com.web4j.marketplatform.entity.TbAppInfo;
import com.web4j.marketplatform.model.AppInfoModel;
/**
 * 应用更新接口
 * @author xixi
 * @date 2013-2-19
 *
 */

@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/market/phone/appup")
public class AppMgrOfPhoneAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(AppMgrOfPhoneAction.class);
	
	@Autowired
	private AppInfoModel appInfoModel;
	
	private String packageName;
	
	private TbAppInfo appInfo;
	
	/**
	 * 取APP
	 * @return
	 */
	@Action("getAppInfo")
	public String findAppInfo(){
		try{
			if(packageName!=null&&!"".equals(packageName)){
				appInfo=appInfoModel.queryAppInfo(packageName);
				packageName=null;
			}else{
				throw new DoErrorException("缺少必要的参数！");
			}
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	/**
	 * 更新下载次数
	 * @return
	 */
	@Action("upDownloads")
	public String upDownloads(){
		try{
			if(packageName!=null&&!"".equals(packageName)){
				TbAppInfo appInfo=appInfoModel.queryAppInfo(packageName);
				if(appInfo!=null){
					appInfo.setDownloads(appInfo.getDownloads()+1);
					appInfoModel.updateAppInfo(appInfo);
				}
				packageName=null;
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public TbAppInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(TbAppInfo appInfo) {
		this.appInfo = appInfo;
	}
	
}
