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
       System.out.println("doPost");
	   request.setCharacterEncoding("UTF-8");
	     
	   id = request.getParameter("id");
	   pw = request.getParameter("pw");
	   name = request.getParameter("name");
	   
	   if(id.equals("abcd")&& pw.equals("1234")){
	       Cookie cookie= new Cookie("id",id);
	       cookie.setMaxAge(60);
	       response.addCookie(cookie);
	    	 
	       Cookie cookie2= new Cookie("name",name);
	       cookie.setMaxAge(60);
	       response.addCookie(cookie2);
	    	 
	      response.sendRedirect("welcome.jsp");
	     } else{
	    	 response.sendRedirect("login.html");
	     }	     
	  %>
</body>
</html>