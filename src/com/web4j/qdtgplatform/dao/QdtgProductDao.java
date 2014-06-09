package com.web4j.qdtgplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.qdtgplatform.entity.TbQdtgProduct;

@Repository("qdtgProductDao")
public class QdtgProductDao extends AbstractDao<TbQdtgProduct> {

	public List<TbQdtgProduct> queryTbQdtgProduct(Integer start,Integer limit) throws Exception{
		String hql="from TbQdtgProduct t order by t.productId desc";
		return list(hql,start,limit);
	}
	
	public List<TbQdtgProduct> queryAllTbQdtgProduct()throws Exception{
		String hql="select new TbQdtgProduct(productId,productName) from TbQdtgProduct t order by t.productId desc";
		return list(hql);
	}
}
