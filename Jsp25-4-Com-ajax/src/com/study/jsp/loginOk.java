package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginOk implements Service {
		
		public loginOk() {}

		@Override
		public void execute(HttpServletRequest request, 
				HttpServletResponse response)
				throws ServletException, IOException 
		{
			System.out.println("loginOk");
			
			request.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html; charset=UTF8");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberDAO dao = MemberDAO.getInstance();
			int checkNum = dao.userCheck(id, pw);
			String json_data ="";
			if (checkNum == -1) {
				json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 존재하지 않습니다.\"}";
			} 
			else if (checkNum == 0) {
				//html
				json_data = "{\"code\":\"fail\", \"desc\":\"비밀번호가 맞지 않습니다.\"}";
			}
			else if (checkNum == 1) {
				MemberDTO dto = dao.getMember(id);
				json_data = "{\"code\":\"success\", \"desc\":\"로그인 성공\"}";
				if(dto == null) {
					json_data = "{\"code\":\"fail\", \"desc\":\"존재하지 않는 아이디 입니다.\"}";					
				}
				else {
					String name = dto.getName();
					HttpSession session = request.getSession();
					session.setAttribute("id", id);
					session.setAttribute("name", name);
					session.setAttribute("ValidMem", "yes");
				}
			}
			response.setContentType("applocation/json; charset=UTF-8");
			writer.println(json_data);
			writer.close();
		}
}
