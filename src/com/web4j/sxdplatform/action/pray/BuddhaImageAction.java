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
import com.web4j.sxdplatform.entity.TbBuddhaImage;
import com.web4j.sxdplatform.model.BuddhaImageModel;
import com.web4j.sxdplatform.pojo.BuddhaImage;
import com.web4j.util.FileUtil;

/**
 * 佛像列表ACTION
 * @author xixi
 * @date 2012-11-7
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/sxd/pray")
public class BuddhaImageAction extends AbstractActionSupport {

	private static Logger log =Logger.getLogger(BuddhaImageAction.class);
	
	@Autowired
	private BuddhaImageModel buddhaImageModel;
	
	private List<BuddhaImage> buddhaImages;
	
	private Long imageId;
	
	private File imageFile;
	
	private String imageFileContentType;
	
	private String imageFileFileName;
	
	private Map<String,Object>result;
	
	@Action("queryBuddhaImages")
	public String queryBuddhaImages(){
		try{
			buddhaImages=buddhaImageModel.queryBuddhaImages(start, limit);
			if(total==null){
				total=buddhaImageModel.getBuddhaImageCounts();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateBuddhaImages")
	public String saveOrUpdateBuddhaImages(){
		try{
			buddhaImageModel.saveOrUpdateBuddhaImages(buddhaImages);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteBuddhaImages")
	public String deleteBuddhaImages(){
		try{
			buddhaImageModel.deleteBuddhaImages(buddhaImages);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("uploadBuddhaImage")
	public String uploadBuddhaImage(){
		HttpServletResponse response=ServletActionContext.getResponse();
		result=new HashMap<String,Object>();
		try{
			response.setContentType("text/html;charset=UTF-8");
			String []types={"image/jpeg","image/pjpeg","image/gif","image/png","image/x-png"};
			int maxSize=102400;
			//验证文件
			FileUtil.validate(imageFile, imageFileContentType, imageFileFileName,types,maxSize);
			//需要保存的路径
			String relaPath="/attachments/sxd/buddhaImage/images";
			String realPath = FileUtil.getTomcatWebAppPath()+relaPath;
			FileUtil.writeFile(imageFile, imageFileFileName, realPath);
			TbBuddhaImage tbBuddhaImage=buddhaImageModel.quereyTbBuddhaImageById(imageId);
			if(tbBuddhaImage!=null){
				tbBuddhaImage.setBuddhaImagePath(relaPath+"/"+imageFileFileName);
				buddhaImageModel.updateTbBuddhaImage(tbBuddhaImage);
			}
			result.put("success", true);
			result.put("msg", imageFileFileName+"上传成功！");
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

	public List<BuddhaImage> getBuddhaImages() {
		return buddhaImages;
	}

	public void setBuddhaImages(List<BuddhaImage> buddhaImages) {
		this.buddhaImages = buddhaImages;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageFileContentType() {
		return imageFileContentType;
	}

	public void setImageFileContentType(String imageFileContentType) {
		this.imageFileContentType = imageFileContentType;
	}

	public String getImageFileFileName() {
		return imageFileFileName;
	}

	public void setImageFileFileName(String imageFileFileName) {
		this.imageFileFileName = imageFileFileName;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
}
