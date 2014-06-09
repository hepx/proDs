package com.web4j.systemplatform.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 模块表
 * @author hepx
 * @date 2010-12-5
 */
@Entity
@Table(name="tb_module")
public class TbModule implements Comparable{
	
	public TbModule() {
	}
	
	public TbModule(Long id){
		this.id=id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 30,nullable=true,unique=true)
	private String moduleId;// 模块代码

	@Column(length = 50,nullable = true)
	private String text;// 模块名称
	
	@Column
	private Boolean leaf;//是否是子节点

	@Column
	private Boolean expanded;//是否默认展开
	
	@Column(length=20)
	private String iconCls;//图标定义
	
	@Column(length = 200)
	private String file;// 对应此模块的JS（相对路径）
	
	@OneToMany(mappedBy="tbModule",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<TbModule> tbModules=new HashSet<TbModule>(0);//子模块
	
	@ManyToOne(targetEntity=TbModule.class)
	@JoinColumn(name="parentId",updatable=false)
	private TbModule tbModule;

	@OneToMany(mappedBy = "tbModule",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TbFunction> tbFunctions = new HashSet<TbFunction>(0);// 对应的方法

	@ManyToMany(mappedBy = "tbModules",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Set<TbRole> tbRoles = new HashSet<TbRole>(0);// 对应的权限

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

	public Set<TbModule> getTbModules() {
		return tbModules;
	}

	public void setTbModules(Set<TbModule> tbModules) {
		this.tbModules = tbModules;
	}

	public TbModule getTbModule() {
		return tbModule;
	}

	public void setTbModule(TbModule tbModule) {
		this.tbModule = tbModule;
	}

	public Set<TbFunction> getTbFunctions() {
		return tbFunctions;
	}

	public void setTbFunctions(Set<TbFunction> tbFunctions) {
		this.tbFunctions = tbFunctions;
	}

	public Set<TbRole> getTbRoles() {
		return tbRoles;
	}

	public void setTbRoles(Set<TbRole> tbRoles) {
		this.tbRoles = tbRoles;
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
	
	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.id.compareTo(((TbModule)o).getId());
	}
}
