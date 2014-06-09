package com.web4j.sxdplatform.action.lore;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.sxdplatform.model.BuddhismLoreModel;
import com.web4j.sxdplatform.pojo.BuddhismLore;

/**
 * 佛学常识
 * @author xixi
 * @date 2012-10-25
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/sxd/lore")
public class BuddhismLoreAction extends AbstractActionSupport {

	private static Logger log =Logger.getLogger(BuddhismLoreAction.class);
	
	@Autowired
	private BuddhismLoreModel buddhismLoreModel;
	
	private List<BuddhismLore> buddhismLores;
	
	@Action("queryBuddhismLores")
	public String queryBuddhismLores(){
		try{
			buddhismLores=buddhismLoreModel.queryBuddhismLores(start, limit);
			if(total==null){
				total=buddhismLoreModel.getBuddhismLoreCounts();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateBuddhismLores")
	public String saveOrUpdateBuddhismLores(){
		try{
			buddhismLoreModel.saveOrUpdateBuddhismLore(buddhismLores);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteBuddhismLores")
	public String deleteBuddhismLores(){
		try{
			buddhismLoreModel.deleteBuddhismLores(buddhismLores);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<BuddhismLore> getBuddhismLores() {
		return buddhismLores;
	}

	public void setBuddhismLores(List<BuddhismLore> buddhismLores) {
		this.buddhismLores = buddhismLores;
	}
	
	
}
