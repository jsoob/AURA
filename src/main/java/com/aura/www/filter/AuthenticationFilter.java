package com.aura.www.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession();
		
		String getURL = (httpRequest.getRequestURL()).toString();
//		System.out.println("getURL = " + getURL);
		
		boolean rst = getURL.contains("/login");
		boolean rst2 = getURL.contains("/aura/css/"); // auraCss/
		boolean rst3 = getURL.contains("/aura/view/"); // auraCss/
//		System.out.println("rst = " + rst);
//		System.out.println("rst2 = " + rst2);
		
		// 로그인 페이지의 URL
		String loginURI = httpRequest.getContextPath()+"/login"; // ?cmd=loginForm
		// System.out.println("loginURI = " + loginURI);
		
		// System.out.println("loginEmp = " + session.getAttribute("loginEmp"));
		
		// css는 안타게
		if(  session.isNew() || (session.getAttribute("loginEmp") == null)) {
//			if ( rst2 ) {
//				System.out.println("if ( rst2 )");
//			} 
			if ( !rst3 ) {
			} 
			else if(!rst) {
				System.out.println("로그인창 이동");
				httpResponse.sendRedirect(loginURI); // 로그인 창으로 새로 이동
				return;
			}
		}
		chain.doFilter(httpRequest, httpResponse);
	}
}