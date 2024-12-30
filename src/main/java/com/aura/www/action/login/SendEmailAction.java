package com.aura.www.action.login;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.aura.www.action.Action;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SendEmailAction implements Action {
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String empNo = req.getParameter("empNo");
		String empName = req.getParameter("empName");
		String empEmail = req.getParameter("empEmail");
		
		String gmD = "@gmail.com";
		String nvD = "@naver.com";
		
		boolean gmailRs = empEmail.contains(gmD);
		boolean naverRs = empEmail.contains(nvD);
		
		Properties p = new Properties();
		
		// 보내는 사람 이메일 // admin 이메일
		String username = "kmhe0128";
		
		Session sessions = null;
		
		if(gmailRs) {
			// 구글 설정
			username+=gmD;
			
			// 보내는 사람
			String userNm = username;
			String password ="jarstjnmhskfreit";
			
			p.put("mail.transport.protocol", "smtp");
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.put("mail.smtp.port", "465");
			p.put("mail.smtp.auth", "true");
			
			sessions = Session.getInstance(p, new Authenticator() {
				protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userNm, password);
				}
			});
		} else if(naverRs) {
			// 네이버 설정
			username+=nvD;

			// 보내는 사람
			String userNm = username;
			String password ="S9D4ZFELCW85";
			
			p.put("mail.smtp.enable", "true"); 
			p.put("mail.smtp.host", "smtp.naver.com"); 
			p.put("mail.smtp.auth", "true"); 
			p.put("mail.smtp.port", "587"); // IMAP/SMTP 설정

			sessions = Session.getInstance(p, new Authenticator() {
				protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userNm, password);
				}
			});
		}
		p.put("mail.smtp.quitwait", "false");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		// 받는 사람 이메일
		String receiver = empEmail;
		
		// 보낼 메세지
		StringBuffer sb = new StringBuffer();
		sb.append("<h3>"+empName+"님, 안녕하세요.</h3>");
		sb.append("<h3>귀하의 이메일 주소를 통해 인증번호 메일 발송입니다.</h3>");
		String code = ""+(int)(Math.random() * 8999) + 1000; // (int)(Math.random()*100000);
		sb.append("<h3> 인증번호는 ["+code+"] 입니다. </h3>");
		sb.append("<h3> 이 이메일은 발신 전용입니다. 자세한 정보는 AURA 관리자에게 문의해 주세요. </h3>");
		
//		System.out.println("인증번호 code : " + code);
//		System.out.println("sessions : " + sessions);
		
		// 인증번호 Redis 나중에 구현하자.
		req.setAttribute("empNo", empNo);
		req.setAttribute("empName", empName);
		req.setAttribute("authCode", code);
		
		String title = "AURA 인증번호";
		
		Message message = new MimeMessage(sessions);
		try {
			message.setFrom(new InternetAddress(username, "AURA 관리자", "UTF-8"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
							// 보낼메세지
			message.setContent(sb.toString(), "text/html; charset=UTF-8");
			Transport.send(message); // 메일 전송
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	   return "view/login/sendEmail.jsp";
   }
}
