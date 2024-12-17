package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.admin.AdminLoadAction;
import com.aura.www.action.main.MainAction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  String cmd = (String)req.getParameter("cmd");
      String url = "";
      
      if(cmd==null) {
    	  Action bc = new MainAction();
          url = bc.execute(req, resp);
      } else if (cmd.equals("adminLoad")) {
    	  Action bc = new AdminLoadAction();
          url = bc.execute(req, resp);
      }
      
      if(url=="main") {
    	  resp.sendRedirect("main");
      } else {
    	req.setAttribute("title", "AURA 관리자 페이지");
		req.setAttribute("catecory", "관리자 조회");  
    	  
        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(req, resp);
      }
   }
}