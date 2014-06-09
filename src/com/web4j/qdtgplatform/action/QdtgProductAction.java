package com.web4j.qdtgplatform.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.qdtgplatform.model.QdtgProductModel;
import com.web4j.qdtgplatform.pojo.QdtgProduct;

@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/qdtg/product")
public class QdtgProductAction extends AbstractActionSupport {

	private static Logger log=Logger.getLogger(QdtgProductAction.class);
	
	@Autowired
	private QdtgProductModel qdtgProductModel;
	
	private List<QdtgProduct> qdtgProducts;
	
	private QdtgProduct qdtgProduct;
	
	@Action("queryQdtgProduct")
	public String queryQdtgProduct(){
		try{
			qdtgProducts=qdtgProductModel.queryQdtgProduct(start, limit);
			if(total==null){
				total=qdtgProductModel.queryQdtgProductCount();
			}
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("queryAllQdtgProduct")
	public String queryAllQdtgProduct(){
		try{
			qdtgProducts=qdtgProductModel.queryAllQdtgProduct();
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateQdtgProduct")
	public String saveOrUpdateQdtgProduct(){
		try{
			qdtgProductModel.saveOrUpdateQdtgProduct(qdtgProduct);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteQdtgProduct")
	public String deleteQdtgProduct(){
		try{
			qdtgProductModel.deleteQdtgProduct(qdtgProducts);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<QdtgProduct> getQdtgProducts() {
		return qdtgProducts;
	}

	public void setQdtgProducts(List<QdtgProduct> qdtgProducts) {
		this.qdtgProducts = qdtgProducts;
	}

	public QdtgProduct getQdtgProduct() {
		return qdtgProduct;
	}

	public void setQdtgProduct(QdtgProduct qdtgProduct) {
		this.qdtgProduct = qdtgProduct;
	}
	
}
