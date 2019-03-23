package project.jsp.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jsp.dao.MemberDao;
import project.jsp.dto.MemberDto;



public class MJoinCommand implements MCommand{

	public MJoinCommand() {
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("mid");
		String mPw = request.getParameter("mpw");
		String mName = request.getParameter("mname");
		String mEmail = request.getParameter("memail");
		String mAddress = request.getParameter("maddress");
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		
		dto.setmId(mId);
		dto.setmPw(mPw);
		dto.setmName(mName);
		dto.setmEmail(mEmail);
		dto.setmAddress(mAddress);
		dto.setmDate(new Timestamp(System.currentTimeMillis()));
		
		System.out.println("join command");
		System.out.println(mId + mPw);
		
		//String json_data = "";
		if(dao.confirmId(dto.getmId()) == MemberDao.MEMBER_EXISTENT){
			//json_data = "{\"code\":\"fail\", \"desc\":\"아이디가 이미 존재 합니다.\"}";
			System.out.println("아이디 존재");
		}else{
			int ri = dao.insertMember(dto);
			if(ri == MemberDao.MEMBER_JOIN_SUCCESS){
				HttpSession session = request.getSession();
				session.setAttribute("mId", dto.getmId());
				//json_data = "{\"code\":\"success\", \"desc\":\"회원가입을 축하합니다.\"}";
			}else{
				//json_data = 
				//"{\"code\":\"fail\", \"desc\":\"에러가 발생하여 회원가입에 실패했습니다.\"}";
			}
		}
		
		//response.setContentType("text/html; charset=UTF-8");
//		response.setContentType("application/json; charset=UTF-8");
//		PrintWriter writer = response.getWriter();
//		writer.println(json_data);
//		writer.close();
	}
}
