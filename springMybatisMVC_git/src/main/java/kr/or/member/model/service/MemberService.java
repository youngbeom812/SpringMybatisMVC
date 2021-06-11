package kr.or.member.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.common.LogClass;
import kr.or.common.LoggingClass;
import kr.or.member.model.dao.MemberDao;
import kr.or.member.model.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberDao dao;
	public MemberService() {
		super();
		System.out.println("MemberService호출완료");
	}
	
	public Member selectOneMember(Member m) {
		return dao.selectOneMember(m); // dao에서 받아온 리스트
	}
	@Transactional		//트랜잭션을 관리해야하는 메소드 들에는 위에 이런 어노테이션을 걸어줘야한다 (커밋롤백)
	public int insertMember(Member m) {
		return dao.insertMember(m);
	}
	
	public Member searchId(Member m) {
		Member member = dao.searchId(m);
		return member;
	}
	
	public Member searchPw(Member m) {
		Member member = dao.searchPw(m);
		return member;
	}
	@Transactional
	public int deleteMember(String memberId) {
		int result = dao.deleteMember(memberId);
		return result;
	}
	
	public Member selectOneMember(String memberId) {
		Member member = dao.selectOneMember(memberId);
		return member;
	}
	@Transactional
	public int memberUpdate(Member m) {
		int result = dao.memberUpdate(m);
		return result;
	}
	
	public ArrayList<Member> selectAllMember() {
		List list = dao.selectAllMember();
		return (ArrayList<Member>) list;
	}
	
	public int allMemberCount() {
		int result = dao.allMemberCount();
		return result;
	}
	@Transactional
	public int pwChangeMember(Member m) {
		return dao.pwChangeMember(m);
	}
}
