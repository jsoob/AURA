package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.dao.DeptBoardDAO;
import com.aura.www.vo.DeptBoardVO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/writeDeptBOk")
public class writeDeptBOk  extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptBTitle = req.getParameter("deptBTitle");
	    String deptBContent = req.getParameter("deptBContent");
	    String dbn = req.getParameter("deptBNotice");
	    String dbp = req.getParameter("deptBPblc");
	    
	    String isNotice = req.getParameter("deptBNotice");
	    System.out.println("isNotice: " + isNotice); 
	    

	    HttpSession session = req.getSession();
	    EmpVO loginEmp = (EmpVO) session.getAttribute("loginEmp");

	    int deptBCrtr = loginEmp.getEmpNo();
	    int posNo = loginEmp.getPosNo(); // 로그인한 사용자의 직급 번호 가져오기
	    int deptBNotice = 0; // 공지 여부 기본값 (공지 아님)
	    int deptBPblc = 1; // 공개 여부 기본값
	    int deptBStatus = 1; // 등록 상태 기본값
	    
	    if(isNotice != null) {
	    	// deptBTitle = "[공지] " + deptBTitle;
	    	deptBNotice = 1;
	    }


	    // 공지 설정 (직급 번호 확인)
	    if (dbn != null && posNo >= 200 && posNo == 0) { // 직급 200 이상만 공지 가능
	        deptBNotice = Integer.parseInt(dbn);
	    }

	    // 공개 설정
	    if (dbp != null) {
	        deptBPblc = Integer.parseInt(dbp);
	    }

	    // 게시글 저장
	    DeptBoardDAO dao = new DeptBoardDAO();
	    DeptBoardVO vo = new DeptBoardVO();

	    vo.setDeptBTitle(deptBTitle);
	    vo.setDeptBContent(deptBContent);
	    vo.setDeptBNotice(deptBNotice);
	    vo.setDeptBStatus(deptBStatus);
	    vo.setDeptBPblc(deptBPblc);
	    vo.setDeptBCrtr(deptBCrtr);
	    vo.setDeptNo(loginEmp.getDeptNo());

	    dao.insertOne(vo);
	    
	    resp.sendRedirect(req.getContextPath()  +"/success");

	
	}
	
	
}
