package com.study.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;


import com.study.jsp.dto.BDto;
import com.study.jsp.dto.BpageInfo;



public class BDao {
	
//	private static BDao instance = new BDao();
//	
//	public static BDao getInstance() {
//		return instance;
//	}
	
	DataSource dataSource = null;
	
	int listCount = 10;
	int pageCount = 10;

	private int curPage;
	//private BDao() {
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int write(int bCategory,String bName, String bTitle, String bContent) {
		int rn = 0; //리턴값을 이용해 글이 완전히 써지기전 이동을 방지
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board " +
					   " (bCategory,bid, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) " +
					   " values " +
					   " (?,mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bCategory);
			pstmt.setString(2, bName);
			pstmt.setString(3, bTitle);
			pstmt.setString(4, bContent);
			
			
			rn = pstmt.executeUpdate();
			return rn;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return rn;
	}
	
	public ArrayList<BDto> list(int curPage, int bCategory){
//		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage -1) * listCount + 1;
		int nEnd = (curPage -1) * listCount + listCount;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * " + 
		                   "  from ( " + 
				           "    select rownum num, A.* " +
		                   "      from ( " +
				           "        select * " + 
		                   "          from mvc_board " +
				           "          where bCategory = ? " +
				           "        order by bgroup desc, bstep asc ) A" + 
		                   "      where rownum <= ? ) B " + 
				           " where B.num >= ? ";
		 
//            카테고리를 3가지로 분류
			    pstmt = con.prepareStatement(query);
				pstmt.setInt(1, bCategory);
				pstmt.setInt(2, nEnd);
				pstmt.setInt(3, nStart);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				
				int nboard = resultSet.getInt("bCategory");
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				
				BDto dto = new BDto(bCategory, bId, bName, bTitle, bContent, bDate,
						            bHit, bGroup, bStep, bIndent);
				
				dtos.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
public ArrayList<BDto> sList(String select, String content, int curPage){
		
		ArrayList<BDto> sdtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
		
		System.out.println("현재 페이지>>>" + curPage);
				
		try {
			con = dataSource.getConnection();
			
			String query = "select * " + 
			               "  from ( " + 
					       "    select rownum num, A.* " +
			               "      from ( " +
					       "        select * " + 
			               "          from mvc_board " +
					       "        where " + select + " like '%" + content + "%') A" + 
			               "      where rownum <= ? ) B " + 
					       " where B.num >= ? ";
			
			System.out.println(query);
			System.out.println(">>>>" + nEnd);
			System.out.println(">>>>" + nStart);
			
			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, select);
//			pstmt.setString(2, content);
			pstmt.setInt(1, nEnd);
			pstmt.setInt(2, nStart);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				int bCategory = resultSet.getInt("bCategory");
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				System.out.println(bCategory+bName+bTitle);
				
				BDto dto = new BDto(bCategory,bId, bName, bTitle, bContent, bDate,
						            bHit, bGroup, bStep, bIndent);
				sdtos.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return sdtos;
	}
	

//	public ArrayList<BDto> list(int curPage, int bCategory) {
//		ArrayList<BDto> dtos = new ArrayList<BDto>();
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet resultSet = null;
//		
//		int nStart = (curPage -1) * listCount + 1;
//		int nEnd = (curPage -1) * listCount + listCount;
//		
//		try {
//			con = dataSource.getConnection();
//			String query = "  select * " + 
//					" from (select rownum num, A.* "+
//						"	 from (select *   "    +
//						"			 from mvc_board"  +
//						"			 order by bgroup desc, bstep asc ) A " +
//				        "   where rownum <= 10 and bCategory= 1) B "+
//				        " where B.num >= 1 and bCategory= 1";
//			// bId의 bGroup으로 bStep 순서대로 정렬
//			pstmt = con.prepareStatement(query);
//			pstmt.setInt(1, bCategory);
//			pstmt.setInt(2, nEnd);
//			pstmt.setInt(3, nStart);
//			resultSet = pstmt.executeQuery();
//			
//			while (resultSet.next()) {
//			    bCategory = resultSet.getInt("bCategory");
//				int bId = resultSet.getInt("bId");
//				String bName = resultSet.getString("bName");
//				String bTitle = resultSet.getString("bTitle");
//				String bContent = resultSet.getString("bContent");
//				Timestamp bDate = resultSet.getTimestamp("bDate");
//				int bHit = resultSet.getInt("bHit");
//				int bGroup = resultSet.getInt("bGroup");
//				int bStep = resultSet.getInt("bStep");
//				int bIndent = resultSet.getInt("bIndent");
//				
//				BDto dto = new BDto(bCategory,bId, bName, bTitle, bContent, bDate,
//									bHit, bGroup, bStep, bIndent);
//				dtos.add(dto);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(resultSet != null) resultSet.close();
//				if(pstmt !=null) pstmt.close();
//				if(con !=null) con.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return dtos;
//	}

	public BDto contentView(String strID, String kind) {
		//글쓴 사람일 경우 조회수가 증가되지 않도록 변경 필요
		if(kind.equals("view")) {
			upHit(strID);
		}		
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				int bCategory = resultSet.getInt("bCategory");
				int bId= resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				
				dto = new BDto(bCategory,bId, bName, bTitle, bContent, bDate,
									bHit , bGroup, bStep, bIndent);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}

	private void upHit(String bId) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
			int rn = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void modify(String bId, String bName, String bTitle, String bContent) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board "+
					   "  set bName = ?, " +
					   "      bTitle = ?, " +
					   "      bContent = ? " +
					   "where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bId);
			
			int rn = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void delete(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = " delete from mvc_board where bId = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			int rn = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public BDto reply_view(String str) {
		BDto dto = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(str));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				int bCategory = resultSet.getInt("bCategory");
				int bId= resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				
				dto = new BDto(bCategory ,bId, bName, bTitle, bContent+"\n---답변 작성---\n", 
						bDate, bHit, bGroup, bStep, bIndent);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(resultSet != null) resultSet.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}

