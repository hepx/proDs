package com.web4j.systemplatform.action.module;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.systemplatform.model.module.ModuleModel;
import com.web4j.systemplatform.pojo.module.ModulePojo;

/**
 * 模块ACTION
 * 
 * @author hepx
 * 
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/module")
public class ModuleAction extends AbstractActionSupport {

	protected Logger log = Logger.getLogger(ModuleAction.class);

	@Autowired
	private ModuleModel moduleModel;

	private List<ModulePojo> moduleTreePojos;

	private ModulePojo modulePojo;

	private String node;

	/**
	 * 保存模块
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveModule")
	public String saveModule() {
		try {
			moduleModel.saveOrUpdateModule(modulePojo);
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 删除模块
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("deleteModule")
	public String deleteModule() {
		try {
			moduleModel.deleteModule(modulePojo);
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 查询模块
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("queryModuleTreePojos")
	public String queryModulePojos() {
		try {
			moduleTreePojos = moduleModel.queryModuleTreePojos("root".equals(node) ? null : new Long(node));
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public ModulePojo getModulePojo() {
		return modulePojo;
	}

	public void setModulePojo(ModulePojo modulePojo) {
		this.modulePojo = modulePojo;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public List<ModulePojo> getModuleTreePojos() {
		return moduleTreePojos;
	}

	public void setModuleTreePojos(List<ModulePojo> moduleTreePojos) {
		this.moduleTreePojos = moduleTreePojos;
	}
}
