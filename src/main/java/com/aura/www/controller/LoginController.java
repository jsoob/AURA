package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.login.LoginFormAction;
import com.aura.www.action.login.LoginOkAction;
import com.aura.www.action.login.LogoutAction;

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
      String cmd = req.getParameter("cmd");
      String url = "";
      
      if(cmd==null || cmd.equals("loginForm")) {
    	  Action bc = new LoginFormAction();
          url = bc.execute(req, resp);
      } else if (cmd.equals("loginOk")) {
    	  Action bc = new LoginOkAction();
          url = bc.execute(req, resp);
      } else if (cmd.equals("logout")) {
    	  Action bc = new LogoutAction();
          url = bc.execute(req, resp);
      }
      
      RequestDispatcher rd = req.getRequestDispatcher(url);
      rd.forward(req, resp);
   }
}
