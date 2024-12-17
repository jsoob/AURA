package com.aura.www.action.login;

import com.aura.www.action.Action;
import com.aura.www.dao.LoginDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

public class LoginOkAction implements Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		LoginDAO dao = new LoginDAO();
		
		String no = req.getParameter("empno");
		String pw = req.getParameter("psswd");
		
		String url = "";
		
		try {
			int empNo = Integer.parseInt(no);
			EmpVO vo = dao.selectLogin(empNo, pw);
			
			if(vo==null) {
				url = "login";// "view/login/login.jsp";
			} else {
				// req.setAttribute("loginEmp", vo);
				HttpSession session = req.getSession();
				
				if(session.isNew() || session.getAttribute("loginEmp") == null) {
					session.setAttribute("loginEmp", vo);
					
					req.setAttribute("title", "AURA");
					req.setAttribute("catecory", "main");
					req.setAttribute("pages", "main");
					
					if(session.isNew()) {
						// System.out.print("Session 생성 후, 로그인 완료");
					} else {
						System.out.print("로그인을 완료하였습니다.");
					}
				}else {
					System.out.println("현재 로그인 상태입니다.");
				}
				url = "main"; // "view/main/main.jsp";
			}
		} catch (NumberFormatException e) {
			url = "login"; // "view/login/login.jsp";
		}
		return url;
	}
}
