package com.study.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/helloworld")
public class helloworld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("doGet"); // 콘솔에서 뜸 
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		//사용자 브라우져에보냄
		writer.println("<html>");
		writer.println("<head>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1>GET방식<br> 따라서doGet메서드를 호출<h1>");
		writer.println("<h2>hello~<h2>");
		writer.println("</body>");
		writer.println("</html>");
		
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
