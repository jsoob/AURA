package com.aura.www.action.board.freeboard;

import java.util.ArrayList;

import com.aura.www.action.Action;
import com.aura.www.dao.FreeBoardCommentDAO;
import com.aura.www.vo.FreeBoardCommentVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectCommentAction implements Action {
	


	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String fbno = req.getParameter("freeBNo");
		int freeBNo = Integer.parseInt(fbno);
		
		FreeBoardCommentDAO dao = new FreeBoardCommentDAO();
		ArrayList<FreeBoardCommentVO> list = dao.selectCommentAll(freeBNo);
		
		req.setAttribute("comentList", list);
		
		return null;
	}}
