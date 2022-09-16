<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> Cookie Test</h1>

<%
	Cookie[] cookies=request.getCookies(); // 모든 쿠키 얻어 옴
	for (Cookie cookie : cookies) {
%>
		<h4> Cookie <%=cookie.getName() %> : <%=cookie.getValue() %></h4>
<% 		
	}
%>
</body>
</html>