package com.aura.www.filter;

import java.io.IOException;

import org.apache.tomcat.jakartaee.commons.lang3.StringUtils;

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
		
		boolean rst = getURL.contains("/aura/");
		boolean rst2 = getURL.contains("/aura/login");
		// getURL.contains("/aura/css/") || getURL.contains("/aura/js/") || getURL.contains("/aura/img/")
		
//		System.out.println("rst = " + rst);
//		System.out.println("rst2 = " + rst2);
		
		// css는 안타게
		if(  session.isNew() || (session.getAttribute("loginEmp") == null)) {
			if ( !rst ) {
			
			} else if(!rst2) {
				String[] urlArr = {
						"/aura/css/", 
						"/aura/fonts/", 
						"/aura/img/", 
						"/aura/js/", 
						"/aura/comm/", 
						"/aura/login"
//						, "/aura/loginasync"
				};
				boolean  rst3 = StringUtils.containsAny(getURL, urlArr);
				if( rst3 ) {
					
				} else {
					System.out.println("로그인창 이동");
					// 로그인 페이지의 URL
					String loginURI = httpRequest.getContextPath()+"/login"; // ?cmd=loginForm
					// System.out.println("loginURI = " + loginURI);
					
					// System.out.println("loginEmp = " + session.getAttribute("loginEmp"));
					httpResponse.sendRedirect(loginURI); // 로그인 창으로 새로 이동
					return;
				}
			}
		}
		chain.doFilter(httpRequest, httpResponse);
	}
}