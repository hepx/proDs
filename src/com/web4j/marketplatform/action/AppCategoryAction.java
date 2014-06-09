package com.web4j.marketplatform.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.marketplatform.entity.TbAppCategory;
import com.web4j.marketplatform.model.AppCategoryModel;

/**
 * 应用分类ACTION
 * @author xixi
 * @date 2013-1-22
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/market/category")
public class AppCategoryAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(AppCategoryAction.class);
	
	@Autowired
	private AppCategoryModel appCategoryModel;
	
	private List<TbAppCategory> appCategorys;
	
	@Action("queryAppCategorys")
	public String queryAppCategorys(){
		try{
			appCategorys=appCategoryModel.queryAppCategorys(start, limit);
			if(total==null){
				total=appCategoryModel.getAppCategoryCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("queryAllAppCategory")
	public String queryAllAppCategory(){
		try{
			appCategorys=appCategoryModel.queryAllAppCategorys();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateAppCategorys")
	public String saveOrUpdateAppCategorys(){
		try{
			appCategoryModel.saveOrUpdateAppCategorys(appCategorys);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteAppCategorys")
	public String deleteAppCategorys(){
		try{
			appCategoryModel.deleteAppCategorys(appCategorys);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<TbAppCategory> getAppCategorys() {
		return appCategorys;
	}

	public void setAppCategorys(List<TbAppCategory> appCategorys) {
		this.appCategorys = appCategorys;
	}
}
