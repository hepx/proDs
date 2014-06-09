package com.web4j.sxdplatform.action.app;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.exception.DoErrorException;
import com.web4j.sxdplatform.model.DictModel;

@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/sxd/phone/dict")
public class DictOfPhoneAction extends AbstractActionSupport {

	private static Logger log = Logger .getLogger(DictOfPhoneAction.class);
	
	@Autowired
	private DictModel dictModel;
	
	private List dicts;
	
	private String dictGroup;
	
	@Action("queryDicts")
	public String queryDicts(){
		try{
			if(dictGroup!=null){
				dicts=dictModel.queryDictsOfPhone(dictGroup);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	

	public List getDicts() {
		return dicts;
	}

	public void setDicts(List dicts) {
		this.dicts = dicts;
	}

	public String getDictGroup() {
		return dictGroup;
	}

	public void setDictGroup(String dictGroup) {
		this.dictGroup = dictGroup;
	}
	
}
