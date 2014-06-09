package com.web4j.marketplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.marketplatform.entity.TbAppCategory;

@Repository("appCategoryDao")
public class AppCategoryDao extends AbstractDao<TbAppCategory> {

	public List<TbAppCategory> quereyTbAppCategorys(Integer start,Integer limit)throws Exception{
		String queryString = "from TbAppCategory t order by t.categoryId DESC";
		return list(queryString, start, limit);
	}
	
	public List<TbAppCategory> queryAllTbAppCategorys() throws Exception{
		String queryString ="select new TbAppCategory(categoryName,categoryValue) from TbAppCategory t order by t.categoryId ASC";
		return list(queryString);
	}
}
