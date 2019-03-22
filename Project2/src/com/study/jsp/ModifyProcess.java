package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.struy.jsp.dto.MemberDTO;
import com.study.jsp.dao.MemberDAO;

@WebServlet("/ModifyProcess")
public class ModifyProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
        request.setCharacterEncoding("UTF-8");
		
		String pw = request.getParameter("pw");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		HttpSession session = request.getSession();
		
		dto.setPw(pw);
		dto.seteMail(eMail);
		dto.setAdress(address);
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		
		String id = (String)session.getAttribute("id");
		dto.setId(id);
		dao = MemberDAO.getInstance();
		int ri = dao.updateMember(dto);
		String json_data = "";
		if (ri == 1) { // 정보가 수정되었습니다.
			json_data = "{\"code\":\"success\", \"desc\":\"정보가 수정되었습니다.\"}";
		} else { // 정보수정에 실패했습니다.
			json_data = "{\"code\":\"fail\", \"desc\":\"정보수정에 실패했습니다.\"}";
		}
		
		response.setContentType("applocation/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();
	}

}
