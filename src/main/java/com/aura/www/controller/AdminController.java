package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.admin.position.SelectPosAction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/admin")
public class adminController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 2. page 파라미터 값 가져오기
		String cmd = req.getParameter("cmd");
		String url = "";

		// 3. page==null or SelectFreeB 라면
		if (cmd == null || cmd.equals("SelectPos")) {
			Action action = new SelectPosAction();
			url = action.execute(req, resp);
		}
		

		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
}
