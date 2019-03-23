package project.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jsp.dao.BoardDao;
import project.jsp.dto.BoardDto;
import project.jsp.frontcontroller.BPageInfo;

public class BTitleListCommand implements BCommand{

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
		
		String bTitle = request.getParameter("word");
		BoardDao dao = BoardDao.getInstance();
		
		session.setAttribute("column", "bTitle");
		session.setAttribute("word", bTitle);
		

		BPageInfo pinfo = dao.articleTitlePage(nPage, bTitle);
		request.setAttribute("page", pinfo);
		System.out.println("article 완");
		
		nPage = pinfo.getCurPage();
		
		session.setAttribute("cpage", nPage);
		System.out.println("카테고리:"+pinfo.getBoardTitle());
		
		ArrayList<BoardDto>	dtos = dao.titleList(nPage, bTitle);
		request.setAttribute("list", dtos);
		
		System.out.println("titlelist 완");
	}
}
