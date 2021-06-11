package kr.or.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowingAdvice {
	public void except(JoinPoint jp, Exception exceptObj) {
		String methodName = jp.getSignature().getName();
		System.out.println("[예외발생]" + methodName + "메소드 수행중 에러발생");
		System.out.println("[발생예외] :" + methodName);
	}
}
