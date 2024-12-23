package com.aura.www.action.board.freeboard;

import com.aura.www.action.Action;
import com.aura.www.dao.FreeBoardCommentDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteCommentAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String cno = req.getParameter("cmntNo");

		int cmntNo = Integer.parseInt(cno);

		FreeBoardCommentDAO dao = new FreeBoardCommentDAO();
		dao.deleteComment(cmntNo);

		return null;
	}

}
