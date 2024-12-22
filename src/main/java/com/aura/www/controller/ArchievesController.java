package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.board.archivesboard.DeleteArcBAction;
import com.aura.www.action.board.archivesboard.DetailArcBAction;
import com.aura.www.action.board.archivesboard.ModifyArcBAction;
import com.aura.www.action.board.archivesboard.ModifyArcOkAction;
import com.aura.www.action.board.archivesboard.SelectArcBAction;
import com.aura.www.action.board.archivesboard.WriteArcBAction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/archives")
public class ArchievesController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 2. cmd로부터 파라미터 값 가져오기
		String cmd = req.getParameter("cmd");
		// System.out.println("cmd :" + cmd); // cmd : null (정상적으로 콘솔에 찍힘)
		String url = "";

		// 3. cmd = null or SelectArcBAction 라면?
			// 3-1. 파라미터 값 가져오기
			// 3-2. dao, vo 만들고 값을 담기
			// 3-3. db에서 수정
			// 3-4. 다시 목록으로 작성
		if (cmd == null || cmd.equals("SelectArcBAction")) {
			Action action = new SelectArcBAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("DetailArcBAction")) {
			Action action = new DetailArcBAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("WriteArcBAction")) {
			Action action = new WriteArcBAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("ModifyArcBAction")) {
			Action action = new ModifyArcBAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("ModifyArcOkAction")) {
			Action action = new ModifyArcOkAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("DeleteArcBAction")) {
			Action action = new DeleteArcBAction();
			url = action.execute(req, resp);
		}
					
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}

}
