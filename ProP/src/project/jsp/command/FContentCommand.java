package project.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.jsp.dao.FileDao;
import project.jsp.dto.FileDto;



public class FContentCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String fId = request.getParameter("fId");
		String bKind = request.getParameter("kind");
		FileDao dao = FileDao.getInstance();
		FileDto dto = dao.filecontentView(fId, bKind);
		
		System.out.println("content커맨드 진행");
		request.setAttribute("filecontent_view", dto);
	}
}
