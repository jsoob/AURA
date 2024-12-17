package com.aura.www.controller;

import java.awt.Desktop.Action;
import java.io.IOException;

import com.aura.www.action.board.deptboard.DeleteDeptBAction;
import com.aura.www.action.board.deptboard.DetailDeptBAction;
import com.aura.www.action.board.deptboard.ModifyDeptBAction;
import com.aura.www.action.board.deptboard.SelectDeptBAction;
import com.aura.www.action.board.deptboard.WriteDeptBFormAction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/freeboard")
public class DeptBoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 2. page 파라미터 값 가져오기
		String cmd = req.getParameter("cmd");
		String url = "";

		// 3. page==null or SelectDeptB 라면
		if (cmd == null || cmd.equals("selectDeptB")) {
			com.aura.www.action.Action action = new SelectDeptBAction();
			url = action.execute(req, resp); 
		} else if(cmd.equals("detailDeptB")) {
			com.aura.www.action.Action action = new DetailDeptBAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("writeDeptBForm")) {
			com.aura.www.action.Action action = new WriteDeptBFormAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("writeDeptOk")) {
			com.aura.www.action.Action action = new WriteDeptBFormAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("modifyFreeB")) {
			com.aura.www.action.Action action = new ModifyDeptBAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("modifyFreeBOk")) {
			com.aura.www.action.Action action = new ModifyDeptBAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("deleteFreeB")) {
			com.aura.www.action.Action action = new DeleteDeptBAction();
			url = action.execute(req, resp);
		}
		
		req.setAttribute("title", "AURA 자유게시판");
	    req.setAttribute("catecory", "게시판");
	      
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);

	}
}