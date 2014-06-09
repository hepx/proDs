package com.web4j.systemplatform.action.main;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/main")
@Result(name = "page", location = "/main/main.jsp")
public class MainAction extends AbstractActionSupport {

	protected Logger log = Logger.getLogger(MainAction.class);

	@Action("main")
	public String execute() throws Exception {
		return PAGE;
	}
}
