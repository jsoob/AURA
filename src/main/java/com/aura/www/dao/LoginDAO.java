package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aura.www.vo.EmpVO;

public class LoginDAO {
	// 1. 환경변수
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/aura"; // mysql port -> 3306 / 3307
	String user = "aura";
	String password = "tigertiger12$$";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	// 기본 생성자
	public LoginDAO() {
		// 2. 클래스 로딩
		try {
			Class.forName(driver);
			// 3. Connection
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn");
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	} // constructer end

	public EmpVO selectLogin(int emp_no, String emp_pw) {
		sb.setLength(0);
		sb.append("SELECT ");
		sb.append("EMP_NO, EMP_PW, EMP_NAME, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, CELLPHONE, ");
		sb.append("HIREDATE, QUITDATE, BIRTHDATE, ");
		sb.append("POS_NO, DEPT_NO, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM EMP ");
		sb.append("WHERE EMP_NO = ? ");
		sb.append("AND EMP_PW = ? ");
		// 2024000
		EmpVO vo = null;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, emp_no);
			pstmt.setString(2, emp_pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String emp_name = rs.getString("EMP_NAME");
				String emp_image = rs.getString("EMP_IMAGE");
				String cmp_email = rs.getString("CMP_EMAIL");
				String emp_email = rs.getString("EMP_EMAIL");
				String cellphone = rs.getString("CELLPHONE");
				
				String hiredate = rs.getString("HIREDATE");
				String quitdate = rs.getString("QUITDATE");
				String birthdate = rs.getString("BIRTHDATE");
				
				int pos_no = rs.getInt("POS_NO");
				int dept_no = rs.getInt("DEPT_NO");
				String create_date = rs.getString("CREATE_DATE");
				String update_date = rs.getString("UPDATE_DATE");
			
				vo = new EmpVO();
				
				vo.setEmpNo(emp_no);
				vo.setEmpPw(emp_pw);
				vo.setEmpName(emp_name);
				vo.setEmpImage(emp_image);
				vo.setCmpEmail(cmp_email);
				vo.setEmpEmail(emp_email);
				vo.setCellphone(cellphone);
				vo.setHiredate(hiredate);
				vo.setQuitdate(quitdate);
				vo.setBirthdate(birthdate);
				vo.setPosNo(pos_no);
				vo.setDeptNo(dept_no);
				vo.setCreateDate(create_date);
				vo.setUpdateDate(update_date);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return vo;
	}
	
}
