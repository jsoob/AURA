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

// MVC 패턴을 구현하기 위해 사용
// MainController 서블릿이 클라이언트 요청을 받아 적절한 처리를 수행한 뒤 결과를 사용자에게 전달하는 역할
// MainController로 시작해서 모든 인덱스로 접근

// Controller: MainController는 요청을 받아 적절한 로직을 실행하고 결과를 반환
// Model: 데이터를 처리하거나 데이터베이스와 통신하는 클래스
// View: RequestDispatcher를 통해 반환된 JSP와 같은 프론트엔드 리소스가 사용자에게 보여줄 결과 화면

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

