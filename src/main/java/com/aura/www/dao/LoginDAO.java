package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aura.www.vo.EmpVO;

public class LoginDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://192.168.90.65:3306/aura"; // 학원에서 사용시
	String url = "jdbc:mysql://localhost:3306/aura"; // mysql port -> 집에서 사용시
	String user = "aura";
	String password = "tigertiger12$$";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	public LoginDAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	} // constructer end

	public EmpVO selectLogin(int empNo, String empPw) {
		sb.setLength(0);
		sb.append("SELECT ");
		sb.append("E.EMP_NO, P.POS_NO, D.DEPT_NO, ");
		sb.append("POS_NAME, DEPT_NAME, EMP_PW, EMP_NAME, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, CELLPHONE, ");
//		sb.append("HIREDATE, QUITDATE, BIRTHDATE, ");

		// 유저들은 YYYY-MM-DD 로 보이게
		sb.append("DATE_FORMAT(HIREDATE, '%Y-%m-%d') AS HIREDATE, ");
		sb.append("DATE_FORMAT(QUITDATE, '%Y-%m-%d') AS QUITDATE, ");
		sb.append("DATE_FORMAT(BIRTHDATE, '%Y-%m-%d') AS BIRTHDATE, ");
		
		sb.append("CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM EMP E ");
		
		sb.append("LEFT OUTER JOIN POSITION P ");
		sb.append("ON E.POS_NO = P.POS_NO ");
		sb.append("LEFT OUTER JOIN DEPT D ");
		sb.append("ON E.DEPT_NO = D.DEPT_NO ");
		
		sb.append("WHERE EMP_NO = ? ");
		sb.append("AND EMP_PW = ? ");
//		sb.append("AND QUITDATE IS NULL "); 
		sb.append("AND ( QUITDATE IS NULL OR QUITDATE > current_timestamp() ) "); 
		// 퇴사처리 안된 사람만 로그인 가능 + 퇴사일자 남았으면 가능
		// 2024000
		EmpVO vo = null;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			pstmt.setString(2, empPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String empName = rs.getString("EMP_NAME");
				String empImage = rs.getString("EMP_IMAGE");
				String cmpEmail = rs.getString("CMP_EMAIL");
				String empEmail = rs.getString("EMP_EMAIL");
				String cellphone = rs.getString("CELLPHONE");
				
				String hiredate = rs.getString("HIREDATE");
				String quitdate = rs.getString("QUITDATE");
				String birthdate = rs.getString("BIRTHDATE");
				
				int posNo = rs.getInt("POS_NO");
				int deptNo = rs.getInt("DEPT_NO");
				String posName = rs.getString("POS_NAME");
				String deptName = rs.getString("DEPT_NAME");
				
				String createDate = rs.getString("CREATE_DATE");
				String updateDate = rs.getString("UPDATE_DATE");
			
				vo = new EmpVO();
				
				vo.setEmpNo(empNo);
				vo.setEmpPw(empPw);
				vo.setEmpName(empName);
				vo.setEmpImage(empImage);
				vo.setCmpEmail(cmpEmail);
				vo.setEmpEmail(empEmail);
				vo.setCellphone(cellphone);
				vo.setHiredate(hiredate);
				vo.setQuitdate(quitdate);
				vo.setBirthdate(birthdate);
				vo.setPosNo(posNo);
				vo.setDeptNo(deptNo);
				vo.setPosName(posName);
				vo.setDeptName(deptName);
				vo.setCreateDate(createDate);
				vo.setUpdateDate(updateDate);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return vo;
	}
	
	public EmpVO getEmailEmp(int empNo) {
		EmpVO vo = null;
		
		sb.setLength(0);
		sb.append("SELECT EMP_NO, EMP_NAME, EMP_EMAIL " );
		sb.append("FROM EMP " );
		sb.append("WHERE EMP_NO = ? ");
		sb.append("AND ( QUITDATE IS NULL OR QUITDATE > current_timestamp() ) ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new EmpVO();
				
				String empName = rs.getString("EMP_NAME");
				String empEmail = rs.getString("EMP_EMAIL");
				
				vo.setEmpNo(empNo);
				vo.setEmpName(empName);
				vo.setEmpEmail(empEmail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	public void changeEmpPw(EmpVO vo) {
		sb.setLength(0);
		sb.append("UPDATE EMP SET ");
		sb.append("EMP_PW = ? ");
		sb.append("WHERE EMP_NO = ? " );
		
		int rst = 0;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, vo.getEmpPw());
			pstmt.setInt(2, vo.getEmpNo());
			
			rst = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int editMyEmp(EmpVO vo) {
		sb.setLength(0);
		sb.append("UPDATE EMP SET ");
		sb.append("UPDATE_DATE  = CURRENT_TIMESTAMP() "); // 수정
		
		if( vo.getBirthdate() == null ) {
			sb.append(", BIRTHDATE  = CURRENT_TIMESTAMP() ");
		} else if(vo.getBirthdate() != null && !vo.getBirthdate().equals("")) {
			sb.append(", BIRTHDATE  = STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s') ");
		}
		
		if(vo.getCellphone() != null && !vo.getCellphone().equals("") ) sb.append(", CELLPHONE = ? ");
		if(vo.getEmpEmail() != null && !vo.getEmpEmail().equals("") ) sb.append(", EMP_EMAIL = ? ");
		
		if(vo.getEmpImage() == null ) { 
			sb.append(", EMP_IMAGE = NULL ");
		} else if(vo.getEmpImage() != null && !vo.getEmpImage().equals("") ) {
			sb.append(", EMP_IMAGE = ? ");
		}
		
		sb.append("WHERE EMP_NO = ? " );
		
		int rst = 0;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			int cnt = 0;
			
			if(vo.getBirthdate() != null && !vo.getBirthdate().equals("") )
				pstmt.setString(++cnt, vo.getBirthdate()+" 09");
			
			if(vo.getCellphone() != null && !vo.getCellphone().equals("") )
				pstmt.setString(++cnt, vo.getCellphone());
			
			if(vo.getEmpEmail() != null && !vo.getEmpEmail().equals("") )
				pstmt.setString(++cnt, vo.getEmpEmail());

			if(vo.getEmpImage() != null && !vo.getEmpImage().equals("") )
				pstmt.setString(++cnt, vo.getEmpImage());
			
			pstmt.setInt(++cnt, vo.getEmpNo());
			
			rst = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}
	
}
