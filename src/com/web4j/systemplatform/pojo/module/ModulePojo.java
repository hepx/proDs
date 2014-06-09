package com.web4j.systemplatform.pojo.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.web4j.systemplatform.entity.TbFunction;
import com.web4j.systemplatform.entity.TbModule;
import com.web4j.systemplatform.pojo.function.FunctionPojo;

public class ModulePojo {
	
	private Long id;
	
	private Long parentId; 
	
	private String moduleId;// 模块编号

	private String text;// 模块名称
	
	private Boolean expanded;//是否默认展开
	
	private String iconCls;//图标定义

	private String file;// 对应ACTION的类名(绝对路径)
	
	private Boolean leaf;

	private List<ModulePojo>modulePojos;//子模块
	
	private List <FunctionPojo> functionPojos;// 对应的方法

	public ModulePojo() {
		// TODO Auto-generated constructor stub
	}
	
	public ModulePojo(Long id,String text,Set<TbFunction>tbFunctions) {
		this.id=id;
		this.text=text;
		this.functionPojos=paserTbFunctions(tbFunctions);
	}
	
	public ModulePojo(TbModule t){
		this.moduleId=t.getModuleId();
		this.text=t.getText();
		this.id=t.getId();
		this.file=t.getFile();
		this.leaf=t.getLeaf();
		this.expanded=t.getExpanded();
		this.iconCls=t.getIconCls();
		this.functionPojos=paserFunctionPojo(t.getTbFunctions());
	}

	private List<FunctionPojo>paserFunctionPojo(Set<TbFunction> tbFunctions){
		List<FunctionPojo>functionPojos=null;
		if(tbFunctions!=null&&tbFunctions.size()>0){
			functionPojos=new ArrayList<FunctionPojo>();
			for(TbFunction function:tbFunctions){
				FunctionPojo functionPojo=new FunctionPojo(function.getId(),function.getType(),function.getFunctionName());
				functionPojos.add(functionPojo);
			}
		}
		return functionPojos;
	}
	
	private List<FunctionPojo> paserTbFunctions(Set<TbFunction> tbFunctions){
		List<FunctionPojo>functionPojos=null;
		if(tbFunctions!=null&&tbFunctions.size()>0){
			functionPojos=new ArrayList<FunctionPojo>();
			for(TbFunction tbFunction:tbFunctions){
				FunctionPojo functionPojo=new FunctionPojo(tbFunction.getId(), tbFunction.getType());
				functionPojos.add(functionPojo);
			}
			Collections.sort(functionPojos, new Comparator<FunctionPojo>() {
				@Override
				public int compare(FunctionPojo o1, FunctionPojo o2) {
					// TODO Auto-generated method stub
					return o1.getType().compareTo(o2.getType());
				}
			});
		}
		return functionPojos;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public List<ModulePojo> getModulePojos() {
		return modulePojos;
	}

	public void setModulePojos(List<ModulePojo> modulePojos) {
		this.modulePojos = modulePojos;
	}

	public List<FunctionPojo> getFunctionPojos() {
		return functionPojos;
	}

	public void setFunctionPojos(List<FunctionPojo> functionPojos) {
		this.functionPojos = functionPojos;
	}
}
