package com.web4j.sxdplatform.action.lore;

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
import com.web4j.sxdplatform.model.BuddhismLoreModel;
import com.web4j.sxdplatform.pojo.BuddhismLore;
import com.web4j.util.SysConfig;
/**
 * 佛学常识手机调用ACTION
 * @author xixi
 * @date 2012-11-2
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/sxd/phone/lore")
public class BuddhismLoreOfPhoneAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(BuddhismLoreOfPhoneAction.class);
	
	@Autowired
	private BuddhismLoreModel buddhismLoreModel;
	
	private Long loreId;
	
	private String loreTitle;
	
	private String loreType;
	
	private String loreContent;

	private List lores;
	
	@Action("queryLores")
	public String queryLores(){
		try{
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			BuddhismLore lore=new BuddhismLore(loreTitle,loreType);
			lores=buddhismLoreModel.queryBuddhismLoresOfPhone(lore, start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("queryContent")
	public String queryContent(){
		try{
			if(loreId!=null){
				loreContent=buddhismLoreModel.getBuddhismLoreContent(loreId);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public Long getLoreId() {
		return loreId;
	}

	public void setLoreId(Long loreId) {
		this.loreId = loreId;
	}

	public String getLoreTitle() {
		return loreTitle;
	}

	public List getLores() {
		return lores;
	}

	public void setLores(List lores) {
		this.lores = lores;
	}

	public void setLoreTitle(String loreTitle) {
		this.loreTitle = loreTitle;
	}

	public String getLoreType() {
		return loreType;
	}

	public void setLoreType(String loreType) {
		this.loreType = loreType;
	}

	public String getLoreContent() {
		return loreContent;
	}

	public void setLoreContent(String loreContent) {
		this.loreContent = loreContent;
	}
	
}
