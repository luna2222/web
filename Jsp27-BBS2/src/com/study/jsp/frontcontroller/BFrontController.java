package com.study.jsp.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.command.BContentCommand;
import com.study.jsp.command.BDeleteCommand;
import com.study.jsp.command.BListCommand;
import com.study.jsp.command.BModifyCommand;
import com.study.jsp.command.BReplyCommand;
import com.study.jsp.command.BReplyViewCommnad;
import com.study.jsp.command.BWriteCommand;
import com.study.jsp.command.Bcommand;

@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doGet");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("doPost");
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("actionDo");
		
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		Bcommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		System.out.println(uri);
		System.out.println(conPath);
		System.out.println(com);
		
		if (com.equals("/write_view.do")) { 
			viewPage = "write_view.jsp";
		}
		else if (com.equals("/write.do")) {
			command = new BWriteCommand();
			command.execute(request, response);
			viewPage = "list.do";
		}
		else if (com.equals("/list.do")) {
			command = new BListCommand();
			command.execute(request, response);
			viewPage = "list.jsp";
		}
		else if (com.equals("/content_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";
		}
		else if (com.equals("/modify_view.do")) {
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "modify_view.jsp";
		}
		else if (com.equals("/modify.do")) {
			command = new BModifyCommand();
			command.execute(request, response);
			
			command = new BContentCommand();
			command.execute(request, response);
			viewPage = "content_view.jsp";
		}
		else if (com.equals("/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do";
		}
		else if (com.equals("/reply_view.do")) {
			command = new BReplyViewCommnad();
			command.execute(request, response);
			viewPage = "reply_view.jsp";
		}else if (com.equals("/reply.do")) {
				command = new BReplyCommand();
				command.execute(request, response);
				viewPage = "list.do";
		}else if (com.equals("/main.do")) {
			command = new BListCommand();
			command.execute(request, response);
			viewPage = "list.jsp";
		}
		
		response.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		//dispatcher.include(request, response);
	}

}