	public void reply(String bCategory,String bId, String bName, String bTitle, String bContent, String bGroup, String bStep,
			String bIndent) 
	{
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board " +
					   " (bCategory,bid, bName, bTitle, bContent, bGroup, bStep, bIndent) " +
					   " values " +
					   " (?,mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bCategory));
			pstmt.setString(2, bName);
			pstmt.setString(3, "└" + bTitle + "re");
			pstmt.setString(4, bContent);
			pstmt.setInt(5, Integer.parseInt(bGroup));
			pstmt.setInt(6, Integer.parseInt(bStep)+1);
			pstmt.setInt(7, Integer.parseInt(bIndent)+1);
			
			int rn = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
	}

	private void replyShape(String bGroup, String bStep) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bStep = bStep + 1 " +
						   " where bGroup = ? and bStep > ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bGroup));
			pstmt.setInt(2, Integer.parseInt(bStep));
			
			int rn = pstmt.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public BpageInfo articlePage(int curPage, HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
//		HttpSession session = null;
//		session = request.getSession();
		int listCount = 10;   //한 페이지당 보여줄 게시물의 갯수
		int pageCount = 10;	  //하단에 보여줄 페이지 리스트의 갯수
		
		String bCategory=request.getParameter("bCategory");
		//총 게시물의 갯수
		int totalCount = 0;
		int notice=0;
		int free=0;
		int data=0;
		
		try {
			con = dataSource.getConnection();
			
			String query = " select count(*) as total from mvc_board where bCategory='"+bCategory+"'";

//			String query = "select " + 
//					" count(case when bCategory='공지사항' then 1 end) as notice, " + 
//					" count(case when bCategory='자유게시판' then 1 end) as free, " + 
//					" count(case when bCategory='자료실' then 1 end) as data" + 
//					" from mvc_board" ;
			
//			String query = "select " + 
//					" bCategory = case when bCategory='1' then notice," + 
//					" when bCategory='2' then free, " + 
//					" when bCategory='3' then data" + 
//					" from mvc_board" ;
					
			
			
			pstmt = con.prepareStatement(query);
			resultSet = pstmt.executeQuery();
			if(resultSet.next())
				totalCount = resultSet.getInt("total");
			
//			if (resultSet.next()) {
//				notice= resultSet.getInt("notice");
//				free =resultSet.getInt("free");
//				data =resultSet.getInt("data");
//			      
//			}			 	
//				if(bCategory.equals("공지사항"))
//					totalCount = notice;
//				else if(bCategory.equals("자유게시판"))
//					totalCount = free;
//				else if(bCategory.equals("자료실"))
//					totalCount = data;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		// 총 페에지 수
		int totalPage = totalCount / listCount;
		if (totalCount % listCount > 0)
			totalPage++;
		
		//현재 페이지
		int myCurPage = curPage;
		if (myCurPage > totalPage)
			myCurPage = totalPage;
		if (myCurPage < 1)
			myCurPage = 1;
		
		//시작 페이지
		int startPage = ((myCurPage -1) / pageCount) * pageCount + 1;
		
		//끝 페이지
		int endPage = startPage + pageCount - 1;
		if (endPage > totalPage)
			endPage = totalPage;
		
		BpageInfo pinfo = new BpageInfo();
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
			
		return pinfo;
	}

	public BpageInfo sArticlePage(String select, String content, int nPage) {//검색
		
		Connection con = null;
    	PreparedStatement pstmt = null;
    	ResultSet resultSet = null;
    	
    	int listCount = 10;    // 한 페이지당 보여줄 게시물의 갯수
    	int pageCount = 10;    // 하단에 보여줄 페이지 리스트의 갯수
    	
    	// 총 게시물의 갯수
    	int totalCount = 0;    	
    	
    	try {
    		con = dataSource.getConnection();
    		// ' 뒤에 % 가 올경우 또는 % 뒤에 숫자가 올경우
    		// java 에서는 error 이 발생될 수 있음.
    		String query = "select count(*) as total from mvc_board " + 
	                       " where " + select + " like '%" + content + "%'";
    		pstmt = con.prepareStatement(query);
//    		pstmt.setString(1, search1);
//    		pstmt.setString(2, search2);
    		resultSet = pstmt.executeQuery();
    		
    		if(resultSet.next()) {
    			totalCount = resultSet.getInt("total");
    			System.out.println(totalCount);
    		}
    		System.out.println(">>>>" +query);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
    	}
    	
    	// 총 페이지 수
    	int totalPage = totalCount / listCount;
    	if(totalCount % listCount > 0)
    		totalPage++;
    	
    	// 현재 페이지
    	int myCurPage = curPage;
    	if(myCurPage > totalPage)
    		myCurPage = totalPage;
    	if(myCurPage < 1)
    		myCurPage = 1;
    	
    	// 시작 페이지
    	int startPage = ((myCurPage - 1) / pageCount) * pageCount +1;
    	
    	// 끝 페이지
    	int endPage = startPage + pageCount -1;
    	if(endPage > totalPage)
    		endPage = totalPage;
    	
    	BpageInfo pinfo = new BpageInfo();
    	pinfo.setTotalCount(totalCount);
    	pinfo.setListCount(listCount);
    	pinfo.setTotalPage(totalPage);
    	pinfo.setCurPage(curPage);
    	pinfo.setPageCount(pageCount);
    	pinfo.setStartPage(startPage);
    	pinfo.setEndPage(endPage);
    	
		return pinfo;
	}
	
//	private Connection getConnection() {
//		
//		Context context = null;
//		DataSource dataSource = null;
//		Connection con = null;
//		
//		try {
//			//lookup 함수의 파라메터는 context.xml에 설정된
//			//name(jdbc/Oracle11g)와 동일해야 한다.
//			context = new InitialContext();
//			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
//			con = dataSource.getConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return con;
//	}
}
