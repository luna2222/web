package project.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jsp.dao.BoardDao;


public class BWriteCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		int bCategory = Integer.parseInt(request.getParameter("bCategory"));
		HttpSession session = request.getSession();
		String bName = (String)session.getAttribute("mId");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		
		BoardDao dao = BoardDao.getInstance();
		dao.write(bCategory, bName, bTitle, bContent);
		session.setAttribute("bCategory", request.getParameter("bCategory"));
	}
}
