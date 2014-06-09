package com.web4j.sxdplatform.action.app;

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
import com.web4j.sxdplatform.entity.TbDict;
import com.web4j.sxdplatform.model.DictModel;
import com.web4j.sxdplatform.pojo.Dict;
import com.web4j.util.FileUtil;

/**
 * SXD字典管理
 * @author xixi
 * @date 2012-11-1
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/sxd/dict")
public class DictAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(DictAction.class);
	
	@Autowired
	private DictModel dictModel;
	
	private List<Dict> dicts;
	
	private String dictGroup;
	
	private Long dictId;
	
	private File dictFile;
	
	private String dictFileContentType;
	
	private String dictFileFileName;
	
	private Map<String,Object>result;
	
	@Action("queryAllDict")
	public String queryAllDict(){
		try{
			dicts=dictModel.queryAllDict();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("queryDicts")
	public String queryDicts(){
		try{
			if(dictGroup!=null){
				dicts=dictModel.queryDict(dictGroup);
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateDicts")
	public String saveOrUpdateDicts(){
		try{
			dictModel.saveOrUpdateDicts(dicts);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(errorMessage, e);
		}
		return JSON;
	}
	
	@Action("deleteDicts")
	public String deleteDicts(){
		try{
			dictModel.deleteDicts(dicts);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("uploadIcon")
	public String uploadIcon(){
		HttpServletResponse response=ServletActionContext.getResponse();
		try{
			response.setContentType("text/html;charset=UTF-8");
			result=new HashMap<String, Object>();
			String []types={"image/png","image/x-png"};
			Integer maxSize=102400;
			FileUtil.validate(dictFile, dictFileContentType, dictFileFileName, types, maxSize);
			String relaPath="/attachments/sxd/dict/icons";
			String realPath = FileUtil.getTomcatWebAppPath()+relaPath;
			FileUtil.writeFile(dictFile, dictFileFileName, realPath);
			TbDict tbDict=dictModel.quereyTbDict(dictId);
			if(tbDict!=null){
				tbDict.setIconPath(relaPath+"/"+dictFileFileName);
				dictModel.updateTbDict(tbDict);
			}
			result.put("success", true);
			result.put("msg", dictFileFileName+"上传成功！");
		}catch(Exception e){
			result.put("success", false);
			result.put("msg", e.getMessage());
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		try {
			response.getWriter().write(JSONUtil.serialize(result));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 
		return null;
	}

	public List<Dict> getDicts() {
		return dicts;
	}

	public void setDicts(List<Dict> dicts) {
		this.dicts = dicts;
	}

	public String getDictGroup() {
		return dictGroup;
	}

	public void setDictGroup(String dictGroup) {
		this.dictGroup = dictGroup;
	}

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public File getDictFile() {
		return dictFile;
	}

	public void setDictFile(File dictFile) {
		this.dictFile = dictFile;
	}

	public String getDictFileContentType() {
		return dictFileContentType;
	}

	public void setDictFileContentType(String dictFileContentType) {
		this.dictFileContentType = dictFileContentType;
	}

	public String getDictFileFileName() {
		return dictFileFileName;
	}

	public void setDictFileFileName(String dictFileFileName) {
		this.dictFileFileName = dictFileFileName;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
	
}
