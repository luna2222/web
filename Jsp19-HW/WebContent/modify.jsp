<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%!
    Connection con;
    Statement stmt;
    ResultSet resultSet;
    

	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String uid="scott";
	String upw="tiger";
	String query="select * from member";
  
    String name,id,pw,phone1,phone2,phone3, gender;
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
id=(String)session.getAttribute("id");

String query="select * from member where id ='"+id+"'";

Class.forName(driver);
con = DriverManager.getConnection(url, uid, upw);
stmt = con.createStatement();
resultSet = stmt.executeQuery(query);
String phone=" ";
while (resultSet.next()) 
{
    name = resultSet.getString("name");
    pw = resultSet.getString("pw");
	phone = resultSet.getString("phone");
	gender = resultSet.getString("gender");
}
	phone1=phone.substring(1,3);
	phone2=phone.substring(4,8);
	phone3=phone.substring(9,13);
	%>
	
	<form action="ModifyProcess" method="post">
	아이디:<%=id %><br/>
	비밀번호:<input type="password" name="pw"size="10"><br>
	이름: <input type="text" name="name" size="10"value=<%=name%>><br>
	전화번호:<select name="phone1">
		<option value="010">010</option>
		<option value="016">016</option>
		<option value="017">017</option>
		<option value="018">018</option>
		<option value="019">019</option>
		<option value="011">011</option>	
		</select>-
		
	<input type="text" name="phone2" size="5" value=<%=phone2 %>>-
	<input type="text" name="phone3" size="5"value=<%=phone3 %>><br>
	<%
	if(gender.equals("man")){
	%>
	 성별구분:<input type="radio" name="gender" value="man"checked="checked">남&nbsp;
	        <input type="radio" name="gender" value="woman">여<br>
	        <%
	} else {
	        %>
    성별구분:<input type="radio" name="gender" value="man">남&nbsp;
	        <input type="radio" name="gender" value="woman" checked="checked">여<br>
	 <%
	}
	 %>       
	<input type="submit"value ="정보 수정">
</form>	
</body>
</html>