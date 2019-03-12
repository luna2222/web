<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% if(session.getAttribute("ValidMem") != null) {%>
	<jsp:forward page="main.jsp" />
<% } %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
	<script src="http://code.jquery.com./jquery.js"></script>
	<script type="text/javascript">
	function submit_ajax() {
		var queryString = $("#login_frm").serialize();
		$.ajax({
			url: 'loginOk.do',
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
	<form id="login_frm" method="post">
    <div id="container">
        <div id="content">
            <div class="join_content">
            <div><h1 style="text-align:center; margin-top:30px; font-weight:'bold'">로 그 인</h1></div>
                <div class="row_group">
                	<!-- 아이디 -->
                    <div class="join_row">
                        <h3 class="join_title"><label for="id">아이디</label></h3>
                        <span class="ps_box int_id">
							<input type="text" id="id" name="id" class="int" title="ID" maxlength="20" 
							value="<%if(session.getAttribute("id")!= null)
									out.println(session.getAttribute("id"));
							%>">
						</span>
                    </div>
					<!-- 비밀번호 -->
                    <div class="join_row">
                        <h3 class="join_title"><label for="pw">비밀번호</label></h3>
                         <span class="ps_box box_right_space">
							<input type="password" id="pw" name="pw" class="int" title="비밀번호 입력" aria-describedby="pwMsg" maxlength="20">
                            <span class="lbl"><span id="pwSpan" class="step_txt"></span></span>
						</span>
                    </div>
                </div>  
                <div class="btn_area">
                    <button type="button" id="btnJoin" class="btn_type btn_primary" onclick="submit_ajax()"><span>로그인하기</span></button>
                    <input class="btn_type btn_primary" type="button" value="회원가입" onclick="jsavascript:window.location='join.jsp'">
                </div>
            </div>
        </div>
    </div>
    <!-- // container -->
</form>

	
	<!-- 
	<form method="post" name="login_frm" id="login_frm">
		아이디 : <input type="text" name="id" id="id" size="20"
						value="<%if(session.getAttribute("id")!= null)
									out.println(session.getAttribute("id"));
							%>"><br>
		비밀번호 : 	<input type="password" name="pw" id="pw" size="20"><br><p>
	
		<input type="button" value="로그인" onclick="submit_ajax()">&nbsp;&nbsp;
		<input type="button" value="회원가입" onclick="jsavascript:window.location='join.jsp'">
	</form>
	 -->
</body>
</html>