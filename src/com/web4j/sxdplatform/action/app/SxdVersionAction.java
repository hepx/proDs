package com.web4j.sxdplatform.action.app;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.web4j.sxdplatform.entity.TbSxdVersion;
import com.web4j.sxdplatform.model.SxdVersionModel;
import com.web4j.sxdplatform.pojo.SxdVersion;
import com.web4j.util.FileUtil;
/**
 * Sxd 版本ACTION
 * @author xixi
 * @date 2012-11-15
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/sxd/version")
public class SxdVersionAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(SxdVersionAction.class);
	
	@Autowired
	private SxdVersionModel sxdVersionModel;
	
	private List<SxdVersion> sxdVersions;
	
	private SxdVersion sxdVersion;
	
	private Long versionId;
	
	private String version;
	
	private File apkFile;
	
	private String apkFileContentType;
	
	private String apkFileFileName;
	
	private Map<String,Object>result;
	
	@Action("queryVersion")
	public String querySxdVersions(){
		try{
			sxdVersions=sxdVersionModel.querySxdVersions(start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	@Action("saveOrUpdateVersion")
	public String saveOrUpdateVersion(){
		try{
			sxdVersionModel.saveOrUpdateSxdVersion(sxdVersion);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteVersion")
	public String deleteSxdVersions(){
		try{
			sxdVersionModel.deleteSxdVersions(sxdVersions);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("uploadApk")
	public String uploadApk(){
		result=new HashMap<String,Object>();
		try{
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			String []types={"application/x-zip-compressed","application/octet-stream"};
			int maxSize=10485760;//10M
			FileUtil.validate(apkFile, apkFileContentType, apkFileFileName,types,maxSize);
			String relaPath="/attachments/sxd/apk/versions";
			if(version!=null){//加上版本号作为目录
				relaPath=relaPath+"/"+version;
			}
			String realPath = FileUtil.getTomcatWebAppPath()+relaPath;
			FileUtil.writeFile(apkFile, apkFileFileName, realPath);
			TbSxdVersion tbSxdVersion=sxdVersionModel.quereyTbSxdVersionById(versionId);
			if(tbSxdVersion!=null){
				tbSxdVersion.setApkPath(relaPath+"/"+apkFileFileName);
				sxdVersionModel.updateTbSxdVersion(tbSxdVersion);
			}
			result.put("success", true);
			result.put("msg", apkFileFileName+"上传成功！");			
		}catch(Exception e){
			result.put("success", false);
			result.put("msg", e.getMessage());
			log.error(e.getMessage(), e);
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONUtil.serialize(result));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 		
		return null;
	}

	public List<SxdVersion> getSxdVersions() {
		return sxdVersions;
	}

	public void setSxdVersions(List<SxdVersion> sxdVersions) {
		this.sxdVersions = sxdVersions;
	}

	public SxdVersion getSxdVersion() {
		return sxdVersion;
	}

	public void setSxdVersion(SxdVersion sxdVersion) {
		this.sxdVersion = sxdVersion;
	}

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public File getApkFile() {
		return apkFile;
	}

	public void setApkFile(File apkFile) {
		this.apkFile = apkFile;
	}

	public String getApkFileContentType() {
		return apkFileContentType;
	}

	public void setApkFileContentType(String apkFileContentType) {
		this.apkFileContentType = apkFileContentType;
	}

	public String getApkFileFileName() {
		return apkFileFileName;
	}

	public void setApkFileFileName(String apkFileFileName) {
		this.apkFileFileName = apkFileFileName;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
	
}
