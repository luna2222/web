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
			if (checkNum == -1) {
				//html
				writer.println("<html><head></head><body>");
				writer.println("<script>");
				writer.println("	alert(\"아이디가 존재하지 않습니다.\");");
				writer.println("	history.go(-1);");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			} 
			else if (checkNum == 0) {
				//html
				writer.println("<html><head></head><body>");
				writer.println("<script>");
				writer.println("	alert(\"비밀번호가 틀립니다.\");");
				writer.println("	history.go(-1);");
				writer.println("</script>");
				writer.println("</body></html>");
				writer.close();
			}
			else if (checkNum == 1) {
				MemberDTO dto = dao.getMember(id);
				if(dto == null) {
					//html
					writer.println("<html><head></head><body>");
					writer.println("<script>");
					writer.println("	alert(\"존재하지 않는 회원 입니다.\");");
					writer.println("	history.go(-1);");
					writer.println("</script>");
					writer.println("</body></html>");
					writer.close();
				}
				else {
					String name = dto.getName();
					HttpSession session = request.getSession();
					session.setAttribute("id", id);
					session.setAttribute("name", name);
					session.setAttribute("ValidMem", "yes");
					response.sendRedirect("main.jsp");
				}
			}
		}
}
