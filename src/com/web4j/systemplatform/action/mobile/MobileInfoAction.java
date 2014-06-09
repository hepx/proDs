package com.web4j.systemplatform.action.mobile;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.systemplatform.model.mobile.MobileInfoModel;
import com.web4j.systemplatform.pojo.mobile.MobileInfo;

@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/mobile")
public class MobileInfoAction extends AbstractActionSupport {
	
	private static Logger log=Logger.getLogger(MobileInfoAction.class);
	
	@Autowired
	private MobileInfoModel mobileInfoModel;
	
	private MobileInfo mobileInfo;
	
	private List<MobileInfo> mobileInfos;
	
	@Action("queryAllMobileInfos")
	public String queryAllMobileInfos(){
		try{
			setMobileInfos(mobileInfoModel.queryMobileInfos());
			if(total==null){
				total=mobileInfoModel.queryMobileInofoCount(null);
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("queryOnePageMobileInfos")
	public String queryOnePageMobileInfos(){
		try{
			setMobileInfos(mobileInfoModel.queryMobileInfos(mobileInfo,start, limit));
			if(total==null){
				total=mobileInfoModel.queryMobileInofoCount(mobileInfo);
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateMobileInfo")
	public String saveOrUpdateMobileInfo(){
		try{
			mobileInfoModel.saveOrUpdateMobileInfo(mobileInfo);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateMobileInfos")
	public String saveOrUpdateMobileInfos(){
		try{
			mobileInfoModel.saveOrUpdateMobileInfos(mobileInfos);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("deleteMobileInfo")
	public String deleteMobileInfo(){
		try{
			mobileInfoModel.deleteMobileInfo(mobileInfo);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}

	public List<MobileInfo> getMobileInfos() {
		return mobileInfos;
	}

	public void setMobileInfos(List<MobileInfo> mobileInfos) {
		this.mobileInfos = mobileInfos;
	}

	public MobileInfo getMobileInfo() {
		return mobileInfo;
	}

	public void setMobileInfo(MobileInfo mobileInfo) {
		this.mobileInfo = mobileInfo;
	}
	
	
}
