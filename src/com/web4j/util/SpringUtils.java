package com.web4j.util;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringUtils {
	private static ApplicationContext context = getContext();

	private static ApplicationContext getContext() {
		if (context == null) {
			return WebApplicationContextUtils
					.getWebApplicationContext(ServletActionContext
							.getServletContext());
		}
		return context;
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}
}
