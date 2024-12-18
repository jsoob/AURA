package com.aura.www.action;

import java.io.IOException;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutAction implements Action {
   @Override
   public String execute(HttpServletRequest req, HttpServletResponse resp) {
	   
	   String url = "";
	   HttpSession session = req.getSession(false);
	   
	   // https://blogshine.tistory.com/20
	   if(session != null && session.getAttribute("loginEmp") != null) {
			session.invalidate(); // 설정된 모든 session을 제거하기
			System.out.print("로그아웃 완료");
		}else {
			System.out.print("현재 로그인 상태가 아닙니다.");
		}
	   url = "login"; 
	   // "view/login/login.jsp";

	  
      return url;
   }
}
