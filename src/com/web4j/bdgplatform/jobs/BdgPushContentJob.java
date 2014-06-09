package com.web4j.bdgplatform.jobs;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.web4j.bdgplatform.model.BdgContentPushModel;
import com.web4j.job.QuartzScheduleMgr;
import com.web4j.systemplatform.model.logs.LogsModel;
/**
 * 不得哥定时推送内容任务
 * @author xixi
 * @date 2012-12-11
 *
 */
@Service("bdgPushContentJob")
public class BdgPushContentJob extends QuartzJobBean {

	private static Logger log = Logger.getLogger(BdgPushContentJob.class);
	
	private static final String JOBNAME="bdg_pushcontent";
	private static final String JOBGROUP="job_group";
	private static final String TRIGGERNAME="bdg_pushcontent_trigger";
	private static final String TRIGGERGROUP="trigger_group";
	
	@Autowired
	private LogsModel logs;
	//这个定义蛋痛吧。JOBS里不能注入logs，只能通过XML里jobDataAsMap定义logsModel
	private LogsModel logsModel;
	private BdgContentPushModel bdgContentPushModel;
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try{
			logsModel.insertOneLog("执行BDG推送内容任务......");
			bdgContentPushModel.pushRecord();
		}catch(Exception e){
			//e.printStackTrace();
			String error="BDG定时推送内容任务异常。"+e.getMessage();
			try {
				logsModel.insertOneLog(error);
			} catch (Exception e1) {
				log.error(e1.getMessage(), e1);
			}
			log.error(error, e);
		}

	}
	
	public void start(String cronExpression) throws Exception{
		//获得XML里默认配置的JobDetail
		JobDetail jobDetail=QuartzScheduleMgr.getJobDetail(JOBNAME, JOBGROUP);
		JobDetail newJobDetail=(JobDetail)jobDetail.clone();
		QuartzScheduleMgr.deleteJob(JOBNAME, JOBGROUP);
		//创建一个Cron触发器
		CronTrigger trigger=new CronTrigger(TRIGGERNAME, TRIGGERGROUP);
		//设置Cron表达式
		trigger.setCronExpression(cronExpression);
		logs.insertOneLog("启动BDG定时推送内容任务...");
		QuartzScheduleMgr.scheduleJob(newJobDetail, trigger);
		if(!QuartzScheduleMgr.isStart()){
			QuartzScheduleMgr.start();
		}
	}
	
	public void resume() throws Exception{
		logs.insertOneLog("恢复BDG定时推送内容任务...");
		QuartzScheduleMgr.resumeJob(JOBNAME, JOBGROUP);
	}
	
	public void pause() throws Exception{
		logs.insertOneLog("暂停BDG定时推送内容任务...");
		QuartzScheduleMgr.pauseJob(JOBNAME, JOBGROUP);
	}
	
	public Map<String,Object> getCronTriggerStatus() throws SchedulerException{
		Map<String,Object> jobStatus=null;
		CronTrigger trigger=QuartzScheduleMgr.getCronTrigger(TRIGGERNAME, TRIGGERGROUP);
		if(trigger!=null){
			jobStatus=new HashMap<String,Object>();
			int status= QuartzScheduleMgr.getTriggerState(TRIGGERNAME, TRIGGERGROUP);
			String ex=trigger.getCronExpression();
			jobStatus.put("status", status);
			jobStatus.put("cronExpression", ex);
		}
		return jobStatus;
	}
	
	public LogsModel getLogsModel() {
		return logsModel;
	}

	public void setLogsModel(LogsModel logsModel) {
		this.logsModel = logsModel;
	}

	public BdgContentPushModel getBdgContentPushModel() {
		return bdgContentPushModel;
	}

	public void setBdgContentPushModel(BdgContentPushModel bdgContentPushModel) {
		this.bdgContentPushModel = bdgContentPushModel;
	}
	
}
