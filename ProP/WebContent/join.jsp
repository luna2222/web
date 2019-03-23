<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join</title>

	<script src="http://code.jquery.com/jquery.js"></script>
	<script>
	function mId_check(){
		
	}
	function form_check(){
		if($('#mid').val().length == 0){
			alert("아이디는 필수사항입니다.");
			$('#mid').focus();
			return;
		}
		
		if($('#mid').val().length < 4){
			alert("아이디는 4글자 이상이어야 합니다.");
			$('#mid').focus();
			return;
		}
		
		if($('#mpw').val().length == 0){
			alert("비밀번호는 필수사항입니다.");
			$('#mpw').focus();
			return;
		}
		
		if($('#mpw').val() != $('#mpw_check').val()){
			alert("비밀번호가 일치하지 않습니다.");
			$('#mpw').focus();
			return;
		}
		
		if($('#mname').val().length == 0){
			alert("이름은 필수사항입니다.");
			$('#mname').focus();
			return;
		}
		
		if($('#memail').val().length == 0){
			alert("메일은 필수사항입니다.");
			$('#memail').focus();
			return;
		}
		document.join_form.submit();
		//submit_ajax();
	}
	function submit_ajax(){
		var queryString = $("#join_form").serialize();
		$.ajax({
			//url:'/Jsp212/joinOk.jsp',
			url:'/Project02/join.do',
			type:'post',
			dataType:'text',
			//data:$('#my-form').serialize(),
			data: queryString,
			success:function(json){
				console.log(json);
				var result = JSON.parse(json);
				if(result.code =="success"){
					alert(result.desc)
					window.location.replace("main.jsp");
				}else{
					alert(result.desc);
					window.location.replace("join.jsp");
				}
			}
		});
	}
	</script>


</head>
<body>
	
		<form id="join_form" name="join_form" action="join.do" method="post">
			아이디 : <input type="text" id="mid" name="mid" size="20"><br>
			<input type="button" value="아이디중복체크" onclick="mId_check();">&nbsp;&nbsp;&nbsp;
			비밀번호 : <input type="password" id="mpw" name="mpw" size="20"><br>
			비밀번호 확인 : <input type="password" id="mpw_check" name="mpw_check" size="20"><br>
			이름 : <input type="text" name="mname" id="mname" size="20"><br>
			메일 : <input type="text" name="memail" id="memail" size="20"><br>
			주소 : <input type="text" name="maddress" id="maddress" size="50"><br>
			<input type="submit" value="조인">
			<input type="button" value="회원가입" onclick="JavaScript:form_check();">&nbsp;&nbsp;&nbsp;
			<input type="button" value="로그인" onclick="javascript:window.location='login.jsp'">
		</form>
	
</body>
</html>