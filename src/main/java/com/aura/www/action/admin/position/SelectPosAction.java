package com.aura.www.action.admin.position;

import java.util.ArrayList;
import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.PositionDAO;
import com.aura.www.vo.PositionVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectPosAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		 HashMap<String, String> map = new HashMap<String, String>();
			
		   map.put("title", "AURA 직급관리 페이지"); // 웹 제목?
		   map.put("category", "position"); // 카테고리 찾는 key
		   map.put("categoryName", "직급관리 페이지"); // 사용자에게 보여주는 카테고리명
		   map.put("pages", "selectPos"); // 페이지명
		   map.put("pagesName", "직급 조회"); // 사용자에게 보여주는 페이지명
			
		   req.setAttribute("commAt", map);
		
		PositionDAO dao = new PositionDAO();
		ArrayList<PositionVO> list = dao.selectPosAll();
		
		req.setAttribute("list", list);
		
		
		
		return "view/admin/selectPos.jsp";
	}

}
