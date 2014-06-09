package com.web4j.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import com.web4j.util.SpringUtils;

/**
 * Schedule管理
 * @author xixi
 * @date 2012-11-30
 *
 */
public class QuartzScheduleMgr {

	private static Logger log = Logger.getLogger(QuartzScheduleMgr.class);
	
	private static Scheduler scheduler=getScheduler();
			
	/**
	 * 创建一个调度对象
	 * @return
	 * @throws SchedulerException
	 */
	private static void createScheduler(){
		try{
			//scheduler=new StdSchedulerFactory().getScheduler();
			scheduler=(Scheduler)SpringUtils.getBean("schedulerFactoryBean");
		}catch(Exception e){
			log.error(e.getMessage(), e);
		}
	}
	/**
	 * 获得创建的对象
	 * @return
	 * @throws SchedulerException
	 */
	public static Scheduler getScheduler() {
		if(scheduler==null){
			createScheduler();
		}
		return scheduler;
	}
	/**
	 * 启动调度
	 * @throws SchedulerException
	 */
	public static void start() throws SchedulerException{
		scheduler.start();
	}
	/**
	 * 调度是否启动
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean isStart() throws SchedulerException{
		return scheduler.isStarted();
	}
	/**
	 * 停止调度
	 * @throws SchedulerException
	 */
	public static void shutdown() throws SchedulerException{
		scheduler.shutdown();
	}
	/**
	 * 判断是否停止
	 * @return
	 * @throws SchedulerException
	 */
	public static boolean isShutDown()throws SchedulerException{
		return scheduler.isShutdown();
	}

	/**
	 * 获得JobDetail
	 * @param jobName
	 * @param jobGroup
	 * @return
	 * @throws SchedulerException
	 */
	public static JobDetail getJobDetail(String jobName,String jobGroup) throws SchedulerException{
		return scheduler.getJobDetail(jobName, jobGroup);
	}
	/**
	 * 增加指定的触发器到调度
	 * @param trigger
	 * @throws SchedulerException
	 */
	public static Date scheduleJob(Trigger trigger) throws SchedulerException{
		return scheduler.scheduleJob(trigger);
	}
	/**
	 * 在给定的JobDetail和它关联的触发。
	 * @param job
	 * @param trigger
	 * @throws SchedulerException
	 */
	public static Date scheduleJob(JobDetail job,Trigger trigger) throws SchedulerException{
		return scheduler.scheduleJob(job, trigger);
	}
	
	/**
	 * 暂停JobDetail给定的jobName和groupName
	 * @param jobName
	 * @param groupName
	 * @throws SchedulerException
	 */
	public static void pauseJob(String jobName, String groupName) throws SchedulerException{
		scheduler.pauseJob(jobName, groupName);
	}
	/**
	 * 暂停JobDetail给定的groupName
	 * @param groupName
	 * @throws SchedulerException 
	 */
	public static void pauseJob(String groupName) throws SchedulerException{
		scheduler.pauseJobGroup(groupName);
	}
	/**
	 * 暂停所有的JOB
	 * @throws SchedulerException
	 */
	public static void pauseAllJob()throws SchedulerException{
		scheduler.pauseAll();
	}
	/**
	 * 恢复（取消暂停）的JobDetail具有给定名称
	 * @param jobName
	 * @param groupName
	 * @throws SchedulerException
	 */
	public static void resumeJob(String jobName, String groupName) throws SchedulerException {
		scheduler.resumeJob(jobName, groupName);
	}
	/**
	 * 恢复（取消暂停）的JobDetail具有给定groupName
	 * @param groupName
	 * @throws SchedulerException
	 */
	public static void resumeJobGroup(String groupName) throws SchedulerException{
		scheduler.resumeJobGroup(groupName);
	}
	/**
	 * 恢复所有（取消暂停）的JobDetail
	 * @throws SchedulerException
	 */
	public static void resumeAll() throws SchedulerException{
		scheduler.resumeAll();
	}
	
	/**
	 * 删除一个JobDetail
	 * @param jobName
	 * @param groupName
	 * @throws SchedulerException
	 */
	public static boolean deleteJob(String jobName, String groupName) throws SchedulerException{
		return scheduler.deleteJob(jobName, groupName);
	}
	/**
	 * 触发JOB
	 * @param jobName
	 * @param groupName
	 * @throws SchedulerException
	 */
	public static void triggerJob(String jobName,String groupName) throws SchedulerException{
		scheduler.triggerJob(jobName, groupName);
	}
	
	/**
	 * 简单Trigger
	 * @param triggerName
	 * @param triggerGroup
	 * @return
	 * @throws SchedulerException
	 */
	public static SimpleTrigger getSimpleTrigger(String triggerName,String triggerGroup) throws SchedulerException{
		return (SimpleTrigger)scheduler.getTrigger(triggerName, triggerGroup);
	}
	
	/**
	 * 高级Trigger
	 * @param triggerName
	 * @param triggerGroup
	 * @return
	 * @throws SchedulerException
	 */
	public static CronTrigger getCronTrigger(String triggerName,String triggerGroup) throws SchedulerException{
		return (CronTrigger)scheduler.getTrigger(triggerName, triggerGroup);
	}
	
	
	/**
	 * 获得指定trigger的状态
	 * @param triggerName
	 * @param triggerGroup
	 * @return
	 * @throws SchedulerException
	 */
	public static int getTriggerState(String triggerName,String triggerGroup) throws SchedulerException{
		return scheduler.getTriggerState(triggerName, triggerGroup);
	}
	
	/**
	 * 获得Interval
	 * @param triggerName
	 * @param triggerGroup
	 * @return
	 * @throws SchedulerException
	 */
	public static Long getRepeatInterval(String triggerName,String triggerGroup) throws SchedulerException{
		SimpleTrigger trigger=getSimpleTrigger(triggerName, triggerGroup);
		if(trigger!=null){
			return trigger.getRepeatInterval();
		}else{
			return null;
		}
	}

	/**
	 * 所有JOB列表
	 * @return
	 * @throws SchedulerException
	 */
	public static List<QuartzJob> getAllJobs() throws SchedulerException{
		List<QuartzJob> jobs=new ArrayList<QuartzJob>();
		for(String groupName:scheduler.getJobGroupNames()){
			for(String jobName:scheduler.getJobNames(groupName)){
				Trigger []triggers=scheduler.getTriggersOfJob(jobName, groupName);
				if(triggers!=null&&triggers.length>0){
					Date nextFireTime=triggers[0].getNextFireTime();
					String triggerName=triggers[0].getName();
					String triggerGroup=triggers[0].getGroup();
					int status=getTriggerState(triggerName, triggerGroup);
					jobs.add(new QuartzJob(jobName, groupName, nextFireTime,status));
				}
			}
		}
		return jobs;
	}
}
