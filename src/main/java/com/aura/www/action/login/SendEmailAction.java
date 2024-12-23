package com.aura.www.action.login;

import java.io.IOException;
import java.util.Properties;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SendEmailAction implements Action {
   @Override
   public String execute(HttpServletRequest req, HttpServletResponse resp) {
	   String empNo = req.getParameter("empNo");
	   String empName = req.getParameter("empName");
	   String empEmail = req.getParameter("empEmail");
	   
	   boolean rst = empEmail.contains("google.com");
	   
	   Properties p = new Properties();
	   p.put("mail.smtp.enable", "true");                                                    
	   p.put("mail.smtp.host", "smtp.naver.com");                                            

	   p.put("mail.smtp.auth", "true");                                                      

	   p.put("mail.smtp.port", "587"); // IMAP/SMTP 설정   
	   
	   
	   return "view/login/sendEmail.jsp";
   }
}
