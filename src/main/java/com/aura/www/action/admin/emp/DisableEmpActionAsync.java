package com.aura.www.action.admin.emp;

import org.json.simple.JSONObject;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminEmpDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DisableEmpActionAsync implements Action {
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		int cnt = 0;
		EmpVO vo = new EmpVO();
		
		// select 조건문
		String empNo = req.getParameter("empNo");
		
		if(empNo != null) {
			AdminEmpDAO dao = new AdminEmpDAO();
			cnt = dao.disableEmpOne(Integer.parseInt(empNo)); // 실패시 0 성공시 1 반환
		}
		
		JSONObject obj = new JSONObject();
		obj.put("deleteStatus", cnt); // jsonobject에 넣고
		 
		return obj.toJSONString(); // 문자로 return
	}
	
}
