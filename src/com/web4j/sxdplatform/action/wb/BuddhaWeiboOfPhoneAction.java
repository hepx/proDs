package com.web4j.sxdplatform.action.wb;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.sxdplatform.model.BuddhaWeiboModel;
import com.web4j.util.SysConfig;
/**
 * 手机调用大师微博接口
 * @author xixi
 * @date 2012-11-24
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/sxd/phone/wb")
public class BuddhaWeiboOfPhoneAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(BuddhaWeiboOfPhoneAction.class);
	
	@Autowired
	private BuddhaWeiboModel buddhaWeiboModel;
	
	private List buddhaWeibos;
	
	@Action("queryWeibos")
	public String queryBuddhaWeibos(){
		try{
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			buddhaWeibos=buddhaWeiboModel.queryTbBuddhaWeibosOfPhone(start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List getBuddhaWeibos() {
		return buddhaWeibos;
	}

	public void setBuddhaWeibos(List buddhaWeibos) {
		this.buddhaWeibos = buddhaWeibos;
	}
	
}
