package com.study.jsp03;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListenerEx implements ServletContextListener 
{

	public ContextListenerEx() 
	{

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) 
	{
		System.out.println("어플리케이션 종료");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) 
	{
		System.out.println("어플리케이션 시작");

		// Ex#1
		ServletContext sc = event.getServletContext();
		sc.setAttribute("schedule", 1000);
	}
	// 다른 앱에서 다은과 같이 사용 가능
	/*
	 * int nTime=request.getSession().getServletContext().getAttribute("schedule");
	 */
	// 서버 중지시 어플리케이션 제거(가장 늦게 실행
	// 시작과 종료 등의 감시이지만 자바의 스태틱 특성과 비슷한 점을 이용하여 데이터 공유로도 사용할 수 있다.
	// 실제 데이터 공유로 사용하는 것은 추천하지 않는다.)
}
