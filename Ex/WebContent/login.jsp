<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% if(session.getAttribute("ValidMem") != null) {%>
	<jsp:forward page="main.jsp" />
<% } %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <script src="http://code.jquery.com/jquery.js"></script>  
  <script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="114278354697-r9lk9ii6vpcf7669a0emug5sqroqqk7u.apps.googleusercontent.com">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>로그인</title>
     <script>
function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
  console.log('Name: ' + profile.getName());
  console.log('Image URL: ' + profile.getImageUrl());
  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
 

	$('#login').css('display', 'none');
	$('#logout').css('display', 'block');
	$('#upic').attr('src', profile.getImageUrl());
	$('#uname').html('[ ' +profile.getName() + ' ]');
 
}
function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');

  	$('#login').css('display', 'block');
  	$('#logout').css('display', 'none');
  	$('#upic').attr('src', '');
  	$('#uname').html('');    
    });
  }
  
  </script>
	<script src="http://code.jquery.com./jquery.js"></script>
	<script type="text/javascript">
	
	function submit_ajax() {
		var queryString = $("#login_frm").serialize();
		$.ajax({
			//url: 'loginOk.jsp',
			url: 'LoginProcess',
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
</head>
<body>
     <!-- 구글 -->
    <div class="container">
	<div id="login" class="g-signin2" data-onsuccess="onSignIn"></div>

<div id="logout" style="display: none;">
    <input type="button" onclick="signOut();" value="로그아웃" /><br>

    <img id="upic" src=""><br>
    <span id="uname"></span>
 </div>
</div>

	<form method="post" name="login_frm" id="login_frm">
	  <div class="container">
         <div class="form-group">
         <label for="exampleId">아이디</label>
		   <input type="text" class="form-control" name="id" id="id" placeholder="Enter id" size="15"
						value="<%if(session.getAttribute("id")!= null)
									out.println(session.getAttribute("id"));
							%>"><br>
			</div>
			
			<div class="form-group">
			<label for="examplepassward">비밀번호</label>
			<input type="password" class="form-control" name="pw" id="pw" placeholder="Enter passowrd"size="15"><br><p>
			</div>
		<input type="button" class="btn btn-primary" value="로그인" onclick="submit_ajax()">&nbsp;&nbsp;
		<input type="button" class="btn btn-primary" value="회원가입" onclick="jsavascript:window.location='join.jsp'">
		</div>
		
	</form>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>