package com.web4j.bdgplatform.action.push;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.bdgplatform.entity.TbBdgAppPush;
import com.web4j.bdgplatform.jobs.BdgPushAppJob;
import com.web4j.bdgplatform.model.BdgAppPushModel;
import com.web4j.common.action.AbstractActionSupport;
import com.web4j.exception.DoErrorException;

/**
 * 推送内容ACTION
 * @author xixi
 * @date 2013-3-7
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/bdg/pushapp")
public class PushAppAction extends AbstractActionSupport {

	private static Logger log=Logger.getLogger(PushAppAction.class);
	
	@Autowired
	private BdgAppPushModel bdgAppPushModel;
	
	@Autowired
	private BdgPushAppJob bdgPushAppJob;
	
	private List<TbBdgAppPush> bdgPushApps; 
	
	private List<TbBdgAppPush> appInfos; 
	
	private TbBdgAppPush bdgPushApp;
	
	private String cronExpression;
	
	private Map<String,Object> jobStatus;
	
	@Action("queryBdgPushApps")
	public String queryBdgPushApps(){
		try{
			bdgPushApps=bdgAppPushModel.queryTbBdgPushApps(start, limit);
			if(total==null){
				total=bdgAppPushModel.getBdgPushAppCount();
			}
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateBdgPushApp")
	public String saveOrUpdateBdgPushApp(){
		try{
			bdgAppPushModel.saveOrUpdateBdgPushApp(bdgPushApp);
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;		
	}
	
	@Action("addBdgPushAppFromMarket")
	public String addBdgPushAppFromMarket(){
		try{
			StringBuffer fullpath=ServletActionContext.getRequest().getRequestURL();
			String path= ServletActionContext.getRequest().getRequestURI();
			String realPath=(fullpath.substring(0, fullpath.length()-path.length()));
			ServletActionContext.getRequest().getRequestURL();
			for(TbBdgAppPush appPush:appInfos){
				appPush.setCreateTime(new Date());
				appPush.setAppPath(realPath+appPush.getAppPath());
				appPush.setMsgContent(appPush.getAppName()+"应用推送");
			}
			bdgAppPushModel.saveBdgPushApps(appInfos);
			appInfos=null;
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;		
	}
	
	@Action("deleteBdgPushApps")
	public String deleteBdgPushApps(){
		try{
			bdgAppPushModel.deleteBdgPushApps(bdgPushApps);
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
	@Action("pushBdgApp")
	public String pushBdgApp(){
		try{
			bdgAppPushModel.pushBdgApp(bdgPushApp);
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
	@Action("startBdgPushAppJob")
	public String startBdgPushAppJob(){
		try{
			if(cronExpression!=null){
				bdgPushAppJob.start(cronExpression);
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
	@Action("pauseBdgPushAppJob")
	public String pauseBdgPushAppJob(){
		try{
			bdgPushAppJob.pause();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("queryBdgPushAppJobStatus")
	public String queryBdgPushAppJobStatus(){
		try{
			jobStatus=bdgPushAppJob.getCronTriggerStatus();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
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

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		PushAppAction.log = log;
	}

	public List<TbBdgAppPush> getBdgPushApps() {
		return bdgPushApps;
	}

	public void setBdgPushApps(List<TbBdgAppPush> bdgPushApps) {
		this.bdgPushApps = bdgPushApps;
	}

	public TbBdgAppPush getBdgPushApp() {
		return bdgPushApp;
	}

	public void setBdgPushApp(TbBdgAppPush bdgPushApp) {
		this.bdgPushApp = bdgPushApp;
	}

	public List<TbBdgAppPush> getAppInfos() {
		return appInfos;
	}

	public void setAppInfos(List<TbBdgAppPush> appInfos) {
		this.appInfos = appInfos;
	}
	
	
}
