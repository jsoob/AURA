package com.aura.www.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.aura.www.action.Action;
import com.aura.www.action.login.FindEmailEmpActionAsync;
import com.aura.www.action.login.FindEmailEmpOkActionAsync;
import com.aura.www.action.login.LoginOkActionAsync;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/loginasync")
public class LoginAsyncController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
        
		String cmd = req.getParameter("cmd");
		
		JSONArray jArr = new JSONArray();
		String tArr = "";
		
		// String -> JSON
		// JSONParser jsonParser = new JSONParser();
		
		if(cmd==null) {}
		
		// 사원 관리
		else if (cmd.equals("findEmailEmp")) {
			Action action = new FindEmailEmpActionAsync();
			tArr = action.execute(req, resp);
		} else if (cmd.equals("findEmailEmpOk")) {
			Action action = new FindEmailEmpOkActionAsync();
			tArr = action.execute(req, resp);
		} else if (cmd.equals("loginOk")) {
	  	  Action bc = new LoginOkActionAsync();
	  	  tArr = bc.execute(req, resp);
	    } 
		
		if(cmd!=null) {
			// 페이징 방식때문에 jsonObject로 받아한다.
//			try {
				// JSONObject jsonObj = (JSONObject) jsonParser.parse(tArr);

				// resp.getWriter().print(jsonObj.toJSONString()); // 값 보내기
				resp.getWriter().print(tArr); // 값 보내기
				resp.setContentType("application/json; charset=UTF-8");
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
		}
    }
}
