package com.aura.www.action.admin.dept;

import java.util.ArrayList;
import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminDeptDAO;
import com.aura.www.vo.DeptVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectDeptAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("title", "AURA 부서관리 페이지"); // 웹 제목?
		map.put("category", "dept"); // 카테고리 찾는 key
		map.put("categoryName", "부서 관리 페이지"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "selectDept"); // 페이지명
		map.put("pagesName", "부서 관리"); // 사용자에게 보여주는 페이지명

		req.setAttribute("commAt", map);

		AdminDeptDAO dao = new AdminDeptDAO();
		ArrayList<DeptVO> list = dao.selectDeptAll();

		req.setAttribute("list", list);

		return "view/admin/selectDept.jsp";
	}

}
