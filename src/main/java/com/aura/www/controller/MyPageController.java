package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.main.MainAction;
import com.aura.www.action.mypage.MyInfoAction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mypage")
public class MyPageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
	    String url = "";
	      
	    if(cmd==null) {
	    	Action bc = new MainAction();
	        url = bc.execute(req, resp);
	    } else if (cmd.equals("detail")) {
	    	Action bc = new MyInfoAction();
	        url = bc.execute(req, resp);
	    }
	      
	    RequestDispatcher rd = req.getRequestDispatcher(url);
	    rd.forward(req, resp);
	}
}
