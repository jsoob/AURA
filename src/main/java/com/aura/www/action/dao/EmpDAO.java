package com.aura.www.action.dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.action.vo.EmpVO;

public class EmpDAO {
	
	// 1. 환경변수 선언

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "scott";
	String password = "tiger";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public EmpDAO() {
		// 2. 드라이버 로딩
		try {
			Class.forName(driver);
			// 3. Connection
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	} // constructor end
	
	/////////////////////////////////// 전체조회 //////////////////////////////////////

	public ArrayList<EmpVO> selectAll(){
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		
		//		4. SQL문 작성
		sb.setLength(0);
		sb.append("SELECT EMP_NO, EMP_PW, EMP_NAME, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, CELLPHONE, HIREDATE, QUITDATE, BIRTHDATE, POS_NO, DEPT_NO, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM EMP ");
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			//		6. 실행 (SELECT ==> ResultSet 객체 )
			while(rs.next()) {
				int empNo = rs.getInt("EMP_NO");
				String empPw = rs.getString("EMP_PW");
				String empName = rs.getString("EMP_NAME");
				String empImage = rs.getString("EMP_IMAGE");
				String cmpEmail = rs.getString("CMP_EMAIL");
				String empEmail = rs.getString("EMP_EMAIL");
				String cellPhone = rs.getString("CELLPHONE");
				String hireDate = rs.getString("HIREDATE");
				String quitDate = rs.getString("QUITDATE");
				String birthDate = rs.getString("BIRTHDATE");
				int posNo = rs.getInt("POS_NO");
				int deptNo = rs.getInt("DEPT_NO");
				String createDate = rs.getString("CREATE_DATE");
				String updateDate = rs.getString("UPDATE_DATE");
				
				EmpVO vo = new EmpVO(empNo, empPw, empName, empImage, cmpEmail, empEmail, cellPhone, hireDate, quitDate, birthDate, posNo, deptNo, createDate, updateDate);
				
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return list;
	}

}
