<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("ValidMem") != null){ %>
 	<jsp:forward page="main.jsp"></jsp:forward>
<% } %>
<script src="naveridlogin_js_sdk_2.0.0.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
    <title>Member logIn</title>
	
	<script>
	function form_check(){
		if($('#mid').val().length == 0){
			alert("아이디를 입력하세요.");
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
		
		submit_ajax();
	}
	function submit_ajax(){
		var queryString = $("#login_form").serialize();		
		$.ajax({
			//url:'/Jsp212/joinOk.jsp',
			url:'/Project02/login.do',
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
					alert(result.desc)
					window.location.replace("login.jsp");
				}
			}
		});
	}
	</script>
	
	
	<!-- 구글 -->
	<script src="http://code.jquery.com/jquery.js"></script>	
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<meta name="google-signin-client_id" content="100588982777-89g7j4695h2nc5q5cbuibmjpbsha7o36.apps.googleusercontent.com">	
	<script>
	function onSignIn(googleUser) {
		var profile = googleUser.getBasicProfile();
		console.log('ID: ' + profile.getId()); 
		console.log('Name: ' + profile.getName());
		console.log('Image URL: ' + profile.getImageUrl());
		console.log('Email: ' + profile.getEmail()); 
		var mId = profile.getId();
		if(mId != null){
			window.location.replace("SnsCon?mId="+mId);
		}else{
			alert("로그인 실패");
		}
		signOut();
	}
	function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	     	console.log('User signed out.');  
	    });
	    auth2.disconnect();
	}	
	</script>
	
	
	<!-- 페이스북 -->
	
	<script src="http://code.jquery.com/jquery.js"></script>

	<script>
	  window.fbAsyncInit = function() {
	    FB.init({
	      appId      : '560975147728224',
	      cookie     : true,
	      xfbml      : true,
	      version    : 'v3.2'
	    });
	
	    FB.getLoginStatus(function(response) {
	    	console.log(response);
	      statusChangeCallback(response);
	    });
	  };
	
	  // Load the SDK asynchronously
	  (function(d, s, id) {
	    var js, fjs = d.getElementsByTagName(s)[0];
	    if (d.getElementById(id)) return;
	    js = d.createElement(s); js.id = id;
	    js.src = "https://connect.facebook.net/en_US/sdk.js";
	    fjs.parentNode.insertBefore(js, fjs);
	  }(document, 'script', 'facebook-jssdk'));
	
	  function statusChangeCallback(response) {
	    if (response.status === 'connected') {
	      getINFO();
	    } else {
	      $('#login').css('display', 'block');
	      $('#logout').css('display', 'none');
	 
	    }
	  }
		  
	  function fbLogin () {
	    FB.login(function(response){
	      statusChangeCallback(response);
	    }, {scope: 'public_profile, email'});
	  }
	
	  function fbLogout () {
	    FB.logout(function(response) {
	      statusChangeCallback(response);
	    });
	  }
	
	  function getINFO() {
	    FB.api('/me?fields=id,name,picture.width(100).height(100).as(picture_small)', function(response) {
	      console.log(response);
	      
	      $('#login').css('display', 'none');
	      $('#logout').css('display', 'block');
	      var mId = response.id;
	      console.log(mId);
	      window.location.replace("SnsCon?mId="+mId);
	      fbLogout();
	    });
	  }
	
	</script>
	
	
	
		
  </head>
  <body>
    <form id="login_form" name="login_form" action="login.do" method="post">
		아이디 : <input type="text" name="mid" id="mid"
						value="<% if(session.getAttribute("mId") != null)
									out.println(session.getAttribute("mId"));
							%>"><br>
		비밀번호 : <input type="password" name="mpw" id="mpw"><br><p>
		<input type="submit" value="로그인1" >&nbsp;&nbsp;
		<input type="button" value="로그인2" onclick="form_check();">&nbsp;&nbsp;
		<input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'">
			
	</form>
	<!-- 구글 -->
	<div id="login" class="g-signin2" data-onsuccess="onSignIn">	</div>
	
	<!-- 페이스북 -->
	<div id="login" style="display: block;">
    	<input type="button" onclick="fbLogin();" value="페이스북로그인" /><br>
	</div>

	<div id="logout" style="display: none;">
   		<input type="button" onclick="fbLogout();" value="로그아웃" /><br>
	</div>
	
	
	<!-- 카카오 -->
	<div id="login" style="display: block">
   		<a id="custom-login-btn" href="javascript:loginWithKakao()">
   		<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
    	</a>
	</div>
	
	<div id="logout" style="display: none;">
	    <input type="button" class="btn btn-success" onclick="signOut();" value="로그아웃" /><br>
	</div>
	
		
	<script type='text/javascript'>
	    Kakao.init('7bfa4c1c9f2b22e77c3f8d3234e8c8a8');
	    function loginWithKakao() {
	      // 로그인 창을 띄웁니다.
	      Kakao.Auth.login({
	        success: function(authObj) {
	          //alert(JSON.stringify(authObj));
	          signIn(authObj);
	        },
	        fail: function(err) {
	          alert(JSON.stringify(err));
	        }
	      });
	    };
	
	    function signIn(authObj) {
	        //console.log(authObj);
	        Kakao.API.request({
	            url: '/v2/user/me',
	            success: function(res) {
	                //console.log(res);
	                console.log(res.id);
	                var mId = res.id;
	                $('#login').css('display', 'none');
	               	$('#logout').css('display', 'block');
	               	window.location.replace("SnsCon?mId="+mId);
	               	signOut();
	             }
	         })
		}
	
	    function signOut() {
		    Kakao.Auth.logout(function () {
		    	$('#login').css('display', 'block');
		    	$('#logout').css('display', 'none');
		    });
		} 
	</script>
	
	
	
	<!-- 네이버아이디로로그인 버튼 노출 영역 -->
	<div id="naverIdLogin"></div>
	<!-- //네이버아이디로로그인 버튼 노출 영역 -->

	<script type="text/javascript">
	var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "5YyvEgGrov8XSYaxDz7G",
			callbackUrl: "http://localhost:8081/Project02/callback.jsp",
			isPopup: false, /* 팝업을 통한 연동처리 여부 */
			
			loginButton: {color: "green", type: 3, height: 60} /* 로그인 버튼의 타입을 지정 */
			
		}
	);
	
   /* 설정정보를 초기화하고 연동을 준비 */
	naverLogin.init();
	</script>
	
	<!-- 카카오 -->
	
	
	
  </body>
</html>