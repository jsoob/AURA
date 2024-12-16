package com.aura.www.dao;

import java.lang.reflect.Array;
import java.security.spec.RSAKeyGenParameterSpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.vo.EmpVO;

public class EmpDAO {

	// 1. 환경변수 선언

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:aura";
	String user = "aura";
	String password = "tigertiger12$$";
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

	public ArrayList<EmpVO> selectAll() {
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();

		// 4. SQL문 작성
		sb.setLength(0);
		sb.append(
				"SELECT EMP_NO, EMP_PW, EMP_NAME, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, CELLPHONE, HIREDATE, QUITDATE, BIRTHDATE, POS_NO, DEPT_NO, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM EMP ");

		// 5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			// 6. 실행 (SELECT ==> ResultSet 객체 )
			while (rs.next()) {
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

				EmpVO vo = new EmpVO(empNo, empPw, empName, empImage, cmpEmail, empEmail, cellPhone, hireDate, quitDate,
						birthDate, posNo, deptNo, createDate, updateDate);

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // selectAll() end

	/////////////////////////////////// 1건 조회 //////////////////////////////////////

	public EmpVO selectOne(int empNo) {

		// 4. SQL문 작성
		sb.setLength(0);
		sb.append(
				"SELECT EMP_NO, EMP_PW, EMP_NAME, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, CELLPHONE, HIREDATE, QUITDATE, BIRTHDATE, POS_NO, DEPT_NO, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM EMP ");
		sb.append("WHERE EMP_NO = ? ");

		EmpVO vo = null;

		// 5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			// 6. 실행 (SELECT ==> ResultSet 객체 )
			rs = pstmt.executeQuery();
			while (rs.next()) {
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

				vo = new EmpVO(empNo, empPw, empName, empImage, cmpEmail, empEmail, cellPhone, hireDate, quitDate,
						birthDate, posNo, deptNo, createDate, updateDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	} // selectOne() end

	/////////////////////////////////// 추가 //////////////////////////////////////

	public void insertOne(EmpVO vo) {

		// 4. SQL문 작성
		sb.setLength(0);
		sb.append("INSERT INTO EMP ");
		// EMP_EMP_NO_SEQ.NEXTVAL : 구글링 해볼 것
		sb.append(
				"VALUES (EMP_EMP_NO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP ) ");

		// 5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getEmpPw());
			pstmt.setString(2, vo.getEmpName());
			pstmt.setString(3, vo.getEmpImage());
			pstmt.setString(4, vo.getCmpEmail());
			pstmt.setString(5, vo.getEmpEmail());
			pstmt.setString(6, vo.getCellphone());
			pstmt.setString(7, vo.getHiredate());
			pstmt.setString(8, vo.getQuitdate());
			pstmt.setString(9, vo.getBirthdate());
			pstmt.setInt(10, vo.getPosNo());
			pstmt.setInt(11, vo.getDeptNo());
			pstmt.setString(12, vo.getCreateDate());
			pstmt.setString(13, vo.getUpdateDate());

			// 6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // insertOne() end

	/////////////////////////////////// 변경 //////////////////////////////////////

	public void updateOne(EmpVO vo) {

		// 4. SQL문 작성
		sb.setLength(0);
		sb.append("UPDATE EMP ");
		sb.append(
				"SET EMP_PW = ? , EMP_NAME = ? , EMP_IMAGE = ?, CMP_EMAIL = ?, EMP_EMAIL = ?, CELLPHONE = ?, HIREDATE = CURRENT_TIMESTAMP, QUITDATE = CURRENT_TIMESTAMP, BIRTHDATE = CURRNET_TIMESTAMP, POS_NO = ?, DEPT_NO = ?, CREATE_DATE = CURRENT_TIMESTAMP, UPDATE_DATE = CURRENT_TIMESTAMP ");
		sb.append("WHERE EMP_NO = ? ");

		// 5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getEmpPw());
			pstmt.setString(2, vo.getEmpName());
			pstmt.setString(3, vo.getEmpImage());
			pstmt.setString(4, vo.getCmpEmail());
			pstmt.setString(5, vo.getEmpEmail());
			pstmt.setString(6, vo.getCellphone());
			pstmt.setString(7, vo.getHiredate());
			pstmt.setString(8, vo.getQuitdate());
			pstmt.setString(9, vo.getBirthdate());
			pstmt.setInt(10, vo.getPosNo());
			pstmt.setInt(11, vo.getDeptNo());
			pstmt.setString(12, vo.getCreateDate());
			pstmt.setString(13, vo.getUpdateDate());
			// 6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // updateOne() end

	/////////////////////////////////// 삭제 //////////////////////////////////////

	public void deleteOne(int empNo) {

		// 4. SQL문 작성
		sb.setLength(0);
		sb.append("DELETE FROM EMP ");
		sb.append("WHERE EMP_NO = ? ");

		// 5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			// 6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // deleteOne() end

	/////////////////////////////////// 자원반납 //////////////////////////////////////

	public void close() {
			try {
				if (rs != null)	rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}	// close() end
					
}
