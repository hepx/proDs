package com.web4j.datacapture.action.record;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.datacapture.entity.TbWeiboRecord;
import com.web4j.datacapture.model.WeiboRecordModel;
/**
 * 微博计录管理ACTION
 * @author xixi
 * @date 2012-11-29
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/capture/record")
public class WeiboRecordAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(WeiboRecordAction.class);
	
	@Autowired
	private WeiboRecordModel weiboRecordModel;
	
	private List<TbWeiboRecord> records;
	
	@Action("queryWbRecords")
	public String queryWeiboRecords(){
		try{
			records=weiboRecordModel.queryWeiboRecords(start, limit);
			if(total==null){
				total=weiboRecordModel.getTbWeiboRecordCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateWbRecords")
	public String saveOrUpdateWeiboRecords(){
		try{
			weiboRecordModel.saveOrUpdateWeiboRecords(records);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteWbRecords")
	public String deleteWeiboRecords(){
		try{
			weiboRecordModel.deleteTbWeiboRecords(records);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<TbWeiboRecord> getRecords() {
		return records;
	}

	public void setRecords(List<TbWeiboRecord> records) {
		this.records = records;
	}
}
