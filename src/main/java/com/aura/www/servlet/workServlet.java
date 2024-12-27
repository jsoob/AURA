package com.aura.www.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.json.simple.JSONObject;

import com.aura.www.dao.AttendanceDAO;
import com.aura.www.vo.AttendanceVO;
import com.aura.www.vo.EmpVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/workservlet")
public class workServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("잘 되고있니ㅠ?");
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	
    	JSONObject obj = new JSONObject();


		HttpSession session = request.getSession();
		// 세션에서 EmpVO 객체 가져오기 (강제 형변환)
	    EmpVO loginEmp = (EmpVO)session.getAttribute("loginEmp");
	    String empNo = loginEmp.getEmpNo() + "";

    	String workGubun = request.getParameter("workGubun");
    	System.out.println("empNo : " + empNo);
        AttendanceVO vo = new AttendanceVO();
        
       if(empNo != null) {
        	
        	vo.setEmpNo(Integer.parseInt(empNo));
        	vo.setWorkGubun(workGubun);
        	AttendanceDAO dao = new AttendanceDAO();
        	
        	int cnt = dao.checkAttendance(vo);
        	
        	if(cnt == 0) {
        		if(workGubun.equals("start")) { // 출근 
        			dao.insertOne(vo);
        		} else if(workGubun.equals("end")) { // 퇴근
        			dao.updateEndWork(vo);
        		}
        		obj.put("status", 1);
        	} else if(cnt == 1) {
        		obj.put("status", 0);
        	}
        }
        response.getWriter().print(obj.toJSONString()); // 값 보내기
        response.setContentType("application/json; charset=UTF-8");
    }
}
