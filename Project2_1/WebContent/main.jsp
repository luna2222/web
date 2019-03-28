<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	if(session.getAttribute("ValidMem")==null){
%>
		<jsp.forward page="login.jsp" />
<%
	}
	
	String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="theme-color" content="#563d7c">
<!-- Bootstrap CSS -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>main</title>
</head>
<body>
<div class="alert alert-warning alert-dismissible fade show" role="alert">
  <strong>Welcome!</strong>  <h3><%= name %>님 안녕하세요!</h3> <br>
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
        
        
	 
	 <form action="logout.jsp" method="post">
		<input type="submit" value="로그아웃">&nbsp;&nbsp;&nbsp;
		<input type="button" value="정보수정"
			   onclick="javascript:window.location='modify.jsp'">&nbsp;&nbsp;&nbsp;
		<input type="button" value="회원탈퇴" 
			   onclick="javascript:window.location='(./DeleteProcess/)'">&nbsp;&nbsp;
			   
	    <input type="button" value="게시판" onclick="javascript:window.location='list.jsp'">&nbsp;&nbsp;
	    <input type="button" value="채팅창" onclick="javascript:window.location='client.jsp'"><hr>
           <tr>
				<td> 게시판 구분 </td>
				<td> <select name="bCategory"> 
				    <option value="1" >공지사항</option>
				    <option value="2" >자유게시판</option>
				    <option value="3" >자료실</option>
				</select></td>
			</tr>
	
	</div>
<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>		
</body>
</html>