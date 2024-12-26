package com.aura.www.controller;

import java.io.IOException;

import com.aura.www.action.Action;
import com.aura.www.action.admin.emp.ModifyEmpOkAction;
import com.aura.www.action.main.MainAction;
import com.aura.www.action.main.editMyEmpOkAction;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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
@MultipartConfig( // 파일 업로드 처리를 위한 설정 정보
		fileSizeThreshold = 1024*1024*2, // 2MB / 이 크기를 초과하면 디스크에 임시 파일로 저장
		maxFileSize = 1024*1024*30, // 30MB
		maxRequestSize = 1024*1024*60 // 최대 요청 사이즈
 )
public class MainController extends HttpServlet {
   
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  req.setCharacterEncoding("UTF-8");
	  resp.setContentType("text/html;charset=UTF-8");

	  String cmd = req.getParameter("cmd");
      System.out.println("cmd = "+cmd);
      
      
      String url = "";
      if(cmd==null || cmd.equals("main")) {
    	  Action bc = new MainAction();
    	  url = bc.execute(req, resp);
    	  
    	  RequestDispatcher rd = req.getRequestDispatcher(url);
    	  rd.forward(req, resp);
      } else if (cmd.equals("editMyEmpOk")) {
    	  Action action = new editMyEmpOkAction();
          url = action.execute(req, resp);
          
          resp.getWriter().print(url); // 값 보내기
          resp.setContentType("application/json; charset=UTF-8");
      }
      
   }
}

