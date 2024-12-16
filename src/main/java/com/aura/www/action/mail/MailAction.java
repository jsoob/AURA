package com.aura.www.action.mail;

import com.aura.www.action.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MailAction implements Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String url = "";
		
		req.setAttribute("title", "AURA Mail");
		req.setAttribute("catecory", "mail");
		req.setAttribute("pages", "mail");
		
		url = "view/mail/selectAllMail.jsp";
		return url;
	}
}