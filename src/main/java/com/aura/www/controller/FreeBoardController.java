package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.board.freeboard.DeleteFreeBAction;
import com.aura.www.action.board.freeboard.DetailFreeBAction;
import com.aura.www.action.board.freeboard.ModifyFreeBAction;
import com.aura.www.action.board.freeboard.ModifyFreeBOkAction;
import com.aura.www.action.board.freeboard.SelectFreeBAction;
import com.aura.www.action.board.freeboard.WriteFreeBFormAction;
import com.aura.www.action.board.freeboard.WriteFreeBOkAction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/freeboard")
public class FreeBoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 2. page 파라미터 값 가져오기
		String cmd = req.getParameter("cmd");
		String url = "";

		// 3. page==null or SelectFreeB 라면
		if (cmd == null || cmd.equals("selectFreeB")) {
			Action action = new SelectFreeBAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("detailFreeB")) {
			Action action = new DetailFreeBAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("writeFreeBForm")) {
			Action action = new WriteFreeBFormAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("writeFreeBOk")) {
			Action action = new WriteFreeBOkAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("modifyFreeB")) {
			Action action = new ModifyFreeBAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("modifyFreeBOk")) {
			Action action = new ModifyFreeBOkAction();
			url = action.execute(req, resp);
		} else if(cmd.equals("deleteFreeB")) {
			Action action = new DeleteFreeBAction();
			url = action.execute(req, resp);
		}
		
		req.setAttribute("title", "AURA 자유게시판");
	    req.setAttribute("catecory", "게시판");
	      
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);

	}
}
