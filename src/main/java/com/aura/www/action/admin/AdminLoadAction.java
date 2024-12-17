package com.aura.www.action.admin;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminLoadAction implements Action {
	   @Override
	   public String execute(HttpServletRequest req, HttpServletResponse resp) {
		   
		   String url = "";
		   
		   HttpSession session = req.getSession();
		   
			req.setAttribute("pages", "관리자 조회");
			
		   
		   url = "view/admin/adminLoad.jsp";
		  
	      return url;
	   }
	}
