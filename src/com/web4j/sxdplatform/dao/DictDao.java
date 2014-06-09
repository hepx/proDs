package com.web4j.sxdplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.sxdplatform.entity.TbDict;

@Repository("dictDao")
public class DictDao extends AbstractDao<TbDict> {

	public List<TbDict> quereyTbDicts(String dictGroup) throws Exception{
		String queryString="select new TbDict(dictName,dictValue) " +
				"from TbDict t where t.dictGroup=? order by t.sort";
		return list(queryString, dictGroup);
	}
	
	public List<TbDict> queryTbDictsOfPhone(String dictGroup)throws Exception{
		String queryString="select new TbDict(dictName,dictValue,iconPath) " +
				"from TbDict t where t.dictGroup=? order by t.sort";
		return list(queryString, dictGroup);
	}
}
