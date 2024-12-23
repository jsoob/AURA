package com.aura.www.action.login;

import org.json.simple.JSONObject;

import com.aura.www.action.Action;
import com.aura.www.dao.LoginDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FindEmailEmpActionAsync implements Action {
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		EmpVO vo2 = null;
		int status = 0;
		
		// select 조건문
		String eNo = req.getParameter("empNo");
		
		if(eNo != null) {
			int empNo = 0;
			
			try {
				empNo = Integer.parseInt(eNo);
				
				LoginDAO dao = new LoginDAO();
				
				vo2 = dao.getEmailEmp(empNo); // 전체수
				if(vo2 != null) {
					status = 1;
				}
			} catch (NumberFormatException e) {
				status = 3;
			}
		}
		
		JSONObject obj = new JSONObject();
		obj.put("getEmailEmp", vo2);
		obj.put("status", status);
		
		return obj.toJSONString(); // jArr.toJSONString(); // JSON -> Array
	}
}
