package com.aura.www.action.attendance;

import com.aura.www.action.Action;
import com.aura.www.dao.AttendanceDAO;
import com.aura.www.vo.AttendanceVO;
import com.aura.www.vo.EmpVO;
import com.aura.www.vo.PositionVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UpdateEndWorkAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		// 파라미터값 가져와서 db에 저장
		String eno = req.getParameter("empNo");
		String empName = req.getParameter("empName");

		if (eno != null) {
			int empNo = Integer.parseInt(eno);
			AttendanceDAO dao = new AttendanceDAO();
			
			HttpSession session = req.getSession();
			// 세션에서 EmpVO 객체 가져오기 (강제 형변환)
		    EmpVO loginEmp = (EmpVO)session.getAttribute("loginEmp");
		    
		 // EmpVO 데이터를 기반으로 AttendanceVO 객체 생성
		    AttendanceVO vo = new AttendanceVO();
		    // empVO.getEmpNo()로 할게 아니라 loginEmp.getEmpNo()로 변경
		    	// 정적 메서드가 아니기 때문에 오류 발생
		    	// empVO 객체를 종해서 getEmpNo()를 호출해야함
		    // loginEmp.getEmpNo() : empVO 클래스의 인스턴스 메서드이기 때문에, 객체의 인스턴스를 통해서 호출
		    vo.setEmpNo(loginEmp.getEmpNo()); // EmpVO의 사번 데이터를 AttendanceVO에 설정

			dao.updateEndWork(vo);
		}

		return "admin?cmd=selectWork";
	}

}
