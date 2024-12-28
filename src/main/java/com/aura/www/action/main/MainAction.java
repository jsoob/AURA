package com.aura.www.action.main;

import java.util.ArrayList;
import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.MainDAO;
import com.aura.www.vo.DeptBoardVO;
import com.aura.www.vo.EmpVO;
import com.aura.www.vo.FreeBoardVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MainAction implements Action {
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String url = "";
		
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("title", "AURA"); // 웹 제목?
		map.put("category", "main"); // 카테고리 찾는 key
		map.put("categoryName", "Main"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "main"); // 페이지명
		map.put("pagesName", "메인 화면"); // 사용자에게 보여주는 페이지명
			
		req.setAttribute("commAt", map);
		
//		req.setAttribute("title", "AURA");
//		req.setAttribute("catecory", "main");
//		req.setAttribute("pages", "main");
		
		MainDAO dao = new MainDAO();
		
		HttpSession session = req.getSession();
		EmpVO loginEmp = (EmpVO)session.getAttribute("loginEmp");
		int empNo = loginEmp.getEmpNo();
		int deptNo = loginEmp.getDeptNo();
		
		int limitNo = 6; // 안쓰면 -1로 하기
		
		FreeBoardVO freeBVo = new FreeBoardVO();
		freeBVo.setFreeBCrtr(empNo);
		ArrayList<FreeBoardVO> freeBList = dao.selectFreeBoard(freeBVo, limitNo);
		int freeBTotalCount = dao.getFreeBTotalCount(freeBVo);
		req.setAttribute("freeBList", freeBList);
		req.setAttribute("freeBTotalCount", freeBTotalCount);
		
		DeptBoardVO deptBVo = new DeptBoardVO();
		deptBVo.setDeptBCrtr(empNo);
		deptBVo.setDeptNo(deptNo);
		ArrayList<DeptBoardVO> deptBList = dao.selectDeptBoard(deptBVo, limitNo);
		int deptBTotalCount = dao.getDeptBTotalCount(deptBVo);
		req.setAttribute("deptBList", deptBList);
		req.setAttribute("deptBTotalCount", deptBTotalCount);
		
		url = "view/main/main.jsp";
		return url;
	}
	
}