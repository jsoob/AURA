package com.aura.www.action.attendance;

import java.util.ArrayList;

import com.aura.www.action.Action;
import com.aura.www.attendance.dao.AttendanceDAO;
import com.aura.www.attendance.vo.AttendanceVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectAttenAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		AttendanceDAO dao = new AttendanceDAO();
		ArrayList<AttendanceVO> vo = dao.selectAll();
		
		req.setAttribute("vo", vo);
		
		
		return "view/attendance/SelectAttendance.jsp";
	}
	

}
