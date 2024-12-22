package com.aura.www.action.board.archives;

import com.aura.www.action.Action;
import com.aura.www.dao.archives.ArchivesDAO;
import com.aura.www.dao.archives.ArchivesFileDAO;
import com.aura.www.vo.archives.ArchivesVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyArcOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String b = req.getParameter("arcNo");
		// String writer = req.getParameter("writer");
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");

		if (b != null) {
			// 형변환
			int arcNo = Integer.parseInt(b);

			ArchivesDAO dao = new ArchivesDAO();
			ArchivesVO vo = new ArchivesVO();

			vo.setArcNo(arcNo);
			// 일단 vo.로 가져올 거 생각해보기

		}

		return "archives?cmd=list";
	}

}
