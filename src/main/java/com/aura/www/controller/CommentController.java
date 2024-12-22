package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.board.freeboard.InsertCommentAction;
import com.aura.www.action.board.freeboard.SelectCommentAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/comment")
public class CommentController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		String url = "";

		// 3. page==null or SelectFreeB 라면
		if (cmd == null || cmd.equals("insertCmnt")) {
			Action action = new InsertCommentAction();
			action.execute(req, resp);
		} else if(cmd.equals("selectCmnt")) {
			Action action = new SelectCommentAction();
			action.execute(req, resp);
		}
	}
}
