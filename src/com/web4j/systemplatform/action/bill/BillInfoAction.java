package com.web4j.systemplatform.action.bill;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.systemplatform.model.bill.BillInfoModel;
import com.web4j.systemplatform.pojo.bill.BillInfo;

@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/bill")
public class BillInfoAction extends AbstractActionSupport {

	Logger log=Logger.getLogger(BillInfoAction.class);
	
	private BillInfoModel billInfoModel;
	
	private List<BillInfo> billInfos;
	
	public String queryBillInfos(){
		try{
			billInfos=billInfoModel.queryBillInfos();
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<BillInfo> getBillInfos() {
		return billInfos;
	}

	public void setBillInfos(List<BillInfo> billInfos) {
		this.billInfos = billInfos;
	}
	
}
