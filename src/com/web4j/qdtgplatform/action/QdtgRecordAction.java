package com.web4j.qdtgplatform.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsExporterBuilder;
import net.sf.dynamicreports.jasper.constant.JasperProperty;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.jasperreports.engine.JREmptyDataSource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.web4j.common.action.AbstractActionSupport;
import com.web4j.common.report.ReportConfig;
import com.web4j.exception.DoErrorException;
import com.web4j.qdtgplatform.entity.TbQdtgRecord;
import com.web4j.qdtgplatform.model.QdtgRecordModel;
import com.web4j.qdtgplatform.pojo.QdtgRecord;
import com.web4j.util.LoginUserUtils;
import com.web4j.util.SysConfig;

@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/qdtg/record")
public class QdtgRecordAction extends AbstractActionSupport implements ModelDriven<QdtgRecord> {

	private static Logger log=Logger.getLogger(QdtgRecordAction.class);
	
	@Autowired
	private QdtgRecordModel qdtgRecordModel;
	
	private List<TbQdtgRecord>qdtgRecords;
	
	private List<Map<String,Object>>statRecords;
	
	private TbQdtgRecord qdtgRecord;
	
	private QdtgRecord statCondition;
	
	private InputStream inputStream;
	
	private String downloadFileName;
	
	@Override
	public QdtgRecord getModel() {
		// TODO Auto-generated method stub
		if(statCondition==null){
			statCondition=new QdtgRecord();
		}
		return statCondition;
	}
	
