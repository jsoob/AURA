package com.aura.www.action.admin.position;

import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminPositionDAO;
import com.aura.www.vo.PositionVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyPosAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String pno = req.getParameter("posNo");
		if (pno != null) {
			int posNo = Integer.parseInt(pno);
			AdminPositionDAO dao = new AdminPositionDAO();
			PositionVO vo = dao.selectPosOne(posNo);
			req.setAttribute("vo", vo);
		}

		 HashMap<String, String> map = new HashMap<String, String>();
			
		   map.put("title", "AURA 직급 수정 페이지"); // 웹 제목?
		   map.put("category", "position"); // 카테고리 찾는 key
		   map.put("categoryName", "직급관리 페이지"); // 사용자에게 보여주는 카테고리명
		   map.put("pages", "modifyPos"); // 페이지명
		   map.put("pagesName", "직급 수정"); // 사용자에게 보여주는 페이지명
			
		   req.setAttribute("commAt", map);
		
		return "view/admin/modifyPos.jsp";
	}

}
