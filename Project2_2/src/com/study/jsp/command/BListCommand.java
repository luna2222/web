package com.study.jsp.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.BpageInfo;

import sun.print.resources.serviceui_zh_CN;

public class BListCommand implements Bcommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		int nPage = 1;
		int bCategory = 0;
		String sCategory="";
		try {
			String sPage = request.getParameter("page");
			System.out.println(sPage);
			nPage = Integer.parseInt(sPage);
			
			sCategory = request.getParameter("bCategory");
			bCategory = Integer.parseInt(sCategory);
		} catch (Exception e) {
		}
		
		System.out.println(111111);
		System.out.println(sCategory);
		System.out.println(bCategory);
		
		
		BDao dao = new BDao();		
		BpageInfo pinfo = dao.articlePage(nPage);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		
		ArrayList<BDto> dtos = dao.list(nPage,bCategory);
		request.setAttribute("list", dtos);
	}
}
