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
	String id= request.getParameter("id");
	String pw=request.getParameter("pw");
	
	%>
	아이디:<%=id %><br>
	비밀번호:<%=pw %><br>
	<hr>
	
	아이디:	${parma.id}<br>
	비밀번호:${parma.pw}<br>
	아이디:	${parma.["id"]}<br>
	비밀번호:${parma.["pw"]}<br>
	<hr>
	context 초기화 파라미터<br>
	${initParam.con_name }<br>
	${initParam.con_id }<br>
	${initParam.con_pw }<br>
	
	
</body>
</html>