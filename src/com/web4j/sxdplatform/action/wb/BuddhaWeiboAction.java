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
import com.web4j.sxdplatform.pojo.BuddhaWeibo;
/**
 * 大师微博列表Action
 * @author xixi
 * @date 2012-11-24
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/sxd/wb")
public class BuddhaWeiboAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(BuddhaWeiboAction.class);
	
	@Autowired
	private BuddhaWeiboModel buddhaWeiboModel;
	
	private List<BuddhaWeibo> buddhaWeibos;
	
	@Action("queryBuddhaWeibos")
	public String queryBuddhaWeibos(){
		try{
			buddhaWeibos=buddhaWeiboModel.queryBuddhaWeibos(start, limit);
			if(total==null){
				total = buddhaWeiboModel.getBuddhaWeiboCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateBuddhaWeibos")
	public String saveOrUpdateBuddhaWeibos(){
		try{
			buddhaWeiboModel.saveOrUpdateBuddhaWeibos(buddhaWeibos);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteBuddhaWeibos")
	public String deleteBuddhaWeibos(){
		try{
			buddhaWeiboModel.deleteBuddhaWeibos(buddhaWeibos);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<BuddhaWeibo> getBuddhaWeibos() {
		return buddhaWeibos;
	}

	public void setBuddhaWeibos(List<BuddhaWeibo> buddhaWeibos) {
		this.buddhaWeibos = buddhaWeibos;
	}
}
