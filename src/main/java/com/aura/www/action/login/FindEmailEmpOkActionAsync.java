package com.aura.www.action.login;

import java.lang.reflect.Field;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aura.www.action.Action;
import com.aura.www.dao.LoginDAO;
import com.aura.www.vo.DeptVO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FindEmailEmpOkActionAsync implements Action {
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		int status = 0;
		
		JSONObject obj = new JSONObject();
		
		String code = req.getParameter("code");
		String authCode = req.getParameter("authCode");
//		System.out.println("속성 get : " + req.getAttribute("authCode"));
		
//		System.out.println("code = " + code);
//		System.out.println("authCode = " + authCode);
		
		if(code != null && authCode != null) {
			if( code.equals(authCode) ) {
				status = 1;
			}
		}
		obj.put("status", status);
		
		return obj.toJSONString(); // jArr.toJSONString(); // JSON -> Array
	}
}
