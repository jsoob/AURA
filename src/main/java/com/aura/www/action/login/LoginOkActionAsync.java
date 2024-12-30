package com.aura.www.action.login;

import java.util.HashMap;

import org.json.simple.JSONObject;

import com.aura.www.action.Action;
import com.aura.www.dao.LoginDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginOkActionAsync implements Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		LoginDAO dao = new LoginDAO();
		
		String no = req.getParameter("empNo");
		String pw = req.getParameter("psswd");
		
		int status = 0;
		JSONObject obj = new JSONObject();
		
		try {
			int empNo = Integer.parseInt(no);
			EmpVO vo = dao.selectLogin(empNo, pw);
			if(vo==null) {
				
			} else {
				HttpSession session = req.getSession();
				
				if(session.isNew() || session.getAttribute("loginEmp") == null) {
					session.setAttribute("loginEmp", vo);
					
					HashMap<String, String> map = new HashMap<String, String>();
					
					map.put("title", "AURA"); // 웹 제목?
					map.put("category", "main"); // 카테고리 찾는 key
					map.put("categoryName", "Main"); // 사용자에게 보여주는 카테고리명
					map.put("pages", "main"); // 페이지명
					map.put("pagesName", "메인 화면"); // 사용자에게 보여주는 페이지명
					
					req.setAttribute("commAt", map);
					
					if(session.isNew()) {
						// System.out.println("Session 생성 후, 로그인 완료");
					} else {
						System.out.println("로그인을 완료하였습니다.");
					}
				}else {
					System.out.println("현재 로그인 상태입니다.");
				}
				status = 1;
			}
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
		}
		obj.put("status", status);
		return obj.toJSONString();
	}
}
