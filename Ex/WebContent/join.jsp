<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
     <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
		 <div class="container">
		 <div class="form-group">
		    <h4>* 회 원 가 입 *</h4>
		   <label for="id">아이디</label>
			 <input type="text"  name="id" id="id" placeholder="Enter Id"size="20"><br> 
		 </div>
		 <div class="form-group"> 
			<label for="password">비밀번호</label>
			  <input type="password" name="pw" id="pw"size="20"><br>
		</div>
		<div class="form-group"> 
			<label for="password">비밀번호 확인</label>
		    <input type="password" name="pw_check" id="pw_check" size="20"><br>
			</div>			
		<div class="form-group">
		     <label for="name">이름</label>
			 <input type="text" name="name" id="name" size="20"><br>
		</div>
		<div class="form-group">
		     <label for="email">메일</label>
			 <input type="eMail" name="eMail" id="eMail" size="20"><br>
		</div>
		<div class="form-group">
		     <label for="address">주소</label>
			 <input type="text" name="address" id="address" size="50"><br><p>
			 </div>
			<input type="button" value="회원가입" class="btn btn-primary" onclick="form_check()">&nbsp;&nbsp;&nbsp;
			<input type="button" value="로그인" class="btn btn-primary" onclick="javascript:window.location='login.jsp'">
		</form>
	</div>
	</div>	
	
	 
 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>