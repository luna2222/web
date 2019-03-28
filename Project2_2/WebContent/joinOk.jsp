<%@page import="com.study.jsp.dao.MemberDAO"%>

<%@page import="com.study.jsp.*"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="com.study.jsp.dto.MemberDTO" />
<jsp:setProperty property="*" name="dto"/>
<%
	dto.setrDate(new Timestamp(System.currentTimeMillis()));
	MemberDAO dao = MemberDAO.getInstance();
	String json_data = "";
	if(dao.confirmId(dto.getId()) == MemberDAO.MEMBER_EXISTENT){
		json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 이미 존재합니다.\"}";
	} else {
		int ri = dao.insertMember(dto);
		if (ri == MemberDAO.MEMBER_JOIN_SUCCESS) {
			session.setAttribute("id", dto.getId());
			json_data = "{\"code\":\"success\", \"desc\":\"회원가입을 축하합니다.\"}";
		} else {
			json_data = "{\"code\":\"fail\", \"desc\":\"에러가 발생하여 회원가입에 실패했습니다.\"}";
		}
	}
	response.setCharacterEncoding("UTF-8");
	out.println(json_data);
%>
<!-- 이게 없어야 json 파싱에 성공을 한다!!!
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

</body>
</html>
-->