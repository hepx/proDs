package com.web4j.sxdplatform.action.music;

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
import com.web4j.sxdplatform.entity.TbBuddhaMusic;
import com.web4j.sxdplatform.model.BuddhaMusicModel;
import com.web4j.sxdplatform.pojo.BuddhaMusic;
import com.web4j.util.FileUtil;
/**
 * 佛音ACTION
 * @author xixi
 * @date 2012-11-5
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/sxd/music")
public class BuddhaMusicAction extends AbstractActionSupport {

	private static Logger log=Logger.getLogger(BuddhaMusicAction.class);

	@Autowired
	private BuddhaMusicModel buddhaMusicModel;
	
	private List<BuddhaMusic>buddhaMusics;
	
	private Long musicId;
	
	private File musicFile;
	
	private String musicFileContentType;
	
	private String musicFileFileName;
	
	private Map<String,Object>result;
	
	@Action("queryBuddhaMusics")
	public String queryBuddhaMusics(){
		try{
			buddhaMusics=buddhaMusicModel.queryBuddhaMusics(start, limit);
			if(total==null){
				total=buddhaMusicModel.getBuddhaMusicCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateBuddhaMusics")
	public String saveOrUpdateMusics(){
		try{
			buddhaMusicModel.saveOrUpdateMusics(buddhaMusics);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("deleteBuddhaMusics")
	public String deleteBuddhaMusics(){
		try{
			buddhaMusicModel.deleteBuddhaMusics(buddhaMusics);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("uploadLyric")
	public String uploadLyric(){
		result=new HashMap<String,Object>();
		try{
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			String []types={"text/plain","application/octet-stream"};
			int maxSize=102400;
			FileUtil.validate(musicFile, musicFileContentType, musicFileFileName, types, maxSize);
			String relaPath="/attachments/sxd/musics/lyrics";
			String realPath = FileUtil.getTomcatWebAppPath()+relaPath;
			FileUtil.writeFile(musicFile, musicFileFileName, realPath);
			TbBuddhaMusic tbBuddhaMusic=buddhaMusicModel.getBuddhaMusicById(musicId);
			if(tbBuddhaMusic!=null){
				tbBuddhaMusic.setLyricPath(relaPath+"/"+musicFileFileName);
				buddhaMusicModel.updateBuddhaMusic(tbBuddhaMusic);
			}
			result.put("success", true);
			result.put("msg", musicFileFileName+"上传成功！");			
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
	
	@Action("uploadMusic")
	public String uploadMusic(){
		result=new HashMap<String,Object>();
		try{
			System.out.println(musicFileContentType);
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			String []types={"audio/mp3","audio/mpeg"};
			int maxSize=10485760;
			FileUtil.validate(musicFile, musicFileContentType, musicFileFileName, types, maxSize);
			String relaPath="/attachments/sxd/musics/musics";
			String realPath = FileUtil.getTomcatWebAppPath()+relaPath;
			FileUtil.writeFile(musicFile, musicFileFileName, realPath);
			TbBuddhaMusic tbBuddhaMusic=buddhaMusicModel.getBuddhaMusicById(musicId);
			if(tbBuddhaMusic!=null){
				tbBuddhaMusic.setMusicPath(relaPath+"/"+musicFileFileName);
				buddhaMusicModel.updateBuddhaMusic(tbBuddhaMusic);
			}
			result.put("success", true);
			result.put("msg", musicFileFileName+"上传成功！");	
		}catch(Exception e){
			result.put("success", false);
			result.put("msg", e.getMessage());
			log.error(e.getMessage(),e);
		}
		try {
			ServletActionContext.getResponse().getWriter().write(JSONUtil.serialize(result));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 
		return null;
	}
	

	public List<BuddhaMusic> getBuddhaMusics() {
		return buddhaMusics;
	}

	public void setBuddhaMusics(List<BuddhaMusic> buddhaMusics) {
		this.buddhaMusics = buddhaMusics;
	}

	public File getMusicFile() {
		return musicFile;
	}

	public void setMusicFile(File musicFile) {
		this.musicFile = musicFile;
	}

	public String getMusicFileContentType() {
		return musicFileContentType;
	}

	public void setMusicFileContentType(String musicFileContentType) {
		this.musicFileContentType = musicFileContentType;
	}

	public String getMusicFileFileName() {
		return musicFileFileName;
	}

	public void setMusicFileFileName(String musicFileFileName) {
		this.musicFileFileName = musicFileFileName;
	}

	public Long getMusicId() {
		return musicId;
	}

	public void setMusicId(Long musicId) {
		this.musicId = musicId;
	}

	
}
