<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<style>
.chatting {
	width: 500px;
	display: none;
}

.messageArea {
	overflow-y: auto;
	border: 1px solid black;
	height: 500px;
	display: flex;
	flex-direction: column;
	background-color: #b2c7d9;   
}

.messageArea>p {
	text-align: center;
	width: 100%;
}

#sendMsg {
	width: 75%;
}

#sendBtn {
	width: 20%;
}

.chat {
	margin-bottom: 10px;
	padding: 8px;
	border-radius: 3px;
}

.right {
	background-color:  #ffeb33;
	align-self: flex-end;
}

.left {
	background-color: #ffffff;
	align-self: flex-start;
}
</style>
<body>
	<button onclick="initChat('${sessionScope.m.memberId}')">채팅시작</button>
	<!-- ID를 매개변수로 보내면서 채팅시작 -->
	<hr>
	<div class="chatting">
		<div class="messageArea"></div>
		<div class="sendBox">
			<input type="text" id="sendMsg">
			<button id="sendBtn" onclick="sendMsg();">전송</button>
		</div>
	</div>
	<script>
		var ws;
		var memberId;
		function initChat(param) {
			memberId = param;

			//웹소켓 연결시도
			ws = new WebSocket("ws://192.168.10.14:70/chat.do");

			//소켓 연결 성공시 자동으로 실행 될 함수 지정 
			ws.onopen = startChat;

			//소켓으로 서버가 데이터를 전송하면 로직을 실행 할 함수
			ws.onmessage = receiveMsg;

			//소켓연결이 종료되면 수행할 함수 지정
			ws.onclose = endChat;

			$(".chatting").slideDown();

		}
		function startChat() {
			var data = {type:"enter",msg:memberId};		//들어왔단 enter라는 것과 msg에 멤버아이디를 객체로 만들어놓는다
			ws.send(JSON.stringify(data));			//자바스크립트 객체를 문자열로 변환해서 서버로 전송하는 것
			appendChat("<p>채팅방에 입장하셨습니다.</p>;");
		}
		function receiveMsg(param) {
			appendChat(param.data);
		}
		function endChat() {
			apeendChat('<p>채팅이 종료되었습니다.</p>');
		}
		function appendChat(msg) {
			$('.messageArea').append(msg);
			$('.messageArea').scrollTop($(".messageArea")[0].scrollHeight);
			//최신메세지 보여주기위한 스크롤 내리기 기능
			//메세지가 양쪽으로 나오다가 스크롤 생기면 아래로 내려서 최신메시지 보여주게 하기 위해
		}
		function sendMsg(){		//글씨 입력하고 버튼 누르면 보내지기
			var msg = $("#sendMsg").val();
			if(msg!=''){
				var data ={type:"chat",msg:msg};
				//소켓 서버로 문자열 전송
				ws.send(JSON.stringify(data));
				//내 화면에 출력
				appendChat("<div class='chat right'>"+msg+"</div>");
				$("#sendMsg").val("");
			}
		}
	</script>
</body>
</html>