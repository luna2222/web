package project.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.jsp.dao.BoardDao;



public class BModifyCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		System.out.println("modify 커맨드");
		
		BoardDao dao = BoardDao.getInstance();
		dao.modify(bId, bTitle, bContent);
	}
}
