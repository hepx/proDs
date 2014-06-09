<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.web4j.systemplatform.pojo.userInfo.UserInfoPojo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath %>">
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache, must-revalidate, no-store" />
<!-- 引入样式表 -->
<link rel="stylesheet" type="text/css" href="<%= path %>/extjs/resources/css/ext-all_1.css" />
<link rel="stylesheet" type="text/css" href="<%= path %>/extjs/ux/fileuploadfield/css/fileuploadfield.css"/>
<link rel="stylesheet" type="text/css" href="<%= path %>/extjs/ux/dataView/data-view.css"/>
<link rel="stylesheet" type="text/css" href="<%= path %>/extjs/ux/dataView/animated-dataview.css"/>
<link rel="stylesheet" type="text/css" href="<%= path %>/css/icons.css" />
<link rel="stylesheet" type="text/css" href="<%= path %>/css/frontdesk.css"/>

<% 
	UserInfoPojo userInfo=(UserInfoPojo)request.getSession().getAttribute("login_user");
%>
<script type="text/javascript">
	USER_NAME='<%=userInfo.getUserName()%>';
</script>
</head>
<body>
<div id="loading">
<div class="loading-indicator"><img
	src="images/public/blue-loading.gif" width="32" height="32"
	style="margin-right: 8px; float: left; vertical-align: top;" /> <span
	id="loading-msg"></span></div>
</div>
	<!-- 核心代码 -->
	<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '初始化核心代码...';</script>
	<script type="text/javascript" src="<%= path %>/extjs/adapter/ext/ext-base.js"></script>
	
	<script type="text/javascript" src="<%= path %>/extjs/ext-all-debug_1.js"></script>
	<!--
	<script type="text/javascript" src="<%= path %>/extjs/ext-all_1.js"></script>
	-->
	<script type="text/javascript" src="<%= path %>/extjs/src/locale/ext-lang-zh_CN.js"></script>
	<!-- 插件 -->
	<script type="text/javascript" src="<%= path %>/extjs/ux/RowExpander.js"></script>
	<script type="text/javascript" src="<%= path %>/extjs/ux/fileuploadfield/FileUploadField.js"></script>
	<script type="text/javascript" src="<%= path %>/extjs/ux/dataView/DataView-more.js"></script>
	<script type="text/javascript" src="<%= path %>/extjs/ux/pagesize/pPageSize.js"></script>
	<script type="text/javascript" src="<%= path %>/extjs/ux/dataView/DataViewTransition.js"></script>
	<script type="text/javascript" src="<%= path %>/extjs/ux/monthpicker/monthPick.js"></script>
	<!-- 自定义公用代码 -->
	<script type="text/javascript" src="<%= path %>/util/commonUtils.js"></script>
	<script type="text/javascript" SRC="<%= path %>/util/commonMethods.js"></script>
	<script type="text/javascript" src="<%= path %>/util/userRegistWin.js"></script>
	<script type="text/javascript" src="<%= path %>/market/appMgr/appMainWin.js"></script>
	<!-- 
	<script type="text/javascript" src="<%= path %>/grid/gridCommon.js"></script>
	 -->
	<script type="text/javascript" src="<%= path %>/fileUpload/singleFileUploadWin.js"></script>
	<!-- 布局代码 -->
	<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '初始化业务代码...';</script>
	<script type="text/javascript" src="<%= path %>/main/MainHeader.js"></script>
	<script type="text/javascript" src="<%= path %>/main/MainMeau.js"></script>
	<script type="text/javascript" src="<%= path %>/main/MainTree.js"></script>
	<script type="text/javascript" src="<%= path %>/main/MainView.js"></script>
	<script type="text/javascript" src="<%= path %>/main/MainTab.js"></script>
	<script type="text/javascript" src="<%= path %>/main/main.js"></script>
	<script type="text/javascript">document.getElementById('loading-msg').innerHTML = '欢迎进入管理系统...';</script>
</body>
</html>