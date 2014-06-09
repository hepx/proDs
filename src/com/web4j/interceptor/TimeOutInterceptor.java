package com.web4j.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.web4j.common.action.AbstractActionSupport;
import com.web4j.systemplatform.pojo.userInfo.UserInfoPojo;
import com.web4j.util.SysConfig;

/**
 * 检查登录超时
 * @author hepx
 *
 */
public class TimeOutInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 6107796196783785175L;

	@Override
	public String intercept(ActionInvocation invocation)throws Exception{
		// TODO Auto-generated method stub
		Map<String,Object>sessionMap=invocation.getInvocationContext().getSession();
		UserInfoPojo userInfo=(UserInfoPojo)sessionMap.get(SysConfig.LOGIN_USER);
		if(userInfo==null){
			if(isAjaxRequest(ServletActionContext.getRequest())){
				AbstractActionSupport support=(AbstractActionSupport)invocation.getAction();
				support.setTimeout(true);
				return SysConfig.TIMEOUT_MESSAGE_PAGE;
			}else{
				return SysConfig.TIMEOUT_MESSAGE_PAGE;
			}
		}
		return invocation.invoke();
	}
	
	/**
	 * 判断请求是否为AJAX
	 * @param request
	 * @return
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}

}
