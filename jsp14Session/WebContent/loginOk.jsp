<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
String id,pw,name;
%>
<% 
       
	   request.setCharacterEncoding("UTF-8");
	     
	   id = request.getParameter("id");
	   pw = request.getParameter("pw");
	   name = request.getParameter("name");
	   
	   if(id.equals("abcd")&& pw.equals("1234")){
	      session.setAttribute("id", id);
	      session.setAttribute("name", name);
	    
	      response.sendRedirect("welcome.jsp");
	     } else{
	    	 response.sendRedirect("login.html");
	     }	     
	  %>
</body>
</html>