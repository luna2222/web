package project.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jsp.dao.BoardDao;
import project.jsp.dto.BoardDto;
import project.jsp.frontcontroller.BPageInfo;

public class BListCommand implements BCommand{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		int nPage = 1;
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
			
		}catch(Exception e) {
			
		}
		
		String bCategory = request.getParameter("bCategory");
		int boardCategory = Integer.parseInt(request.getParameter("bCategory"));
		System.out.println("리스트 커맨드" + nPage + ":" + boardCategory);
		
		
		BoardDao dao = BoardDao.getInstance();
		BPageInfo pinfo = dao.articlePage(nPage, boardCategory);
		request.setAttribute("page", pinfo);
		System.out.println("article 완");
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		System.out.println("카테고리:"+pinfo.getBoardCategory());
		
		ArrayList<BoardDto>	dtos = dao.list(nPage, boardCategory);
		request.setAttribute("list", dtos);
		session.setAttribute("bCategory", bCategory);
		System.out.println("list 완");
		
	}

}
