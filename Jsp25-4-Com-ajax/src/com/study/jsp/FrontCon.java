package com.study.jsp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("*.do")
public class FrontCon extends HttpServlet {
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
		//response.setContentType("text/html; charset=UTF8");
		response.setContentType("applocation/json; charset=UTF-8");
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : " + conPath);
		String command = uri.substring(conPath.length());
		System.out.println("command : " + command);
		
		if (command.equals("/loginOk.do")) {
			Service service = new loginOk();
			service.execute(request, response);
		}
		else if (command.equals("/modifyOk.do")) {
			Service service = new modifyOk();
			service.execute(request, response);
		}
		else if (command.equals("/joinOk.do")) {
			Service service = new joinOk();
			service.execute(request, response);
		}
		else if (command.equals("/logout.do")) {
			logoutOk(request, response);
		}
	}

	private void logoutOk(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		System.out.println("logout");
		
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login.jsp");
	}

}
