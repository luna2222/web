<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<script>
		function form_check(){
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			
			document.modify_form.submit();
		}
	</script>
</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="nctmodify.do" name="modify_form" method="post">
			<input type="hidden" name="bId" value="${content_view.bId }">
			<input type="hidden" name="kind" value="modify">
			<input type="hidden" name="bCategory" value=<%=session.getAttribute("bCategory")%>>
			<tr>
				<td>카테고리</td>
				<td>${content_view.bCategory }</td>
			</tr>
			<tr>
				<td>번호</td>
				<td>${content_view.bId }</td>
			</tr>
			<tr>
				<td>히트</td>
				<td>${content_view.bHit }</td>
			</tr>	
			<tr>
				<td>이름</td>
				<td>${content_view.bName}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" value="${content_view.bTitle}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea name="bContent" id="ir1" rows="10" cols="100">${content_view.bContent}</textarea>
					<script type="text/javascript">
						var oEditors = [];
						nhn.husky.EZCreator.createInIFrame({
						    oAppRef: oEditors,
						    elPlaceHolder: "ir1",
						    sSkinURI: "naver-editor/SmartEditor2Skin.html",
						    fCreator: "createSEditor2"
						});
					</script>
				</td>
			</tr>
			<tr>
				<td colspan="2">
	
				<a href="JavaScript:form_check();">수정완료</a>&nbsp;&nbsp;
				
				<a href="search.do?page=<%=session.getAttribute("cpage")%>&column=<%=session.getAttribute("column")%>&word=<%=session.getAttribute("word")%>">목록보기</a>&nbsp;&nbsp;
				</td>
			</tr>
		</form>
	</table>


</body>
</html>