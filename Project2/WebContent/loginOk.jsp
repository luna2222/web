<%@page import="com.struy.jsp.dto.MemberDTO"%>
<%@page import="com.study.jsp.dao.MemberDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.study.jsp.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	MemberDAO dao = MemberDAO.getInstance();
	String json_data ="";
	int checkNum = dao.userCheck(id, pw);
	if (checkNum == -1) { // 아이디가 존재하지 않습니다.
		json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 존재하지 않습니다.\"}";
	} else if (checkNum == 0) { // 비밀번호가 맞지 않습니다.
		json_data = "{\"code\":\"fail\", \"desc\":\"비밀번호가 맞지 않습니다.\"}";
	} else if (checkNum == 1) { 
		MemberDTO dto = dao.getMember(id);
			json_data = "{\"code\":\"success\", \"desc\":\"로그인 성공\"}";
		if(dto == null) { // 존재하지 않는 아이디 입니다.
			json_data = "{\"code\":\"fail\", \"desc\":\"존재하지 않는 아이디 입니다.\"}";
		} else {
			String name = dto.getName();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem", "yes");
		}
	}
	response.setCharacterEncoding("UTF-8");
	out.println(json_data);	
%>