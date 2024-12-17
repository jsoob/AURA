package com.aura.www.action.mail;

import java.util.HashMap;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MailAction implements Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String url = "";
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("title", "AURA Mail"); // 웹 제목?
		map.put("category", "mail"); // 카테고리 찾는 key
		map.put("categoryName", "Mail"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "mail"); // 페이지명
		map.put("pagesName", "전체 메일 조회"); // 사용자에게 보여주는 페이지명
			
		req.setAttribute("commAt", map);
		
		url = "view/mail/selectAllMail.jsp";
		return url;
	}
}