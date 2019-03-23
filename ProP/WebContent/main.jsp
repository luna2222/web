<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
</head>
<body>
	<h1>로그인 성공 메인페이지</h1>
	
		<form name="secession_form" action="deleteMember.do" method="post">
		아이디 : <input type="text" name="mid" id="mid"
						value="<% if(session.getAttribute("mId") != null)
									out.println(session.getAttribute("mId"));
							%>"><br>
		<input type="button" value="회원탈퇴" onclick="javascript:window.location='secession.jsp'">&nbsp;&nbsp;
		<input type="button" value="로그인" onclick="javascript:window.location='login.jsp'">
		<input type="button" value="로그아웃" onclick="javascript:window.location='logout.jsp'">
		<input type="button" value="공지사항" onclick=<%session.setAttribute("bCategory", 0);%>"javascript:window.location='list.do?bCategory=0'">
		<input type="button" value="자유게시판" onclick=<%session.setAttribute("bCategory", 1);%>"javascript:window.location='list.do?bCategory=1'">
		<input type="button" value="oo게시판" onclick=<%session.setAttribute("bCategory", 2);%>"javascript:window.location='list.do?bCategory=2'">
		<input type="button" value="채팅" onclick="javascript:window.location='chatlogin.jsp'">
		</form>
		
		<input type="button" value="자료실" onclick="javascript:window.location='filelist.do'">
		<br><br>
		<form action="search.do" method="post">
		
			<select name="column">
				<option value="bName" >이름</option>
				<option value="bContent" >내용</option>
				<option value="bTitle" >제목</option>
			</select>
			<input type="text" name="word" value="" placeholder="특수문자는 금지.">
			<input type="submit" value="검색">
			
		</form>
</body>
</html>