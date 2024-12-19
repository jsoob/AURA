package com.aura.www.action.board.freeboard;

import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.FreeBoardDAO;
import com.aura.www.vo.FreeBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DetailFreeBAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String fbno = req.getParameter("freeBNo");
		if(fbno != null) {
			int freeBNo = Integer.parseInt(fbno);
			FreeBoardDAO dao = new FreeBoardDAO();
			FreeBoardVO vo = dao.selectOne(freeBNo);
			req.setAttribute("vo", vo);
			System.out.println(vo.toString());
		}
		
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("title", "AURA 자유게시판 페이지"); // 웹 제목?
		map.put("category", "freeboard"); // 카테고리 찾는 key
		map.put("categoryName", "자유게시판 페이지"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "detailFreeB"); // 페이지명
		map.put("pagesName", "게시글 상세보기"); // 사용자에게 보여주는 페이지명

		req.setAttribute("commAt", map);
		
		return "view/board/freeboard/detailFreeB.jsp";
	}

}
