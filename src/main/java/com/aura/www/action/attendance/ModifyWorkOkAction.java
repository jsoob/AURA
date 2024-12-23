package com.aura.www.action.attendance;

import com.aura.www.action.Action;
import com.aura.www.attendance.dao.AttendanceDAO;
import com.aura.www.attendance.vo.AttendanceVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyWorkOkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		String attenDate = req.getParameter("attenDate");
		String num = req.getParameter("empNo");
		String startWorkTime = req.getParameter("startWorkTime");
		String endWorkTime = req.getParameter("endWorkTime");

		if (num != null) {
			int empNo = Integer.parseInt(num);

			AttendanceDAO dao = new AttendanceDAO();
			AttendanceVO vo = new AttendanceVO();

			vo.setAttenDate(attenDate);
			vo.setEmpNo(empNo);
			vo.setStartworkTime(startWorkTime);
			vo.setEndworkTime(endWorkTime);

			dao.updateOne(vo);

		}

		// jsp 파일 위치
		return "view/modifyWorkOk.jsp";
	}

}
