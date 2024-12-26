package com.aura.www.controller;

import java.awt.Desktop.Action;
import java.io.IOException;

import com.aura.www.action.board.deptboard.DeleteDeptBAction;
import com.aura.www.action.board.deptboard.DetailDeptBAction;
import com.aura.www.action.board.deptboard.ModifyDeptBAction;
import com.aura.www.action.board.deptboard.ModifyDeptBOkAction;
import com.aura.www.action.board.deptboard.SelectDeptBAction;
import com.aura.www.action.board.deptboard.WriteDeptBFormAction;
import com.aura.www.action.board.deptboard.WriteDeptBOkAction;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/deptboard")
public class DeptBoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		 // 2. 세션에서 로그인한 사용자 정보 가져오기
	    HttpSession session = req.getSession();
	    EmpVO loginEmp = (EmpVO) session.getAttribute("loginEmp");
	    if (loginEmp == null) {
	        req.setAttribute("error", "로그인이 필요합니다.");
	        RequestDispatcher rd = req.getRequestDispatcher("view/common/login.jsp");
	        rd.forward(req, resp);
	        return;
	    }
	    int userDeptNo = loginEmp.getDeptNo(); // 사용자의 부서 ID
		
		
		// 3. page 파라미터 값 가져오기
		String cmd = req.getParameter("cmd");
		String url = "";

		// 3. page==null or SelectDeptB 라면
		if (cmd == null || cmd.equals("selectDeptB")) {
			System.out.println("inside deptBoardcontroller");
			com.aura.www.action.Action action = new SelectDeptBAction(userDeptNo);
			url = action.execute(req, resp); 
		} else if(cmd.equals("detailDeptB")) {
			com.aura.www.action.Action action = new DetailDeptBAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("writeDeptBForm")) {
			com.aura.www.action.Action action = new WriteDeptBFormAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("writeDeptBOk")) {
			com.aura.www.action.Action action = new WriteDeptBOkAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("modifyDeptB")) {
			com.aura.www.action.Action action = new ModifyDeptBAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("modifyDeptBOk")) {
			com.aura.www.action.Action action = new ModifyDeptBOkAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("deleteDeptB")) {
			com.aura.www.action.Action action = new DeleteDeptBAction();
			url = action.execute(req, resp);
		}
		
	      
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);

	}
}