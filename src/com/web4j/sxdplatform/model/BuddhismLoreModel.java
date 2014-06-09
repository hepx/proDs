package com.web4j.sxdplatform.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.sxdplatform.dao.BuddhismLoreDao;
import com.web4j.sxdplatform.entity.TbBuddhismLore;
import com.web4j.sxdplatform.pojo.BuddhismLore;

@Service("buddhismLoreModel")
public class BuddhismLoreModel {

	@Autowired
	private BuddhismLoreDao buddhismLoreDao;
	
	public void saveOrUpdateBuddhismLore(List<BuddhismLore> buddhismLores)throws Exception{
		List<TbBuddhismLore>tbBuddhismLores=new ArrayList<TbBuddhismLore>();
		for(BuddhismLore buddhismLore:buddhismLores){
			TbBuddhismLore tbBuddhismLore=new TbBuddhismLore();
			BeanUtils.copyProperties(buddhismLore, tbBuddhismLore);
			if(tbBuddhismLore.getCreateTime()==null){
				tbBuddhismLore.setCreateTime(new Date());
			}
			tbBuddhismLores.add(tbBuddhismLore);
		}
		buddhismLoreDao.saveOrUpdateAll(tbBuddhismLores);
	}
	
	public List<BuddhismLore> queryBuddhismLores(Integer start,Integer limit) throws Exception{
		List<TbBuddhismLore>tbBuddhismLores =buddhismLoreDao.quereyTbBuddhismLores(start, limit);
		List<BuddhismLore> buddhismLores =new ArrayList<BuddhismLore>();
		for(TbBuddhismLore tbBuddhismLore : tbBuddhismLores){
			BuddhismLore buddhismLore=new BuddhismLore();
			BeanUtils.copyProperties(tbBuddhismLore, buddhismLore);
			buddhismLores.add(buddhismLore);
		}
		return buddhismLores;
	}
	
	public Long getBuddhismLoreCounts(){
		return buddhismLoreDao.counts();
	}
	
	public void deleteBuddhismLores(List<BuddhismLore>buddhismLores)throws Exception{
		for(BuddhismLore buddhismLore : buddhismLores){
			if(buddhismLore.getLoreId()!=null&&!"".equals(buddhismLore.getLoreId())){
				buddhismLoreDao.deleteById(buddhismLore.getLoreId());
			}
		}
	}
	
	public String getBuddhismLoreContent(Long loreId) throws Exception{
		TbBuddhismLore tbLore=buddhismLoreDao.get(loreId);
		if(tbLore!=null){
			return tbLore.getLoreContent();
		}else{
			return null;
		}
	}
	
	public List<TbBuddhismLore> queryBuddhismLoresOfPhone(BuddhismLore lore,Integer start,Integer limit) throws Exception{
		TbBuddhismLore tbLore=new TbBuddhismLore();
		BeanUtils.copyProperties(lore, tbLore);
		return buddhismLoreDao.queryTbBuddhismLoresByPhone(tbLore, start, limit);
	}
}
