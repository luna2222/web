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

import com.strudy.jsp.dto.MemberDTO;
import com.study.jsp.dao.MemberDAO;

@WebServlet("/LoginProcess")
public class LoginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
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
		
		String json_data ="";
		int checkNum = dao.userCheck(id, pw);
		if (checkNum == -1) { // 아이디가 존재하지 않습니다.
			json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 존재하지 않습니다.\"}";
		} else if (checkNum == 0) { // 비밀번호가 맞지 않습니다.
			json_data = "{\"code\":\"fail\", \"desc\":\"비밀번호가 맞지 않습니다.\"}";
		} else if (checkNum == 1) { 
			dto = dao.getMember(id);
				json_data = "{\"code\":\"success\", \"desc\":\"로그인 성공\"}";
			if(dto == null) { // 존재하지 않는 아이디 입니다.
				json_data = "{\"code\":\"fail\", \"desc\":\"존재하지 않는 아이디 입니다.\"}";
			} else {
				name = dto.getName();
				session.setAttribute("id", id);
				session.setAttribute("name", name);
				session.setAttribute("ValidMem", "yes");
			}
		}		
//		response.setContentType("text/html; charset=UTF-8");
		response.setContentType("applocation/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();
	}

}
