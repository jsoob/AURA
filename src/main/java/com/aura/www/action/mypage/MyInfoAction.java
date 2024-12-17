package com.aura.www.action.mypage;

import java.util.HashMap;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 일단 회원 상세정보는 modal 창으로 보여줄 생각.. 사라질 Action일 수 있음.
public class MyInfoAction implements Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String url = "";
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("title", "AURA"); // 웹 제목?
		map.put("category", "main"); // 카테고리 찾는 key
		map.put("categoryName", "Main"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "myInfo"); // 페이지명
		map.put("pagesName", "사원 정보"); // 사용자에게 보여주는 페이지명
			
		req.setAttribute("commAt", map);
		
		
		url = "view/mypage/info.jsp";
		return url;
	}
}
