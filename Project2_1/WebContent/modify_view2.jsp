<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!String bName;
   int bCategory;%>
<% bName =(String)session.getAttribute("name");
   if (session.getAttribute("VaildMem")==null) {
 %>
 <jsp:forward page="logout.do"></jsp:forward>  
 <%
   }
   session.removeAttribute("modify");
   bCategory =(int)session.getAttribute("bCategory");
  %> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

 <!-- Bootstrap CSS -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Insert title here</title>
<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	    function form_check() {
		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		document.modify_form.submit();
	}
</script>
</head>
<body>
	
	<table width="800" cellpadding="0" cellspacing="0" border="1">
		<form action="modify.do" method="post" name="modify_form">
			<input type="hidden" name="bId" value="${content_view.bId}">
			<input type="hidden" name="kind" value="modify">
			<input type="hidden" name="bCategory" value=<%=session.getAttribute("bCategory")%>>
			<tr>
				<td>카테고리</td>
				<td>${content_view.bCategory }</td>
			</tr>
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
					<textarea name="bContent" id="ir1" rows="10" cols="100">${content_view.bContent}</textarea>
					<script type="text/javascript">
						var oEditors = [];
						    nhn.husky.EZCreator.createInIFrame({
    						oAppRef: oEditors,
    						elPlaceHolder: "ir1",
    						sSkinURI: "./naver-editor/SmartEditor2Skin.html",
    						fCreator: "createSEditor2"
						});
					</script>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				    <input type="submit"  value="수정완료">
					<a href="JavaScript:form_check();"button type="button" class="btn btn-outline-primary">수정완료</a> &nbsp;&nbsp;
					<a href="content_view.do?bId=${content_view.bId}&kind=modify" method="post"button type="button" class="btn btn-outline-success">취소</a> &nbsp;&nbsp;
					<a href="list.do?page=<%=session.getAttribute("curpage")%>&bCategory=<%=session.getAttribute("bCategory")%>"button type="button" class="btn btn-outline-info">목록보기</a> &nbsp;&nbsp;
				</td>
			</tr>
		</form>
	</table>
	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>