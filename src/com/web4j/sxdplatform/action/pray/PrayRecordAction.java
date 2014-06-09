package com.web4j.sxdplatform.action.pray;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.sxdplatform.model.PrayRecordModel;
import com.web4j.sxdplatform.pojo.PrayRecord;
/**
 * 祈福记录ACTION
 * @author xixi
 * @date 2012-11-14
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/sxd/pray")
public class PrayRecordAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(PrayRecordAction.class);
	
	@Autowired
	private PrayRecordModel prayRecordModel;
	
	private List<PrayRecord>prayRecords;
	
	@Action("queryPrayRecords")
	public String queryPrayRecords(){
		try{
			prayRecords=prayRecordModel.queryPrayRecords(start, limit);
			if(total==null){
				total=prayRecordModel.getPrayRecordCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deletePrayRecords")
	public String deletePrayRecords(){
		try{
			prayRecordModel.deletePrayRecords(prayRecords);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<PrayRecord> getPrayRecords() {
		return prayRecords;
	}

	public void setPrayRecords(List<PrayRecord> prayRecords) {
		this.prayRecords = prayRecords;
	}
	
}
