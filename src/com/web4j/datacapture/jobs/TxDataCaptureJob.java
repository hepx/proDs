package com.web4j.datacapture.jobs;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.springframework.stereotype.Service;

import com.web4j.job.QuartzScheduleMgr;

@Service("txDataCaptureJob")
public class TxDataCaptureJob implements Job {
	
	private static final String JOBNAME="tx_datacapture";
	private static final String JOBGROUP="job_group";
	private static final String TRIGGERNAME="tx_datacapture_trigger";
	private static final String TRIGGERGROUP="trigger_group";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("tx job...");
		JobDataMap jobDataMap=context.getJobDetail().getJobDataMap();
		String size=jobDataMap.getString("size");
		System.out.println("tx size:"+size);
	}
	
	public void start(long interval) throws SchedulerException{
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
		System.out.println("启动TX任务");
		QuartzScheduleMgr.scheduleJob(newJobDetail, trigger);
		if(!QuartzScheduleMgr.isStart()){
			QuartzScheduleMgr.start();
		}
	}
	
	public void resume() throws SchedulerException{
		System.out.println("恢复TX任务...");
		QuartzScheduleMgr.resumeJob(JOBNAME, JOBGROUP);
	}
	
	public void pause() throws SchedulerException{
		System.out.println("暂停TX任务...");
		QuartzScheduleMgr.pauseJob(JOBNAME, JOBGROUP);
	}
}
