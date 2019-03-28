<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Insert title here</title>
<script src="./ckeditor/ckeditor.js"></script>
</head>
<body>
	
	<table	width="600" cellpadding="5" cellspacing="5" border="5">
		<form action="write.do" method="get">
		<table class="table">
        
        
           <tr>
				<td> 게시판 구분 </td>
				<td> <select name="bCategory"> 
				    <option value="1">공지사항</option>
				    <option value="2">자유게시판</option>
				    <option value="3">자료실</option>
				</select></td>
			</tr>
        
			<tr>
				<td> 이름 </td>
				<td> <input type="text" name="bName" size="50"> </td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="bTitle" size="50"> </td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> 
					<textarea name="bContent" id="editor1" rows="10" cols="80"></textarea>
					 <script>
             
                		CKEDITOR.replace( 'editor1' );
            		 </script>
				 
				</td>
				
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="입력"> &nbsp;&nbsp;
					<a href="list.do?bCategory=1">1공지사항</a>
					<a href="list.do?bCategory=2">2자유게시판</a>
					<a href="list.do?bCategory=3">3자료실</a>
				</td>
			</tr>
		</form>	
	</table>
	
	<form>
  <div class="form-group">
    <label for="exampleFormControlFile1">파일 보내기</label>
    <input type="file" class="form-control-file" id="exampleFormControlFile1">
  </div>
  </form>
<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>	
</body>
</html>