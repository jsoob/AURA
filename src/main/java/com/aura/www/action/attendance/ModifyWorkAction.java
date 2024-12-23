package com.aura.www.action.attendance;

import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.AttendanceDAO;
import com.aura.www.vo.AttendanceVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifyWorkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String num = req.getParameter("empNo");
		if (num != null) {
			int empNo = Integer.parseInt(num);
			AttendanceDAO dao = new AttendanceDAO();
			AttendanceVO vo = dao.selectOne(empNo);
			req.setAttribute("vo", vo);
		}
		
		HashMap<String, String> map = new HashMap<>();
		
		map.put("attenDate", "날짜");				// 오늘 날짜
		map.put("startWorkTime", "출근시간");		// 출근 시간
		map.put("endWorkTime", "퇴근시간");		// 퇴근 시간
		
		req.setAttribute("commAt", map);

		return "view/attendance/ModifyWork.jsp";
	}
	

}
