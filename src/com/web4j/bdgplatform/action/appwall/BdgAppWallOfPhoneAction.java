package com.web4j.bdgplatform.action.appwall;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.bdgplatform.model.BdgAppWallModel;
import com.web4j.common.action.AbstractActionSupport;
import com.web4j.exception.DoErrorException;
import com.web4j.util.SysConfig;
/**
 * BDG 软件墙，手机调用接口
 * @author xixi
 * @date 2012-12-14
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/bdg/phone/appwall")
public class BdgAppWallOfPhoneAction extends AbstractActionSupport {

	private static Logger log=Logger.getLogger(BdgAppWallOfPhoneAction.class);
	
	@Autowired
	private BdgAppWallModel bdgAppWallModel;
	
	private List<Map<String,?>>apps;
	
	private Long appWallId;
	
	@Action("queryBdgAppWall")
	public String queryBdgAppWall(){
		try{
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			apps=bdgAppWallModel.queryBdgAppWallsOfPhone(start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("toDownload")
	public String toDownload(){
		try{
			if(appWallId!=null&&!"".equals(appWallId)){
				bdgAppWallModel.updateDownloads(appWallId);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<Map<String, ?>> getApps() {
		return apps;
	}

	public void setApps(List<Map<String, ?>> apps) {
		this.apps = apps;
	}

	public Long getAppWallId() {
		return appWallId;
	}

	public void setAppWallId(Long appWallId) {
		this.appWallId = appWallId;
	}

}
