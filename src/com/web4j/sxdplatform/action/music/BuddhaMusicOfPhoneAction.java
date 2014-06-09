package com.web4j.sxdplatform.action.music;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.exception.DoErrorException;
import com.web4j.sxdplatform.model.BuddhaMusicModel;
import com.web4j.util.SysConfig;
/**
 * 手机调用佛音ACTION
 * @author xixi
 * @date 2012-11-12
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/sxd/phone/music")
public class BuddhaMusicOfPhoneAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(BuddhaMusicAction.class);
	
	@Autowired
	private BuddhaMusicModel buddhaMusicModel;
	
	List musics;
	
	private String musicType;
	
	@Action("queryMusics")
	public String quereyBuddhaMusics(){
		try{
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			if(musicType!=null){
				musics=buddhaMusicModel.quereyBuddhaMusicByType(musicType, start, limit);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List getMusics() {
		return musics;
	}

	public void setMusics(List musics) {
		this.musics = musics;
	}

	public String getMusicType() {
		return musicType;
	}

	public void setMusicType(String musicType) {
		this.musicType = musicType;
	}
	
}
