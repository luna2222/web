package project.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.jsp.dao.BoardDao;



public class BReplyCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bCategory = request.getParameter("bCategory");
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		
		BoardDao dao = BoardDao.getInstance();
		dao.reply(bCategory, bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
		System.out.println("reply command");
	}
}
