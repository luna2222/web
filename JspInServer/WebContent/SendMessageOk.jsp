<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.net.URLEncoder" %>
 <%@page import="org.json.simple.JSONArray" %>
 <%@page import="org.json.simple.JSONObject"%>
 <%@page import="java.io.BufferedReader" %>
 <%@page import="java.io.IOException" %>
 <%@page import="java.io.InputStreamReader" %>
 <%@page import="java.io.OutputStreamWriter" %>
 <%@page import="java.net.HttpURLConnection" %>
 <%@page import="java.net.URL" %>
 
 <%!
 
 String ApiKey= "AIzaSyCmYv_eDKDHHtvcAtAUNQL4g7Ybjl-LWNM ";
 String fcmURL="https://fcm.googleapis.com/fcm/send";
 %>
<!DOCTYPE html>
<html>
<head>
<meta Http-equiv=Content-Type" content="text/html; charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	response.setCharacterEncoding("utf-8");
	request.setCharacterEncoding("utf-8");


	String notiTitle=request.getParameter("notiTitle");
	String notiBody=request.getParameter("notiBody");
	String msg=request.getParameter("message");
	
	try {	
	//데이터베이스에 저장된 토큰을 자져와 ArrayList에 저장
	String deviceId1= "fz8T1bjP-yI:APA91bHpgXwkKIxc5vzISLx9wLFDHGFwnIhms5iXiykSP4dSueX6hsJBHY9lgsdTESvCz-Azs479onk52NRPrz-JvKdSrFuadu_rfzkiOL1tU4TjE67jv2tWcHF0UWzZrJv_B4vL5ZAY";
	String deviceId2= "fz8T1bjP-yI:APA91bHpgXwkKIxc5vzISLx9wLFDHGFwnIhms5iXiykSP4dSueX6hsJBHY9lgsdTESvCz-Azs479onk52NRPrz-JvKdSrFuadu_rfzkiOL1tU4TjE67jv2tWcHF0UWzZrJv_B4vL5ZAY";
	
	JSONArray deviceId= new JSONArray();
	deviceId.add(deviceId1);
	deviceId.add(deviceId2);
	
	URL url= new URL(fcmURL);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	
	conn.setUseCaches(false);
	conn.setDoInput(true);
	conn.setDoOutput(true);
	
	conn.setRequestMethod("POST");
	conn.setRequestProperty("Authorization","key="+ApiKey);
	conn.setRequestProperty("Content-Type","application/json");
	
	JSONObject json= new JSONObject();
	
	JSONObject noti= new JSONObject();
	noti.put("title",notiTitle);  // Notification title
	noti.put("body",notiBody);  // Notification 알림
	
	JSONObject data =new JSONObject();
	data.put("message",msg);  //Data-message:value
	
	//json.put("to",deviceId); // 한명에게 보냄
	json.put("registration_ids",deviceId);    //여러명에게 보낼때  
	json.put("notification",noti);
	json.put("data",data);
	
	System.out.println(json.toString());
	
	try{
		
		OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
		wr.write(json.toString());
		wr.flush();
		
		BufferedReader br= new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
		String output;
		System.out.println("Output from Server...\n");
		while ((output= br.readLine())!= null){
			System.out.println(output);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	
	out.print(notiTitle+"<br>");
	out.print(notiBody+"<br>");
	out.print("Firebase Cloud Message가 발송되었습니다");
}catch(Exception e){
	e.printStackTrace();
}

%>
</body>
</html>