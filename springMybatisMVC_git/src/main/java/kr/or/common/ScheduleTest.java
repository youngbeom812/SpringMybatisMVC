package kr.or.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.coupon.model.service.CouponService;

@Component
public class ScheduleTest {
	@Autowired
	private CouponService service;
	@Scheduled(cron="0 16 10 * * *")		
	public void expired() {		//매개 변수가 없어야 에러가 없이 시작을 할 수 있다. 자동으로 초리하는 거기 때문에
		service.couponExpired();
	}
	/*
	  @Scheduled(fixedDelay = 60000) //ms단위로 넣어준다 이게예약실행 public void test1() {
	  System.out.println("예약실행 테스트 1"); }
	 */
	/*				<cron>
	  
	 				초 / 분 / 시간 / 날짜 / 월 / 요일 / 년도(생략가능)
	 					
	 	초 : 0 ~ 59 or * , - , /   		ex) 10-20 이런식으로
	 	분 : 0 ~ 59 or *	 , - , /  		ex) 10-20 이런식으로
	 	시간 : 0 ~ 23 or * ,  - , / 
	 	날짜 : 1 ~ 31 or * , - , / 
	 	월 : 1 ~ 12 or JAN ~ DEC , - , * , / 
	 	요일 : 1 ~ 7 or SUN ~ SAT , - , * , /
	 	년도 : 1970 ~ 2099 , * , - , /
	 	
	 	EX) * * * * * * : 1000ms 느낌임 매초마다 실행되는 것
	 		0 * * * * * : 매분 0초에 실행 ==> 결국 1분에 한번
	 		10 * * * * * : 매분 10초에 실행 ==> 결국 1분에 한번
	 	    0 0 12 * * * : 매일 12시 0분 0초에 ==> 결국 하루에 한번
	 				*/
	/*
	  @Scheduled(cron = "* * * * * *") public void test2() {
	  System.out.println("cron :매초에 한번 실행"); }
	  
	  @Scheduled(cron = "10 * * * * *") public void test3() {
	  System.out.println("cron : 매 분 10초마다마다 실행(1분에 한번)"); }
	
	  @Scheduled(cron="0-15 * * * * *") public void test4() {
	  System.out.println("cron test ( 매분 0초에서 15초 사이에 1초간격으로) "); }
	 
	@Scheduled(cron="0/5 * * * * *")
	public void test5() {
		System.out.println("cron test ( 매분 0초에 시작해서 5초 간격으로 )");
	}*/
}