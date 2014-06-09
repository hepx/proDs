package com.web4j.sxdplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.sxdplatform.entity.TbSxdVersion;

@Repository("sxdVersionDao")
public class SxdVersionDao extends AbstractDao<TbSxdVersion> {

	public List<TbSxdVersion> queryTbSxdVersions(Integer start,Integer limit) throws Exception{
		String queryString = "from TbSxdVersion t order by t.versionId DESC";
		return list(queryString, start, limit);
	}
	
	public TbSxdVersion getLastTbSxdVersion()throws Exception{
		String queryString = "from TbSxdVersion t order by t.versionId DESC limit 1";
		List<TbSxdVersion> ls=list(queryString);
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}else{
			return null;
		}
	}
	
	public TbSxdVersion getTbSxdVersion(Integer internalVersion)throws Exception{
		String queryString = "from TbSxdVersion t where t.internalVersion=?";
		List<TbSxdVersion> ls=list(queryString,internalVersion);
		if(ls!=null&&ls.size()>0){
			return ls.get(0);
		}else{
			return null;
		}
	}

}
