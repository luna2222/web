<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="./dist/summernote.css" rel="stylesheet">
  <script src="./dist/summernote.js"></script>
</head>
<body>
	
	<table width="600" cellpadding="0" cellspacing="0" border="1">
		<form action="reply.do" method="post">
			<input type="hidden" name="bId" value="${reply_view.bId}">
			<input type="hidden" name="bGroup" value="${reply_view.bGroup}">
			<input type="hidden" name="bStep" value="${reply_view.bStep}">
			<input type="hidden" name="bIndent" value="${reply_view.bIndent}">
			<tr>
				<td> 번호 </td>
				<td>${reply_view.bId}</td>
			</tr>
			<tr>
				<td> 히트 </td>
				<td>${reply_view.bHit}</td>
			</tr>
			<tr>
				<td> 이름 </td>
				<td> <input type="text" name="bName" value="${reply_view.bName}"> </td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td><input type="text" name="bTitle" value="${reply_view.bTitle}"></td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td>
					<textarea rows="10" name="bContent" id="summernote">${reply_view.bContent}</textarea>
					  <script>
    					$(document).ready(function() {
        					$('#summernote').summernote();
    					});
  					  </script>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="답변"> &nbsp;&nbsp;
					<a href="list.do?page=<%=session.getAttribute("cpage")%>">목록</a> 
				</td>
			</tr>
		</form>
	</table>
	
</body>
</html>