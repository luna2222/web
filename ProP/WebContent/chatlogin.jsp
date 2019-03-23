<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script>
	function form_check(){
		submit();
	}
	
	function submit(){
		document.chatlogin_form.submit();
	}
</script>
<body>

	<form name="chatlogin_form" action="chatclient.jsp" method="post">
		대화명입력 : <input type="text" name="chatName"><br>
		<input type="button" value="대기실입장" onclick="form_check();">&nbsp;&nbsp;
		<input type="button" value="메인" onclick="javascript:window.location='main.jsp'">
	</form>

</body>
</html>