package project.jsp.frontcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.jsp.command.BCommand;
import project.jsp.command.BContentCommand;
import project.jsp.command.BContentListCommand;
import project.jsp.command.BDeleteCommand;
import project.jsp.command.BListCommand;
import project.jsp.command.BModifyCommand;
import project.jsp.command.BNameListCommand;
import project.jsp.command.BReplyCommand;
import project.jsp.command.BReplyViewCommand;
import project.jsp.command.BTitleListCommand;
import project.jsp.command.BWriteCommand;
import project.jsp.command.FContentCommand;
import project.jsp.command.FDeleteCommand;
import project.jsp.command.FListCommand;
import project.jsp.command.MCommand;
import project.jsp.command.MDeleteCommand;
import project.jsp.command.MJoinCommand;
import project.jsp.command.MLogInCommand;
import project.jsp.command.NCTcontentCommand;

@WebServlet("*.do")
public class BFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public BFrontController() {
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
		BCommand bcommand = null;
		MCommand mcommand = null;
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String conPath = request.getContextPath();
		System.out.println("conPath : " + conPath);
		String com = uri.substring(conPath.length());
		System.out.println("command : " + com);
		
		HttpSession session = null;
		session = request.getSession();
		int curPage = 1;
		if(session.getAttribute("cpage") != null) {
			curPage = (int)session.getAttribute("cpage");
		}
		if(session.getAttribute("fpage") != null) {
			curPage = (int)session.getAttribute("fpage");
		}
		//session.setAttribute("bCategory", request.getAttribute("bCategory"));
		
		
//		int category = Integer.parseInt(request.getParameter("bCategory"));
//		System.out.println("게시판카테고리 : " + category);
		
		if(com.equals("/login.do")) {
			mcommand = new MLogInCommand();
			mcommand.execute(request, response);
//			viewPage = "";
			if (session.getAttribute("checkNum").equals(1)) {
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인성공');location.href='main.jsp'</script>");
				return;
				//viewPage = "main.jsp";
			}else {
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인실패');location.href='login.jsp';</script>");
				out.flush();
				return;
				//viewPage = "login.jsp";
			}
		}else if(com.equals("/join.do")) {
			mcommand = new MJoinCommand();
			mcommand.execute(request, response);
			viewPage = "login.jsp";
		}else if(com.equals("/deleteMember.do")) {
			mcommand = new MDeleteCommand();
			mcommand.execute(request, response);
			viewPage = "logout.jsp";
		}else if(com.contentEquals("/list.do")) {
			bcommand = new BListCommand();
			bcommand.execute(request, response);
			viewPage = "list.jsp";
		}
		
		else if(com.contentEquals("/write_view.do")) {
			viewPage = "write_view.jsp";
		}else if(com.contentEquals("/write.do")) {
			bcommand = new BWriteCommand();
			bcommand.execute(request, response);
			
			//request.setAttribute("bCategory", session.getAttribute("bCategory"));
			bcommand = new BListCommand();
			bcommand.execute(request, response);
			viewPage = "list.jsp";
		}else if(com.contentEquals("/content_view.do")) {
			bcommand = new BContentCommand();
			bcommand.execute(request, response);
			viewPage = "content_view.jsp";
		}else if(com.contentEquals("/modify_view.do")) {
			String id = (String)session.getAttribute("mId");
			String name = request.getParameter("bName");
			System.out.println(id + name);
			if(id.equals(name)) {
				bcommand = new BContentCommand();
				bcommand.execute(request, response);
				viewPage = "modify_view.jsp";
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('권한없음');history.go(-1);</script>");		
				out.flush();
				return;
			}		
		}else if(com.contentEquals("/modify.do")) {
			bcommand = new BModifyCommand();
			bcommand.execute(request, response);
			viewPage = "list.do?page="+curPage;				
		}else if(com.contentEquals("/delete.do")) {
			String id = (String)session.getAttribute("mId");
			String name = request.getParameter("bName");
			System.out.println(id + name);
			if(id.equals(name)) {
				bcommand = new BDeleteCommand();
				bcommand.execute(request, response);
				String boardCategory = (String)session.getAttribute("bCategory");
				viewPage = "list.do?page="+curPage+"&bCategory="+boardCategory;
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('권한없음');history.go(-1);</script>");		
				out.flush();
				return;
			}
		}else if(com.contentEquals("/reply_view.do")) {
			bcommand = new BReplyViewCommand();
			bcommand.execute(request, response);
			viewPage = "reply_view.jsp";
		}else if(com.contentEquals("/reply.do")) {
			bcommand = new BReplyCommand();
			bcommand.execute(request, response);
			String boardCategory = (String)session.getAttribute("bCategory");
			viewPage = "list.do?page="+curPage+"&bCategory="+boardCategory;
//		}else if(com.contentEquals("/search.do")) {
//			
		}else if(com.contentEquals("/filelist.do")) {
			bcommand = new FListCommand();
			bcommand.execute(request, response);
			viewPage = "filelist.jsp";
		}else if(com.contentEquals("/upload_view.do")) {
			viewPage = "upload_view.jsp";
		}else if(com.contentEquals("/filecontent_view.do")) {
			bcommand = new FContentCommand();
			bcommand.execute(request, response);
			viewPage = "filecontent_view.jsp";
		}else if(com.contentEquals("/filedelete.do")) {
			bcommand = new FDeleteCommand();
			bcommand.execute(request, response);
			viewPage = "filelist.do?page="+curPage;
		}
		
