package com.study.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.BDao;

public class BWriteCommand implements Bcommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String sNotice = request.getParameter("bNotice");
		int bNotice = Integer.parseInt(sNotice);
		
		BDao dao = new BDao();
		dao.write(bName, bTitle, bContent, bNotice);
	}	
}
