package com.aura.www.action.login;

import java.io.IOException;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SendEmailAction implements Action {
   @Override
   public String execute(HttpServletRequest req, HttpServletResponse resp) {
	   return "view/login/sendEmail.jsp";
   }
}
