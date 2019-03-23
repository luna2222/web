<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">

	<tr>
		<td>번호</td>
		<td>${filecontent_view.fId }</td>
	</tr>
	<tr>
		<td>히트</td>
		<td>${filecontent_view.fHit }</td>
	</tr>	
	<tr>
		<td>이름</td>
		<td>${filecontent_view.fName }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${filecontent_view.fTitle }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			${filecontent_view.fContent }
		</td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td>
			<a href="download?fileName=${filecontent_view.fileName }">${filecontent_view.fileName }</a>
		</td>
	</tr>
	<tr>
		<td colspan="2">		
			<a href="filelist.do?page=<%=session.getAttribute("cpage")%>">목록보기</a>&nbsp;&nbsp;,
			<a href="filedelete.do?fId=${filecontent_view.fId}">삭제</a>&nbsp;&nbsp;
		</td>		
	</tr>
	
	
	</table>


</body>
</html>