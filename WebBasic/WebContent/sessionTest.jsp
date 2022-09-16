<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    String userId = (String) session.getAttribute("userId");
    int[] intArray = (int[]) session.getAttribute("intArray");
    List<String> strList = (List<String>) session.getAttribute("strList");
%>

<%
    if( userId != null ){
%>
    <b> userId = <%=userId %></b>
<%
    }
%>
<hr>
<%
    if( intArray != null ){
        for( int i : intArray ){
%>
    <b> <%= i %></b>
<%
        }
    }
%>
<hr>
<%
    if( strList != null ){
        for( String str : strList ){
%>
    <b> <%= str %></b>
<%
        }
    }
%>
</body>
</html>