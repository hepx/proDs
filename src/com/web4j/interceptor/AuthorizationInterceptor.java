package com.web4j.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 授权访问
 * @author xixi
 * @date 2012-12-19
 *
 */
public class AuthorizationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -3785426072305258825L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest)invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		String token =req.getHeader("kyb_token");
		if(!"888888".equals(token)){
			HttpServletResponse res=(HttpServletResponse)invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
			String error="访问未授权！";
			res.setContentType("text/html;charset=UTF-8");
			res.getWriter().println(error);
			//req.setAttribute("error",error);
			//res.getOutputStream().print(error);
			return null;
		}else{
			return invocation.invoke();
		}
	}
}
