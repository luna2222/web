package com.study.jsp.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

public class BListCommand  implements Bcommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		 int nPage=1;
		 try {
			 String sPage=request.getParameter("page");
			 nPage=Integer.parseInt(sPage);
		 }catch(Exception e) {
			 
		 }
		BDao dao = new BDao();
		BPageInfo pinfo= dao.articlePage(nPage);
		request.setAttribute("Page", pinfo);
		
		nPage=pinfo.getCurPage();
		
		HttpSession session=null;
		session=request.getSession();
		session.setAttribute("cpage", nPage);

		ArrayList<BDto> dtos=dao.list(nPage);
		request.setAttribute("list", dtos);
		}
}