	/**
	 * 查询录入状态的record
	 * @return
	 */
	@Action("queryQdtgRecordByInStatus")
	public String queryQdtgRecordByInStatus(){
		try{
			qdtgRecords=qdtgRecordModel.queryQdtgRecordByInStatus(start, limit);
			if(total==null){
				total=qdtgRecordModel.queryQdtgRecordByInStatusCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("queryQdtgRecord")
	public String queryQdtgRecord(){
		try{
			qdtgRecords=qdtgRecordModel.queryQdtgRecords(start, limit,statCondition);
			if(total==null){
				total=qdtgRecordModel.queryQdtgRecordCount(statCondition);
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("enterQdtgRecord")
	public String enterQdtgRecords(){
		try{
			qdtgRecordModel.updateQdtgRecordStatus(qdtgRecords);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}	
	
	@Action("saveOrUpdateQdtgRecord")
	public String saveOrUpdateQdtgRecord(){
		try{
			if(qdtgRecord!=null&&qdtgRecord.getChannelId()!=null){
				qdtgRecordModel.saveOrUpdateQdtgRecord(qdtgRecord);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteQdtgRecord")
	public String deleteQdtgRecord(){
		try{
			qdtgRecordModel.deleteQdtgRecords(qdtgRecords);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	/**
	 * 统计
	 * 2013-05-27 修改为不分页形式
	 * @return
	 */
	@Action("statQdtgRecord")
	public String statQdtgRecord(){
		try{
			if(statCondition.getStatType()==SysConfig.QDTG_STAT_DAY){
				statRecords=qdtgRecordModel.statQdtgRecordByDay(statCondition,start, limit);
//				if(total==null){
//					total=qdtgRecordModel.statQdtgRecordByDayCount(statCondition);
//				}
			}else{
				statRecords=qdtgRecordModel.statQdtgRecordByMonth(statCondition);
//				if(total==null){
//					total=(long)statRecords.size();
//				}
			}
			statCondition=null;
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;		
	}
	
	/**
	 * 统计 by 客户
	 * 2013-05-27 修改为不分页形式
	 * @return
	 */
	@Action("statQdtgRecordByCvs")
	public String statQdtgRecordByCvs(){
		try{
			Long userId=LoginUserUtils.getLoginUserInfo().getId();
			if(userId!=null){
				if(statCondition.getStatType()==SysConfig.QDTG_STAT_DAY){
					statRecords=qdtgRecordModel.statQdtgRecordByCvsByDay(userId,statCondition,start, limit);
//					if(total==null){
//						total=qdtgRecordModel.statQdtgRecordByCvsByDayCount(userId,statCondition);
//					}
				}else{
					statRecords=qdtgRecordModel.statQdtgRecordByCvsByMonth(userId,statCondition);
//					if(total==null){
//						total=(long)statRecords.size();
//					}
				}
				statCondition=null;
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;		
	}
	
	@Action(value="exportQdtgXLS",results = { @Result(name = "xls", type = "stream", params = {
			"contentType", "application/octet-stream;charset=ISO8859-1", "inputName",
			"inputStream", "contentDisposition",
			"attachment;filename=\"${downloadFileName}\"", "bufferSize", "4096" }) })
	public String exportQdtgXLS(){
		String title="";
		HttpServletResponse response = ServletActionContext.getResponse();
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try{
			if(statCondition.getStatType()==SysConfig.QDTG_STAT_DAY){
				title="对帐单-按日";
				statRecords=qdtgRecordModel.statQdtgRecordByDay(statCondition,start, limit);
			}else{
				title="对帐单-按月";
				statRecords=qdtgRecordModel.statQdtgRecordByMonth(statCondition);
			}
			setDownloadFileName(title);
			JasperReportBuilder report=DynamicReports.report();//创建空报表
			JasperXlsExporterBuilder xlsExporter=DynamicReports.export.xlsExporter(buffer)
				.setDetectCellType(true)
		        .setIgnorePageMargins(true)
		        .setWhitePageBackground(false)
		        .setRemoveEmptySpaceBetweenColumns(true);
			report.setColumnTitleStyle(ReportConfig.getColumnTitleStl())
				.addProperty(JasperProperty.EXPORT_XLS_FREEZE_ROW, "2")
			    .ignorePageWidth()
			    .ignorePagination()
			    .columns(Columns.column("日期", "recordTime", DataTypes.stringType()),
						   Columns.column("产品","productName",DataTypes.stringType()),
						   Columns.column("渠道号","channelNo",DataTypes.stringType()),
						   Columns.column("文件名","fileName",DataTypes.stringType()),
						   Columns.column("单价","unitPrice",DataTypes.floatType()),
						   Columns.column("激活","activateQt_Net",statCondition.getStatType()
								   ==SysConfig.QDTG_STAT_DAY?DataTypes.integerType():DataTypes.longType()),
						   Columns.column("小计","totalPrice_Net",statCondition.getStatType()
								   ==SysConfig.QDTG_STAT_DAY?DataTypes.floatType():DataTypes.doubleType()))
				.title(Components.text(title).setStyle(ReportConfig.getTitleStl()));//标题
			if(statRecords==null||statRecords.size()<=0){
				report.setDataSource(new JREmptyDataSource());
			}else{
				report.setDataSource(statRecords);//数据源
			}
			report.toXls(xlsExporter);
			byte[] bytes = buffer.toByteArray();
			response.setContentLength(bytes.length);
			if(bytes!=null){
				inputStream = new ByteArrayInputStream (bytes);
			}
			return "xls";
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
			statRecords=null;
			return JSON;
		}finally{
			try{
				buffer.close();
				if(inputStream!=null)
					inputStream.close();
			}catch(Exception e){
				log.error(e.getMessage(), e);
			}
		}
	}

	public List<TbQdtgRecord> getQdtgRecords() {
		return qdtgRecords;
	}

	public void setQdtgRecords(List<TbQdtgRecord> qdtgRecords) {
		this.qdtgRecords = qdtgRecords;
	}

	public TbQdtgRecord getQdtgRecord() {
		return qdtgRecord;
	}

	public void setQdtgRecord(TbQdtgRecord qdtgRecord) {
		this.qdtgRecord = qdtgRecord;
	}

	public QdtgRecord getStatCondition() {
		return statCondition;
	}

	public void setStatCondition(QdtgRecord statCondition) {
		this.statCondition = statCondition;
	}

	public List<Map<String, Object>> getStatRecords() {
		return statRecords;
	}

	public void setStatRecords(List<Map<String, Object>> statRecords) {
		this.statRecords = statRecords;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getDownloadFileName() throws UnsupportedEncodingException {
		return new String((downloadFileName+".xls").getBytes(),"ISO8859-1");
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}


}
