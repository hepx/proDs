package com.web4j.systemplatform.action.dic;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.systemplatform.model.dic.DicModel;
import com.web4j.systemplatform.pojo.dic.DicInfo;

@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/dic")
public class DicInfoAction extends AbstractActionSupport {

	protected Logger log=Logger.getLogger(DicInfoAction.class);
	
	@Autowired
	private DicModel dicModel;
	
	private String dicGroup;
	
	private List<DicInfo>dicInfos;
	
	/**
	 * 
	 * @author : hepx 
	 * @date : 2011-3-11下午12:23:51
	 * @description : 查询字典消息
	 * @return : String
	 */
	@Action("queryDicInfo")
	public String execute(){
		try{
			dicInfos=dicModel.queryDicInfos(dicGroup);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public String getDicGroup() {
		return dicGroup;
	}

	public void setDicGroup(String dicGroup) {
		this.dicGroup = dicGroup;
	}

	public List<DicInfo> getDicInfos() {
		return dicInfos;
	}

	public void setDicInfos(List<DicInfo> dicInfos) {
		this.dicInfos = dicInfos;
	}
	
	
}
