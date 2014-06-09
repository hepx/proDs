package com.web4j.systemplatform.action.role;

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
import com.web4j.systemplatform.model.role.RoleModel;
import com.web4j.systemplatform.pojo.module.ModulePojo;
import com.web4j.systemplatform.pojo.role.RolePojo;
import com.web4j.systemplatform.pojo.userInfo.UserInfoPojo;
import com.web4j.util.LoginUserUtils;

@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/role")
public class RoleAction extends AbstractActionSupport {

	protected Logger log = Logger.getLogger(RoleAction.class);

	@Autowired
	private RoleModel roleModel;

	private RolePojo rolePojo;
	private List<RolePojo> rolePojos;
	private List<ModulePojo> modulePojos;

	/**
	 * 查询所有角色 author:hepx 2011-3-9
	 * 
	 * @return
	 */
	@Action("queryRole")
	public String queryRoleList() {
		try {
			rolePojos = roleModel.queryRolePojo();
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 根据角色ID查询该角色所有的权限 author:hepx 2011-3-9
	 * 
	 * @return
	 */
	@Action("queryRoleById")
	public String queryRoleById() {
		try {
			rolePojo = roleModel.queryRolePojoById(rolePojo.getId());
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 保存和更新角色权限 author:hepx 2011-3-9
	 * 
	 * @return
	 */
	@Action("saveOrUpdateRole")
	public String saveOrUpdateRole() {
		try {
			roleModel.saveRolePojo(rolePojo);
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	@Action("deleteRole")
	public String deleteRole() {
		try {
			roleModel.deleteRolePojo(rolePojo);
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	@Action("queryModules")
	public String queryModules() {
		try {
			modulePojos = roleModel.queryModulePojos();
		} catch (Exception e) {
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("queryRoleModules")
	public String queryModulesByRole(){
		try{
			UserInfoPojo userInfo=LoginUserUtils.getLoginUserInfo();
			if(userInfo!=null){
				if(userInfo.getRoleId()!=null){
					modulePojos = roleModel.queryModulePojos(userInfo.getRoleId());
				}else{
					throw new DoErrorException("缺失必要参数！");
				}
			}else{
				throw new DoErrorException("会话超时，请重新登录！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;	
	}

	public List<ModulePojo> getModulePojos() {
		return modulePojos;
	}

	public void setModulePojos(List<ModulePojo> modulePojos) {
		this.modulePojos = modulePojos;
	}

	public RolePojo getRolePojo() {
		return rolePojo;
	}

	public void setRolePojo(RolePojo rolePojo) {
		this.rolePojo = rolePojo;
	}

	public List<RolePojo> getRolePojos() {
		return rolePojos;
	}

	public void setRolePojos(List<RolePojo> rolePojos) {
		this.rolePojos = rolePojos;
	}

}
