package com.web4j.qdtgplatform.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.qdtgplatform.model.QdtgCvsModel;
import com.web4j.qdtgplatform.pojo.QdtgCvs;
import com.web4j.systemplatform.pojo.userInfo.UserInfoPojo;

@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/qdtg/cvs")
public class QdtgCvsAction extends AbstractActionSupport {

	private static Logger log=Logger.getLogger(QdtgCvsAction.class);
	
	@Autowired
	private QdtgCvsModel qdtgCvsModel;
	
	private List<QdtgCvs> qdtgCvsList;
	
	private QdtgCvs qdtgCvs;
	private UserInfoPojo userInfo;
	
	@Action("queryQdtgCvs")
	public String queryQdtgCvs(){
		try{
			qdtgCvsList=qdtgCvsModel.queryQdtgCvs(start, limit);
			if(total==null){
				total=qdtgCvsModel.queryQdtgCvsCount();
			}
		}catch(Exception e){
			log.error(e.getMessage(), e);
			setErrorMessage(e);
		}
		return JSON;
	}
	
	@Action("queryAllQdtgCvs")
	public String queryAllQdtgCvs(){
		try{
			qdtgCvsList=qdtgCvsModel.queryAllQdtgCvs();
		}catch(Exception e){
			log.error(e.getMessage(), e);
			setErrorMessage(e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateQdtgCvs")
	public String saveOrUpdateQdtgCvs(){
		try{
			qdtgCvsModel.saveOrUpdateQdtgCvs(qdtgCvs,userInfo);
		}catch(Exception e){
			log.error(e.getMessage(), e);
			setErrorMessage(e);
		}
		return JSON;
	}
	
	@Action("deleteQdtgCvs")
	public String deleteQdtgCvs(){
		try{
			qdtgCvsModel.deleteQdtgCvs(qdtgCvsList);
		}catch(Exception e){
			log.error(e.getMessage(), e);
			setErrorMessage(e);
		}
		return JSON;
	}


	public List<QdtgCvs> getQdtgCvsList() {
		return qdtgCvsList;
	}

	public void setQdtgCvsList(List<QdtgCvs> qdtgCvsList) {
		this.qdtgCvsList = qdtgCvsList;
	}

	public QdtgCvs getQdtgCvs() {
		return qdtgCvs;
	}

	public void setQdtgCvs(QdtgCvs qdtgCvs) {
		this.qdtgCvs = qdtgCvs;
	}

	public UserInfoPojo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoPojo userInfo) {
		this.userInfo = userInfo;
	}
	
}
