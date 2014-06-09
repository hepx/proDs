package com.web4j.marketplatform.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.marketplatform.dao.AppCategoryDao;
import com.web4j.marketplatform.entity.TbAppCategory;

@Service("appCategoryModel")
public class AppCategoryModel {

	@Autowired
	private AppCategoryDao appCategoryDao;
	
	public List<TbAppCategory> queryAppCategorys(Integer start,Integer limit)throws Exception{
		return appCategoryDao.quereyTbAppCategorys(start, limit);
	}
	
	public List<TbAppCategory> queryAllAppCategorys()throws Exception{
		return appCategoryDao.queryAllTbAppCategorys();
	}
	
	public void saveOrUpdateAppCategorys(List<TbAppCategory> appCategorys)throws Exception{
		appCategoryDao.saveOrUpdateAll(appCategorys);
	}
	
	public Long getAppCategoryCount(){
		return appCategoryDao.counts();
	}
	
	public void deleteAppCategorys(List<TbAppCategory> appCategorys)throws Exception{
		for(TbAppCategory appCategory:appCategorys){
			if(appCategory.getCategoryId()!=null){
				appCategoryDao.deleteById(appCategory.getCategoryId());
			}
		}
	}
}
