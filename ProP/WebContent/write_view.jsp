<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	
<title>Insert title here</title>
<script type="text/javascript" src="./naver-editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	
	<script>
		function form_check(){
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			
			document.write_form.submit();
		}
	</script>
	
	<style>
		

	</style>
</head>
<body>
		
		<table width="950px" cellpadding="5" cellspacing="5" border="5">
			<form action="write.do" name="write_form" method="post">
			
				<tr>
					<td> 카테고리 </td>
					<td>
						<select name="bCategory">
							<option value = "0">공지사항</option>
							<option value = "1">자유게시판</option>
							<option value = "2">etc게시판</option>
						</select>
					</td>
				</tr>
				<tr>
					<td> 회원아이디=bName </td>
					<td><% if(session.getAttribute("mId") != null)
									out.println(session.getAttribute("mId"));%></td>			
				</tr>
				<tr>
					<td> 제목 </td>
					<td> <input type="text" name="bTitle" size="50"></td>
				</tr>
				<tr>
					<td> 내용 </td>
					<td> 
					<textarea name="bContent" id="ir1" rows="10" cols="100"></textarea>
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
					<button type="button" class="btn btn-outline-dark" onclick="JavaScript:form_check();">저장</button>
						<!-- <a href="JavaScript:form_check();">저장</a>&nbsp;&nbsp; -->
					<a class="btn btn-outline-dark" href="list.do?bCategory=<%=session.getAttribute("bCategory")%>" role="button">목록보기</a>	
						
						<!-- <a href="list.do">목록보기</a> -->
					</td>	
				</tr>
			</form>
		</table>
		
<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
  </body>
</html>