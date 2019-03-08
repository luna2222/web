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
int num1,num2, result;
String operator;
%>
 <% System.out.println("doPost");
	     request.setCharacterEncoding("UTF-8");
	     
	     String sNum1 = request.getParameter("num1");
	     String sNum2 = request.getParameter("num2");
	     String operator=request.getParameter("operator");
	     result=0;
	     
	     response.setContentType("text/html; charset=UTF-8");
	   	    	     
	     if(operator.equalsIgnoreCase("pls")) {
	    	 result=num1+num2;	    	 
	     }
	    		 
	     if(operator.equalsIgnoreCase("min")) {
	    	 result=num1-num2;
	    }
	     if(operator.equalsIgnoreCase("mul")) {
	    	 result=num1*num2;
        }
	     if(operator.equalsIgnoreCase("div")) {
	    	 result=num1/num2;
	    	 }
%>	  
   <%
   out.println(" "+<br>");
   out.println(" ");
   out.println(" ");
   out.println(" ");
   out.println(" ");
   out.println(" ");
   
   %>
</body>
</html>