package com.aura.www.action.board.freeboard;

import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.freeboard.FreeBoardDAO;
import com.aura.www.vo.freeboard.FreeBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WriteFreeBFormAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("title", "AURA 페이지"); // 웹 제목?
		map.put("category", "board"); // 카테고리 찾는 key
		map.put("categoryName", "게시판"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "freeB"); // 페이지명
		map.put("pagesName", "자유게시판 글쓰기"); // 사용자에게 보여주는 페이지명
			
		req.setAttribute("commAt", map);
		
		return "view/board/freeB/writeFreeB.jsp";
	}

}
