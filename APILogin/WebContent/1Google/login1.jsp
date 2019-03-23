<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="http://code.jquery.com/jquery.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="114278354697-r9lk9ii6vpcf7669a0emug5sqroqqk7u.apps.googleusercontent.com">
<title>구글 로그인</title>
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
</head>
<body>
<div id="login" class="g-signin2" data-onsuccess="onSignIn"></div>

<div id="logout" style="display: none;">
    <input type="button" onclick="signOut();" value="로그아웃" /><br>

    <img id="upic" src=""><br>
    <span id="uname"></span>
</div>



</body>
</html>