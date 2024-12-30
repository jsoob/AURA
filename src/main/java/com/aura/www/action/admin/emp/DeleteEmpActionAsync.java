package com.aura.www.action.admin.emp;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminEmpDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteEmpActionAsync implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String result = "0"; 
		EmpVO vo = new EmpVO();
		
		// select 조건문
		String empNo = req.getParameter("empNo");
		
		if(empNo != null) {
			AdminEmpDAO dao = new AdminEmpDAO();
			int cnt = dao.deleteEmpOne(Integer.parseInt(empNo)); // 전체수
			
			result = cnt+"";
		}
		return result;
	}
	
}
