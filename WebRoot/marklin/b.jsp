<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="marklin.user.domain.User"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
</head>
<body>
	<h1>欢迎来到登陆页面</h1>
	<%
		User user=new User();
		//HttpRequest hr=request.getAttribute(user.getUsername());
	%>
	<p style="color:red;font-weight: 300">${requestScope.msg}</p>
	<form action="<c:url value='/LoginServlet'/>" method="post">
		用户名<input type="text" name="username"/><br/><br/>
		密&nbsp;&nbsp;&nbsp;码<input type="password" name="password"/><br><br>
		<input type="submit" value="登陆"/>
	</form>
</body>
</html>