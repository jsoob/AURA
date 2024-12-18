package com.aura.www.action.main;

import java.util.HashMap;

import com.aura.www.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainAction implements Action {
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String url = "";
		
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("title", "AURA"); // 웹 제목?
		map.put("category", "main"); // 카테고리 찾는 key
		map.put("categoryName", "Main"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "main"); // 페이지명
		map.put("pagesName", "메인 화면"); // 사용자에게 보여주는 페이지명
			
		req.setAttribute("commAt", map);
		
//		req.setAttribute("title", "AURA");
//		req.setAttribute("catecory", "main");
//		req.setAttribute("pages", "main");
		
		url = "view/main/main.jsp";
		return url;
	}
	
}