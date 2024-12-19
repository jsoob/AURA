package com.aura.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.aura.www.action.Action;
import com.aura.www.action.admin.emp.SelectEmpActionAsync;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/adminasync")
public class AdminAsyncController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
        
		String cmd = req.getParameter("cmd");
		
		JSONArray jArr = new JSONArray();
		// String -> JSON
		JSONParser jsonParser = new JSONParser();
		
		// 사원 관리
		if (cmd.equals("selectEmp")) {
			ArrayList<EmpVO> list = null;
			Action action = new SelectEmpActionAsync();
			String tArr = action.execute(req, resp);
			try {
				// JSON 변환
				jArr = (JSONArray) jsonParser.parse(tArr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
//			JSONObject jObj = new JSONObject();
//	        jObj.put("dataList", list); // key, value
		}
		
		 resp.setContentType("application/json; charset=UTF-8");
		// resp.setContentType("application/x-json; charset=utf-8");
		// resp.setContentType("text/html; charset=UTF-8");
		resp.getWriter().print(jArr.toJSONString());
		
		
		// String jsonInfo = jObj.toJSONString();
		// resp.getWriter().print(jsonInfo);
    }
}
