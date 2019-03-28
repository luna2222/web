<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>search.jsp</title>
</head>
<body>
 <form action="search.do?page=<%= session.getAttribute("cpage") %>" method="post">
         
         <tr>
             search : <select name="sSelect">
                       <option value="bName">작성자</option>
                       <option value="bTitle">제목</option>
                       <option value="bContent">내용</option>
                           
                      </select>
                      
        <input type="text" name="sContent" /><br>
        <input type="submit" value="검색시작">
    
     </form>

</body>
</html>