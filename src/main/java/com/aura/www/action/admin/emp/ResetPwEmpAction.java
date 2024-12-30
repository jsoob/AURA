package com.aura.www.action.admin.emp;

import org.json.simple.JSONObject;

import com.aura.www.action.Action;
import com.aura.www.dao.AdminEmpDAO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ResetPwEmpAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		int cnt = 0;
		
		String eNo = req.getParameter("empNo");
	    
	    if (eNo != null && !eNo.isEmpty()) {
	        AdminEmpDAO dao = new AdminEmpDAO();
	        int empNo = Integer.parseInt(eNo);
	        
	        cnt = dao.resetEmpPwOne(empNo);
	    }
	    
	    JSONObject obj = new JSONObject();
		obj.put("resetPwStatus", cnt); // jsonobject에 넣고
		
		return obj.toJSONString(); // 문자로 return
	}
}
