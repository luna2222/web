package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class joinOk implements Service{
	public joinOk(){
				
	}
	@Override
	public void execute(HttpServletRequest request,
			    HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("joinOk");
		
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String pw=request.getParameter("id");
		String name=request.getParameter("name");
		String eMail=request.getParameter("eMail");
		String address=request.getParameter("address");
		
		MemberDTO dto=new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setAddress(address);
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer=response.getWriter();
		
		MemberDAO dao=MemberDAO.getInstance();
		
		if(dao.confirmId(dto.getId())==MemberDAO.MEMBER_EXISTENT) {
			//html출력
			writer.println("<html><head></head><body>");
			writer.println("<script language=\"javascript\">");
			writer.println("   alert(\"아이디가 이미 존재합니다\");");
			writer.println("   history.back();");
			writer.println("<script>");
			writer.println("</body></html>");
			writer.close();
			
		}else {
			int ri =dao.insertMember(dto);
			if(ri==MemberDAO.MEMBER_JOIN_SUCCESS) {
				HttpSession session=request.getSession();
				session.setAttribute("id", dto.getId());
				
				//html출력
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println("   alert(\"회원가입을 축하합니다\");");
				writer.println("   document.location.href=\"login.jsp\";");
				writer.println("<script>");
				writer.println("</body></html>");
				writer.close();
			}else {
				//html출력
				writer.println("<html><head></head><body>");
				writer.println("<script language=\"javascript\">");
				writer.println("   alert(\"회원가입을 실패했습니다\");");
				writer.println("   document.location.href=\"login.jsp\";");
				writer.println("<script>");
				writer.println("</body></html>");
				writer.close();
			}
		}
     }

}
