package com.web4j.sxdplatform.action.books;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.sxdplatform.model.ChantingRecordModel;
import com.web4j.sxdplatform.pojo.ChantingRecord;
/**
 * 诵经记录ACTION
 * @author xixi
 * @date 2012-11-1
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/sxd/chantrecord")
public class ChantingRecordAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(ChantingRecordAction.class);
	
	@Autowired
	private ChantingRecordModel chantingRecordModel; 
	
	private List<ChantingRecord> chantingRecords;
	
	@Action("queryChantRecords")
	public String queryChantingRecords(){
		try{
			chantingRecords=chantingRecordModel.queryChantingRecords(start, limit);
			if(total==null){
				total=chantingRecordModel.getChantingRecordCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteChantRecords")
	public String deleteChantingRecords(){
		try{
			chantingRecordModel.deleteChantingRecord(chantingRecords);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<ChantingRecord> getChantingRecords() {
		return chantingRecords;
	}

	public void setChantingRecords(List<ChantingRecord> chantingRecords) {
		this.chantingRecords = chantingRecords;
	}
	
	
}
