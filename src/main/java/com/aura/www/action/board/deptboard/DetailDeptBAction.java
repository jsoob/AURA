package com.aura.www.action.board.deptboard;

import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.DeptBoardDAO;
import com.aura.www.vo.DeptBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DetailDeptBAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String deptno = req.getParameter("deptBNo");
		if(deptno != null) {
			int deptBNo = Integer.parseInt(deptno);
			DeptBoardDAO dao = new DeptBoardDAO();
			dao.raiseViews(deptBNo);
			DeptBoardVO vo = dao.selectOne(deptBNo);
			req.setAttribute("vo", vo);
			
		}
		
		
		
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("title", "AURA 부서게시판 페이지"); // 웹 제목?
		map.put("category", "deptboard"); // 카테고리 찾는 key
		map.put("categoryName", "부게시판 페이지"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "detaiDeptB"); // 페이지명
		map.put("pagesName", "게시글 상세보기"); // 사용자에게 보여주는 페이지명

		req.setAttribute("commAt", map);
		
		return "view/board/deptboard/detailDeptB.jsp";
	
	}

	

}