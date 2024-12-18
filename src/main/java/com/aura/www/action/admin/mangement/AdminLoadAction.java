package com.aura.www.action.admin.mangement;

import java.util.HashMap;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminLoadAction implements Action {
	   @Override
	   public String execute(HttpServletRequest req, HttpServletResponse resp) {
		   
		   String url = "";
		   
		   HttpSession session = req.getSession();
		   
		   HashMap<String, String> map = new HashMap<String, String>();
			
		   map.put("title", "AURA 관리자 페이지"); // 웹 제목?
		   map.put("category", "admin"); // 카테고리 찾는 key
		   map.put("categoryName", "관리자 페이지"); // 사용자에게 보여주는 카테고리명
		   map.put("pages", "adminLoad"); // 페이지명
		   map.put("pagesName", "관리자 조회"); // 사용자에게 보여주는 페이지명
			
		   req.setAttribute("commAt", map);
		   
		   req.setAttribute("commAt", map);
		   
		   url = "view/admin/adminLoad.jsp";
		  
	      return url;
	   }
	}
