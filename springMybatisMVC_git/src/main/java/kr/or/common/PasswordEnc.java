package kr.or.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.member.model.vo.Member;

@Service
@Aspect
public class PasswordEnc {
	@Autowired
	private SHA256Enc enc;
	
	//MemberService에서 메소드 이름이 Member로 끝나면서 매개변수 Member타입인 메소드만
	// == insert,update,selectOne 이렇게가 있게ㅐㅆ죠
	@Pointcut("execution (* kr.or.member.model.service.MemberService.*Member(kr.or.member.model.vo.Member))")
	public void encPointcut(){}
	
	@Before("encPointcut()")
	public void encPass(JoinPoint jp) throws Exception {
		String methodName = jp.getSignature().getName();
		Object [] args = jp.getArgs();
		Member m = (Member)args[0];		//무조건 한개만
		if(m.getMemberPw() != null) {	//널이 아닐경우에는 암호화 그니깐 로그인 시에 널일 때는 이럴 필요가 ㄴㄴ
			String passwd = m.getMemberPw();
			String encPw = enc.encData(passwd);
			System.out.println("메소드 명 : "+methodName);
			System.out.println("암호화 된 비번 : "+encPw);
			m.setMemberPw(encPw);
		}
	}
}
