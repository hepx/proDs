package com.web4j.qdtgplatform.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.qdtgplatform.dao.QdtgProductDao;
import com.web4j.qdtgplatform.entity.TbQdtgProduct;
import com.web4j.qdtgplatform.pojo.QdtgProduct;

@Service("qdtgProductModel")
public class QdtgProductModel {

	@Autowired
	private QdtgProductDao qdtgProductDao;
	
	public List<QdtgProduct> queryQdtgProduct(Integer start,Integer limit)throws Exception{
		List<TbQdtgProduct> tbQdtgProducts=qdtgProductDao.queryTbQdtgProduct(start, limit);
		List<QdtgProduct> qdtgProducts=new ArrayList<QdtgProduct>();
		for(TbQdtgProduct source:tbQdtgProducts){
			QdtgProduct target=new QdtgProduct();
			BeanUtils.copyProperties(source, target);
			qdtgProducts.add(target);
		}
		return qdtgProducts;
	}
	
	public List<QdtgProduct> queryAllQdtgProduct()throws Exception{
		List<TbQdtgProduct> tbQdtgProducts=qdtgProductDao.queryAllTbQdtgProduct();
		List<QdtgProduct> qdtgProducts=new ArrayList<QdtgProduct>();
		for(TbQdtgProduct source:tbQdtgProducts){
			QdtgProduct target=new QdtgProduct();
			BeanUtils.copyProperties(source, target);
			qdtgProducts.add(target);
		}
		return qdtgProducts;		
	}
	
	public Long queryQdtgProductCount(){
		return qdtgProductDao.counts();
	}
	
	public void saveOrUpdateQdtgProduct(QdtgProduct qdtgProduct)throws Exception{
		TbQdtgProduct tbQdtgProduct=new TbQdtgProduct();
		BeanUtils.copyProperties(qdtgProduct, tbQdtgProduct);
		qdtgProductDao.saveOrUpdate(tbQdtgProduct);
	}
	
	public void deleteQdtgProduct(List<QdtgProduct> qdtgProducts)throws Exception{
		for(QdtgProduct qdtgProduct:qdtgProducts){
			if(qdtgProduct.getProductId()!=null){
				qdtgProductDao.deleteById(qdtgProduct.getProductId());
			}
		}
	}
}
