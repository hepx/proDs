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
import com.web4j.sxdplatform.model.ArticleModel;
import com.web4j.util.SysConfig;
/**
 * 手机调用获取供奉物品ACTION
 * @author xixi
 * @date 2012-11-8
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/sxd/phone/pray")
public class WorshipArticleOfPhoneAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(WorshipArticleOfPhoneAction.class);
	
	@Autowired
	private ArticleModel articleModel;
	
	private List articles;
	
	@Action("queryArticles")
	public String quereyArticles(){
		try{
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			articles=articleModel.queryArticlesOfPhone(start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List getArticles() {
		return articles;
	}

	public void setArticles(List articles) {
		this.articles = articles;
	}
	
}
