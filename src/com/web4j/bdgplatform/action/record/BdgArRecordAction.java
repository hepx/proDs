package com.web4j.bdgplatform.action.record;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.bdgplatform.entity.TbBdgArRecord;
import com.web4j.bdgplatform.model.BdgArRecordModel;
import com.web4j.common.action.AbstractActionSupport;
/**
 * BDG发布后计录管理ACTION
 * @author xixi
 * @date 2012-11-29
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/bdg/arrecord")
public class BdgArRecordAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(BdgArRecordAction.class);
	
	@Autowired
	private BdgArRecordModel bdgArRecordModel;
	
	private List<TbBdgArRecord> records;
	
	@Action("queryBdgArRecords")
	public String queryBdgArRecords(){
		try{
			records=bdgArRecordModel.queryBdgArRecords(start, limit);
			if(total==null){
				total=bdgArRecordModel.getBdgArRecordCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateBdgArRecords")
	public String saveOrUpdateBdgArRecords(){
		try{
			bdgArRecordModel.saveOrUpdateBdgArRecords(records);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteBdgArRecords")
	public String deleteBdgArRecords(){
		try{
			bdgArRecordModel.deleteBdgArRecords(records);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<TbBdgArRecord> getRecords() {
		return records;
	}

	public void setRecords(List<TbBdgArRecord> records) {
		this.records = records;
	}
}
