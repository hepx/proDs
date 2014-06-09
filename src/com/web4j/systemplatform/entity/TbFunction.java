package com.web4j.systemplatform.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.web4j.common.entity.BaseEntity;

/**
 * 方法表
 * @author hepx
 * @date 2010-12-5
 */
@Entity
@Table(name="tb_function")
public class TbFunction extends BaseEntity {

	private static final long serialVersionUID = -6226534911632359153L;
	
	public TbFunction(){}
	
	public TbFunction(Long id){
		super.setId(id);
	}

	public TbFunction(Long id,String functionName,Integer type,Long moduleId){
		super.setId(id);
		this.functionName=functionName;
		this.type=type;
		this.tbModule=new TbModule(moduleId);
	}

	@Column(name = "functionName", length = 300)
	private String functionName;// 方法名称，可以有多个中间用","隔开

	@Column(name = "type")
	private Integer type;// 操作类型：录入、查询、打印、导出等。

	@ManyToOne(targetEntity = TbModule.class)
	@JoinColumn(name = "tbModule_fk")
	private TbModule tbModule;// 所属模块
	
	@ManyToMany(mappedBy="tbFunctions")
	private Set<TbRole>tbRoles=new HashSet<TbRole>(0);//对应的权限

	public Set<TbRole> getTbRoles() {
		return tbRoles;
	}

	public void setTbRoles(Set<TbRole> tbRoles) {
		this.tbRoles = tbRoles;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public TbModule getTbModule() {
		return tbModule;
	}

	public void setTbModule(TbModule tbModule) {
		this.tbModule = tbModule;
	}

}
