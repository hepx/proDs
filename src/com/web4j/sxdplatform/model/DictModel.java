package com.web4j.sxdplatform.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.sxdplatform.dao.DictDao;
import com.web4j.sxdplatform.entity.TbDict;
import com.web4j.sxdplatform.pojo.Dict;

@Service("dictModel")
public class DictModel {

	@Autowired
	private DictDao dictDao;
	
	public List<Dict> queryAllDict() throws Exception{
		List<Dict> dicts=new ArrayList<Dict>();
		List<TbDict>tbDicts=dictDao.listAll();
		for(TbDict tbDict : tbDicts){
			Dict dict=new Dict();
			BeanUtils.copyProperties(tbDict, dict);
			dicts.add(dict);
		}
		return dicts;
	}
	
	public List<Dict> queryDict(String dictGroup) throws Exception{
		List<TbDict>tbDicts=dictDao.quereyTbDicts(dictGroup);
		List<Dict> dicts=new ArrayList<Dict>();
		for(TbDict src :tbDicts){
			Dict target=new Dict();
			BeanUtils.copyProperties(src, target);
			dicts.add(target);
		}
		return dicts;
	}
	
	public void saveOrUpdateDicts(List<Dict>dicts)throws Exception{
		List<TbDict> tbDicts =new ArrayList<TbDict>();
		for(Dict dict : dicts){
			TbDict tbDict =new TbDict();
			BeanUtils.copyProperties(dict, tbDict);
			tbDicts.add(tbDict);
		}
		dictDao.saveOrUpdateAll(tbDicts);
	}
	
	public void deleteDicts(List<Dict>dicts)throws Exception{
		for(Dict dict:dicts){
			if(dict.getDictId()!=null&&!"".equals(dict.getDictId())){
				dictDao.deleteById(dict.getDictId());
			}
		}
	}
	
	public TbDict quereyTbDict(Long dictId)throws Exception{
		return dictDao.get(dictId);
	}
	
	public void updateTbDict(TbDict tbDict)throws Exception{
		dictDao.update(tbDict);
	}
	
	/***************手机调用**************/
	public List<TbDict> queryDictsOfPhone(String dictGroup)throws Exception{
		return dictDao.queryTbDictsOfPhone(dictGroup);
	}
	
}
