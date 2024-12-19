package com.aura.www.action.admin.emp;

import java.util.ArrayList;
import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminEmpDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectEmpAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		// comm 설정
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("title", "AURA 사원관리 페이지"); // 웹 제목?
		map.put("category", "emp"); // 카테고리 찾는 key
		map.put("categoryName", "사원관리"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "selectEmp"); // 페이지명
		map.put("pagesName", "사원 조회"); // 사용자에게 보여주는 페이지명
		
		req.setAttribute("commAt", map);
		
		EmpVO vo = new EmpVO();
		
		// select 조건문
		String deptName = req.getParameter("deptName");
		String empNo = req.getParameter("empNo");
		String empName = req.getParameter("empName");
		String hiredate_st = req.getParameter("hiredate_st");
		String hiredate_ed = req.getParameter("hiredate_ed");
		
		if(deptName == null) { }
		else if(!deptName.equals("")) { vo.setDeptName(deptName); };
		
		if(empNo == null) { }
		else if(!empNo.equals("")) { vo.setEmpNo(Integer.parseInt(empNo)); };
		
		if(empName == null) { }
		else if(!empName.equals("")) { vo.setEmpName(empName); };
		
		if(hiredate_st == null) { }
		else if(!hiredate_st.equals("")) { vo.setHiredate_st(hiredate_st); };
		
		if(hiredate_ed == null) { }
		else if(!hiredate_ed.equals("")) { vo.setHiredate_ed(hiredate_ed); };
		
		AdminEmpDAO dao = new AdminEmpDAO();
		ArrayList<EmpVO> list = dao.selectEmp(vo);
		
		req.setAttribute("empList", list);
		
		return "view/admin/selectEmp.jsp";
	}

}