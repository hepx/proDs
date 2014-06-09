package com.web4j.systemplatform.dao.module;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.systemplatform.entity.TbModule;

@Repository("moduleDao")
public class ModuleDao extends AbstractDao<TbModule> {
	
	public List<TbModule>queryModule()throws Exception{
		return listAll();
	}
	
	public void saveModule(TbModule t)throws Exception{
		save(t);
	}
	
	public void saveOrUpdateModule(TbModule t)throws Exception{
		saveOrUpdate(t);
	}
	
	public void deleteModule(TbModule t)throws Exception{
		delete(t);
	}
	
	public void deleteModule(Long id)throws Exception{
		deleteById(id);
	}
	
	public List<TbModule> queryTbModuleTreeList(Long node)throws Exception{
		String hql="from TbModule t";
		if(node!=null&&!"".equals(node)){
			hql+=" where t.tbModule.id="+node;
		}else{
			hql+=" where t.tbModule is null";
		}
		hql+=" order by t.id";
		return list(hql);
	}
	
	public List<TbModule> queryTbModuleTreeList(Long node,Long roleId)throws Exception{
		String hql="select t from TbModule t left join t.tbRoles e ";
		if(node!=null&&!"".equals(node)){
			hql+=" where t.tbModule.id="+node;
		}else{
			hql+=" where t.tbModule is null";
		}		
		hql+=" and e.id="+roleId+" order by t.id";
		return list(hql);
	}
}
