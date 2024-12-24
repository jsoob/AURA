package com.aura.www.action.board.deptboard;

import com.aura.www.action.Action;
import com.aura.www.dao.DeptBoardDAO;
import com.aura.www.vo.DeptBoardVO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class WriteDeptBOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// 파라미터값 가져와서 db에 저장

		String deptBTitle = req.getParameter("deptBTitle");
		String deptBContent = req.getParameter("deptBContent");
		String dbn = req.getParameter("deptBNotice");
		String dbp = req.getParameter("deptBPblc");
		
		HttpSession session = req.getSession();
		EmpVO loginEmp = (EmpVO)session.getAttribute("loginEmp");
		int deptBCrtr = loginEmp.getEmpNo();
		int deptBNotice=0;	// default값=0, 공지를 누르지 않았다면 fbn==null
		int deptBPblc = 1; // 
		int deptBStatus=1; // 등록하면 status = 1
		
		
		if(dbn != null) { // 공지가 눌렸다면
			deptBNotice = Integer.parseInt(dbn);		 
		}
		
		if(dbp!= null) { // 
			deptBPblc = Integer.parseInt(dbp);
		}

			DeptBoardDAO dao = new DeptBoardDAO();

			DeptBoardVO vo = new DeptBoardVO();

			vo.setDeptBTitle(deptBTitle);
			vo.setDeptBContent(deptBContent);
			vo.setDeptBNotice(deptBNotice);
			vo.setDeptBStatus(deptBStatus);
			vo.setDeptBPblc(deptBPblc);
			vo.setDeptBCrtr(deptBCrtr);

			dao.insertOne(vo);
		

		return "deptboard?cmd=selectDeptB";
	}}