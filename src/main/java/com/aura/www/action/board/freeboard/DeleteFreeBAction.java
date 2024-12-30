package com.aura.www.action.board.freeboard;

import com.aura.www.action.Action;
import com.aura.www.dao.FreeBoardDAO;
import com.aura.www.dao.FreeBoardFileDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteFreeBAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String fbno = req.getParameter("freeBNo");

		if (fbno != null) {
			// 게시글 삭제
			int freeBNo = Integer.parseInt(fbno);
			
			FreeBoardDAO dao = new FreeBoardDAO();
			dao.deleteOne(freeBNo);
			
			// 해당 게시글에 첨부된 파일도 함께 삭제
			FreeBoardFileDAO fileDao = new FreeBoardFileDAO();
			fileDao.deleteFileAll(freeBNo);
			
		}
		return "freeboard?cmd=selectFreeB";
	}

}
