package com.study.jsp;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RequestObj")
public class RequestObj extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public RequestObj() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request,response);
		
	}


	protected void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("actionDo");
		request.setAttribute("id", "abcde");
		request.setAttribute("pw", "12345");	
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/dispacherJsp.jsp");
		dispatcher.forward(request, response);
  }
}