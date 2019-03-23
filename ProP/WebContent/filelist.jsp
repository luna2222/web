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
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>히트</td>
		</tr>
		<c:forEach items="${list }" var="dto">
		<tr>
			<td>${dto.fId }</td>
			<td>${dto.fName }</td>
			<td>
				<a href="filecontent_view.do?fId=${dto.fId }&kind=view">${dto.fTitle }</a></td>
			<td>${dto.fDate }</td>
			<td>${dto.fHit }</td>
		</tr>
		</c:forEach>
		
		<tr>
			<td colspan="3"><a href="upload_view.do">파일업로드</a></td>
			<td colspan="2"><a href="main.jsp">메인</a></td>
		</tr>
		
		<tr>
			<td colspan="5">
			<!-- 처음 -->
			<c:choose>
			<c:when test="${(fpage.curPage -1) < 1}">
				[ &lt;&lt; ]
			</c:when>
			<c:otherwise>
				<a href="filelist.do?page=1">[ &lt;&lt; ]</a>
			</c:otherwise>
			</c:choose>
			<!-- 이전 -->
			<c:choose>
			<c:when test="${(fpage.curPage -1) < 1}">
				[ &lt; ]
			</c:when>
			<c:otherwise>
				<a href="filelist.do?page=${fpage.curPage - 1}">[ &lt; ]</a>
			</c:otherwise>
			</c:choose>
			
			<!-- 개별 페이지 -->
			<c:forEach var="fEach" begin="${fpage.startPage}" end="${fpage.endPage}" step="1">
				<c:choose>
				<c:when test="${fpage.curPage == fEach}">
					[ ${fEach} ]&nbsp;
				</c:when>
				
				<c:otherwise>
					<a href="filelist.do?page=${fEach}">[ ${fEach} ]</a>&nbsp;
				</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
			<c:when test="${(fpage.curPage + 1) > fpage.totalPage}">
				[&gt;]
			</c:when>
			<c:otherwise>
				<a href="filelist.do?page=${fpage.curPage + 1}">[&gt;]</a>
			</c:otherwise>
			</c:choose>
			
			<!-- 끝 -->
			<c:choose>
			<c:when test="${fpage.curPage == fpage.totalPage}">
				[&gt;&gt;]
			</c:when>
			<c:otherwise>
				<a href="filelist.do?page=${fpage.totalPage}">[&gt;&gt;]</a>
			</c:otherwise>
			</c:choose>
		</tr>
	</table>

	totalCount : ${fpage.totalCount}<br>
	listCount : ${fpage.listCount}<br>
	totalPage : ${fpage.totalPage}<br>
	curPage : ${fpage.curPage}<br>
	pageCount : ${fpage.pageCount}<br>
	startPage : ${fpage.startPage}<br>
	endPage : ${fpage.endPage}<br>
	
</body>
</html>