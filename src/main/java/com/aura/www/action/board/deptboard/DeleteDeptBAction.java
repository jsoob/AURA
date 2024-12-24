package com.aura.www.action.board.deptboard;

import com.aura.www.action.Action;
import com.aura.www.dao.DeptBoardDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteDeptBAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String deptno = req.getParameter("deptBNo");

		if (deptno != null) {
			int deptNo = Integer.parseInt(deptno);

			DeptBoardDAO dao = new DeptBoardDAO();
			dao.deleteOne(deptNo);
		}
		return "deptboard?cmd=selectDeptB";
	}

}
