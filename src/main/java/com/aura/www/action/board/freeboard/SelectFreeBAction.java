package com.aura.www.action.board.freeboard;

import java.util.ArrayList;

import com.aura.www.action.Action;
import com.aura.www.dao.freeboard.FreeBoardDAO;
import com.aura.www.vo.freeboard.FreeBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectFreeBAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		FreeBoardDAO dao = new FreeBoardDAO();
		ArrayList<FreeBoardVO> list = dao.selectAll();
		
		req.setAttribute("list", list);
		
		req.setAttribute("pages", "자유게시판");
		
		return "view/board/freeB/selectFreeB.jsp";
	}

}