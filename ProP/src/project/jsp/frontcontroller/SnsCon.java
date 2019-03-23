package project.jsp.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.jsp.command.MCommand;
import project.jsp.command.SnsCommand;


@WebServlet("/SnsCon")
public class SnsCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SnsCon() {
        super();
    }

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

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		System.out.println("actionDo");
		String viewPage = null;
		MCommand mcommand = null;
		String mId = request.getParameter("mId");
		//String mName = request.getParameter("mname");
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : " + conPath);
		String com = uri.substring(conPath.length());
		System.out.println("command : " + com);
		
		System.out.println(mId);
		
			mcommand = new SnsCommand();
			mcommand.execute(request, response);
			System.out.println("과정 완료");
			viewPage = "main.jsp";
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
}
