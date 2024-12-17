package com.aura.www.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

//@WebFilter(urlPatterns="/*")
public class EncodingFilter implements Filter{

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 필터
		System.out.println("필터 처리");
		
		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		// 응답의 컨텐트타입지정
		response.setContentType("text/html;charset=UTF-8");
		
		// 접속한 사람의 IP를 로그 테이블에 저장해
		
		//다음 필터 또는 서블릿에게 전달
		chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 필터 초기화
		System.out.println("필터 초기화");
	}

	@Override
	public void destroy() {
		// 자원 정리
		System.out.println("자원 정리");
	}
}
