package com.aura.www.servlet; // 패키지 경로

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EndWorkServlet")  // 서블릿 경로
public class EndWorkServlet extends HttpServlet {
    private Map<String, String> workTimes = new HashMap<>();  // 사원 번호를 키로, 퇴근 시간을 값으로 저장

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empNo = request.getParameter("empNo");  // 사원번호
        String currentTime = getCurrentTime();  // 퇴근 시간을 현재 시간으로 설정

        // 퇴근 시간 저장
        workTimes.put(empNo, currentTime);

        // 응답으로 결과 반환
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write("{\"success\": true, \"endworkTime\": \"" + currentTime + "\"}");
        out.flush();
    }

    // 현재 시간을 구하는 간단한 메소드 (하드코딩된 시간 형식)
    private String getCurrentTime() {
        return "2024-12-26 18:00:00";  // 하드코딩된 예시 시간
    }
}
