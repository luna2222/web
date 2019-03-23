<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	
<title>Insert title here</title>
	
	
	<table width="500px" cellpadding="5" cellspacing="5" border="5">
			<form action="upload.jsp"  method="post" enctype="multipart/form-data">
			
				<tr>
					<td> 회원아이디=bName </td>
					<td><% if(session.getAttribute("mId") != null)
									out.println(session.getAttribute("mId"));%></td>			
				</tr>
				<tr>
					<td> 제목 </td>
					<td> <input type="text" name="fTitle" size="50"></td>
				</tr>
				<tr>
					<td> 내용 </td>
					<td> 
					<input type="text" name="fContent" >
					</td>
				</tr>
				<tr>
					<td> 파일첨부 </td>
					<td> <input type="file" name="file"></td>
				</tr>
				<tr>
					<td colspan="2"> 
					<input type="submit" value="업로드">
						
					<a class="btn btn-outline-dark" href="filelist.do" role="button">목록보기</a>	
						
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