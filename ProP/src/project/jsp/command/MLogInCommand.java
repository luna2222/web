package project.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jsp.dao.MemberDao;
import project.jsp.dto.MemberDto;


public class MLogInCommand implements MCommand{
	
	public MLogInCommand( ) {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("mid");
		String mPw = request.getParameter("mpw");

		MemberDao dao = MemberDao.getInstance();
		int checkNum = dao.userCheck(mId, mPw);
		String json_data = "";
		System.out.println(mId + mPw + "로그인커맨드");
		HttpSession session = request.getSession();

		if(checkNum == MemberDao.MEMBER_LOGIN_SUCCESS)
		{
			checkNum = 1;
			MemberDto Dto = dao.getMember(mId);
			session.setAttribute("mId", mId);
			session.setAttribute("mName", Dto.getmName());
			System.out.println(mId +":"+ Dto.getmName() + "로그인성공");
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>alert('로그인성공');</script>");
//			out.println("<script>alert('로그인성공'); location.href='main.jsp';</script>");
			session.setAttribute("checkNum", 1);
			
			//json_data = "{\"code\":\"success\", \"desc\":\"로그인 성공\"}";
		}else {
			//json_data = "{\"code\":\"fail\", \"desc\":\"로그인 실패\"}";
			System.out.println("로그인실패, login command");
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>alert('로그인실패'); location.href='login.jsp';</script>");
//			response.sendRedirect("login.jsp");
//			out.println("<script>alert('로그인실패');</script>");
//			
			checkNum = 0;
			session.setAttribute("checkNum", 0);
		}
//		response.setContentType("application/json; charset=UTF-8");
//		PrintWriter writer = response.getWriter();
//		writer.println(json_data);
//		writer.close();

	}
}