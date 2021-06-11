package kr.or.dm.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.dm.model.service.DmService;
import kr.or.dm.model.vo.Dm;


@Controller
public class DmController {
	@Autowired
	private DmService service;
	
	
	@RequestMapping(value="/dmList.do")
	public String dmList(String memberId,Model model) {
		ArrayList<Dm> getList = service.selectGetDmList(memberId);
		ArrayList<Dm> sendList = service.selectSendDmList(memberId);
		/*
		 * for (int i = 0; i<sendList.size(); i++) {
		 * if((sendList.get(i).getReadStatus()).equals("0")) {
		 * System.out.println("아오어케바꾸지"); }else { System.out.println(""); } }
		 */
		model.addAttribute("getList", getList);
		model.addAttribute("sendList", sendList);
		return "dm/dmList";
	}
	
	@ResponseBody
	@RequestMapping(value="/sendDm.do")
	public String sendDm(Dm dm) {
		int result = service.sendDm(dm);
		if(result > 0) {
			return "1";
		}else {			
			return "0";
		}
	}
}
