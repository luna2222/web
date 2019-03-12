package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class joinOk implements Service {
	
	public joinOk() {
		
	}
	
	@Override
	public void execute(HttpServletRequest request, 
						HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("joinOk");
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.seteMail(eMail);
		dto.setAdress(address);
		dto.setrDate(new Timestamp(System.currentTimeMillis()));

		MemberDAO dao = MemberDAO.getInstance();
		
		HttpSession session = request.getSession();		
		String json_data ="";
		if(dao.confirmId(dto.getId()) == MemberDAO.MEMBER_EXISTENT) {
			// html 출력
			json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 이미 존재합니다.\"}";
		} else {
			int ri = dao.insertMember(dto);
			if (ri == MemberDAO.MEMBER_JOIN_SUCCESS) {
				session.setAttribute("id", dto.getId());
				session.setAttribute("name", dto.getName());
				session.setAttribute("ValidMem", "yes");
				json_data = "{\"code\":\"success\", \"desc\":\"회원가입을 축하합니다.\"}";
			} else {
				json_data = "{\"code\":\"fail\", \"desc\":\"에러가 발생하여 회원가입에 실패했습니다.\"}";
			}
			
			response.setContentType("applocation/json; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println(json_data);
			writer.close();
		}
	}
}
