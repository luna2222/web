package project.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jsp.dao.BoardDao;
import project.jsp.dto.BoardDto;



public class BContentCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		String bId = request.getParameter("bId");
		String bKind = request.getParameter("kind");
		BoardDao dao = BoardDao.getInstance();
		BoardDto dto = dao.contentView(bId, bKind);
		
		System.out.println("content커맨드 진행");
		request.setAttribute("content_view", dto);
		
	}
}
