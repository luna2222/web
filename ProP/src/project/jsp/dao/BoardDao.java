package project.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import project.jsp.dto.BoardDto;
import project.jsp.frontcontroller.BPageInfo;

public class BoardDao {

	DataSource dataSource;
	
	int listCount = 5;	//한페이지당 보여줄 게시물의 갯수
	int pageCount = 5;	//하단에 보여줄페이지 리스트의 갯수
	
	private static BoardDao instance = new BoardDao();
	private BoardDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BoardDao getInstance() {
		return instance;
	} // 프라이빗 을 퍼블릭스태틱 으로 : 싱글턴패턴
	
	public BPageInfo articlePage(int curPage, int boardCategory){
		
		System.out.println("아티클");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int listCount = 5;	// 한페이지당 보여줄 게시물의 객수
		int pageCount = 5;	// 하단에 보여줄 페이지리스트의 객수
		
		//총 게시물의 갯수
		int totalCount = 0;
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from ("
						 + "select * from mvc_board where bCategory = ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardCategory);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				totalCount = resultSet.getInt("total");
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
		
		//총페이지 수
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
			totalPage++;
		
		//현재페이지
		int myCurPage = curPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		//시작페이지
		int startPage = ( (myCurPage -1)/pageCount ) * pageCount + 1;
		
		//끝페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage)
			endPage = totalPage;
		System.out.println("아티클 : " + boardCategory);
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setBoardCategory(boardCategory);
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public ArrayList<BoardDto> list(int curPage, int boardCategory){
		
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
//		System.out.println("리스트카테고리 : " + Category);
//		System.out.println("start: " + nStart + " end : " + nEnd);
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from( " +
						   "select rownum num, A.* from( " +
						   "select * from( " +
						   "select * from mvc_board where bcategory=?) " +
						   "order by bgroup desc, bstep asc) A " +
						   "where rownum <= ?) B where b.num >= ? ";
			
//			String query = "select * " +
//						   " from(" +
//						   "  select rownum num, A.* " +
//						   "    from(" + 
//						   "     select * " +
//						   "      from mvc_board where bCategory = ?" +
//						   "      order by bgroup desc, bstep asc) A " +
//						   "      where rownum <= ?) B " + 
//						   " where b.num >= ?";
			System.out.println("here");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardCategory);
			pstmt.setInt(2, nEnd);
			pstmt.setInt(3, nStart);
			resultSet = pstmt.executeQuery();
//			System.out.println( resultSet.getInt("bCategory") + " listDao");
			System.out.println("here2");
			while(resultSet.next()) {
				System.out.println("here3");
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
				System.out.println("here4");
				BoardDto dto = new BoardDto(bCategory, bId, bName, bTitle, bContent, bDate,
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
	
	public void write(int bCategory, String bName, String bTitle, String bContent) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board " + 
						   " (bCategory, bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) " +
						   " values " +
						   " (?, mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0 )";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bCategory);
			pstmt.setString(2, bName);
			pstmt.setString(3, bTitle);
			pstmt.setString(4, bContent);
			int rn = pstmt.executeUpdate();
			System.out.println("write 메서드 실행");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public BoardDto contentView(String strID, String kind) {
		if(kind.equals("view") ) {
			upHit(strID);			
		}
		
		BoardDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
	
		try{
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strID));
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
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
				
				dto = new BoardDto(bCategory, bId, bName, bTitle, bContent, bDate,
						bHit, bGroup, bStep, bIndent);
				System.out.println("content dao진행");
			}
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(resultSet != null) resultSet.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	private void upHit(String bId) {
		//contentView 메서드에서 호출됨. 히트수 증가 
		//현재는 수정하면 2개씩 증가됨 게시판 작성시에는 수정해서 작업.
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			
			int rn = pstmt.executeUpdate();
			
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void modify(String bId, String bTitle, String bContent)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String query = "update mvc_board " +
					   " set bTitle = ?, " +
					   "	 bContent = ? " +
					   " where bId = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bContent);
			pstmt.setString(3, bId);
			int rn = pstmt.executeUpdate();
			
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public void delete(String bId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String query = "delete from mvc_board " +
					   " where bId = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bId);
			System.out.println(bId +" delete boardDao진행");
			int rn = pstmt.executeUpdate();
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
	}
	
	public BoardDto reply_view(String str) {
		
		BoardDto dto = null;
		
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
				int bId = resultSet.getInt("bId");
				String bName = resultSet.getString("bName");
				String bTitle = resultSet.getString("bTitle");
				String bContent = resultSet.getString("bContent");
				Timestamp bDate = resultSet.getTimestamp("bDate");
				int bHit = resultSet.getInt("bHit");
				int bGroup = resultSet.getInt("bGroup");
				int bStep = resultSet.getInt("bStep");
				int bIndent = resultSet.getInt("bIndent");
				
				dto = new BoardDto(bCategory, bId, bName, bTitle, bContent, bDate,
						bHit, bGroup, bStep, bIndent); 
			}
			
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}

	
	public void reply(String bCategory, String bId, String bName, String bTitle,  
			String bContent, String bGroup, String bStep, String bIndent)	{
		
		replyShape(bGroup, bStep);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "insert into mvc_board " +
						   " (bCategory, bId, bName, bTitle, bContent, bGroup, bStep, bIndent) " +
						   " values (?, mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, Integer.parseInt(bCategory));
			pstmt.setString(2, bName);
			pstmt.setString(3, bTitle);
			pstmt.setString(4, bContent);
			pstmt.setInt(5, Integer.parseInt(bGroup));
			pstmt.setInt(6, Integer.parseInt(bStep) + 1);
			pstmt.setInt(7, Integer.parseInt(bIndent) + 1);
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
		
	private void replyShape(String strGroup, String strStep) {
			
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			String query = "update mvc_board " +
						   " set bStep = bStep + 1" +
						   " where bGroup = ? and bStep > ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			
			int rn = pstmt.executeUpdate();
		}catch(Exception e)	{
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}	
//	public int confirmNCT(String bName, String bContent, String bTitle) {
//		int ri = 0;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet resultSet = null;
//		
//		try {
//			con = dataSource.getConnection();
//			String query = "select count(*) as total from ( "
//					 + " select * from mvc_board where bName = ? "
//					 + " or bContent = ? or bTitle = ?)";
//			pstmt = con.prepareStatement(query);
//			pstmt.setString(1, bName);
//			resultSet = pstmt.executeQuery();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(resultSet != null) resultSet.close();
//				if(pstmt != null) pstmt.close();
//				if(con != null) con.close();
//			}catch(Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return ri;
//	}
	
	public BPageInfo articleNamePage(int curPage, String bName){
		
		System.out.println("아티클");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int listCount = 5;	// 한페이지당 보여줄 게시물의 객수
		int pageCount = 5;	// 하단에 보여줄 페이지리스트의 객수
		
		//총 게시물의 갯수
		int totalCount = 0;
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from ("
						 + "select * from mvc_board where bName = ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				totalCount = resultSet.getInt("total");
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
		
		//총페이지 수
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
			totalPage++;
		
		//현재페이지
		int myCurPage = curPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		//시작페이지
		int startPage = ( (myCurPage -1)/pageCount ) * pageCount + 1;
		
		//끝페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage)
			endPage = totalPage;
		System.out.println("아티클 : " + bName);
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setBoardName(bName);
		pinfo.setBoardContent("");
		pinfo.setBoardTitle("");
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public ArrayList<BoardDto> nameList(int curPage, String str){
		
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;
//		System.out.println("리스트카테고리 : " + Category);
//		System.out.println("start: " + nStart + " end : " + nEnd);
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from( " +
						   "select rownum num, A.* from( " +
						   "select * from( " +
						   "select * from mvc_board where bName=?) " +
						   "order by bgroup desc, bstep asc) A " +
						   "where rownum <= ?) B where b.num >= ? ";
			
//			String query = "select * " +
//						   " from(" +
//						   "  select rownum num, A.* " +
//						   "    from(" + 
//						   "     select * " +
//						   "      from mvc_board where bCategory = ?" +
//						   "      order by bgroup desc, bstep asc) A " +
//						   "      where rownum <= ?) B " + 
//						   " where b.num >= ?";
			System.out.println("here");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, str);
			pstmt.setInt(2, nEnd);
			pstmt.setInt(3, nStart);
			resultSet = pstmt.executeQuery();
//			System.out.println( resultSet.getInt("bCategory") + " listDao");
			System.out.println("here2");
			while(resultSet.next()) {
				System.out.println("here3");
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
				System.out.println("here4");
				BoardDto dto = new BoardDto(bCategory, bId, bName, bTitle, bContent, bDate,
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
		
	public BPageInfo articleContentPage(int curPage, String bContent){
		
		System.out.println("아티클");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int listCount = 5;	// 한페이지당 보여줄 게시물의 객수
		int pageCount = 5;	// 하단에 보여줄 페이지리스트의 객수
		
		//총 게시물의 갯수
		int totalCount = 0;
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from ("
						 + "select * from mvc_board where bContent like ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+bContent+"%");
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				totalCount = resultSet.getInt("total");
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
		
		//총페이지 수
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
			totalPage++;
		
		//현재페이지
		int myCurPage = curPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		//시작페이지
		int startPage = ( (myCurPage -1)/pageCount ) * pageCount + 1;
		
		//끝페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage)
			endPage = totalPage;
		System.out.println("아티클 : " + bContent);
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setBoardName("");
		pinfo.setBoardContent(bContent);
		pinfo.setBoardTitle("");
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public ArrayList<BoardDto> contentList(int curPage, String str){
		
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;

		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from( " +
						   "select rownum num, A.* from( " +
						   "select * from( " +
						   "select * from mvc_board where bContent like ?) " +
						   "order by bgroup desc, bstep asc) A " +
						   "where rownum <= ?) B where b.num >= ? ";
			
			System.out.println("here");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+str+"%");
			pstmt.setInt(2, nEnd);
			pstmt.setInt(3, nStart);
			resultSet = pstmt.executeQuery();
//			System.out.println( resultSet.getInt("bCategory") + " listDao");
			System.out.println("here2");
			while(resultSet.next()) {
				System.out.println("here3");
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
				System.out.println("here4");
				BoardDto dto = new BoardDto(bCategory, bId, bName, bTitle, bContent, bDate,
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
	
	public BPageInfo articleTitlePage(int curPage, String bTitle){
		
		System.out.println("아티클");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int listCount = 5;	// 한페이지당 보여줄 게시물의 객수
		int pageCount = 5;	// 하단에 보여줄 페이지리스트의 객수
		
		//총 게시물의 갯수
		int totalCount = 0;
		try {
			con = dataSource.getConnection();
			
			String query = "select count(*) as total from ("
						 + "select * from mvc_board where bTitle like ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+bTitle+"%");
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				totalCount = resultSet.getInt("total");
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
		
		//총페이지 수
		int totalPage = totalCount / listCount;
		if(totalCount % listCount > 0)
			totalPage++;
		
		//현재페이지
		int myCurPage = curPage;
		if(myCurPage > totalPage)
			myCurPage = totalPage;
		if(myCurPage < 1)
			myCurPage = 1;
		
		//시작페이지
		int startPage = ( (myCurPage -1)/pageCount ) * pageCount + 1;
		
		//끝페이지
		int endPage = startPage + pageCount - 1;
		if(endPage > totalPage)
			endPage = totalPage;
		System.out.println("아티클 : " + bTitle);
		
		BPageInfo pinfo = new BPageInfo();
		pinfo.setBoardName("");
		pinfo.setBoardContent("");
		pinfo.setBoardTitle(bTitle);
		pinfo.setTotalCount(totalCount);
		pinfo.setListCount(listCount);
		pinfo.setTotalPage(totalPage);
		pinfo.setCurPage(curPage);
		pinfo.setPageCount(pageCount);
		pinfo.setStartPage(startPage);
		pinfo.setEndPage(endPage);
		
		return pinfo;
	}
	
	public ArrayList<BoardDto> titleList(int curPage, String str){
		
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		int nStart = (curPage - 1) * listCount + 1;
		int nEnd = (curPage - 1) * listCount + listCount;

		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from( " +
						   "select rownum num, A.* from( " +
						   "select * from( " +
						   "select * from mvc_board where bTitle like ?) " +
						   "order by bgroup desc, bstep asc) A " +
						   "where rownum <= ?) B where b.num >= ? ";
			

			System.out.println("here");
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+str+"%");
			pstmt.setInt(2, nEnd);
			pstmt.setInt(3, nStart);
			resultSet = pstmt.executeQuery();
//			System.out.println( resultSet.getInt("bCategory") + " listDao");
			System.out.println("here2");
			while(resultSet.next()) {
				System.out.println("here3");
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
				System.out.println("here4");
				BoardDto dto = new BoardDto(bCategory, bId, bName, bTitle, bContent, bDate,
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
	
	
	
	
}
