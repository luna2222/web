package project.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jsp.dao.BoardDao;
import project.jsp.dto.BoardDto;
import project.jsp.frontcontroller.BPageInfo;

public class BNameListCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		int nPage = 1;
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
			
		}catch(Exception e) {
			
		}
		HttpSession session = null;	
		session = request.getSession();
	
		String bName = request.getParameter("word");
		BoardDao dao = BoardDao.getInstance();
		
		session.setAttribute("column", "bName");
		session.setAttribute("word", bName);
		
		BPageInfo pinfo = dao.articleNamePage(nPage, bName);
		request.setAttribute("page", pinfo);
		System.out.println("article 완");
		
		nPage = pinfo.getCurPage();		
		
		session.setAttribute("cpage", nPage);				
		System.out.println("카테고리:"+pinfo.getBoardName());
		
		ArrayList<BoardDto>	dtos = dao.nameList(nPage, bName);
		request.setAttribute("list", dtos);

		System.out.println("namelist 완");
	}

}
