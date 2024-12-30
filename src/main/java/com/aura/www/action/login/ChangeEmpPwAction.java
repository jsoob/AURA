package com.aura.www.action.login;

import java.io.IOException;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeEmpPwAction implements Action {
   @Override
   public String execute(HttpServletRequest req, HttpServletResponse resp) {
	   String empNo = req.getParameter("empNo");
	   String empName = req.getParameter("empName");
		
	   req.setAttribute("empNo", empNo);
	   req.setAttribute("empName", empName);
	   
	   return "view/login/changeEmpPw.jsp";
   }
}
