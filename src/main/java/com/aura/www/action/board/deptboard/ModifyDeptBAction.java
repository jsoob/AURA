package com.aura.www.action.board.deptboard;

import com.aura.www.action.Action;
import com.aura.www.dao.DeptBoardDAO;
import com.aura.www.vo.DeptBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ModifyDeptBAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String deptno = req.getParameter("deptBNo");
		
		if(deptno != null) {
			int deptBNo = Integer.parseInt(deptno);
			
			DeptBoardDAO dao = new DeptBoardDAO();
			DeptBoardVO vo = dao.selectOne(deptBNo);
			req.setAttribute("vo", vo);
			
		}
		
		return "view/board/deptboard/modifyDeptB.jsp";
	}

	

}