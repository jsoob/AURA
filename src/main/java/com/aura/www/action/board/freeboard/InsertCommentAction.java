package com.aura.www.action.board.freeboard;

import com.aura.www.action.Action;
import com.aura.www.dao.FreeBoardCommentDAO;
import com.aura.www.vo.FreeBoardCommentVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertCommentAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String pid = req.getParameter("postId");
		String uid = req.getParameter("userId");
		String comment = req.getParameter("comment");

		int userId = Integer.parseInt(uid);
		int postId = Integer.parseInt(pid);
		
		FreeBoardCommentDAO dao = new FreeBoardCommentDAO();
		
		FreeBoardCommentVO vo = new FreeBoardCommentVO();
		vo.setEmpNo(userId);
		vo.setFreeBNo(postId);
		vo.setFBCmntContent(comment);
		
		dao.insertComment(vo);
		
		return null;
	}

}
