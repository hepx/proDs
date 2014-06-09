package com.web4j.bdgplatform.action.push;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.bdgplatform.entity.TbBdgContentPush;
import com.web4j.bdgplatform.jobs.BdgPushContentJob;
import com.web4j.bdgplatform.model.BdgContentPushModel;
import com.web4j.common.action.AbstractActionSupport;
import com.web4j.exception.DoErrorException;

/**
 * 推送应用ACTION
 * @author xixi
 * @date 2013-3-7
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/bdg/pushcontent")
public class PushContentAction extends AbstractActionSupport {

	private static Logger log=Logger.getLogger(PushContentAction.class);
	
	@Autowired
	private BdgContentPushModel bdgContentPushModel;
	
	@Autowired
	private BdgPushContentJob bdgPushJob;
	
	private List<TbBdgContentPush> bdgPushRecords; 
	
	private TbBdgContentPush bdgPushRecord;
	
	private String cronExpression;
	
	private Map<String,Object> jobStatus;
	
	@Action("queryBdgPushRecords")
	public String queryBdgPushRecords(){
		try{
			bdgPushRecords=bdgContentPushModel.queryBdgPushHistories(start, limit);
			if(total==null){
				total=bdgContentPushModel.getBdgPushRecordCount();
			}
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateBdgPushRecord")
	public String saveOrUpdateBdgPushRecord(){
		try{
			bdgContentPushModel.saveOrUpdatePushRecord(bdgPushRecord);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;		
	}
	
	@Action("delBdgPushRecords")
	public String deleteBdgPushRecords(){
		try{
			bdgContentPushModel.deleteBdgPushRecords(bdgPushRecords);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	/**
	 * 立即推
	 * @return
	 */
	@Action("pushBdgRecord")
	public String pushBdgRecord(){
		try{
			bdgContentPushModel.pushBdgRecord(bdgPushRecord);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	/**
	 * 启动定时推送
	 * @return
	 */
	@Action("startBdgPushContentJob")
	public String startBdgPushJob(){
		try{
			if(cronExpression!=null){
				bdgPushJob.start(cronExpression);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	/**
	 * 暂停定时推送
	 * @return
	 */
	@Action("pauseBdgPushContentJob")
	public String pauseBdgPushJob(){
		try{
			bdgPushJob.pause();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("queryBdgPushJobStatus")
	public String queryBdgPushJobStatus(){
		try{
			jobStatus=bdgPushJob.getCronTriggerStatus();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<TbBdgContentPush> getBdgPushRecords() {
		return bdgPushRecords;
	}

	public void setBdgPushRecords(List<TbBdgContentPush> bdgPushRecords) {
		this.bdgPushRecords = bdgPushRecords;
	}

	public TbBdgContentPush getBdgPushRecord() {
		return bdgPushRecord;
	}

	public void setBdgPushRecord(TbBdgContentPush bdgPushRecord) {
		this.bdgPushRecord = bdgPushRecord;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Map<String, Object> getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Map<String, Object> jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	
}
