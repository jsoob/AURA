package com.aura.www.action.admin.emp;

import java.util.ArrayList;
import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminEmpDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DetailEmpAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String eNo = req.getParameter("empNo");
		String url = "";
		if(eNo == null ) {
			url = "admin?cmd=selectEmp";
		} else {
			url = "view/admin/detailEmp.jsp";
			
			// comm 설정
			HashMap<String, String> map = new HashMap<String, String>();
			
			   map.put("title", "AURA 사원관리 페이지"); // 웹 제목?
			   map.put("category", "emp"); // 카테고리 찾는 key
			   map.put("categoryName", "사원관리"); // 사용자에게 보여주는 카테고리명
			   map.put("pages", "detailEmp"); // 페이지명
			   map.put("pagesName", "사원 상세조회"); // 사용자에게 보여주는 페이지명
				
			   req.setAttribute("commAt", map);
			
			int empNo = Integer.parseInt(eNo);
			AdminEmpDAO dao = new AdminEmpDAO();
			
			EmpVO vo = dao.selectEmpOne(empNo);
			System.out.println("img = " + vo.getEmpImage());
			req.setAttribute("empVo", vo);
		}
		return url;
	}

}
