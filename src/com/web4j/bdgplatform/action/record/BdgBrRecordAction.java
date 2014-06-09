package com.web4j.bdgplatform.action.record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.bdgplatform.entity.TbBdgBrRecord;
import com.web4j.bdgplatform.entity.TbBdgContentPush;
import com.web4j.bdgplatform.jobs.BdgTimeReleaseJob;
import com.web4j.bdgplatform.model.BdgBrRecordModel;
import com.web4j.bdgplatform.model.BdgContentPushModel;
import com.web4j.common.action.AbstractActionSupport;
import com.web4j.exception.DoErrorException;
/**
 * BDG发布前计录管理ACTION
 * @author xixi
 * @date 2012-11-29
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/bdg/brrecord")
public class BdgBrRecordAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(BdgBrRecordAction.class);
	
	@Autowired
	private BdgBrRecordModel bdgBrRecordModel;
	@Autowired
	private BdgTimeReleaseJob bdgTimeReleaseJob;
	@Autowired
	private BdgContentPushModel bdgPushRecordModel;
	
	private List<TbBdgBrRecord> records;
	
	private String cronExpression;
	
	private Map<String,Object> jobStatus;

	@Action("queryBdgBrRecords")
	public String queryBdgBrRecords(){
		try{
			records=bdgBrRecordModel.queryBdgBrRecords(start, limit);
			if(total==null){
				total=bdgBrRecordModel.getTbBdgBrRecordCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateBdgBrRecords")
	public String saveOrUpdateBdgBrRecords(){
		try{
			bdgBrRecordModel.saveOrUpdateBdgBrRecords(records);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteBdgBrRecords")
	public String deleteBdgBrRecords(){
		try{
			bdgBrRecordModel.deleteBdgBrRecords(records);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("releaseBdgBrRecords")
	public String releaseBdgBrRecords(){
		try{
			bdgBrRecordModel.updateReleaseBdgBrRecords(records);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("startBdgTimeRelease")
	public String startBdgTimeRelease(){
		try{
			if(cronExpression!=null){
				bdgTimeReleaseJob.start(cronExpression);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("pauseTimeRelease")
	public String pauseTimeRelease(){
		try{
			bdgTimeReleaseJob.pause();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("queryTimeReleaseJobStatus")
	public String queryTimeReleaseJobStatus(){
		try{
			jobStatus=bdgTimeReleaseJob.getCronTriggerStatus();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	/**
	 * 把记录增加到等推送的内容中
	 * @return
	 */
	@Action("addBdgPush")
	public String addBdgPush(){
		try{
			List<TbBdgContentPush> bdgPushRecords=null;
			if(records!=null&&records.size()>0){
				bdgPushRecords=new ArrayList<TbBdgContentPush>();
				for(TbBdgBrRecord brRecord:records){
					TbBdgContentPush pushRecord=new TbBdgContentPush(brRecord.getText());
					bdgPushRecords.add(pushRecord);
				}
			}
			//先发布记录
			bdgBrRecordModel.updateReleaseBdgBrRecords(records);
			//然后加入到推送记录中
			bdgPushRecordModel.saveBdgPushRecords(bdgPushRecords);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	public List<TbBdgBrRecord> getRecords() {
		return records;
	}

	public void setRecords(List<TbBdgBrRecord> records) {
		this.records = records;
	}

	public Map<String, Object> getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Map<String, Object> jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
}
