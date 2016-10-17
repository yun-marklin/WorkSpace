<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
	<script type="text/javascript">
		function _change(){
			var img=document.getElementById("img");
			img.src="<c:url value='/VerifyServlet'/>?xxx"
		+new Date().getTime();
		};
	</script>
</head>
<body>
	<p style="color:red;font-weight: 300">${requestScope.msg}</p>
	<p style="color:red;font-weight: 300">${error.verifyCode}</p>
	<form action="<c:url value='/RegistServlet'/>" method="post">
		用户名 <input type="Text" name="username" value="${user.username}">
		<font style="color:red;">${error.username }</font><br><br>
		密&nbsp;&nbsp;&nbsp;码 <input type="password" name="password" value="${user.password }">
		<font color="red">${error.password }</font><br><br>
		验证码<input type="text" name="verifyCode"/>
		<font style="color:red;">${error.verifycode }</font>
			 <img border="1"
			border-color="black" id="img"
			src="<c:url value='/VerifyServlet' />" />&nbsp;&nbsp; 
			<a href="javascript:_change();">换一张</a> <br/><br/>
		<input type="submit" value="注册" />
	</form>
</body>
</html>