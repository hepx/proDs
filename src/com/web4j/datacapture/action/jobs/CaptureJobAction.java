package com.web4j.datacapture.action.jobs;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.datacapture.jobs.SinaDataCaptureJob;
import com.web4j.datacapture.jobs.TxDataCaptureJob;
import com.web4j.exception.DoErrorException;
import com.web4j.job.QuartzJob;
import com.web4j.job.QuartzScheduleMgr;

/**
 * 任务调度ACTION
 * @author xixi
 * @date 2012-12-3
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/captrue/jobs")
public class CaptureJobAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(CaptureJobAction.class);
	
	@Autowired
	private SinaDataCaptureJob sinaDataCaptureJob;
	
	@Autowired
	private TxDataCaptureJob txDataCaptureJob;
	
	private Long interval;
	
	private List<QuartzJob>jobs;
	
	@Action("queryJobs")
	public String queryJobs(){
		try{
			jobs=QuartzScheduleMgr.getAllJobs();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("shutdown")
	public String shutdown(){
		try{
			if(!QuartzScheduleMgr.isShutDown()){
				QuartzScheduleMgr.shutdown();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;		
	}
	
	@Action("startSinaJob")
	public String startSinaJob(){
		try{
			if(interval!=null){
				sinaDataCaptureJob.start(interval);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("pauseSinaJob")
	public String pauseSinaJob(){
		try{
			sinaDataCaptureJob.pause();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("resumeSinaJob")
	public String resumeSinaJob(){
		try{
			sinaDataCaptureJob.resume();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("startTxJob")
	public String startTxJob(){
		try{
			if(interval!=null){
				txDataCaptureJob.start(interval);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("pauseTxJob")
	public String pauseTxJob(){
		try{
			txDataCaptureJob.pause();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("resumeTxJob")
	public String resumeTxJob(){
		try{
			txDataCaptureJob.resume();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public List<QuartzJob> getJobs() {
		return jobs;
	}

	public void setJobs(List<QuartzJob> jobs) {
		this.jobs = jobs;
	}

}
