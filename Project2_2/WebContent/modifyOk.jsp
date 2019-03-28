<%@page import="com.study.jsp.dao.MemberDAO"%>
<%@page import="com.study.jsp.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="dto" class="com.study.jsp.dto.MemberDTO" />
<jsp:setProperty property="*" name="dto"/>

<%
	String id = (String)session.getAttribute("id");
	dto .setId(id);	
	MemberDAO dao = MemberDAO.getInstance();
	int ri = dao.updateMember(dto);
	String json_data = "";
	if (ri == 1) { // 정보가 수정되었습니다.
		json_data = "{\"code\":\"success\", \"desc\":\"정보가 수정되었습니다.\"}";
	} else { // 정보수정에 실패했습니다.
		json_data = "{\"code\":\"fail\", \"desc\":\"정보수정에 실패했습니다.\"}";
	}
	response.setCharacterEncoding("UTF-8");
	out.println(json_data);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>