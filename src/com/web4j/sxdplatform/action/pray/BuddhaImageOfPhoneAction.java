package com.web4j.sxdplatform.action.pray;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.sxdplatform.model.BuddhaImageModel;
import com.web4j.util.SysConfig;
/**
 * 手机调用佛像ACTION
 * @author xixi
 * @date 2012-11-7
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/sxd/phone/pray")
public class BuddhaImageOfPhoneAction extends AbstractActionSupport {

	private static Logger log =Logger.getLogger(BuddhaImageOfPhoneAction.class);
	
	@Autowired
	private BuddhaImageModel buddhaImageModel;
	
	private List buddhaImages;
	
	@Action("queryBuddhaImages")
	public String queryBuddhaImages(){
		try{
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			buddhaImages=buddhaImageModel.queryBuddhaImagesOfPhone(start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List getBuddhaImages() {
		return buddhaImages;
	}

	public void setBuddhaImages(List buddhaImages) {
		this.buddhaImages = buddhaImages;
	}
}
