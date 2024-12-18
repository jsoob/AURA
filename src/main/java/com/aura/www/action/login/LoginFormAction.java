package com.aura.www.action.login;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginFormAction implements Action {
   @Override
   public String execute(HttpServletRequest req, HttpServletResponse resp) {
	   
	   String url = "";
	   
	   HttpSession session = req.getSession();
	   
	   if(session.isNew() || session.getAttribute("loginEmp") == null)
		   url = "view/login/login.jsp";
	   else
		   url = "view/main/main.jsp";
	  
      return url;
   }
}
