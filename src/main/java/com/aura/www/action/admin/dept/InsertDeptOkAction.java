package com.aura.www.action.admin.dept;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminDeptDAO;
import com.aura.www.vo.DeptVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertDeptOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		// 파라미터값 가져와서 db에 저장
		String deptNo = req.getParameter("deptNo");
		String deptName = req.getParameter("deptName");

		if (deptNo != null) {
			int deptno = Integer.parseInt(deptNo);
			AdminDeptDAO dao = new AdminDeptDAO();

			DeptVO vo = new DeptVO();

			vo.setDeptNo(deptno);
			vo.setDeptName(deptName);

			dao.insertDept(vo);
		}

		return "admin?cmd=selectDept";
	}

}
