package kr.or.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;

import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;

@Controller
@CrossOrigin(origins = {})		//크로스 도메인을 설정하기 위한 어노테이션 허용할 배열을 만든다
public class MemberController {
	@Autowired		//만들어져있으면 자동으로 집어넣어줌
	private MemberService service;
	public MemberController() {
		super();
		System.out.println("MemberController 생성완료");
	}
	
	@RequestMapping(value="/login.do")	//컨트롤러에 서블릿그니깐 그런거 ㄹ하나 더 만들어야 되면 이렇게 리퀘스트맵핑처리한다
	public String login(Member m, HttpSession session, Model model) {		//id, pw 로 묶여서 멤버로 들어옴 //세션은 바로 써서 로그인처리 가능 	//모델은 setattribute하듯이 쓰는 것
		Member member = service.selectOneMember(m);
		if(member != null) {
			session.setAttribute("m", member);
			model.addAttribute("msg","로그인성공");		//request.setAttribute하는거 이렇게 쓰는거
		}else {
			model.addAttribute("msg","아이디/비밀번호를 확인하세요");		//request.setAttribute하는거 이렇게 쓰는거
		}
		model.addAttribute("loc","/");
		return "common/msg";		//리졸버가 WEB-INF안에 views까지랑 뒤에 .jsp까지는 해주기 때문에 리턴string을 이렇게 해주면 알아서 페이지이동			
	}
	
	@RequestMapping(value="joinFrm.do")
	public String joinFrm() {
		return "member/joinFrm";
	}
	@RequestMapping(value="/join.do")
	public String join(Member m, Model model) {
		int result = service.insertMember(m);
		if(result > 0) {
			model.addAttribute("msg","회원가입성공");
		}else {
			model.addAttribute("msg","회원가입실패");			
		}
		model.addAttribute("loc", "/");
		return "common/msg";
	}
	
	@RequestMapping(value="/searchFrm.do")
	public String searchFrm() {
		return "member/searchFrm";
	}
	@RequestMapping(value="/idSearch.do")
	public String idSearch(Member m, Model model) {		//대박이삼 멤버로 한번에 넣는다 대신 변수명이 일치해야함
		Member member = service.searchId(m);
		if(member != null) {
			model.addAttribute("msg","아이디는["+member.getMemberId()+"]입니당!");
		}else {
			model.addAttribute("msg","회원정보를 조회 못하겠다");
		}
		model.addAttribute("loc", "/");
		return "common/msg";
	}
	
	@RequestMapping(value="/pwSearch.do")
	public String pwSearch(Member m, Model model) {
		Member member = service.searchPw(m);
		if(member != null) {
			model.addAttribute("msg","회원님의 비밀번호는["+member.getMemberPw()+"]입니다.");
		}else {
			model.addAttribute("msg", "정보를 조회할 수 없습니다.");
		}
		model.addAttribute("loc", "/");
		return "common/msg";
	}
	
	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";		//이렇게 하면 뷰 리졸버가 앞뒤에 원래 붙이는거 안붙임
	}
	@RequestMapping(value="/deleteMember.do")
	public String deleteMember(String memberId, Model model, HttpSession session) {
		int result = service.deleteMember(memberId);
		if(result > 0) {
			session.invalidate();
			model.addAttribute("msg", "Bye~ 비와이 이~");
		}else {
			model.addAttribute("msg", "탈퇴는 무슨 어림도업지 ㅋㅋ");
		}
		model.addAttribute("loc", "/");
		return "common/msg";
	}
	
	@RequestMapping(value="/mypage.do")
	public String mypage(Member m,Model model) {
		Member member = service.selectOneMember(m);
			model.addAttribute("member", member);
		return "member/mypage";
	}
	
	@RequestMapping(value="/updateMember.do")
	public String updateMember(Member m) {
		int result = service.memberUpdate(m);
		return "redirect:/mypage.do?memberId="+m.getMemberId();		//다른 서블릿으로 보내는것
	}
	
	@RequestMapping(value="/allMember.do")
	public String allMember(Model model) {
		ArrayList<Member> list = service.selectAllMember();
		model.addAttribute("list",list);
		return "member/allMember";
	}
	
	@RequestMapping(value="/allMemberCount.do")
	public String allMemberCount(Model model) {
		int count = service.allMemberCount();
		model.addAttribute("msg", "전체회원수 :["+count+"]명");
		model.addAttribute("loc", "/");
		return "common/msg";
	}
	
	@RequestMapping(value="/pwCheck.do")
	public String pwCheck() {
		return "member/pwCheck";
	}
	@ResponseBody		//이렇게하면 return을 써도 페이지이동이 아닌 내가 원하는 데이터를  바로 넘길 수 있다. 스프링에서 아작스를 다룰 수 있는 방법 기본적
	@RequestMapping(value="/checkPw.do")
	public String checkPw(Member m) {
		//해당하는 아이디의 비밀번호가 일치하는지 확인	(암호화가 안됬지만 그래도 검사가 된다)
		System.out.println(m.getMemberId());
		System.out.println(m.getMemberPw());
		Member member = service.selectOneMember(m);
		if(member != null) {
			//입력한 비밀번호가 일치하는 경욱
			return "1";
		}else {			
			return "0";
		}
	}
	
	@RequestMapping(value="/pwChange.do")
	public String pwChange(Member m, Model model) {
		int result = service.pwChangeMember(m);		//Member로 이름 끝나고 매개변수 Member 하나 그래야 암호화로 들어감
		System.out.println(m.getMemberPw());
		if(result > 0) {
			model.addAttribute("msg", "변경성공");
		}else {
			model.addAttribute("msg", "변경성공");
			
		}
		model.addAttribute("loc", "/mypage.do?memberId="+m.getMemberId());
		return "common/msg";
	}
	
	@ResponseBody
	@RequestMapping(value="/idCheck.do")
	public String idCheck(Member member) {
		Member m = service.selectOneMember(member);
		if(m != null) {
			return "1";
		}else {
			return "0";
		}
	}
	@RequestMapping(value="allMemberAjax.do")
	public String allMemberFrm() {
		return "member/allMemberAjax";
	}
	@ResponseBody
	@RequestMapping(value="allMemAjax.do",produces = "application/json;charset=utf-8")	//내가 리턴하는 데이터가 json타입이고 문자셋은 utf-8
	public String allMemAjax() {
		ArrayList<Member> list = service.selectAllMember();
		return new Gson().toJson(list);
	}
	@RequestMapping(value="/allMemberChat.do")
	public String allMemberChat() {
		return "member/allChat";
	}
	@ResponseBody
	@RequestMapping
	public void arduinoTest(String temp) {
		System.out.println("현재온도 : "+temp);
	}
}