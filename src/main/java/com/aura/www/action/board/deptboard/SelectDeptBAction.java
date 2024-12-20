package com.aura.www.action.board.deptboard;

import java.util.ArrayList;
import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.DeptBoardDAO;
import com.aura.www.vo.DeptBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectDeptBAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		DeptBoardDAO dao = new DeptBoardDAO();
		ArrayList<DeptBoardVO> list = dao.selectAll();

		req.setAttribute("list", list);

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("title", "AURA 자유게시판 페이지"); // 웹 제목?
		map.put("category", "freeboard"); // 카테고리 찾는 key
		map.put("categoryName", "자유게시판 페이지"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "selectFreeB"); // 페이지명
		map.put("pagesName", "자유게시판"); // 사용자에게 보여주는 페이지명

		req.setAttribute("commAt", map);

		return "view/board/freeboard/selectFreeB.jsp";
	}

}