package com.web4j.sxdplatform.action.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.exception.DoErrorException;
import com.web4j.sxdplatform.entity.TbSxdVersion;
import com.web4j.sxdplatform.model.SxdVersionModel;
import com.web4j.util.FileUtil;
/**
 * SXD版本升级接口
 * @author xixi
 * @date 2012-11-16
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/sxd/phone/version")
@Results({@Result(name="download",type="stream",
	params={"contentType","application/octet-stream","inputName","inputStream",
		"contentDisposition","attachment;filename=${fileName}","bufferSize","4096"})})
public class SxdVersionOfPhoneAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(SxdVersionOfPhoneAction.class);
	
	@Autowired
	private SxdVersionModel sxdVersionModel;
	
	private TbSxdVersion sxdVersion;
	
	private Integer internalVersion;
	
	private Boolean update;
	
	private InputStream inputStream;
	
	private String fileName;

	/**
	 * 检查更新
	 * @return
	 */
	@Action("checkVersion")
	public String checkVersion(){
		try{
			if(internalVersion!=null){
				sxdVersion=sxdVersionModel.getLastTbSxdVersion();
				if(sxdVersion!=null&&internalVersion<sxdVersion.getInternalVersion()){
					update=true;
				}else{
					update=false;
					sxdVersion=null;
				}
			}else{
				throw new DoErrorException("缺失必要的参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	/**
	 * 下载APP
	 * @return
	 */
	@Action("downloadAPK")
	public String downloadApk(){
		try{
			if(internalVersion!=null){
				TbSxdVersion sxdVersion=sxdVersionModel.getTbSxdVersion(internalVersion);
				String apkPath=sxdVersion.getApkPath();
				if(apkPath!=null&&!"".equals(apkPath)){
					String filePath=FileUtil.getTomcatWebAppPath()+apkPath;
					File file=new File(filePath);
					if(!file.exists()){
						throw new DoErrorException("文件不存在！");
					}else{
						//中文 文件名
						String agent = ServletActionContext.getRequest().getHeader("User-Agent");
						System.out.println(agent);
						if (agent!=null) {
							agent = agent.toLowerCase();
							if (agent.indexOf("msie") != -1) {//IE
								fileName=URLEncoder.encode(file.getName(),"UTF-8");
							}else {//其它
								fileName=new String(file.getName().getBytes(),"ISO8859-1");
							}
						}else{
							throw new DoErrorException("User-Agent无效！");
						}
						inputStream = new FileInputStream(file);
					}
				}else{
					throw new DoErrorException("新版本中的安装文件没有上传！");
				}
			}else{
				throw new DoErrorException("缺失必要的参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return "download";
	}
	
	/**
	 * 更新下载数
	 * @return
	 */
	@Action("updateDownloads")
	public String updateDownloads(){
		try{
			if(internalVersion!=null){
				TbSxdVersion sxdVersion=sxdVersionModel.getTbSxdVersion(internalVersion);
				sxdVersion.setDownLoads(sxdVersion.getDownLoads()+1);
				sxdVersionModel.updateTbSxdVersion(sxdVersion);
			}else{
				throw new DoErrorException("缺失必要的参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public TbSxdVersion getSxdVersion() {
		return sxdVersion;
	}

	public void setSxdVersion(TbSxdVersion sxdVersion) {
		this.sxdVersion = sxdVersion;
	}

	public Integer getInternalVersion() {
		return internalVersion;
	}

	public void setInternalVersion(Integer internalVersion) {
		this.internalVersion = internalVersion;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Boolean getUpdate() {
		return update;
	}

	public void setUpdate(Boolean update) {
		this.update = update;
	}
	
}
