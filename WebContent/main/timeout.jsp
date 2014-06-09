<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录超时</title>
<style type="text/css">
.STYLE1 {
	font-size: 12px;
	margin-top: 25%;
}
</style>
</head>
<body>
<div class="STYLE1">
	<div align="center">
	<table border="1" align="center" bordercolor="#99bbe8" bgcolor="#99bbe8">
		<tr>
			<td align="center" valign="bottom"><span style="font-size: 12px;">[登录超时，请重新登录。] </span></td>
		</tr>
		<tr>
			<td width="100%" height="100%">
				<div align="center"><a href="<%=request.getContextPath()%>/index.html">返回主页</a></div>
			</td>
		</tr>
	</table>
	</div>
</div>
</body>
</html>