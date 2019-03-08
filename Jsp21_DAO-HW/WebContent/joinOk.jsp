<%@page import="com.study.jsp.MemberDAO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="com.study.jsp.MemberDTO" />
<jsp:setProperty property="*" name="dto"/>
<%
	dto.setrDate(new Timestamp(System.currentTimeMillis()));
	MemberDAO dao = MemberDAO.getInstance();
	if(dao.confirmId(dto.getId()) == MemberDAO.MEMBER_EXISTENT){
%>
	<script language="javascript">
		alert("아이디가 이미 존재 합니다.");
		history.back();
	</script>
<%		
	} else {
		int ri = dao.insertMember(dto);
		if (ri == MemberDAO.MEMBER_JOIN_SUCCESS){
			session.setAttribute("id", dto.getId());
%>
		<script language="javascript">
			alert("회원 가입을 축하합니다.");
			document.location.href="login.jsp";
		</script>
<%	
		} else {
%>
		<script language="javascript">
			alert("회원 가입에 실패했습니다.");
			document.location.href="join.jsp";
		</script>
<%
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

</body>
</html>