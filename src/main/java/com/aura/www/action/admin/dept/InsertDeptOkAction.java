package com.aura.www.action.admin.dept;


import com.aura.www.action.Action;
import com.aura.www.dao.AdminDeptDAO;
import com.aura.www.vo.DeptVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertDeptOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
	    String deptName = req.getParameter("deptName");

	    System.out.println(deptName);
	    
	    
	    if (deptName != null && !deptName.isEmpty()) {
	        AdminDeptDAO dao = new AdminDeptDAO();
	        DeptVO vo = new DeptVO();

	        // 부서명 설정
	        vo.setDeptName(deptName);

	        // 부서 삽입
	        dao.insertDept(vo);
	    }

	    // 처리 후 부서 조회 화면으로 이동
	    return "admin?cmd=selectDept";
	}
}
