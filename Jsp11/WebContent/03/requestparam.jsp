
<%@ page import ="java.util.Arrays"%>
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
	String name, id,pw,major,protocol;
	String[] hobbys;
%>
<%
String sType=request.getMethod();

if(sType.equals("get")){
	response.sendRedirect("error40A.html");
}
request.setCharacterEncoding("UTF-8");

name= request.getParameter("name");
id=request.getParameter("id");
pw=request.getParameter("pw");
major=request.getParameter("major");
protocol=request.getParameter("protocol");
hobbys=request.getParameterValues("hobby");
%>


이름: <%= name%><br>
아이디:<%=id %><br>
비밀번호<%=pw %><br>
취미:<%= Arrays.toString(hobbys) %><br>
프로토콜:<%= protocol %><br>


</body>
</html>