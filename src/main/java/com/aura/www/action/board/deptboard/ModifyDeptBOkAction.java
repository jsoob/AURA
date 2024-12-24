package com.aura.www.action.board.deptboard;

import com.aura.www.action.Action;
import com.aura.www.dao.DeptBoardDAO;
import com.aura.www.vo.DeptBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ModifyDeptBOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String deptBno = req.getParameter("deptBNo");
		String deptBTitle = req.getParameter("deptBTitle");
		String deptBContent = req.getParameter("deptBContent");
		String dbn = req.getParameter("deptBNotice");
		String dbp = req.getParameter("deptBPblc");
		int deptBNotice=0;
		int deptBStatus=1;
		
		int deptBNo = Integer.parseInt(deptBno);
		
		if(deptBno != null) {
			deptBNotice = Integer.parseInt(dbn);	
		}
		int freeBPblc = Integer.parseInt(dbp);

		DeptBoardDAO dao = new DeptBoardDAO();

		DeptBoardVO vo = new DeptBoardVO();
		
		vo.setDeptBNo(deptBNo);
		vo.setDeptBTitle(deptBTitle);
		vo.setDeptBContent(deptBContent);
		vo.setDeptBNotice(deptBNotice);
		vo.setDeptBStatus(deptBStatus);
		vo.setDeptBPblc(freeBPblc);
		
		dao.updateOne(vo);
		
		
		return "deptboard?cmd=selectDeptB";
	}
}