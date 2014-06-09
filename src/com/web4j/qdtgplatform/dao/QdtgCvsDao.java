package com.web4j.qdtgplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.qdtgplatform.entity.TbQdtgCvs;

@Repository("qdtgCvsDao")
public class QdtgCvsDao extends AbstractDao<TbQdtgCvs> {

	public List<TbQdtgCvs> quereyTbQdtgCvs(Integer start,Integer limit) throws Exception{
		String hql="from TbQdtgCvs t order by t.cvsId desc";
		return list(hql, start, limit);
	}
	
	public List<TbQdtgCvs> queryAllQdtgCvs()throws Exception{
		String hql="select new TbQdtgCvs(cvsId,cvsName) from TbQdtgCvs t order by t.cvsId desc";
		return list(hql);
	}
}
