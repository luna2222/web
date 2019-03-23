<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="project.jsp.dao.MemberDao" %>   
<%@page import="java.io.PrintWriter" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body>
	<%
		MemberDao dao = MemberDao.getInstance();
		dao.deleteDummy();
		
		session.invalidate();
		response.sendRedirect("login.jsp");
	%>
</body>
</html>