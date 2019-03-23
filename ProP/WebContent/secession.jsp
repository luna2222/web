<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
</head>
<body>

	<form name="secession_form" action="deleteMember.do" method="post">
		아이디 : <input type="text" name="mid" id="mid"
						value="<% if(session.getAttribute("mId") != null)
									out.println(session.getAttribute("mId"));
							%>"><br>
		<input type="submit" value="회원탈퇴" >&nbsp;&nbsp;


</body>
</html>