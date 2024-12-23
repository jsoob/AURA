package com.aura.www.action.attendance;

import com.aura.www.action.Action;
import com.aura.www.dao.AttendanceDAO;
import com.aura.www.vo.AttendanceVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyWorkOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String num = req.getParameter("empNo");
		
		if (num != null) {
			int empNo = Integer.parseInt(num);
			String attenDate = req.getParameter("attenDate");
			String startWorkTime = req.getParameter("startWorkDate");
			String endWorkTime = req.getParameter("endWorkDate");
			
			AttendanceDAO dao = new AttendanceDAO();
			AttendanceVO vo = dao.selectOne(empNo);
			
			vo.setAttenDate(attenDate);
			vo.setStartworkTime(startWorkTime);
			vo.setEndworkTime(endWorkTime);
			
			dao.updateOne(vo);
			
			}

			// jsp 파일 위치
			return "work?cmd=selectWork";
	}

}