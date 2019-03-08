<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<script src="http://code.jquery.com./jquery.js"></script>
	<script>
	
	function form_check() 
	{
		if($('#id').val().length == 0){
			alert("아이디는 필수사항입니다.");
			$('#id').focus();
			return;
		}
		
		if($('#id').val().length < 4) {
			alert("아이디는 4글자 이상 입력하셔야 합니다.");
			$('#id').focus();
			return;
		}
		
		if($('#pw').val().length == 0){
			alert("비밀번호는 필수사항입니다.");
			$('#pw').focus();
			return;
		}
		
		if($('#pw').val() != $('#pw_check').val()){
			alert("비밀번호가 일치하지 않습니다.");
			$('#pw').focus();
			return;
		}
		
		if($('#name').val().length == 0){
			alert("이름은 필수사항입니다.");
			$('#name').focus();
			return;
		}
		
		if($('#eMail').val().length == 0){
			alert("메일은 필수사항입니다.");
			$('#eMail').focus();
			return;
		}
		
		submit_ajax();
	}
	
	function submit_ajax() {
		var queryString = $("#reg_frm").serialize();
		$.ajax({
			//url: 'joinOk.jsp',
			url: 'JoinProcess',
			type: 'POST',
			data: queryString,
			dataType: 'text',
			success: function(json) {
				//consle.log(json);
				var result = JSON.parse(json);
				if(result.code == "success") {
					alert(result.desc)
					window.location.replace("login.jsp");
				} else {
					alert(result.desc);
				}
			}
		});
	}
	</script>
</head>
<body>
	
	<div>
		<form method="post" name="reg_frm" id="reg_frm">
			아이디 : <input type="text" name="id" id="id" size="20"><br> 
			비밀번호 : <input type="password" name="pw" id="pw" size="20"><br>
			비밀번호 확인 : <input type="password" name="pw_check" id="pw_check" size="20"><br>
			이름 : <input type="text" name="name" id="name" size="20"><br>
			메일 : <input type="eMail" name="eMail" id="eMail" size="20"><br>
			주소 : <input type="text" name="address" id="address" size="50"><br><p>
			<input type="button" value="회원가입" onclick="form_check()">&nbsp;&nbsp;&nbsp;
			<input type="button" value="로그인" onclick="javascript:window.location='login.jsp'">
		</form>
	</div>	
	
</body>
</html>