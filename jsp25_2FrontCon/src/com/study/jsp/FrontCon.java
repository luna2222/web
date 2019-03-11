package com.study.jsp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


@WebServlet("*.do")
public class FrontCon extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private Connection con;
	private  PreparedStatement pstmt;
	private ResultSet set;
	private HttpSession session;
	
	private String name, id, pw, phone1, phone2, phone3, gender;
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:xe";
//	String uid = "scott";
//	String upw = "tiger";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("actionDo");
		
		String uri=request.getRequestURI();
		System.out.println("uri:"+uri);
		String conPath=request.getContextPath();
		System.out.println("conPath:"+conPath);
		String command=uri.substring(conPath.length());
		System.out.println("command:"+command);
		
		if(command.equals("/joinOk.do")) {
			loginOk(request,response);
		
		}else if(command.equals("/loginOk.do")) {
			joinOk(request,response);
			
		}else if(command.equals("/modifyOk.do")) {
			modifyOk(request,response);
							
		}else if(command.equals("/logout.do")) {
			logout(request,response);
			
	}
		
}
	private void loginOk(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("loginOk");
		String id, pw, name, phone,gender;
		
		id=request.getParameter("id");
		pw=request.getParameter("pw");
		name=" ";
		phone=" ";
		gender=" ";
		if(id ==null|| pw==null) {
			response.sendRedirect("login.jsp");
		}
		String query ="select *from member where id=? and pw=?";
		
		try {
			con = getConnection();
			
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.setString(2,pw);
			set=pstmt.executeQuery();
			//결과 데이터가 한개이면 while대신 if를 사용해도 된다
			
			while(set.next()) {
				//id=resultSet.getString("id");
				//pw=resultSet.getString("pw");
				name=set.getString("name");
				phone=set.getString("phone");
				gender=set.getString("gender");
			}
			if(name == null || name.equals("")){
				response.sendRedirect("login.jsp");
			}
			// 서버 세션에 데이터 set
			HttpSession session= request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			session.setAttribute("phone",phone);
			session.setAttribute("gender", gender);
					
			response.sendRedirect("loginResult.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(set !=null)set.close();
				if(pstmt !=null)pstmt.close();
				if(con !=null)con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	private void modifyOk(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		    System.out.println("modifyOk");
		    session = request.getSession();
		   
			id=(String)session.getAttribute("id");
			
			pw=request.getParameter("pw");
			name=request.getParameter("name");
			phone1=request.getParameter("phone1");
			phone2=request.getParameter("phone2");
			phone3=request.getParameter("phone3");
			gender=request.getParameter("gender");
			
			if(pwConfirm()) {
				System.out.println("Ok");
				
				try {
				con= getConnection();
					
				String query="update member set name=?,phone=?, gender=? where id=?";
				pstmt= con.prepareStatement(query);
				pstmt.setString(1,name);
				pstmt.setString(2,phone1+"-"+phone2+"-"+phone3);
				pstmt.setString(3,gender);
				pstmt.setString(4,id);
				int updateCount=pstmt.executeUpdate();
				
				if(updateCount==1) {
					System.out.println("update success");
					session.setAttribute("name", name);
					response.sendRedirect("modifyResult.jsp");
				}else {
					System.out.println("update fail");
					response.sendRedirect("modify.jsp");
				   }
				}catch(Exception e) {
					e.printStackTrace();
				} finally {
			    try {
				  if(pstmt !=null) pstmt.close();
				  if(con !=null) con.close();
			   }catch (Exception e) { }
	      }
			
	      } else	 {
			System.out.println("패스워드가 불일치 합니다");
			response.sendRedirect("modify.jsp");
		   }
	     }
		
	private boolean pwConfirm() {
		boolean rs=false;
		String sessionPw=( String)session.getAttribute("pw");
		
		if(sessionPw.equals(pw)) {
			rs=true;
		}else {
			rs=false;
		}
		return rs;
	}


	private void joinOk(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("joinOk");
		
        request.setCharacterEncoding("UTF-8");
        		
		id =request.getParameter("id");
		pw=request.getParameter("pw");
		name = request.getParameter("name");
		phone1 = request.getParameter("phone1");
		phone2 = request.getParameter("phone2");
		phone3 = request.getParameter("phone3");
		gender = request.getParameter("gender");
		//여기서 파라미터로 넘어온 값들 체크
		// 이후 정상이면 다음 진행
		String query ="insert into member values(?,?,?,?,?)";
		
		try 
		{
			
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.setString(2,pw);
			pstmt.setString(3,name);
			pstmt.setString(4,phone1+"-"+phone2+"-"+phone3);
			pstmt.setString(5,gender);
			int updateCount =pstmt.executeUpdate();
			
			if(updateCount==1){
				System.out.println("insert success");
			    response.sendRedirect("joinResult.jsp");
			}else {
				System.out.println("insert fail");
				response.sendRedirect("join.jsp");
			}	
			query="commit";
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		
		}
	}
		
	private void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		session.invalidate();
		response.sendRedirect("login.jsp");
		}
	private Connection getConnection() {
		
		Context context = null;
		DataSource dataSource = null;
		Connection con = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			con = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
