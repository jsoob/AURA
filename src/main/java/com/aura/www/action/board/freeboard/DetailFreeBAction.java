package com.aura.www.action.board.freeboard;

import com.aura.www.action.Action;
import com.aura.www.dao.freeboard.FreeBoardDAO;
import com.aura.www.vo.freeboard.FreeBoardVO;

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
		}
		
		return "view/board/freeB/detailFreeB.jsp";
	}

}
