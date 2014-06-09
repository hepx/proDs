package com.web4j.bdgplatform.action.appwall;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.bdgplatform.entity.TbBdgAppWall;
import com.web4j.bdgplatform.model.BdgAppWallModel;
import com.web4j.common.action.AbstractActionSupport;
import com.web4j.exception.DoErrorException;
import com.web4j.marketplatform.entity.TbAppInfo;
/**
 * bdg 软件墙
 * @author xixi
 * @date 2012-12-14
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/bdg/appwall")
public class BdgAppWallAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(BdgAppWallAction.class);
	
	@Autowired
	private BdgAppWallModel bdgAppWallModel;
	
	private List<TbBdgAppWall> bdgAppWallsTemp;
	
	private List<Map<String,?>> bdgAppWalls;
	
	private List<TbAppInfo> appInfos;
	
	private Long appWallId;
	
	private Integer sort;
	
	private Boolean isPush;
	
	@Action("queryBdgAppWalls")
	public String queryBdgAppWall(){
		try{
			bdgAppWalls=bdgAppWallModel.queryBdgAppWalls(start, limit);
			if(total==null){
				total=bdgAppWallModel.getBdgAppWallCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("addBdgAppWalls")
	public String addBdgAppWalls(){
		try{
			bdgAppWallModel.addBdgAppWalls(appInfos);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("deleteBdgAppWalls")
	public String deleteBdgAppWalls(){
		try{
			bdgAppWallModel.deleteBdgAppWalls(bdgAppWallsTemp);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	@Action("updateBdgAppWallSort")
	public String updateBdgAppWallSort(){
		try{
			if(appWallId!=null&&sort!=null){
				TbBdgAppWall bdgAppWall=bdgAppWallModel.queryBdgAppWallById(appWallId);
				bdgAppWall.setSort(sort);
				bdgAppWallModel.updateBdgAppWall(bdgAppWall);
			}else{
				throw new DoErrorException("缺少必要的参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("updateBdgAppWallPush")
	public String updateBdgAppWallPush(){
		try{
			if(appWallId!=null&&isPush!=null){
				TbBdgAppWall bdgAppWall=bdgAppWallModel.queryBdgAppWallById(appWallId);
				bdgAppWall.setIsPush(isPush);
				bdgAppWallModel.updateBdgAppWall(bdgAppWall);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e.getMessage());
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<TbBdgAppWall> getBdgAppWallsTemp() {
		return bdgAppWallsTemp;
	}

	public void setBdgAppWallsTemp(List<TbBdgAppWall> bdgAppWallsTemp) {
		this.bdgAppWallsTemp = bdgAppWallsTemp;
	}

	public List<Map<String, ?>> getBdgAppWalls() {
		return bdgAppWalls;
	}

	public void setBdgAppWalls(List<Map<String, ?>> bdgAppWalls) {
		this.bdgAppWalls = bdgAppWalls;
	}

	public List<TbAppInfo> getAppInfos() {
		return appInfos;
	}

	public void setAppInfos(List<TbAppInfo> appInfos) {
		this.appInfos = appInfos;
	}

	public Long getAppWallId() {
		return appWallId;
	}

	public void setAppWallId(Long appWallId) {
		this.appWallId = appWallId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getIsPush() {
		return isPush;
	}

	public void setIsPush(Boolean isPush) {
		this.isPush = isPush;
	}
	
}
