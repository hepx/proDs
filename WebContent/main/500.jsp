<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String error=(String)request.getAttribute("error");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页面</title>
</head>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
	    <td align="center" class="bg" valign="top">
	       <table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;word-break:break-all;">
	           <tr>
	              <td align="center" width="100%" height="80">
	                  <img src="<%=request.getContextPath()%>/images/error.jpg" border="0" width="80" height="80">
	                  <div><b><%=error==null||"".equals(error)?"出现系统异常了，请于管理员联系！":error %></b></div>          
	              </td>
	           </tr>
	           <tr>
	              <td height="30" align="center">
	                  <br><a href="<%=request.getContextPath()%>/index.html">返回登录页</a>
	              </td>
	           </tr>
	       </table>
	    </td>
	</tr>
</table>
</body>
</html>