package com.aura.www.action.admin.dept;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminDeptDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteDeptAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String deptno = req.getParameter("deptNo"); 
		
		
		if (deptno != null) {
			int deptNo = Integer.parseInt(deptno);
			AdminDeptDAO dao = new AdminDeptDAO();
			dao.deleteDept(deptNo);
			
		}
		
		return "admin?cmd=selectDept";
	}

}
