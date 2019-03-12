<%@page import="com.study.jsp.MemberDTO"%>
<%@page import="com.study.jsp.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% 
	String id = (String)session.getAttribute("id");
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = dao.getMember(id);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>정보수정</title>
	<script src="http://code.jquery.com./jquery.js"></script>
	<script type="text/javascript">
	
	function form_check() 
	{
		
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
				
		if($('#eMail').val().length == 0){
			alert("메일은 필수사항입니다.");
			$('#eMail').focus();
			return;
		}
		
		submit_ajax();
	}
	
	function submit_ajax() {
		var queryString = $("#modify_frm").serialize();
		$.ajax({
			url: 'modifyOk.do',
			//url: 'ModifyProcess',
			type: 'POST',
			data: queryString,
			dataType: 'text',
			success: function(json) {
				//consle.log(json);
				var result = JSON.parse(json);
				if(result.code == "success") {
					alert(result.desc)
					window.location.replace("main.jsp");
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

<form id="modify_frm" method="post">
    <div id="container">
        <div id="content">
            <div class="join_content">
            <div><h1 style="text-align:center; margin-top:30px; font-weight:'bold'">회 원 가 입</h1></div>
                <div class="row_group">
                	<!-- 아이디 -->
                    <div class="join_row">
                        <h3 class="join_title"><label for="id">아이디</label></h3>
                        <span class="ps_box int_id">
							<span class="int"><%=dto.getId() %></span>
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
                    </div>
                </div>
                <!-- // 아이디, 비밀번호 입력 -->

               <!-- 이름 -->
                <div class="row_group">
                    <!-- lang = ko_KR -->
                    <div class="join_row">
                        <h3 class="join_title"><label for="name">이름</label></h3>
                        <span class="ps_box box_right_space">
							<span class="int"><%=dto.getName() %></span>
						</span>
                    </div>
					
				<!-- 이메일 -->
                    <div class="join_row join_email">
                        <h3 class="join_title"><label for="email">본인 확인 이메일<span class="terms_choice">(필수)</span></label></h3>
                        <span class="ps_box int_email box_right_space">
							<input type="text" id="eMail" name="eMail" maxlength="100" placeholder="필수" aria-label="필수" 
							value="<%=dto.geteMail()%>" class="int">
						</span>
                    </div>
                    
                 <!-- 주소 -->
                    <div class="join_row join_address">
                        <h3 class="join_title"><label for="address">주소<span class="terms_choice">(선택)</span></label></h3>
                        <span class="ps_box int_adress box_right_space">
							<input type="text" id="address" name="address" maxlength="100" placeholder="선택입력" aria-label="선택입력" class="int" value="<%=dto.getAdress()%>">
						</span>
                    </div>     

                </div>
                <div class="btn_area">
                    <button type="button" id="btnJoin" class="btn_type btn_primary" onclick="form_check() "><span>회원 정보 수정</span></button>
                </div>
            </div>
        </div>
    </div>
    <!-- // container -->
</form>

	<!--  
	<form action="modifyOk.jsp" method="post" name="reg_frm" id="reg_frm">
		아이디 : <%=dto.getId() %><br> 
		비밀번호 : <input type="password" name="pw" id="pw" size="20"><br>
		비밀번호 확인 : <input type="password" name="pw_check" id="pw_check" size="20"><br>
		이름 : <%=dto.getName() %><br>
		메일 : <input type="eMail" name="eMail" id="eMail" size="20" value="<%=dto.geteMail()%>"><br>
		주소 : <input type="text" name="address" id="address" size="50" value="<%=dto.getAdress()%>"><br><p>
		<input type="button" value="수정" onclick="form_check()">&nbsp;&nbsp;&nbsp;
		<input type="reset" value="취소" onclick="javascript:window.location='main.jsp'">
	</form>
	-->
</body>
</html>