		else if (com.contentEquals("/search.do")) {
			String column = request.getParameter("column");
			String word = request.getParameter("word");
			System.out.println(column+word);
			if(column.equals("bName")) {
				bcommand = new BNameListCommand();
				bcommand.execute(request, response);
				viewPage = "namelist.jsp";
			}else if(column.equals("bContent")) {
				
				bcommand = new BContentListCommand();
				bcommand.execute(request, response);
				viewPage = "contentlist.jsp";
			}else if(column.equals("bTitle")) {
				
				bcommand = new BTitleListCommand();
				bcommand.execute(request, response);
				viewPage = "titlelist.jsp";
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('재입력');location.href='main.jsp';</script>");
				out.flush();
				viewPage = "main.jsp";
			}
		}else if(com.contentEquals("/nctcontent_view.do")) {
			bcommand = new NCTcontentCommand();
			bcommand.execute(request, response);
			viewPage = "nctcontent_view.jsp";
		}else if(com.contentEquals("/nctdel.do")) {
			String id = (String)session.getAttribute("mId");
			String name = request.getParameter("bName");
			System.out.println(id + name);
			if(id.equals(name)) {
				bcommand = new BDeleteCommand();
				bcommand.execute(request, response);
				//String boardCategory = (String)session.getAttribute("bCategory");
				String column = (String)session.getAttribute("column");
				String word = (String)session.getAttribute("word");			
				viewPage = "search.do?page="+curPage+"&column="+column+"&word="+word;
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('권한없음');history.go(-1);</script>");		
				out.flush();
				return;
			}
		}else if(com.contentEquals("/nctmodify_view.do")) {
			String id = (String)session.getAttribute("mId");
			String name = request.getParameter("bName");
			System.out.println(id + name);
			if(id.equals(name)) {
				bcommand = new NCTcontentCommand();
				bcommand.execute(request, response);
				viewPage = "nctmodify_view.jsp";
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('권한없음');history.go(-1);</script>");		
				out.flush();
				return;
			}
		}else if(com.contentEquals("/nctmodify.do")) {
			bcommand = new BModifyCommand();
			bcommand.execute(request, response);
			String column = (String)session.getAttribute("column");
			String word = (String)session.getAttribute("word");			
			viewPage = "search.do?page="+curPage+"&column="+column+"&word="+word;
		}else if(com.contentEquals("/nctreply_view.do")) {
			bcommand = new BReplyViewCommand();
			bcommand.execute(request, response);
			viewPage = "nctreply_view.jsp";
		}else if(com.contentEquals("/nctreply.do")) {
			bcommand = new BReplyCommand();
			bcommand.execute(request, response);
			String column = (String)session.getAttribute("column");
			String word = (String)session.getAttribute("word");			
			viewPage = "search.do?page="+curPage+"&column="+column+"&word="+word;
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
	
}
