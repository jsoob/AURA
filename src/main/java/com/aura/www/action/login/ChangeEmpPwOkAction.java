package com.aura.www.action.login;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.dao.LoginDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeEmpPwOkAction implements Action {
	
   @Override
   public String execute(HttpServletRequest req, HttpServletResponse resp) {
	   String empNo = req.getParameter("empNo");
	   String code1 = req.getParameter("code1");
	   
	   LoginDAO dao = new LoginDAO();
	   
	   if(empNo != null) {
		   EmpVO vo = new EmpVO();
		   vo.setEmpNo(Integer.parseInt(empNo));
		   vo.setEmpPw(code1);
		   
		   dao.changeEmpPw(vo);
	   }
	   
	   return "login";
   }
}
