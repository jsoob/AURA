package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.mail.MailAction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mail")
public class MailController extends HttpServlet {
   
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String cmd = req.getParameter("cmd");
      String url = "";
      
      if(cmd==null || cmd.equals("selectAll")) {
    	  Action bc = new MailAction();
          url = bc.execute(req, resp);
      }
      
      req.setAttribute("title", "AURA Mail");
      req.setAttribute("catecory", "Mail");
      
      RequestDispatcher rd = req.getRequestDispatcher(url);
      rd.forward(req, resp);
   }
}