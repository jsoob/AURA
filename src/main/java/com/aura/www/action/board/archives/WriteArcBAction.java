package com.aura.www.action.board.archives;

import com.aura.www.action.Action;
import com.aura.www.dao.archives.ArchivesDAO;
import com.aura.www.vo.archives.ArchivesVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WriteArcBAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		// 파라미터 값 가져와서 db에 저장
		// String writer = req.getParameter("writer");
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		
		ArchivesDAO dao = new ArchivesDAO();
		ArchivesVO vo = new ArchivesVO();
		
		vo.setArcTitle(title);
		vo.setArcContent(contents);
		vo.setArcNotice(0);
		dao.addOne(vo);
		
		return "archives?cmd=list";
	}

		
	
	
}
