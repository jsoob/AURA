package com.aura.www.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.aura.www.action.Action;
import com.aura.www.action.admin.dept.SelectDeptActionAsync;
import com.aura.www.action.admin.emp.SelectEmpActionAsync;
import com.aura.www.action.admin.emp.deleteEmpActionAsync;
import com.aura.www.action.admin.position.SelectPosActionAsync;
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
		String tArr = "";
		
		// String -> JSON
		JSONParser jsonParser = new JSONParser();
		
		if(cmd==null) {}
		
		// 사원 관리
		else if (cmd.equals("selectEmp")) {
			ArrayList<EmpVO> list = null;
			Action action = new SelectEmpActionAsync();
			tArr = action.execute(req, resp);
//			JSONObject jObj = new JSONObject();
//	        jObj.put("dataList", list); // key, value
		} else if (cmd.equals("selectDept")) {
			Action action = new SelectDeptActionAsync();
			tArr = action.execute(req, resp);
		} else if (cmd.equals("selectPos")) {
			Action action = new SelectPosActionAsync();
			tArr = action.execute(req, resp);
		}
//		else if (cmd.equals("deleteEmp")) {
//			Action action = new deleteEmpActionAsync();
//			tArr = action.execute(req, resp);
//		}
		
		if(cmd!=null) {
			// 페이징 방식때문에 jsonObject로 받아한다.
			if(cmd.equals("selectEmp")) {
				try {
					JSONObject jsonObj = (JSONObject) jsonParser.parse(tArr);

					resp.getWriter().print(jsonObj.toJSONString());
					resp.setContentType("application/json; charset=UTF-8");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				try {
					// JSON 변환
					jArr = (JSONArray) jsonParser.parse(tArr);
					
					resp.getWriter().print(jArr.toJSONString());
					resp.setContentType("application/json; charset=UTF-8");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		// resp.setContentType("application/x-json; charset=utf-8");
		// resp.setContentType("text/html; charset=UTF-8");
		
		
		// String jsonInfo = jObj.toJSONString();
		// resp.getWriter().print(jsonInfo);
    }
}
