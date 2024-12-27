package com.aura.www.action.attendance;

import java.util.ArrayList;
import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.AttendanceDAO;
import com.aura.www.vo.AttendanceVO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SelectWorkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("title", "AURA 근태관리 페이지"); // 웹 제목?
		map.put("category", "emp"); // 카테고리 찾는 key
		map.put("categoryName", "근태관리 페이지"); // 사용자에게 보여주는 카테고리명
		map.put("pages", "selectEmp"); // 페이지명
		map.put("pagesName", "근태 조회"); // 사용자에게 보여주는 페이지명
			
		// HashMap을 사용하여 공통 데이터를 JSP로 전달
		// map에 있는 데이터는 JSP에서 사용 가능 (ex. 제목, 카테고리 등)
		req.setAttribute("commAt", map);
//		
//		// DAO 호출 & 예외 처리
//		ArrayList<AttendanceVO> list = null;
//		
//		try {
//		AttendanceDAO dao = new AttendanceDAO();
//		// HttpSession session = req.getSession();
//		// AttendanceVO vo = (AttendanceVO)session.getAttribute("loginEmp");
//		
//		HttpSession session = req.getSession();
//		// 세션에서 EmpVO 객체 가져오기 (강제 형변환)
//	    EmpVO loginEmp = (EmpVO)session.getAttribute("loginEmp");
//	    
//	 // EmpVO 데이터를 기반으로 AttendanceVO 객체 생성
//	    AttendanceVO attendanceVo = new AttendanceVO();
//	    // empVO.getEmpNo()로 할게 아니라 loginEmp.getEmpNo()로 변경
//	    	// 정적 메서드가 아니기 때문에 오류 발생
//	    	// empVO 객체를 종해서 getEmpNo()를 호출해야함
//	    // loginEmp.getEmpNo() : empVO 클래스의 인스턴스 메서드이기 때문에, 객체의 인스턴스를 통해서 호출
//	    attendanceVo.setEmpNo(loginEmp.getEmpNo()); // EmpVO의 사번 데이터를 AttendanceVO에 설정
//
//	    // DAO 호출
//	    list = dao.selectAll(attendanceVo);
//	   
//		
//		} catch(Exception e) {			// 예외처리
//			e.printStackTrace();
//			req.setAttribute("error", "데이터 조회 중 오류가 발생하였습니다. 관리자에게 문의해주세요.");
//		}
//		req.setAttribute("list", list);	// list 데이터를 jsp로 전달하는 역할
//		
		// view의 경로 반환
		return "view/work/SelectWork.jsp";
		
	}
}