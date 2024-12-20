package com.aura.www.action.admin.dept;

import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminDeptDAO;
import com.aura.www.vo.DeptVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyDeptAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String deptno = req.getParameter("deptNo");
		if (deptno != null) {
			int deptNo = Integer.parseInt(deptno);
			AdminDeptDAO dao = new AdminDeptDAO();
			DeptVO vo = dao.selecDeptOne(deptNo);
			req.setAttribute("vo", vo);

		}

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("title", "AURA 부서 수정 페이지");
		map.put("category", "dept");
		map.put("categoryName", "부서관리 페이");
		map.put("pages", "modifyDept");
		map.put("pagesName", "부서 수정");

		req.setAttribute("commAt", map);

		return "view/admin/modifyDept.jsp";
	}

}
