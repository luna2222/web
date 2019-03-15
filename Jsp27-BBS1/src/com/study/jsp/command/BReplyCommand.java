package com.study.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

import sun.misc.Perf.GetPerfAction;

public class BReplyCommand  implements Bcommand{

		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException 
		{
			String bId = request.getParameter("bId");
			String bName = request.getParameter("bName");
			String bTitle=request.getParameter("bTitle");
			String bContent=request.getParameter("bContent");
			String bGroup=request.getParameter("bGroup");
			String bStep=request.getParameter("bStep");
			String bIndent=request.getParameter("bIndent");
					
			BDao dao =  new BDao();
			dao.reply(bId,bName ,bTitle,bContent,bGroup,bStep,bIndent);
			
			
		}
}
