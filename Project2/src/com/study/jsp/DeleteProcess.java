package com.study.jsp;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.strudy.jsp.dto.MemberDTO;
import com.study.jsp.dao.MemberDAO;


@WebServlet("/DeleteProcess")
public class DeleteProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
 

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		HttpSession session = request.getSession();
		
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.seteMail(eMail);
		dto.setAdress(address);
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		
	}

}
