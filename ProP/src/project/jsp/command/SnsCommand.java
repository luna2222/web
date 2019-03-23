package project.jsp.command;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jsp.dao.MemberDao;
import project.jsp.dto.MemberDto;

public class SnsCommand implements MCommand{
	
	public SnsCommand( ) {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
	{
		System.out.println("sns command");
		String mId = request.getParameter("mId");
		//String mName = request.getParameter("mname");
		System.out.println(mId);
		
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		int r2 = dao.confirmId(mId);
		System.out.println(r2);
		if(r2 == 0){
			System.out.println("11");
			dto.setmId(mId);
			dto.setmPw("dummy");
			dto.setmName("dummy");
			dto.setmEmail("dummy");
			dto.setmAddress("dummy");
			dto.setmDate(new Timestamp(System.currentTimeMillis()));
			int ri = dao.insertMember(dto);
			System.out.println(ri);
			if(ri == MemberDao.MEMBER_JOIN_SUCCESS)
			{
				System.out.println("Sns로 가입 성공");
				HttpSession session = request.getSession();
				session.setAttribute("mId", dto.getmId());
			}else {
				System.out.println("가입 실패");
				response.sendRedirect("login.jsp");
			}
			
		}else {
			System.out.println("존재아이디:"+mId);
			HttpSession session = request.getSession();
//			session.setAttribute("mId", dto.getmId());
			session.setAttribute("mId", mId);
		}
	}
}
