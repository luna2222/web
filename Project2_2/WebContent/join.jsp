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
		var queryString = $("#join_form").serialize();
		$.ajax({
			//url: 'joinOk.jsp',
			url: 'joinOk.do',
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
	<link rel="stylesheet" href="css/V2Join.css">
</head>
<body>
<form id="join_form" method="post">
    <div id="container">
        <div id="content">
            <div class="join_content">
            <div><h1 style="text-align:center; margin-top:30px; font-weight:'bold'">회 원 가 입</h1></div>
                <div class="row_group">
                	<!-- 아이디 -->
                    <div class="join_row">
                        <h3 class="join_title"><label for="id">아이디</label></h3>
                        <span class="ps_box int_id">
							<input type="text" id="id" name="id" class="int" title="ID" maxlength="20">
						</span>
                    </div>
					<!-- 비밀번호 -->
                    <div class="join_row">
                        <h3 class="join_title"><label for="pw">비밀번호</label></h3>
                         <span class="ps_box box_right_space">
							<input type="password" id="pw" name="pw" class="int" title="비밀번호 입력" aria-describedby="pwMsg" maxlength="20">
                            <span class="lbl"><span id="pwSpan" class="step_txt"></span></span>
						</span>

                        <h3 class="join_title"><label for="pw_check">비밀번호 재확인</label></h3>
                         <span class="ps_box box_right_space">
							<input type="password" id="pw_check" name="pw_check" class="int" title="비밀번호 재확인 입력" aria-describedby="pw_checkBlind" maxlength="20">
						</span>
                        <span class="error_next_box" id="pw_checkMsg" style="display:none" role="alert"></span>
                    </div>
                </div>
                <!-- // 아이디, 비밀번호 입력 -->

               <!-- 이름 -->
                <div class="row_group">
                    <!-- lang = ko_KR -->
                    <div class="join_row">
                        <h3 class="join_title"><label for="name">이름</label></h3>
                        <span class="ps_box box_right_space">
							<input type="text" id="name" name="name" title="이름" class="int" maxlength="40">
						</span>
                    </div>
					
				<!-- 이메일 -->
                    <div class="join_row join_email">
                        <h3 class="join_title"><label for="email">본인 확인 이메일<span class="terms_choice">(필수)</span></label></h3>
                        <span class="ps_box int_email box_right_space">
							<input type="text" id="eMail" name="eMail" maxlength="100" placeholder="필수" aria-label="필수" class="int">
						</span>
                    </div>
                    
                 <!-- 주소 -->
                    <div class="join_row join_address">
                        <h3 class="join_title"><label for="address">주소<span class="terms_choice">(선택)</span></label></h3>
                        <span class="ps_box int_adress box_right_space">
							<input type="text" id="address" name="address" maxlength="100" placeholder="선택입력" aria-label="선택입력" class="int">
						</span>
                    </div>     

                </div>
                <div class="btn_area">
                    <button type="button" id="btnJoin" class="btn_type btn_primary" onclick="form_check() "><span>가입하기</span></button>
                </div>
            </div>
        </div>
    </div>
    <!-- // container -->
</form>



	<!--  
	<div>
		<form method="post" name="reg_frm" id="reg_frm">
			아이디 : <input type="text"name="id"id="id"size="20"><br> 
			비밀번호 : <input type="password" name="pw" id="pw" size="20"><br>
			비밀번호 확인 : <input type="password" name="pw_check" id="pw_check" size="20"><br>
			이름 : <input type="text" name="name" id="name" size="20"><br>
			메일 : <input type="eMail" name="eMail" id="eMail" size="20"><br>
			주소 : <input type="text" name="address" id="address" size="50"><br><p>
			<input type="button" value="회원가입" onclick="form_check()">&nbsp;&nbsp;&nbsp;
			<input type="button" value="로그인" onclick="javascript:window.location='login.jsp'">
		</form>
	</div>
	-->	
</body>
</html>