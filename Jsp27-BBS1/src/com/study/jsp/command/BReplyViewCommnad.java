package com.study.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

public class BReplyViewCommnad implements Bcommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String bId=request.getParameter("bId");
		BDao dao= new BDao();
		BDto dto= dao.reply_view(bId);
		
		request.setAttribute("reply_view",dto);
	}
}	
