package com.study.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.MemberDAO;

public class sns_loginOk  implements Command{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
	{
		//MemberDTO dto=new MemberDTO();
		MemberDAO dao= MemberDAO.getInstance();
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session =request.getSession();
		
		String id= (String)request.getParameter("id");
		String name= (String)request.getParameter("name");
        String img= (String)request.getParameter("img");
        String eMail= (String)request.getParameter("mail");
        if( eMail == null);
        eMail="이메일 입력";
        
        System.out.println(id);
        System.out.println(name);
        System.out.println(img);
        System.out.println(eMail);
        
        if(dao.confirmId(id)==0) {
        	System.out.println();
        	session.setAttribute("id", id);
        	session.setAttribute("name", name);
        	session.setAttribute("email", eMail);
        	response.sendRedirect("../sns_join.do");
        }else {
        	session.setAttribute("id", id);
        	session.setAttribute("name", name);
        	session.setAttribute("validMem", "sns");
        	response.sendRedirect("../main.do");
        }
	}
}
