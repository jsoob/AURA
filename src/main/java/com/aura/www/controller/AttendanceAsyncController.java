package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.attendance.InsertWorkAction;
import com.aura.www.action.attendance.InsertWorkOkAction;
import com.aura.www.action.attendance.ModifyWorkAction;
import com.aura.www.action.attendance.ModifyWorkOkAction;
import com.aura.www.action.attendance.SelectWorkAction;
import com.aura.www.action.attendance.SelectWorkActionAsync;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/workasync")
public class AttendanceAsyncController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 한글처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		// 2. cmd로부터 파라미터 값 가져오기
		String cmd = req.getParameter("cmd");
		// System.out.println("cmd의 값은 : " + cmd); // cmd의 값은 : null
		String tArr = "";
		
		System.out.println("cmd = " + cmd);

		// 3. cmd == null or selectWork 라면?
			// 조회, 수정, 수정ok
		if (cmd == null || cmd.equals("selectWorkAsync")) {
			// 출퇴근 조회 처리
			Action action = new SelectWorkActionAsync();
			tArr = action.execute(req, resp);
		} 

		// 4. 최종적으로 지정된 URL로 요청을 포워딩
		if (cmd == null || cmd.equals("selectWorkAsync")) {
	         resp.getWriter().print(tArr); // 값 보내기
	         resp.setContentType("application/json; charset=UTF-8");
	      }

	}

}
