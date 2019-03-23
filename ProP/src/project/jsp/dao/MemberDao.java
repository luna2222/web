package project.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import project.jsp.dao.MemberDao;

import project.jsp.dto.MemberDto;


	public class MemberDao {
	
	public static final int MEMBER_NONEXISTENT = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	public static final int MEMBER_DELETE_SUCCESS = 1;
	
	private static MemberDao instance = new MemberDao();
	
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	public int userCheck(String mId, String mPw) {
		int ri = 0;
		String dbPw;
		System.out.println(mId +":" + mPw);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select mpw from members where mid = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			set = pstmt.executeQuery();
			System.out.println("유저체크진행");
			
			if(set.next()) {
				dbPw = set.getString("mpw");
				if(dbPw.equals(mPw)) {
					System.out.println("login ok");
					ri = MemberDao.MEMBER_LOGIN_SUCCESS; // 로그인 ok
				}else {
					System.out.println("login pw fail");
					ri = MemberDao.MEMBER_LOGIN_PW_NO_GOOD; // 비번 x
				}
			}else {
				System.out.println("login id fail");
				ri = MemberDao.MEMBER_LOGIN_IS_NOT;		// 아이디 x
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;	
	}
	
	public MemberDto getMember(String mId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		String query = "select * from members where mid = ?";
		MemberDto dto = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next()) {
				dto = new MemberDto();
				System.out.println("dto 생성 ok");
				dto.setmId(resultSet.getString("mId"));
				dto.setmPw(resultSet.getString("mPw"));
				dto.setmName(resultSet.getString("mName"));
				dto.setmEmail(resultSet.getString("mEmail"));
				dto.setmDate(resultSet.getTimestamp("mDate"));
				dto.setmAddress(resultSet.getString("mAddress"));
				System.out.println("겟멤버 ok");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				pstmt.close();
				con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;	
	}
	
	public int confirmId(String mId) {
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select * from members where mId = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			set = pstmt.executeQuery();
			if(set.next()) {
				ri = MemberDao.MEMBER_EXISTENT;
			}else {
				ri = MemberDao.MEMBER_NONEXISTENT;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				set.close();
				pstmt.close();
				con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int insertMember(MemberDto dto) {
		
		int ri = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into members values (?,?,?,?,?,?)";
		System.out.println("insert member");
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getmId());
			pstmt.setString(2, dto.getmPw());
			pstmt.setString(3, dto.getmName());
			pstmt.setString(4, dto.getmEmail());
			pstmt.setTimestamp(5, dto.getmDate());
			pstmt.setString(6, dto.getmAddress());
			pstmt.executeUpdate();
			ri = MemberDao.MEMBER_JOIN_SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}
	
	public int deleteMember(String mId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from members where mid=?";
		int ri = 0;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mId);
			pstmt.executeUpdate();
			ri = MemberDao.MEMBER_DELETE_SUCCESS;
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
		return ri;
	}
	
	public void deleteDummy() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from members where mName=?";
		String dummy = "dummy";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dummy);
			pstmt.executeUpdate();
			System.out.println("더미 삭제");
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

	
	
	
	private Connection getConnection() {
		
		Context context = null;
		DataSource dataSource = null;
		Connection con = null;

		try {
			//lookup 함수의 파라메터는 context.xml에 설정된 name과 동일해야한다.
			//name(jdbc/Oracle11g)
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			con = dataSource.getConnection();
		}catch(Exception e) {
			System.out.println("================ \n");
			e.printStackTrace();
		}
		
		return con;
	}
}
