package kr.or.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class AllMemberChat extends TextWebSocketHandler{		//메소드가 세개 있다 이게 자바스크립트랑 같은
	
	//접속한 회원의 세션을 저장하는 리스트
	private ArrayList<WebSocketSession> sessionList;
	//각 세션별로 접속한 회원의 아이디를 저장하는 map
	private HashMap<WebSocketSession, String> memberList;
	
	
	public AllMemberChat() {
		super();
		// TODO Auto-generated constructor stub
		
		//객체 생성
		sessionList = new ArrayList<WebSocketSession>();
		memberList = new HashMap<WebSocketSession, String>();
	}

	//클라이언트가 최초로 웹소켓 서버에 접속했을 때 수행되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		System.out.println("클라이언트가 접속함");
		//새로 접속한 클라이언트의 웹소켓 세션을 리스트에 추가
		sessionList.add(session);
	}
	
	//클라이언트가 서버로 메세지를 전송햇을 때 수행되는 메소드(실제 웹소켓로직 구현하는 메소드)
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		System.out.println(message.getPayload()); //보낸거 바로 받아오기
		
		//문자열을 JSON타입으로 처리하기 위한 객체 생성
		JsonParser parser = new JsonParser();
		
		//parser을 이용하여 JSON데이터를 분석
		JsonElement element = parser.parse(message.getPayload());
		
		//jsp에서 보내준 키값으로 데이터를 추출하기 (type, msg)
		String type = element.getAsJsonObject().get("type").getAsString();	//"enter"가 들어있겠죠 여기엔
		String msg = element.getAsJsonObject().get("msg").getAsString();	//"user05"가 들어있겠죠
		
		if(type.equals("enter")) {
			//map에 세션에 해당하는 memberId를 지정
			memberList.put(session, msg);		//맵에다가 아이디 넣어놓음
			String sendMsg = "<p>"+msg+"님이 입장하셨습니다</p>";
			for(WebSocketSession s : sessionList) {
				if(!session.equals(s)) { //본인에게 전송은 제외한다
					TextMessage tm = new TextMessage(sendMsg);
					s.sendMessage(tm);
				}
			}
		}else if(type.equals("chat")){
			String sendMsg = "<div class='chat left'><span class='chatId'>"+memberList.get(session)+" : </span>"+msg+"</div>";
			for(WebSocketSession s : sessionList) {
				if(!session.equals(s)) {
					TextMessage tm = new TextMessage(sendMsg);
					s.sendMessage(tm);					
				}
			}
		}
		//jsp에서 웹소켓을 통해 보내준 메시지값(문자열) 확인
		//TextMessage tm = new TextMessage("<div class='chat left'>"+message.getPayload()+"</div>");
		
		
		//접속한 세션에 메세지 전송
		//session.sendMessage(tm);
	}
	
	//클라이언트가 연결을 끊을 때 수행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		//접속이 끊긴 세션을 리스트에서 제거
		sessionList.remove(session);
		for(WebSocketSession s : sessionList) {
			TextMessage tm = new TextMessage("<p>"+memberList.get(session)+"님이 퇴장하셨습니다.</p>");
			s.sendMessage(tm);
		}
		memberList.remove(session);
	}
}
