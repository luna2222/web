package project.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jsp.dao.FileDao;
import project.jsp.dto.FileDto;
import project.jsp.frontcontroller.FPageInfo;

public class FListCommand implements BCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		int nPage = 1;
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
			
		}catch(Exception e) {
			
		}
		
		FileDao dao = FileDao.getInstance();
		FPageInfo finfo = dao.articlePage(nPage);
		request.setAttribute("fpage", finfo);
		System.out.println("article 완");
		
		nPage = finfo.getCurPage();
		
		HttpSession session = null;
		
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		ArrayList<FileDto>	dtos = dao.list(nPage);
		request.setAttribute("list", dtos);

		System.out.println("list 완");
		
	}
}
