<%@page import="com.study.jsp.MemberDAO"%>
<%@page import="com.study.jsp.MemberDTO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버추가</title>
</head>
<body>
	<%
		try{
			MemberDAO memberDAO = new MemberDAO();
			
			MemberDTO dto = new MemberDTO();
			
			dto.setId("abc");
			dto.setPw("123");
			dto.setName("홍길동");
			dto.setPhone("010-1234-5678");
			dto.setGender("man");
			int nResult = memberDAO.memberInsert(dto);
			
			dto.setId("def");
			dto.setPw("456");
			dto.setName("전우치");
			dto.setPhone("010-9012-3456");
			dto.setGender("man");
			nResult = memberDAO.memberInsert(dto);
			
			dto.setId("ghi");
			dto.setPw("789");
			dto.setName("부루마");
			dto.setPhone("010-7234-5678");
			dto.setGender("woman");
			nResult = memberDAO.memberInsert(dto);
			
			out.println("insert success");
		}catch(Exception e){
			
		}
	
	%>
	
	<br />
	<a href ="memberselect.jsp">회원정보 보기</a>
</body>
</html>