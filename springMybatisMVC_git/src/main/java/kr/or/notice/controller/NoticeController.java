package kr.or.notice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.notice.model.vo.Notice;
import kr.or.notice.service.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService service;

	public NoticeController() {
		super();
		System.out.println("notice컨트롤러 생성!");
	}
	
	@RequestMapping(value="/noticeList.do")
	public String noticeList(Model model) {
		ArrayList<Notice> list = service.noticeList();
		model.addAttribute("list", list);
		return "notice/noticeList";
	}
	@RequestMapping(value="/noticeView.do")
	public String noticeView(int noticeNo, Model model) {
		Notice n = service.selectOneNotice(noticeNo);
		model.addAttribute("n", n);
		return "notice/noticeView";
	}
	@RequestMapping(value="/noticeDelete.do")
	public String noticeDelete(int noticeNo,Model model) {
		int result = service.noticeDelete(noticeNo);
		if(result > 0) {
			model.addAttribute("msg", "삭제성공띠!");
		}else {
			model.addAttribute("msg", "삭제성공띠!");			
		}
		model.addAttribute("loc", "/noticeList.do");
		return "common/msg";
	}
	@RequestMapping(value = "/noticeUpdateFrm.do")
	public String noticeUpdateFrm(int noticeNo,Model model) {
		Notice n = service.selectOneNotice(noticeNo);
		model.addAttribute("n", n);
		return "notice/noticeUpdateFrm";
	}
	@RequestMapping(value = "/updateNotice.do")
	public String noticeUpdate(Notice n, Model model) {
		int result = service.noticeUpdate(n);
		return "redirect:noticeView.do?noticeNo="+n.getNoticeNo();	//이렇게 서블릿에서 서블릿 보낼 땐 do를 붙여줘야지 !
	}
	@RequestMapping(value = "/writeNoticeFrm.do")
	public String writerNoticeFrm() {
		return "notice/writeNoticeFrm";
	}
	@RequestMapping(value = "/writeNotice.do")
	public String writeNotice(Notice n, Model model) {
		int result = service.writeNotice(n);
		if(result > 0) {						
			model.addAttribute("msg","공지등록완료");
		}else {
			model.addAttribute("msg","공지등록실패");
		}
		model.addAttribute("loc","/noticeList.do");
		return "common/msg";
	}
	
}
