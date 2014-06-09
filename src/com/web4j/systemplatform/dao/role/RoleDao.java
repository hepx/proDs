package com.web4j.systemplatform.dao.role;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.exception.DoErrorException;
import com.web4j.systemplatform.entity.TbRole;

@Repository("roleDao")
public class RoleDao extends AbstractDao<TbRole> {
	
	public List<TbRole> queryTbRoles()throws Exception{
		return (List<TbRole>)listAll();
	}
	
	public TbRole queryTbRoleById(Long id)throws Exception{
		return get(id);
	}
	
	public void saveOrUpdateTbRole(TbRole tbRole)throws Exception{
		saveOrUpdate(tbRole);
	}
	
	public void deleteTbRole(Long id)throws Exception{
		deleteById(id);
	}

	/**
	 * 根据角色类别找到对应的角色
	 * @author : hepx 
	 * @date : 2011-3-14下午12:00:53
	 * @description : TODO
	 * @param roleType
	 * @return
	 * @throws Exception : TbRole
	 */
	public TbRole queryTbRole(String roleType)throws Exception{
		String hql="from TbRole T where T.roleType=?";
		List<TbRole>tbRoles=list(hql, new String[]{roleType});
		if(tbRoles!=null&&tbRoles.size()>0){
			return tbRoles.get(0);
		}else{
			throw new DoErrorException("角色类型未定义！");
		}
	}
}
