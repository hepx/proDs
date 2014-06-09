package com.web4j.sxdplatform.action.books;

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
import com.web4j.sxdplatform.model.ChantingRecordModel;
import com.web4j.sxdplatform.pojo.ChantingRecord;
/**
 * 手机调用 诵经ACTION
 * @author xixi
 * @date 2012-11-13
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/sxd/phone/chantrecord")
public class ChantingRecordOfPhoneAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(ChantingRecordOfPhoneAction.class);
	
	@Autowired
	private ChantingRecordModel chantingRecordModel;
	
	private Long chantRecordId;
	
	private String xyNo;//香缘号
	
	private String chantingBook;//诵经的经书。
	
	private String meritContent;//功德内容
	
	private String restoreContent;//还原内容
	
	private String month;//月份
	
	private List recordStat;//记录统计
	
	private List recordDetails;//记录详情
	
	/**
	 * 提交诵经记录
	 * @return
	 */
	@Action("sumbitCantingRecord")
	public String createChantingRecord(){
		try{
			if(xyNo!=null&&chantingBook!=null){
				ChantingRecord record=new ChantingRecord(xyNo,chantingBook,meritContent);
				chantingRecordModel.saveChantingRecord(record);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 还原
	 * @return
	 */
	@Action("restore")
	public String uploadChantingRecord(){
		try{
			String restoreStatus="1";//设置状态为1：还原
			if(chantRecordId!=null&&restoreContent!=null){
				chantingRecordModel.updateChantingRecord(chantRecordId,restoreStatus,restoreContent);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	/**
	 * 按月统计指定用户的诵经记录
	 * @return
	 */
	@Action("statByMonth")
	public String statByMonth(){
		try{
			if(xyNo!=null){
				recordStat=chantingRecordModel.statByMonth(xyNo);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	/**
	 * 查询指定当月中的详细诵经信息
	 * @return
	 */
	@Action("queryDetails")
	public String queryDetailsByMonth(){
		try{
			if(xyNo!=null&&month!=null&&chantingBook!=null){
				recordDetails=chantingRecordModel.queryDetails(xyNo, month, chantingBook);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;		
	}
	
	public Long getChantRecordId() {
		return chantRecordId;
	}

	public void setChantRecordId(Long chantRecordId) {
		this.chantRecordId = chantRecordId;
	}

	public String getXyNo() {
		return xyNo;
	}

	public void setXyNo(String xyNo) {
		this.xyNo = xyNo;
	}

	public String getChantingBook() {
		return chantingBook;
	}

	public void setChantingBook(String chantingBook) {
		this.chantingBook = chantingBook;
	}

	public String getMeritContent() {
		return meritContent;
	}

	public void setMeritContent(String meritContent) {
		this.meritContent = meritContent;
	}

	public String getRestoreContent() {
		return restoreContent;
	}

	public void setRestoreContent(String restoreContent) {
		this.restoreContent = restoreContent;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List getRecordStat() {
		return recordStat;
	}

	public void setRecordStat(List recordStat) {
		this.recordStat = recordStat;
	}

	public List getRecordDetails() {
		return recordDetails;
	}

	public void setRecordDetails(List recordDetails) {
		this.recordDetails = recordDetails;
	}
}
