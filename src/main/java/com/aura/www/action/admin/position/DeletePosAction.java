package com.aura.www.action.admin.position;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminPositionDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeletePosAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String pno = req.getParameter("posNo");

		if (pno != null) {
			int posNo = Integer.parseInt(pno);
			AdminPositionDAO dao = new AdminPositionDAO();
			dao.deletePos(posNo);
		}
		return "admin?cmd=selectPos";
	}

}
