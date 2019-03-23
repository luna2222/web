package project.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.jsp.dao.MemberDao;
import project.jsp.dto.MemberDto;

public class MDeleteCommand implements MCommand{

	public MDeleteCommand() {
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		String mId = request.getParameter("mid");
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = new MemberDto();
		if(dao.confirmId(mId) == MemberDao.MEMBER_NONEXISTENT)
		{
			
		}
		
		
		dao.deleteMember(mId);
		if(dao.deleteMember(mId) == MemberDao.MEMBER_DELETE_SUCCESS) {
			System.out.println("탈퇴 성공");

		}else {
			System.out.println("탈퇴 실패");
		}
	}
}