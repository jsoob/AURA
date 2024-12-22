package com.aura.www.action.board.archives;

import com.aura.www.action.Action;
import com.aura.www.dao.archives.ArchivesDAO;
import com.aura.www.vo.archives.ArchivesVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DetailArcBAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String b = req.getParameter("arcNo");
		if (b != null) {
			// 형변환
			int arcNo = Integer.parseInt(b);
			ArchivesDAO dao = new ArchivesDAO();
			ArchivesVO vo = dao.selectOne(arcNo);
			req.setAttribute("vo", vo);
		}

		// detailArcB.jsp에 대한 경로를 설정
		return "view/board/arcB/detailArcB.jsp";
	}

}
