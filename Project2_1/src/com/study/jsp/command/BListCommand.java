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
		String sCategory = request.getParameter("bCategory");
		bCategory = Integer.parseInt(sCategory);
		System.out.println("check:" + bCategory);
		
		try {
			String sPage = request.getParameter("page");
			System.out.println(sPage);
			nPage = Integer.parseInt(sPage);
			
		} catch (Exception e) {
		}
		
		System.out.println("check:" + 2222);
		System.out.println("ck:" + sCategory);
		System.out.println("ck:" + bCategory);
		
		
		BDao dao = new BDao();		
		BpageInfo pinfo = dao.articlePage(nPage, request);
		request.setAttribute("page", pinfo);
		
		nPage = pinfo.getCurPage();
		
		HttpSession session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		
		ArrayList<BDto> dtos = dao.list(nPage,bCategory);
		request.setAttribute("list", dtos);
	}
}
