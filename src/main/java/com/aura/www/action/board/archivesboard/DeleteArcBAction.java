package com.aura.www.action.board.archivesboard;

import com.aura.www.action.Action;
import com.aura.www.dao.ArchivesDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteArcBAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String b = req.getParameter("arcNo");
		// b가 null이 아니라면?
		if (b != null) {
			int arcNo = Integer.parseInt(b);
			ArchivesDAO dao = new ArchivesDAO();
			dao.deleteOne(arcNo);
			
		}
		return "archives?cmd=list";
	}
}
