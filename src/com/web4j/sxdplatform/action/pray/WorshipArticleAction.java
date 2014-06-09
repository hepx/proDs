package com.web4j.sxdplatform.action.pray;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.sxdplatform.entity.TbArticle;
import com.web4j.sxdplatform.model.ArticleModel;
import com.web4j.sxdplatform.pojo.Article;
import com.web4j.util.FileUtil;

/**
 * 供奉物品ACTION
 * @author xixi
 * @date 2012-11-8
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/sxd/pray")
public class WorshipArticleAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(WorshipArticleAction.class);
	
	@Autowired
	private ArticleModel articleModel;
	
	private List<Article>articles;
	
	private Long articleId;
	
	private Map<String,Object>result;
	
	private File articleFile;
	
	private String articleFileContentType;
	
	private String articleFileFileName;
	
	@Action("queryArticles")
	public String queryArticles(){
		try{
			articles=articleModel.quereyArticles(start, limit);
			if(total==null){
				total=articleModel.getArticleCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateArticles")
	public String saveOrUpdateArticles(){
		try{
			articleModel.saveOrUpdateArticles(articles);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteArticles")
	public String deleteArticles(){
		try{
			articleModel.deleteArticles(articles);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("uploadArticleImage")
	public String uploadArticleImage(){
		HttpServletResponse response=ServletActionContext.getResponse();
		result=new HashMap<String,Object>();
		try{
			response.setContentType("text/html;charset=UTF-8");
			String []types={"image/jpeg","image/pjpeg","image/gif","image/png","image/x-png"};
			int maxSize=102400;
			//验证文件
			FileUtil.validate(articleFile, articleFileContentType, articleFileFileName,types,maxSize);
			//需要保存的路径
			String relaPath="/attachments/sxd/article/images";
			String realPath = FileUtil.getTomcatWebAppPath()+relaPath;
			FileUtil.writeFile(articleFile, articleFileFileName, realPath);
			TbArticle tbArticle=articleModel.quereyTbArticleById(articleId);
			if(tbArticle!=null){
				tbArticle.setArticlePath(relaPath+"/"+articleFileFileName);
				articleModel.updateTbArticle(tbArticle);
			}
			result.put("success", true);
			result.put("msg", articleFileFileName+"上传成功！");
		}catch(Exception e){
			result.put("success", false);
			result.put("msg", e.getMessage());
			log.error(e.getMessage(), e);
		}
		try {
			response.getWriter().write(JSONUtil.serialize(result));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public File getArticleFile() {
		return articleFile;
	}

	public void setArticleFile(File articleFile) {
		this.articleFile = articleFile;
	}

	public String getArticleFileContentType() {
		return articleFileContentType;
	}

	public void setArticleFileContentType(String articleFileContentType) {
		this.articleFileContentType = articleFileContentType;
	}

	public String getArticleFileFileName() {
		return articleFileFileName;
	}

	public void setArticleFileFileName(String articleFileFileName) {
		this.articleFileFileName = articleFileFileName;
	}
	
}
