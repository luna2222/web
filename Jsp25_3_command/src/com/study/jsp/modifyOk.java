package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class modifyOk implements Service{
	public modifyOk() {	}

	@Override
	public void execute(HttpServletRequest request, 
						HttpServletResponse response) 
				throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("modifiOk");
		HttpSession session = request.getSession();
		session = request.getSession();		
		String id = (String)session.getAttribute("id");
		String pw = request.getParameter("pw");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.seteMail(eMail);
		dto.setAddress(address);
		
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html; charset=UTF8");
		MemberDAO dao = MemberDAO.getInstance();
		int ri = dao.updateMember(dto);		
		
		if (ri == 1) {
			//html
			writer.println("<html><head></head><body>");
			writer.println("<script>");
			writer.println("	alert(\"정보가 수정되었습니다.\");");
			writer.println("	document.location.href=\"main.jsp\";");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
		} else {
			//html
			writer.println("<html><head></head><body>");
			writer.println("<script>");
			writer.println("	alert(\"정보 수정에 실패했습니다.\");");
			writer.println("	history.go(-1);");
			writer.println("</script>");
			writer.println("</body></html>");
			writer.close();
		}
		
	}
}
