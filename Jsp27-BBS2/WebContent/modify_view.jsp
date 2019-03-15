<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
</head>
<body>
	
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="modify.do" method="post">
			<input type="hidden" name="bId" value="${content_view.bId}">
			<input type="hidden" name="kind" value="modify">
			<tr>
				<td> 번호 </td>
				<td>${content_view.bId}</td>
			</tr>
			<tr>
				<td> 히트 </td>
				<td>${content_view.bHit}</td>
			</tr>
			<tr>
				<td> 이름 </td>
				<td> <input type="text" name="bName" value="${content_view.bName}"> </td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td><input type="text" name="bTitle" value="${content_view.bTitle}"></td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td>
					<textarea rows="10" name="bContent">${content_view.bContent}</textarea>
					 <textarea name="ir1" id="ir1" rows="10" cols="100"></textarea>
		<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "ir1",
    sSkinURI: "./naver-editor/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="수정"> &nbsp;&nbsp;
					<a href="content_view.do?bId=${content_view.bId}&kind=modify">취소</a> &nbsp;&nbsp;
					<a href="list.do?page=<%= session.getAttribute("cpage")%>"> 목록보기</a> &nbsp;&nbsp;
				</td>
			</tr>
		</form>
	</table>
	
</body>
</html>