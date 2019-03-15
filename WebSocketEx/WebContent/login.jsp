<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
	<script >
	 function form_check(){
		 submit();		 
	 }
    
    function submit(){
    	document.login_form.submit();
    }
    
   </script>
</head>
<body>
	<form method="post" name="login_form" action="client.jsp">
	
		아이디 : <input type="text" name="id" 
						value="<% if(session.getAttribute("id") != null)
									out.println(session.getAttribute("id"));
							%>"><br>
		비밀번호 : 	<input type="password" name="pw" ><br><p>
	
		<input type="button" value="로그인" onclick="form_check();">&nbsp;&nbsp;
		<input type="button" value="회원가입">
	</form>
</body>
</html>