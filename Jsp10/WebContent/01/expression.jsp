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
	int i=0;
	String str="abc";    
%>

<%!
	private int sum(int a, int b){
	return a+b;
	}
 %>
 	숫자 i의 값은 <%= i  %>입니다.<br>
 	문자변수에는 <%=str %>값이 저장되어 있습니다.<br>
 	두 수의 합은 <%=sum(1,5)  %>입니다<br>
</body>
</html>