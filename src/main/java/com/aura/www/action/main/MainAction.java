package com.aura.www.action.main;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainAction implements Action {

   @Override
   public String execute(HttpServletRequest req, HttpServletResponse resp) {
      
      String url = "view/main/main.jsp";
      
      return url;
   }

}
