<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <!-- Bootstrap CSS -->
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>client</title>
</head>
<body>
<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		if (id == null) {
%>
	<jsp:forward page="login.jsp" />
<%  
    } else { 
	session.setAttribute("uid",id);
	} 
%>
	<div class="container">
	 <div class="form-group">
	<div>
		사용자 아이디 :<%=id%>
	</div>
	<div>
		<input type="text" id="messageinput" /><br/>
	</div>
	<div>
	  
		<button type="button" class="btn btn-primary"value="Open" onclick="openSocket();">Open</button>
		<button type="button" class="btn btn-primary"value="Send" onclick="send();">Send</button>
		<button type="button" class="btn btn-primary"value="Close"onclick="closeSocket();">Close</button>
	  </div>
	</div>
	</div>
	<!-- sever responses get written here -->
	<div id="messages"></div>

	<script type="text/javascript">
		var webSocket;
		var messages=document.getElementById("messages");
		
		function openSocket(){
			// Ensures only one connection is open at a time
		    if(webSocket !=undefined && webSocket.readyState !=WebSocket.CLOSED){
			    writeResponse("WebSocket is already opened.");
			    return;	
		    }
		    //create a new instance of the websocket
		    //webSocket= new WebSocket("ws://localhost/   *PreojectName */echo");
		    
		webSocket =new WebSocket("ws://localhost:8081/WebSocketEx/websocketendpoint2");
		/**
    	* Binds functions to the listeners for the websocket.        	
    	*/
		webSocket.onopen= function(event){
    		// For reasns I can't determine, onopen gets called twice
    		// and the first time event.data is undefined.
    		// Leave a comment if you know the answer.
			if(event.data==undefined)
				return;
			writeResponse(event.data);
			};
			webSocket.onmessage=function(event){
			   writeResponse(event.data);
			};
			webSocket.onclose=function(event){
				   writeResponse("Connection closed");
				};
			}
		 
       	 		/**
    			* Sends the value of the text input to the server        	
    			*/
				function send(){
					var id = "<%= id %>";
					var text =document.getElementById("messageinput").value;
					webSocket.send(id+ " | " +text);
				}
				function closeSocket(){
					webSocket.close();
				}
				function writeResponse(text){
					messages.innerHTML +="<br/>"+text;
				}			
		</script>
</body>
</html>