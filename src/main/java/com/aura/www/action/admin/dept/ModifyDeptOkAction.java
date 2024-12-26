package com.aura.www.action.admin.dept;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminDeptDAO;
import com.aura.www.vo.DeptVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyDeptOkAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String deptno = req.getParameter("deptNo");
		
		if (deptno!= null) {
			int deptNo = Integer.parseInt(deptno);
			String deptName = req.getParameter("deptName"); 
			
			AdminDeptDAO dao = new AdminDeptDAO();
			DeptVO vo = dao.selecDeptOne(deptNo);
			
			vo.setDeptName(deptName);
			dao.updateOne(vo);
		}
		
		return "admin?cmd=selectDept";
	}

}
