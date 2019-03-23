<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>구글 아이디로 로그인</title>
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
		window.location.replace("SnsCon?mId="+mId);
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

</head>
<body>

<div id="login" class="g-signin2" data-onsuccess="onSignIn"></div>


</div>



</body>
</html>