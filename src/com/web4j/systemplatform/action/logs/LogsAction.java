package com.web4j.systemplatform.action.logs;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.systemplatform.model.logs.LogsModel;
import com.web4j.systemplatform.pojo.logs.LogsPojo;

@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/logs")
public class LogsAction extends AbstractActionSupport {

	private static Logger log=Logger.getLogger(LogsAction.class);

	@Autowired
	private LogsModel logsModel;
	
	private List<LogsPojo>logsPojos;
	
	@Action("queryLogs")
	public String queryLogs(){
		try{
			logsPojos=logsModel.queryLogsPojos(start,limit);
			if(total==null){
				total=logsModel.getLogsCounts();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	@Action("deleteLogs")
	public String deleteLogs(){
		try{
			logsModel.deleteLogsPojos(logsPojos);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<LogsPojo> getLogsPojos() {
		return logsPojos;
	}

	public void setLogsPojos(List<LogsPojo> logsPojos) {
		this.logsPojos = logsPojos;
	}
	
}
