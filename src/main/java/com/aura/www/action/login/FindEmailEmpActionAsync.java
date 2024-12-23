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

public class FindEmailEmpActionAsync implements Action {
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		EmpVO vo = null;
		int status = 0;
		
		// select 조건문
		String eNo = req.getParameter("empNo");
		
		if(eNo != null) {
			int empNo = 0;
			
			try {
				empNo = Integer.parseInt(eNo);
				
				LoginDAO dao = new LoginDAO();
				
				vo = dao.getEmailEmp(empNo); // 전체수
				if(vo != null) {
					status = 1;
				}
			} catch (NumberFormatException e) {
				status = 3;
			}
		}
		
		JSONObject obj = new JSONObject();
		obj.put("getEmailEmp", vo_to_json(vo));
		obj.put("status", status);
		
		return obj.toJSONString(); // jArr.toJSONString(); // JSON -> Array
	}
	
	private JSONObject vo_to_json(EmpVO vo)
    {       
        JSONObject json_obj = new JSONObject();
        
		Field[] fields = vo.getClass().getDeclaredFields();
		for(int i=0; i <fields.length; i++){
			fields[i].setAccessible(true);
			
			String key = fields[i].getName();
			Object value = null;
			try {
				value = fields[i].get(vo);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			json_obj.put(key,value);
		};
        return json_obj;
    }
}
