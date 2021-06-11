package kr.or.dm.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class Alarm extends TextWebSocketHandler{
	@Autowired
	private DmService service;
}
