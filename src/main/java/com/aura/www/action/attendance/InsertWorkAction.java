package com.aura.www.action.attendance;

import java.util.HashMap;

import com.aura.www.action.Action;
import com.aura.www.dao.AttendanceDAO;
import com.aura.www.vo.AttendanceVO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertWorkAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        
        HashMap<String, String> map = new HashMap<String, String>();
        
        map.put("title", "AURA 근태관리 페이지");
        map.put("category", "emp");
        map.put("categoryName", "근태관리 페이지");
        map.put("pages", "insertEmp");
        map.put("pagesName", "근태 등록");
        
        req.setAttribute("commAt", map);
        
        // 파라미터 받기
        String empNo = req.getParameter("empNo");  // 사원번호
        String startworkTime = req.getParameter("startworkTime");  // 출근시간
        String endworkTime = req.getParameter("endworkTime");  // 퇴근시간

        // AttendanceVO 객체 생성 및 값 설정
        AttendanceVO vo = new AttendanceVO();
        vo.setEmpNo(Integer.parseInt(empNo));  // 사원번호는 Integer로 변환
        vo.setStartworkTime(startworkTime);   // 출근시간
        vo.setEndworkTime(endworkTime);       // 퇴근시간

        // AttendanceDAO 사용하여 데이터 삽입
        AttendanceDAO dao = new AttendanceDAO();
        boolean success = dao.insertWork(vo);  // VO 객체를 전달

        // 결과 메시지 전달
        if (success) {
            req.setAttribute("message", "출퇴근 시간이 정상 등록되었습니다.");
        } else {
            req.setAttribute("message", "출퇴근 시간 등록에 실패했습니다.");
        }
        
        return "view/admin/insertWork.jsp";
    }
}
