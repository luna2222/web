<%@page import="project.jsp.*" %>
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

	<table width="600" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>카테고리</td>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>히트</td>
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.bCategory}</td>
			<td>${dto.bId}</td>
			<td>${dto.bName}</td>
			<td>
				<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
				<a href="nctcontent_view.do?bId=${dto.bId}&kind=view">${dto.bTitle }</a></td>
			<td>${dto.bDate }</td>
			<td>${dto.bHit }</td>
		</tr>
		</c:forEach>
		
		<tr>
			<!-- <td colspan="3"><a href="write_view.do?column=bContent&word=<%=session.getAttribute("bContent")%>">글작성</a></td> -->
			<td colspan="3"><a href="main.jsp">메인</a></td>
		</tr>
		
		<tr>
			<td colspan="6">
			<!-- 처음 -->
			<c:choose>
			<c:when test="${(page.curPage -1) < 1}">
				[ &lt;&lt; ]
			</c:when>
			<c:otherwise>
				<a href="search.do?page=1&column=bContent&word=${page.boardContent}">[ &lt;&lt; ]</a>
			</c:otherwise>
			</c:choose>
			<!-- 이전 -->
			<c:choose>
			<c:when test="${(page.curPage -1) < 1}">
				[ &lt; ]
			</c:when>
			<c:otherwise>
				<a href="search.do?page=${page.curPage - 1}&column=bContent&word=${page.boardContent}">[ &lt; ]</a>
			</c:otherwise>
			</c:choose>
			
			<!-- 개별 페이지 -->
			<c:forEach var="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
				<c:choose>
				<c:when test="${page.curPage == fEach}">
					[ ${fEach} ]&nbsp;
				</c:when>
				
				<c:otherwise>
					<a href="search.do?page=${fEach}&column=bContent&word=${page.boardContent}">[ ${fEach} ]</a>&nbsp;
				</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
			<c:when test="${(page.curPage + 1) > page.totalPage}">
				[&gt;]
			</c:when>
			<c:otherwise>
				<a href="search.do?page=${page.curPage + 1}&column=bContent&word=${page.boardContent}">[&gt;]</a>
			</c:otherwise>
			</c:choose>
			
			<!-- 끝 -->
			<c:choose>
			<c:when test="${page.curPage == page.totalPage}">
				[&gt;&gt;]
			</c:when>
			<c:otherwise>
				<a href="search.do?page=${page.totalPage}&column=bContent&word=${page.boardContent}">[&gt;&gt;]</a>
			</c:otherwise>
			</c:choose>
		</tr>
	</table>
	
	boardCategory : ${page.boardCategory}<br>
	boardName : ${page.boardName}<br>
	boardTitle : ${page.boardTitle}<br>
	boardContent : ${page.boardContent}<br>
	totalCount : ${page.totalCount}<br>
	listCount : ${page.listCount}<br>
	totalPage : ${page.totalPage}<br>
	curPage : ${page.curPage}<br>
	pageCount : ${page.pageCount}<br>
	startPage : ${page.startPage}<br>
	endPage : ${page.endPage}<br>
	
</body>
</html>