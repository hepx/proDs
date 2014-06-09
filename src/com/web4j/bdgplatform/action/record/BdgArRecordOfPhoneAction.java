package com.web4j.bdgplatform.action.record;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.bdgplatform.entity.TbBdgArRecord;
import com.web4j.bdgplatform.model.BdgArRecordModel;
import com.web4j.common.action.AbstractActionSupport;
import com.web4j.util.SysConfig;
/**
 * 微博计录手机调用 ACTION
 * @author xixi
 * @date 2012-11-29
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/bdg/phone/arrecord")
public class BdgArRecordOfPhoneAction extends AbstractActionSupport {

	private static Logger log = Logger.getLogger(BdgArRecordOfPhoneAction.class);
	
	@Autowired
	private BdgArRecordModel bdgArRecordModel;
	
	private List<TbBdgArRecord> records;
	
	private Long recordId;
	
	private Long since_id=0L;	//	若指定此参数，则返回ID比since_id大的记录，默认为0。
	private Long max_id	=0L;	//	若指定此参数，则返回ID小于或等于max_id的记录，默认为0。
	
	@Action("queryRecords")
	public String queryBdgRecords(){
		try{
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			records=bdgArRecordModel.quereyBdgArRecordsOfPhone(start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	/**
	 * 上一页数据
	 * @return
	 */
	@Action("preRecords")
	public String preBdgRecords(){
		try{
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			records=bdgArRecordModel.quereyPreBdgArRecordsOfPhone(start, limit, since_id);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	
	/**
	 * 下一页数据
	 * @return
	 */
	@Action("nextRecords")
	public String nextBdgRecords(){
		try{
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			records=bdgArRecordModel.quereNextBdgArRecordsOfPhone(start, limit, max_id);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(),e);
		}
		return JSON;
	}
	/**
	 * 顶
	 * @return
	 */
	@Action("toTop")
	public String toTop(){
		try{
			TbBdgArRecord record=bdgArRecordModel.quereyBdgArRecordById(recordId);
			record.setTops(record.getTops()+1);
			bdgArRecordModel.updateBdgArRecord(record);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	/**
	 * 顶最高
	 * @return
	 */
	@Action("orderTop")
	public String orderTop(){
		try{
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			records=bdgArRecordModel.orderTop(start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 踩
	 * @return
	 */
	@Action("toTread")
	public String toTread(){
		try{
			TbBdgArRecord record=bdgArRecordModel.quereyBdgArRecordById(recordId);
			record.setTreads(record.getTreads()+1);
			bdgArRecordModel.updateBdgArRecord(record);			
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	/**
	 * 踩最深
	 * @return
	 */
	@Action("orderTread")
	public String orderTread(){
		try{
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			records=bdgArRecordModel.orderTread(start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	/**
	 * 收藏
	 * @return
	 */
	@Action("toCollect")
	public String toCollect(){
		try{
			TbBdgArRecord record=bdgArRecordModel.quereyBdgArRecordById(recordId);
			record.setCollects(record.getCollects()+1);
			bdgArRecordModel.updateBdgArRecord(record);			
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	/**
	 * 收藏最多
	 * @return
	 */
	@Action("orderCollect")
	public String orderCollect(){
		try{
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			records=bdgArRecordModel.orderCollect(start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("toShare")
	public String toShare(){
		try{
			TbBdgArRecord record=bdgArRecordModel.quereyBdgArRecordById(recordId);
			record.setShares(record.getShares()+1);
			bdgArRecordModel.updateBdgArRecord(record);	
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("orderShare")
	public String orderShare(){
		try{
			if(start==null){
				start=SysConfig.PHONE_REQ_START;
			}
			if(limit==null){
				limit=SysConfig.PHONE_REQ_LIMIT;
			}
			records=bdgArRecordModel.orderShare(start, limit);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<TbBdgArRecord> getRecords() {
		return records;
	}


	public void setRecords(List<TbBdgArRecord> records) {
		this.records = records;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Long getSince_id() {
		return since_id;
	}

	public void setSince_id(Long since_id) {
		this.since_id = since_id;
	}

	public Long getMax_id() {
		return max_id;
	}

	public void setMax_id(Long max_id) {
		this.max_id = max_id;
	}
	
}
