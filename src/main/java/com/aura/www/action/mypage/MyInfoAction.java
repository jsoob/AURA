package com.aura.www.action.mypage;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyInfoAction implements Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String url = "";
		
		req.setAttribute("title", "AURA");
		req.setAttribute("catecory", "main");
		req.setAttribute("pages", "main");
		
		url = "view/mypage/info.jsp";
		return url;
	}
}
