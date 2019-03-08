package com.study.jsp02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ContextIntParam")
public class ContextIntParam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		

		 System.out.println("doGet"); 
	     
	     String id = getServletContext().getInitParameter("id");
	     String pw = getServletContext().getInitParameter("pw");
	     String path = getServletContext().getInitParameter("path");
	     
	     response.setContentType("text/html;charset=UTF-8");
	     PrintWriter writer=response.getWriter();
	     writer.println("<html><head></head><body>  ");
	     writer.println("아이디:  "+id+"<br>");
	     writer.println("비밀번호"+pw+"<br>");
	     writer.println("PATH "+path);
	  	 writer.println(" </body></html> ");
	  	 writer.close();	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
