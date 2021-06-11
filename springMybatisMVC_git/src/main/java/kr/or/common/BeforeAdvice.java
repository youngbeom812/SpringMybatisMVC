package kr.or.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service			//보통서비스로 많이 단다 왜냐면보통 서비스와 연관되어 있기 때문에
@Aspect
public class BeforeAdvice {
	// 포인트 컷 만들기
	@Pointcut("execution(* kr.or.member.model.service..*Service.*(*,..))")
	public void allPointcut() {}
	
	@Before("allPointcut()")
	public void beforeLog(JoinPoint jp) {
      String methodName = jp.getSignature().getName(); //호출된 비지니스 메소드 이름 가져옴
      Object[] args = jp.getArgs();
      System.out.println("[서전처리]" + methodName +" / 매개변서 정보 :  " + args[0].toString() );
   }
}