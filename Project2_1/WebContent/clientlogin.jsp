<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <!-- Bootstrap CSS -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>로그인</title>
	<script >
	 function form_check(){
		 submit();		 
	 }
    
    function submit(){
    	document.login_form.submit();
    }
    
   </script>
</head>
<body>

<div class="alert alert-warning alert-dismissible fade show" role="alert">
  <strong>Welcome!</strong>  
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>

	<form method="post" name="login_form" action="client.jsp">
	 <div class="container">
	 <div class="form-group">
	 <label for="exampleId">아이디</label>
	 <input type="text" class="form-control" name="id"  placeholder="Enter id"
						value="<% if(session.getAttribute("id") != null)
									out.println(session.getAttribute("id"));
							%>"><br>
			</div>
		<div class="form-group">
	    <label for="examplePassword">비밀번호</label>	
		<input type="password" class="form-control" name="pw" placeholder="Enter passowrd"><br><p>
	    </div>
	    <div class="form-group">
		<input type="button" class="btn btn-primary" value="로그인" onclick="form_check();">&nbsp;&nbsp;&nbsp;
		<input type="button" class="btn btn-primary" value="회원가입">
		</div>
		</div>
	</form>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>