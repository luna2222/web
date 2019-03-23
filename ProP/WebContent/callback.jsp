<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	loading...

	<!-- (1) LoginWithNaverId Javscript SDK -->
<script src="naveridlogin_js_sdk_2.0.0.js"></script>
	<!-- (2) LoginWithNaverId Javscript 설정 정보 및 초기화 -->
	<script>
		var naverLogin = new naver.LoginWithNaverId(
			{
				clientId: "5YyvEgGrov8XSYaxDz7G",
				callbackUrl: "http://localhost:8081/Project02/callback.jsp",
				isPopup: false,
				callbackHandle: true
				/* callback 페이지가 분리되었을 경우에 callback 페이지에서는 callback처리를 해줄수 있도록 설정합니다. */
			}
		);

		/* (3) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
		naverLogin.init();

		/* (4) Callback의 처리. 정상적으로 Callback 처리가 완료될 경우 main page로 redirect(또는 Popup close) */
		window.addEventListener('load', function () {
			naverLogin.getLoginStatus(function (status) {
				if (status) {
					/* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
					var mId = naverLogin.user.getId();
					

					window.location.replace("http://localhost:8081/Project02/SnsCon?mId="+mId);
				} else {
					console.log("callback 처리에 실패하였습니다.");
				}
			});
		});
	</script>
	
</body>
</html>