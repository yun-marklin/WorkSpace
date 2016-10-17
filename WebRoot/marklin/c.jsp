<%@page import="marklin.user.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*"
	import="javax.servlet.http.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆成功</title>
</head>
<body>
	<%
		HttpSession hs=request.getSession();
		User user=(User)hs.getAttribute("user");
	%>
	<div><font size='5'>用户:<%=user.getUsername() %></font>&nbsp;&nbsp;点击<a href=""><font color='blue'>刷新颜色</font></a></div>
	<%
		ArrayList<String> al=new ArrayList<String>();
		Random r=new Random();
		al.add("blue");
		al.add("green");
		al.add("yellow");
		al.add("red");
		//out.print(al.size());
		int x=r.nextInt(al.size());
	%>
	<h1><font size="10" color="<%=al.get(x) %>"><marquee behavior=alternate>炫丽天空之城</marquee></font></h1><br/>
	<%
		ArrayList<String> a2=new ArrayList<String>();
		a2.add("city1.jpg");
		a2.add("20120415225546.jpeg");
		a2.add("20140504151509.jpeg");
		int y=r.nextInt(a2.size());
		int z=r.nextInt(a2.size());
	%>
	<div align="center"><img src="..\<%=a2.get(y) %>" alt="图片" width="45%" height="300"/><img src="..\<%=a2.get(z) %>" alt="麦片" width="45%" height="300"/>
	</div>
</body>
</html>