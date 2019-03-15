<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <!-- Bootstrap CSS -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>리스트</title>
</head>
<body>
	
     <div class="container-fluid">
	 <table width="600" cellpadding="0" cellspacing="0" border="1">
	  	
		  <tr>		
			<td><h6>번호</h6></td>
			 <td><h6>이름></h6></td>
			<td><h6>제목</h6></td>
			<td><h6>날짜</h6></td>
			<td><h6>히트</h6></td>
		      </tr>
		     
		</table>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td><a href="content_view.do?bId=${dto.bId}&kind=view">${dto.bId}</a></td>
			<td>${dto.bName}</td>
			<td>
				<c:forEach begin="1" end="${dto.bIndent}"></c:forEach>
				<a href="content_view.do?bId=${dto.bId}&kind=view">${dto.bTitle}</a></td>
			<td>${dto.bDate}</td>
			<td>${dto.bHit}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> <a href="write_view.do"class="badge badge-primary">글작성</a> </td>
		</tr>
		<tr>
			<td colspan="5">
			<!-- 처음 -->
			<c:choose>
			<c:when test="${(page.curPage -1) < 1}">
				[ &lt;&lt; ]
			</c:when>
			<c:otherwise>
				<a href="list.do?page=1">[ &lt;&lt; ]</a>
			</c:otherwise>
			</c:choose>
			
			<!-- 이전 -->
			<c:choose>
			<c:when test="${(page.curPage -1) < 1}">
				[ &lt; ]
			</c:when>
			<c:otherwise>
				<a href="list.do?page=${page.curPage -1}">[ &lt; ]</a>
			</c:otherwise>
			</c:choose>
			
			<!-- 개별 페이지 -->
			<c:forEach	var="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
				<c:choose>
				<c:when test="${page.curPage == fEach}">
					[ ${fEach} ]
				</c:when>
				<c:otherwise>
					<a href="list.do?page=${fEach}">[ ${fEach} ]</a>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
			<c:when test="${(page.curPage +1) > page.totalPage}">
				[ &gt; ]
			</c:when>
			<c:otherwise>
				<a href="list.do?page=${page.curPage +1}">[ &gt; ]</a>
			</c:otherwise>
			</c:choose>
			<!-- 끝 -->
			<c:choose>
			<c:when test="${page.curPage == page.totalPage}">
				[ &gt;&gt; ]
			</c:when>
			<c:otherwise>
				<a href="list.do?page=${page.totalPage}">[ &gt;&gt; ]</a>
			</c:otherwise>
			</c:choose>
			</td>
		</tr>
		
	</table>
	</div>
	</div>
	
	
	<nav class="navbar navbar-light bg-light">
	<div class="container-fluid">
    <form class="form-inline">
    <input class="form-control mr-sm-2" type="search" placeholder="검색" aria-label="Search">
    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">검색</button>
   </form>
   </div>
   </nav>
	
	<div class="container-fluid">
	
	totalCount : ${page.totalCount}<br>
	listCount : ${page.listCount}<br>
	totalPage : ${page.totalPage}<br>
	curPage : ${page.curPage}<br>
	pageCount : ${page.pageCount}<br>
	startPage : ${page.startPage}<br>
	endPage : ${page.endPage}<br>
	</div>
<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>