package com.aura.www.action.admin.emp;

import java.util.ArrayList;
import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminEmpDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertEmpAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// comm 설정
		HashMap<String, String> map = new HashMap<String, String>();
		
		   map.put("title", "AURA 사원관리 페이지"); // 웹 제목?
		   map.put("category", "emp"); // 카테고리 찾는 key
		   map.put("categoryName", "사원관리"); // 사용자에게 보여주는 카테고리명
		   map.put("pages", "insertEmp"); // 페이지명
		   map.put("pagesName", "사원 등록"); // 사용자에게 보여주는 페이지명
			
		   req.setAttribute("commAt", map);
		
		return "view/admin/insertEmp.jsp";
	}

}
