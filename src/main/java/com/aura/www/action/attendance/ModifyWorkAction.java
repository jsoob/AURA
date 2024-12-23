package com.aura.www.action.attendance;

import com.aura.www.action.Action;
import com.aura.www.attendance.dao.AttendanceDAO;
import com.aura.www.attendance.vo.AttendanceVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyWorkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String b = req.getParameter("empNo");
		
		if(b != null) {
			int empNo = Integer.parseInt(b);
			AttendanceDAO dao = new AttendanceDAO();
			AttendanceVO vo = dao.selectOne(empNo);
			req.setAttribute("vo", vo);
		}
		
		return "view/attendance/ModifyWork.jsp";
	}
	

}
