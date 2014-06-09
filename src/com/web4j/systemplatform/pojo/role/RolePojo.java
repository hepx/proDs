package com.web4j.systemplatform.pojo.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.web4j.systemplatform.entity.TbFunction;
import com.web4j.systemplatform.entity.TbModule;

public class RolePojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7469006427763756030L;
	
	public RolePojo(){}
	
	public RolePojo(Long id,String roleName,String roleType,String roleCode,String roleDesc){
		this.id=id;
		this.roleName=roleName;
		this.roleType=roleType;
		this.roleCode=roleCode;
		this.roleDesc=roleDesc;
	}
	
	public RolePojo(Long id,String roleName,String roleType,String roleCode,String roleDesc,Set<TbModule>tbModules,Set<TbFunction>tbFunctions){
		this.id=id;
		this.roleName=roleName;
		this.roleType=roleType;
		this.roleCode=roleCode;
		this.roleDesc=roleDesc;
		this.moduleIds=createModuleIds(tbModules);
		this.functionIds=CreateFunctionIds(tbFunctions);
	}

	private Long id;
	
	private String roleName;// 角色名称

	private String roleType;// 角色类型 
	
	private String roleCode;//角色代码

	private String roleDesc;// 描述
	
	private List<Long> moduleIds; //对应模块的IDS
	
	private List<Long> functionIds; //对应方法的IDS

	private List<Long> createModuleIds(Set<TbModule>tbModules){
		List<Long> moduleList=null;
		if(tbModules!=null&&tbModules.size()>0){
			moduleList=new ArrayList<Long>();
			for(TbModule tbModule:tbModules){
				moduleList.add(tbModule.getId());
			}
		}
		return moduleList;
	}
	
	private List<Long> CreateFunctionIds(Set<TbFunction>tbFunctions){
		List<Long> functionList=null;
		if(tbFunctions!=null&&tbFunctions.size()>0){
			functionList=new ArrayList<Long>();
			for(TbFunction tbFunction:tbFunctions){
				functionList.add(tbFunction.getId());
			}
		}
		return functionList;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<Long> getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(List<Long> moduleIds) {
		this.moduleIds = moduleIds;
	}

	public List<Long> getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(List<Long> functionIds) {
		this.functionIds = functionIds;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	

}
