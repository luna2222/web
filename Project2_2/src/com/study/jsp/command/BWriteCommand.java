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
		
		System.out.println(1111);
		int bCategory = Integer.parseInt(request.getParameter("bCategory"));
		
//		String bCategory = request.getParameter("bCategory");				
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
//		int bCategory = Integer.parseInt(bCategory);
		System.out.println(22222);
		BDao dao = new BDao();
		System.out.println(33333);
		dao.write(bCategory,bName, bTitle, bContent);
		System.out.println(44444);
//		session.setAttribute("bCategory", request.getParameter("bCategory"));
	}	
}
