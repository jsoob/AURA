package com.aura.www.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/oldStartWorkServlet")
public class OldStartWorkServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empNo = request.getParameter("empNo");  // 사원 번호
        String currentTime = getCurrentTime();  // 출근 시간을 현재 시간으로 설정

        // DB 연결 및 출근 시간 저장
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // MySQL 연결 설정
            String dbUrl = "jdbc:mysql://localhost:3306/aura"; // DB URL
            String dbUser = "aura"; // DB 사용자
            String dbPassword = "password"; // DB 비밀번호
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            
            // 출근 시간 저장 쿼리
            String sql = "UPDATE ATTENDANCE SET STARTWORK_TIME = ? WHERE EMP_NO = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, currentTime);
            stmt.setString(2, empNo);
            
            int result = stmt.executeUpdate(); // 쿼리 실행

            // 응답 처리
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            if (result > 0) {
                out.write("{\"success\": true, \"startworkTime\": \"" + currentTime + "\"}");
            } else {
                out.write("{\"success\": false, \"message\": \"출근 시간 등록에 실패했습니다.\"}");
            }
            out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"success\": false, \"message\": \"DB 오류 발생\"}");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String getCurrentTime() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
    }
}
