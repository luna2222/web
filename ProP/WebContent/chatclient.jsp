<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String chatName = request.getParameter("chatName");
	String userId = (String)session.getAttribute("mId");
	if(chatName == null){
%>
	<jsp:forward page="chatlogin.jsp"/>
<%
	}else{
		session.setAttribute("chatName", chatName);
	}
%>
	<div>
		사용자 아이디 : <%= userId %>
		대화명 : <%=chatName %>
	</div>

	<div>
		<input type="text" id="messageinput" />
	</div>
	<div>
		<button type="button" onclick="openSocket();">open</button>
		<button type="button" onclick="send();">send</button>
		<button type="button" onclick="closeSocket();">close</button>
		<button type="button" onclick="exit();">exit</button>
	</div>
	
	<!-- Server responses get written here -->
	<div id="messages"></div>
	
	<!-- Script to utilize the WebSocket -->
	<script type="text/javascript">
		var webSocket;
		var messages = document.getElementById("messages");
		
		function openSocket(){
			//Ensure only one connection is open at a time
			if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
				writeResponse("WebSocket is already opened.");
				return;
			}
			writeResponse("0");
			//Create a new instance of the websocket
			//webSocket = new WebSocket("ws://localhost/   *ProjectName*   /echo");
			webSocket = new WebSocket("ws://localhost:8081/Project02/websocketendpoint2");
			writeResponse("1");
			/**
			*Bind functions to the listeners for the websocket.
			*/
			webSocket.onopen = function(event){
				//For reason I can't determine, onopen gets called twice
				//and the first time event.dats is undefined.
				//Leave a comment if you know the answer.
				if(event.data == undefined)
					return;
				
				writeResponse(event.data);
			};
			
			webSocket.onmessage = function(event){
				writeResponse(event.data);
			};
			
			webSocket.onclose = function(event){
				writeResponse("Connection closed");
			};
		}
		
		/*
		*Sends the value of the text input to the server
		*/
		function send(){
			var chatName = "<%= chatName %>";
			var text = document.getElementById("messageinput").value;
			webSocket.send(chatName + "|" + text);
		}
		
		function closeSocket(){
			webSocket.close();
		}
		
		function exit(){
			alert("퇴장")
			location.href="main.jsp"
			webSocket.close();
		}
		
		function writeResponse(text){
			messages.innerHTML += "<br/>" + text;
		}
	</script>

</body>
</html>