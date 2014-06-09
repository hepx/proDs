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
import com.web4j.exception.DoErrorException;
import com.web4j.sxdplatform.model.PrayRecordModel;
import com.web4j.sxdplatform.pojo.PrayRecord;
import com.web4j.util.SysConfig;
/**
 * 手机调用祝福记录ACTION
 * @author xixi
 * @date 2012-11-14
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/sxd/phone/pray")
public class PrayRecordOfPhoneAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(PrayRecordOfPhoneAction.class);
	
	@Autowired
	private PrayRecordModel prayRecordModel;
	
	private List<PrayRecord> prayRecords;
	
	private List prayRecordStat;
	
	private Long prayId;
	
	private String xyNo;//香缘号
	
	private String prayType;//祈福类型
	
	private String meritContent;//功德内容
	
	private Long buddhaImageId;//佛像ID
	
	private Long articleId;//物品ID

	@Action("queryPrayRecords")
	public String queryUserPrayRecords(){
		try{
			if(start==null){
				start = SysConfig.PHONE_REQ_START;
			}
			if(limit==null){
				limit = SysConfig.PHONE_REQ_LIMIT;
			}
			if(xyNo!=null&&prayType!=null){
				prayRecords=prayRecordModel.quereyPrayRecordByUser(xyNo,prayType, start, limit);
			}else{
				throw new DoErrorException("缺少必要的参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 提交祈福记录
	 * @return
	 */
	@Action("submitPrayRecord")
	public String submitPrayRecord(){
		try{
			if(xyNo!=null&&prayType!=null){
				PrayRecord prayRecord=new PrayRecord(xyNo, prayType, meritContent);
				prayRecordModel.createPrayRecord(prayRecord, buddhaImageId, articleId);
			}else{
				throw new DoErrorException("缺少必要的参数！");	
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	/**
	 * 按祈福类型统计
	 * @return
	 */
	@Action("statByPrayType")
	public String statByPrayType(){
		try{
			if(xyNo!=null){
				prayRecordStat=prayRecordModel.statByPrayType(xyNo);
			}else{
				throw new DoErrorException("缺少必要的参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<PrayRecord> getPrayRecords() {
		return prayRecords;
	}

	public void setPrayRecords(List<PrayRecord> prayRecords) {
		this.prayRecords = prayRecords;
	}

	public Long getPrayId() {
		return prayId;
	}

	public void setPrayId(Long prayId) {
		this.prayId = prayId;
	}

	public String getXyNo() {
		return xyNo;
	}

	public void setXyNo(String xyNo) {
		this.xyNo = xyNo;
	}

	public String getPrayType() {
		return prayType;
	}

	public void setPrayType(String prayType) {
		this.prayType = prayType;
	}

	public String getMeritContent() {
		return meritContent;
	}

	public void setMeritContent(String meritContent) {
		this.meritContent = meritContent;
	}

	public Long getBuddhaImageId() {
		return buddhaImageId;
	}

	public void setBuddhaImageId(Long buddhaImageId) {
		this.buddhaImageId = buddhaImageId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public List getPrayRecordStat() {
		return prayRecordStat;
	}

	public void setPrayRecordStat(List prayRecordStat) {
		this.prayRecordStat = prayRecordStat;
	}

}
