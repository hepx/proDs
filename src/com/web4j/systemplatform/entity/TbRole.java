package com.web4j.systemplatform.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.web4j.common.entity.BaseEntity;

/**
 * 角色表
 * @author hepx
 * @date 2010-12-5
 */
@Entity
@Table(name="tb_role")
public class TbRole extends BaseEntity {

	private static final long serialVersionUID = -1235273278778464529L;
	
	public TbRole(){}
	
	public TbRole(Long id){
		super.setId(id);
	}
	
	public TbRole(Long id,String roleName,String roleType,String roleCode,String roleDesc){
		super(id);
		this.roleName=roleName;
		this.roleType=roleType;
		this.roleCode=roleCode;
		this.roleDesc=roleDesc;
	}

	@Column(name = "roleName", length = 64, nullable = true, unique = true)
	private String roleName;// 角色名称

	@Column(name = "roleType")
	private String roleType;// 角色类型  对应dicInfo表里的dicValue字段
	
	@Column(name ="roleCode")
	private String roleCode;//角色代码

	@Column(name = "roleDesc", length = 100)
	private String roleDesc;// 描述

	@OneToMany(mappedBy = "tbRole",cascade=CascadeType.ALL)
	private Set<TbUserInfo> tbUserInfos = new HashSet<TbUserInfo>(0);// 角色对应用户

	@ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Set<TbModule> tbModules = new HashSet<TbModule>(0);// 对应的模块

	@ManyToMany(fetch = FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Set<TbFunction> tbFunctions=new HashSet<TbFunction>(0);//对应的方法
	
	public Set<TbFunction> getTbFunctions() {
		return tbFunctions;
	}

	public void setTbFunctions(Set<TbFunction> tbFunctions) {
		this.tbFunctions = tbFunctions;
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

	public Set<TbUserInfo> getTbUserInfos() {
		return tbUserInfos;
	}

	public void setTbUserInfos(Set<TbUserInfo> tbUserInfos) {
		this.tbUserInfos = tbUserInfos;
	}

	public Set<TbModule> getTbModules() {
		return tbModules;
	}

	public void setTbModules(Set<TbModule> tbModules) {
		this.tbModules = tbModules;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	
}
