package com.web4j.bdgplatform.jobs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.web4j.bdgplatform.model.BdgBrRecordModel;
import com.web4j.job.QuartzScheduleMgr;
import com.web4j.systemplatform.model.logs.LogsModel;
/**
 * 不得哥定时发布JOB
 * @author xixi
 * @date 2012-12-11
 *
 */
@Service("bdgTimeReleaseJob")
public class BdgTimeReleaseJob extends QuartzJobBean {

	private static Logger log = Logger.getLogger(BdgTimeReleaseJob.class);
	
	private static final String JOBNAME="bdg_timerelease";
	private static final String JOBGROUP="job_group";
	private static final String TRIGGERNAME="bdg_timerelease_trigger";
	private static final String TRIGGERGROUP="trigger_group";
	
	@Autowired
	private LogsModel logs;
	//这个定义蛋痛吧。JOBS里不能注入logs，只能通过XML里jobDataAsMap定义logsModel
	private LogsModel logsModel;
	private BdgBrRecordModel bdgBrRecordModel;
	
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try{
			logsModel.insertOneLog("执行发布任务......");
			bdgBrRecordModel.updateReleaseBdgBrRecord();	
		}catch(Exception e){
			//e.printStackTrace();
			String error="BDG定时发布任务异常。"+e.getMessage();
			try {
				logsModel.insertOneLog(error);
			} catch (Exception e1) {
				log.error(e1.getMessage(), e1);
			}
			log.error(error, e);
		}

	}
	
	public void start(long interval) throws Exception{
		//获得XML里默认配置的JobDetail
		JobDetail jobDetail=QuartzScheduleMgr.getJobDetail(JOBNAME, JOBGROUP);
		JobDetail newJobDetail=(JobDetail)jobDetail.clone();
		QuartzScheduleMgr.deleteJob(JOBNAME, JOBGROUP);
		//创建一个简单触发器
		SimpleTrigger trigger=new SimpleTrigger(TRIGGERNAME,TRIGGERGROUP);
		//十秒后开始运行
		trigger.setStartTime(new Date(System.currentTimeMillis()+10000));
		trigger.setRepeatInterval(interval*60*1000);
		trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		logs.insertOneLog("启动BDG定时发布任务...");
		QuartzScheduleMgr.scheduleJob(newJobDetail, trigger);
		if(!QuartzScheduleMgr.isStart()){
			QuartzScheduleMgr.start();
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
		logs.insertOneLog("启动BDG定时发布任务...");
		QuartzScheduleMgr.scheduleJob(newJobDetail, trigger);
		if(!QuartzScheduleMgr.isStart()){
			QuartzScheduleMgr.start();
		}
	}
	
	public void resume() throws Exception{
		logs.insertOneLog("恢复BDG定时发布任务...");
		QuartzScheduleMgr.resumeJob(JOBNAME, JOBGROUP);
	}
	
	public void pause() throws Exception{
		logs.insertOneLog("暂停BDG定时发布任务...");
		QuartzScheduleMgr.pauseJob(JOBNAME, JOBGROUP);
	}
	
	public Map<String,Object> getStatus() throws SchedulerException{
		Map<String,Object> jobStatus=null;
		SimpleTrigger trigger=QuartzScheduleMgr.getSimpleTrigger(TRIGGERNAME, TRIGGERGROUP);
		if(trigger!=null){
			jobStatus=new HashMap<String,Object>();
			Long interval=trigger.getRepeatInterval()/(60*1000);
			int status= QuartzScheduleMgr.getTriggerState(TRIGGERNAME, TRIGGERGROUP);
			jobStatus.put("status", status);
			jobStatus.put("interval", interval);
		}
		return jobStatus;
	}
	
	public Map<String,Object> getCronTriggerStatus() throws SchedulerException{
		Map<String,Object> jobStatus=null;
		CronTrigger trigger=QuartzScheduleMgr.getCronTrigger(TRIGGERNAME, TRIGGERGROUP);
		if(trigger!=null){
			jobStatus=new HashMap<String,Object>();
			int status= QuartzScheduleMgr.getTriggerState(TRIGGERNAME, TRIGGERGROUP);
			String ex=trigger.getCronExpression();
			String[] arr=ex.split(" ");
			Integer interval=0;
			if(arr[1].contains("/")){//第二位分
				interval=Integer.parseInt(arr[1].split("/")[1]);
			}else if(arr[2].contains("/")){//第三位时
				interval=Integer.parseInt(arr[2].split("/")[1])*60;
			}
			jobStatus.put("status", status);
			jobStatus.put("interval", interval);
			if(ex.contains("-")){
				String h=arr[2];
				if(!h.equals("*")){
					jobStatus.put("sHour", h.split("-")[0]);
					jobStatus.put("eHour", h.split("-")[1]);
				}
			}
		}
		return jobStatus;
	}

	public LogsModel getLogsModel() {
		return logsModel;
	}

	public void setLogsModel(LogsModel logsModel) {
		this.logsModel = logsModel;
	}

	public BdgBrRecordModel getBdgBrRecordModel() {
		return bdgBrRecordModel;
	}

	public void setBdgBrRecordModel(BdgBrRecordModel bdgBrRecordModel) {
		this.bdgBrRecordModel = bdgBrRecordModel;
	}
}
