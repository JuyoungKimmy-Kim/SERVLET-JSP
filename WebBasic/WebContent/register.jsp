<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//import
List<CodeDto> codeList=(List<CodeDto>)request.getAttribute("codeList";)
%>

<%
	for (codeDto:codeList){
%>
<input type="radio" value=<%=codeDto.getCode()%>><%=codeDto.getCodeName %></input> 

<%
	}
%>
</body>
</html>