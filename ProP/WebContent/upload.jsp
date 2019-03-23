<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
<%@page import="project.jsp.dao.FileDao" %>
<%@page import="java.util.Enumeration" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String fName = (String)session.getAttribute("mId");
	String fTitle = "";
	String fContent = "";
	String fileName = "";
	String orgfileName = "";
	
	System.out.println("h");
	//String uploadPath = request.getSession().getServletContext().getRealPath("/");
	//String uploadPath = request.getRealPath("fileUpload"); // upload는 폴더명 / 폴더의 경로를 구해옴
	String uploadPath = "D:/janghj0429/fileUpload";
	System.out.println(uploadPath);

	try {
		MultipartRequest multi = new MultipartRequest( // MultipartRequest 인스턴스 생성(cos.jar의 라이브러리)
				request, 
				uploadPath, // 파일을 저장할 디렉토리 지정
				10 * 1024 *1024, // 첨부파일 최대 용량 설정(bite) / 10KB / 용량 초과 시 예외 발생
				"utf-8", // 인코딩 방식 지정
				new DefaultFileRenamePolicy() // 중복 파일 처리(동일한 파일명이 업로드되면 뒤에 숫자 등을 붙여 중복 회피)
		);
		System.out.println("h2");
		
		Enumeration files = multi.getFileNames();
		String str = (String)files.nextElement();
		
		fTitle = multi.getParameter("fTitle");
		fContent = multi.getParameter("fContent");

		/* form의 <input type="file"> name값을 모를 경우 name을 구할때 사용
		Enumeration files=multi.getFileNames(); // form의 type="file" name을 구함
		String file1 =(String)files.nextElement(); // 첫번째 type="file"의 name 저장
		String file2 =(String)files.nextElement(); // 두번째 type="file"의 name 저장
		*/

		fileName = multi.getFilesystemName(str); // name=file1의 업로드된 시스템 파일명을 구함(중복된 파일이 있으면, 중복 처리 후 파일 이름)
		orgfileName = multi.getOriginalFileName(str); // name=file1의 업로드된 원본파일 이름을 구함(중복 처리 전 이름)

		
	} catch (Exception e) {
		e.getStackTrace();
	} // 업로드 종료
	
	FileDao dao = FileDao.getInstance(); 
	dao.insertFile(fName, fTitle, fContent, fileName, orgfileName);
%>    
<script>
	alert("업로드 완료");
	window.location.replace("filelist.do");
</script>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>