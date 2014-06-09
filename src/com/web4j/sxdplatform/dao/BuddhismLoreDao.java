package com.web4j.sxdplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.sxdplatform.entity.TbBuddhismLore;

@Repository("buddhismLoreDao")
public class BuddhismLoreDao extends AbstractDao<TbBuddhismLore> {

	public List<TbBuddhismLore> quereyTbBuddhismLores(Integer start,Integer limit)throws Exception{
		String queryString = "from TbBuddhismLore t order by t.loreId DESC";
		return list(queryString, start, limit);
	}
	
	public List<TbBuddhismLore> queryTbBuddhismLoresByPhone(TbBuddhismLore tbLore,Integer start,Integer limit)throws Exception{
		StringBuffer queryString=new StringBuffer("select new TbBuddhismLore(t.loreId,t.loreTitle,t.loreType) from TbBuddhismLore t");
		queryString.append(" where 1=1");
		if(tbLore.getLoreType()!=null){
			queryString.append(" and t.loreType='"+tbLore.getLoreType()+"'");
		}
		if(tbLore.getLoreTitle()!=null){
			queryString.append(" and t.loreTitle like '%"+tbLore.getLoreTitle()+"%'");
		}
		queryString.append(" order by t.loreId DESC");
		return list(queryString.toString(),start,limit);
	}
	
}
