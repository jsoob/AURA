package com.aura.www.action.board.freeboard;

import java.util.ArrayList;
import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.freeboard.FreeBoardDAO;
import com.aura.www.vo.freeboard.FreeBoardVO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectFreeBAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		FreeBoardDAO dao = new FreeBoardDAO();
		ArrayList<FreeBoardVO> list = dao.selectAll();
		
		HashMap<String, String> map = new HashMap<String, String>();
			
		map.put("title", "AURA 페이지"); // 웹 제목?
		map.put("category", "freeB"); // 카테고리 찾는 key(@WebServlet)
		map.put("categoryName", "게시판"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "selectFreeB"); // 페이지명(cmd)
		map.put("pagesName", "자유게시판"); // 사용자에게 보여주는 페이지명
			
		req.setAttribute("commAt", map);
		  
		req.setAttribute("list", list);
		
		
		
		return "view/board/freeB/selectFreeB.jsp";
	}

}