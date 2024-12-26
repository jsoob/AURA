package com.aura.www.action.attendance;

import java.util.ArrayList;
import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.AttendanceDAO;
import com.aura.www.vo.AttendanceVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SelectWorkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("title", "AURA 근태관리 페이지"); // 웹 제목?
		map.put("category", "emp"); // 카테고리 찾는 key
		map.put("categoryName", "근태관리 페이지"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "selectEmp"); // 페이지명
		map.put("pagesName", "근태 조회"); // 사용자에게 보여주는 페이지명
		
		/*
		 * map.put("attenDate", "날짜"); // 오늘 날짜 map.put("startWorkTime", "출근시간"); // 출근
		 * 시간 map.put("endWorkTime", "퇴근시간"); // 퇴근 시간
		 */		
		req.setAttribute("commAt", map);
		
		/*
		 * AttendanceDAO dao = new AttendanceDAO(); ArrayList<AttendanceVO> list =
		 * dao.selectAll();
		 */
		
		// DAO 호출 & 예외 처리
		ArrayList<AttendanceVO> list = null;
		
		try {
		AttendanceDAO dao = new AttendanceDAO();
		list = dao.selectAll();
		} catch(Exception e) {			// 예외처리
			e.printStackTrace();
			req.setAttribute("error", "데이터 조회 중 오류가 발생하였습니다. 관리자에게 문의해주세요.");
		}
		req.setAttribute("list", list);
		
		// view의 경로 반환
		return "view/work/SelectWork.jsp";
		
	}
}