package com.web4j.systemplatform.pojo.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.web4j.systemplatform.entity.TbFunction;
import com.web4j.systemplatform.pojo.function.FunctionPojo;

public class ModuleTreePojo {

	private Long id;

	private String text;// 模块名称
	
	private Boolean expanded;//是否默认展开
	
	private String iconCls;//图标定义

	private String file;// 对应ACTION的类名(绝对路径)
	
	private Boolean leaf;
	
	private List <FunctionPojo> functionPojos;// 对应的方法
	
	public ModuleTreePojo(){}
	
//	public ModuleTreePojo(TbModule t,boolean isLeaf){
//		this.id=t.getId();
//		this.text=t.getModuleName();
//		this.moduleCode=t.getModuleCode();
//		this.actionClassName=t.getActionClassName();
//		this.leaf=isLeaf;
//		this.functionPojos=paserFunctionPojo(t.getTbFunctions());
//	}
	
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public List<FunctionPojo> getFunctionPojos() {
		return functionPojos;
	}

	public void setFunctionPojos(List<FunctionPojo> functionPojos) {
		this.functionPojos = functionPojos;
	}
	
	
}
