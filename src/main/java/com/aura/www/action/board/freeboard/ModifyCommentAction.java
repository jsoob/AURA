package com.aura.www.action.board.freeboard;

import com.aura.www.action.Action;
import com.aura.www.dao.FreeBoardCommentDAO;
import com.aura.www.vo.FreeBoardCommentVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyCommentAction implements Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String cno = req.getParameter("cmntNo");
		String content = req.getParameter("content");
		if(cno != null) {
		int cmntNo = Integer.parseInt(cno);
		
		FreeBoardCommentDAO dao = new FreeBoardCommentDAO();
		FreeBoardCommentVO vo = new FreeBoardCommentVO();
		
		vo.setFBCmntNo(cmntNo);
		vo.setFBCmntContent(content);
		
		dao.updateComment(vo);
		}		
		return null;
	}

}
