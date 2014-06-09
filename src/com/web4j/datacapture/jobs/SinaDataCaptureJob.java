package com.web4j.datacapture.jobs;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.web4j.job.QuartzScheduleMgr;
import com.web4j.systemplatform.model.logs.LogsModel;

@Service("sinaDataCaptureJob")
public class SinaDataCaptureJob extends QuartzJobBean {
	
	private static final String JOBNAME="sina_datacapture";
	private static final String JOBGROUP="job_group";
	private static final String TRIGGERNAME="sina_datacapture_trigger";
	private static final String TRIGGERGROUP="trigger_group";
	
	private static Logger log = Logger.getLogger(SinaDataCaptureJob.class);
	
	@Autowired
	private LogsModel logs;
	//这个定义蛋痛吧。JOBS里不能注入logs，只能通过XML里jobDataAsMap定义logsModel
	private LogsModel logsModel;
	private WeiboUserBean weiboUserBean;


	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try{
			weiboUserBean.sinaCaptrue();
		}catch(Exception e){
			//e.printStackTrace();
			String error="抓取SINA微博数据异常。"+e.getMessage();
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
		logs.insertOneLog("启动抓取SINA微博数据任务...");
		QuartzScheduleMgr.scheduleJob(newJobDetail, trigger);
		if(!QuartzScheduleMgr.isStart()){
			QuartzScheduleMgr.start();
		}
	}
	
	public void resume() throws Exception{
		logs.insertOneLog("恢复抓取SINA微博数据任务...");
		QuartzScheduleMgr.resumeJob(JOBNAME, JOBGROUP);
	}
	
	public void pause() throws Exception{
		logs.insertOneLog("暂停抓取SINA微博数据任务...");
		QuartzScheduleMgr.pauseJob(JOBNAME, JOBGROUP);
	}
	
	public LogsModel getLogsModel() {
		return logsModel;
	}

	public void setLogsModel(LogsModel logsModel) {
		this.logsModel = logsModel;
	}

	public WeiboUserBean getWeiboUserBean() {
		return weiboUserBean;
	}

	public void setWeiboUserBean(WeiboUserBean weiboUserBean) {
		this.weiboUserBean = weiboUserBean;
	}
	
}
