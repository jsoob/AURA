package com.aura.www.action.attendance;

import java.util.HashMap;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectAllWorkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("title", "AURA 근태관리 페이지"); // 웹 제목?
		map.put("category", "emp"); // 카테고리 찾는 key
		map.put("categoryName", "근태관리 페이지"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "selectEmp"); // 페이지명
		map.put("pagesName", "근태 조회"); // 사용자에게 보여주는 페이지명
			
		// HashMap을 사용하여 공통 데이터를 JSP로 전달
		// map에 있는 데이터는 JSP에서 사용 가능 (ex. 제목, 카테고리 등)
		req.setAttribute("commAt", map);
		
		return "view/work/SelectWork.jsp";
	}

}
