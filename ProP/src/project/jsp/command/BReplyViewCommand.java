package project.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.jsp.dao.BoardDao;
import project.jsp.dto.BoardDto;



public class BReplyViewCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bId = request.getParameter("bId");
		
		BoardDao dao = BoardDao.getInstance();
		BoardDto dto = dao.reply_view(bId);
			
		request.setAttribute("reply_view", dto);
		
	}
}