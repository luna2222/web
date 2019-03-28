package com.study.jsp.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;
import com.study.jsp.dto.BpageInfo;

public class BSearchCommand implements Bcommand{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
	int nPage=1;
	
	String select = request.getParameter("sSelect");
	String content = request.getParameter("sContent");
	
	try { 		
		String sPage = request.getParameter("page");
		nPage = Integer.parseInt(sPage);
		
	}catch(Exception e) {
	}
	
//	BDao dao = BDao.getInstance();
	BDao dao = new BDao();
	BpageInfo pinfo = dao.sArticlePage(select, content, nPage);
	request.setAttribute("page", pinfo);
	//nPage = pinfo.getCurPage();
	
	HttpSession session = null;
	session = request.getSession();
	session.setAttribute("cpage", nPage);
	ArrayList<BDto> dtos = dao.sList(select, content, nPage);
	request.setAttribute("list", dtos);
}

}