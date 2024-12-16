package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.main.MainAction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainController extends HttpServlet {
   
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String cmd = req.getParameter("cmd");
      
      String url = "";
      
      Action bc = new MainAction();
      url = bc.execute(req, resp);
      
      RequestDispatcher rd = req.getRequestDispatcher(url);
      rd.forward(req, resp);
   }
}

