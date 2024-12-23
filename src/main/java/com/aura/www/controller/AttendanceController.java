package com.aura.www.controller;

import java.awt.Desktop.Action;
import java.io.IOException;

import com.aura.www.action.attendance.ModifyWorkAction;
import com.aura.www.action.attendance.ModifyWorkOkAction;
import com.aura.www.action.attendance.SelectWorkAction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/work")
public class AttendanceController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 2. cmd로부터 파라미터 값 가져오기
		String cmd = req.getParameter("cmd");
		// System.out.println("cmd의 값은 : " + cmd); // cmd의 값은 : null
		String url = "";

		// 3. cmd == null or list 라면?
			// 조회, 수정, 수정ok
		if (cmd == null || cmd.equals("selectWork")) {
			com.aura.www.action.Action action = new SelectWorkAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("modifyWork")) {
			com.aura.www.action.Action action = new ModifyWorkAction();
			url = action.execute(req, resp);
		} else if (cmd.equals("modifyWorkOk")) {
			com.aura.www.action.Action action = new ModifyWorkOkAction();
			url = action.execute(req, resp);
		}

		// 4. view/attendance/attendanceList.jsp 로 설정할 것
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);

	}

}
