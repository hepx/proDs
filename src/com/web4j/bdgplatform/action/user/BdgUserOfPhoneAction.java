package com.web4j.bdgplatform.action.user;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

import com.web4j.bdgplatform.entity.TbBdgUser;
import com.web4j.bdgplatform.model.BdgUserModel;
import com.web4j.common.action.AbstractActionSupport;
import com.web4j.exception.DoErrorException;
/**
 * 手机调用注册ACTION
 * @author xixi
 * @date 2012-11-10
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("pm-base")
@Namespace("/bdg/phone/user")
public class BdgUserOfPhoneAction extends AbstractActionSupport {

	private Logger log =  Logger.getLogger(BdgUserOfPhoneAction.class);
	
	@Autowired
	private BdgUserModel bdgUserModel;
	
	private String imei;

	private String mobile;//手机号
	
	private String simOperator;//SIM运营商
	
	private String networkType;//网络类型
	
	private String province;//省份
	
	private String city;//城市

	private String sdk;//SDK版本信息

	private String screenSize;//屏幕尺寸
	
	private String channel;
	
	@Action("register")
	public String register(){
		try{
			//当手机号不为空，以手机号段来获得用户所在区域
//			if(mobile!=null&&!"".equals(mobile)){
//				
//			}else{
				getIpInfo(ServletActionContext.getRequest().getRemoteAddr());
//			}
			TbBdgUser user=new TbBdgUser(imei,mobile,simOperator,networkType,sdk,screenSize,province,city,channel);
			bdgUserModel.createBdgUser(user);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	public void getIpInfo(String ip){
		String url=WeiboConfig.getValue("taobao_ip");
		HttpClient client=new HttpClient();
		HttpMethod method=new GetMethod(url+"?ip="+ip);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler(3, false));
		try {
			int status=client.executeMethod(method);
			if(status!=HttpStatus.SC_OK){
				throw new DoErrorException("请求淘宝IP请口失败！IP:"+ip+" status:"+status);
			}
			String result=method.getResponseBodyAsString();
			if(result!=null&&!"".equals(result)){
				JSONObject obj=new JSONObject(result);
				JSONObject data=(JSONObject)obj.get("data");
				this.province=data.getString("region");
				this.city=data.getString("city");
				//this.simOperator=data.getString("isp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("getIpInfo error", e);
		}finally{
			method.releaseConnection();
		}
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSimOperator() {
		return simOperator;
	}

	public void setSimOperator(String simOperator) {
		this.simOperator = simOperator;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}

	public String getSdk() {
		return sdk;
	}

	public void setSdk(String sdk) {
		this.sdk = sdk;
	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
