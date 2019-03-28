 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% if(session.getAttribute("ValidMem") != null) {%>
	<jsp:forward page="main.jsp" />
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 뷰포트 -->
<meta name="viewport" content="width=device-width" initial-scale="1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- 스타일시트 참조  -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>jsp 게시판 웹사이트</title>
	<script src="http://code.jquery.com./jquery.js"></script>
<script type="text/javascript">
	
	function submit_ajax() {
		var queryString = $("#login_frm").serialize();
		$.ajax({
			url : 'loginOk.jsp',
			type : 'POST',
			data : queryString,
			dataType : 'text',
			success : function(json) {
				//consle.log(json);
				var result = JSON.parse(json);
				if (result.code == "success") {
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
 <!-- 네비게이션  -->
   <nav class="navbar navbar-default">
     <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" 
       data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
        aria-expaned="false">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
     </button>
    </div>
  
  <div class="collapse navbar-collapse" id="#bs-example-navbar-collapse-1"></div>  	
    <div id="container">
    	<ul class="nav navbar-nav">
    		<li ><a href="main.jsp">Home</a></li>
    		<li ><a href="list.jsp">게시판</a></li>
   	   </ul>
      <input type="button" value="회원가입"  class="btn btn-primary" style="float: right">
   </div> 
  </div>
 </nav>

 <!-- 로긴폼 -->

 <div class="container">
  <div class="col-lg-4"></div>
  <div class="col-lg-4">

  <!-- 점보트론 -->
   <div class="jumbotron" style="padding-top: 20px;">

   <!-- 로그인 정보를 숨기면서 전송post -->

   <form id="login_frm" method="POST" action="loginOk.jsp">

    <h3 style="text-align: center;"> 로그인화면 </h3>
    <div class="form-group">
    <input type="text" class="form-control" placeholder="아이디" name="id" maxlength="20"
    value="<%if(session.getAttribute("id")!= null)
			out.println(session.getAttribute("id"));
		    %>">
    </div>
    <div class="form-group">

     <input type="password" class="form-control" placeholder="비밀번호" name="pw" maxlength="20">

    </div>

    <input type="button" class="btn btn-primary form-control" value="로그인" onclick="submit_ajax()"><hr>
    
    
   
   <button type="button" class="btn btn-outline-success" maxlength="20">Naver 로그인</button>&nbsp;&nbsp;&nbsp;&nbsp; 
   <button type="button" class="btn btn-outline-warning" maxlength="20">Kakao로그인</button>&nbsp;&nbsp;&nbsp;&nbsp;
   <button type="button" class="btn btn-outline-primary" maxlength="20">Google로그인</button>&nbsp;&nbsp;&nbsp;&nbsp;
   <button type="button" class="btn btn-outline-secondary" maxlength="20">Facebook로그인</button>
   </form>

  </div>

 </div>

</div>

 <!-- 부트스트랩 JS  -->
 <script src="js/bootstrap.js"></script>
<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>









