package com.aura.www.action.board.archivesboard;

import com.aura.www.action.Action;
import com.aura.www.dao.ArchivesDAO;
import com.aura.www.vo.ArchivesVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectArcBAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String b = req.getParameter("arcNo");
		if(b != null) {
			int arcNo = Integer.parseInt(b);
			ArchivesDAO dao = new ArchivesDAO();
			ArchivesVO vo = dao.selectOne(arcNo);
			req.setAttribute("vo", vo);
		}
		return "view/board/arcB/selectArcB.jsp";
		
	}
}
