package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.login.LoginFormAction;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  String cmd = (String)req.getParameter("cmd");
      String url = "";
      
      if(cmd==null || cmd.equals("loginForm")) {
    	  Action bc = new LoginFormAction();
          url = bc.execute(req, resp);
      } else if (cmd.equals("loginOk")) {
    	  Action bc = new com.aura.www.action.LoginOkAction();
          url = bc.execute(req, resp);
      } else if (cmd.equals("logout")) {
    	  Action bc = new com.aura.www.action.LogoutAction();
          url = bc.execute(req, resp);
      }
      
      if(url=="login") {
    	  resp.sendRedirect("login");
      } else if(url=="main") {
    	  resp.sendRedirect("main");
      } else {
        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(req, resp);
      }
   }
}
