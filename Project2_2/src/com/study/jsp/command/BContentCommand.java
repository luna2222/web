package com.study.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

public class BContentCommand implements Bcommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String bId = request.getParameter("bId");
		String bKind = request.getParameter("kind");
		BDao dao = new BDao();
		BDto dto = dao.contentView(bId, bKind);
		
		request.setAttribute("content_view", dto);
	}
}
