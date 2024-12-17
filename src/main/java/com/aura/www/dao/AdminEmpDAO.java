package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.vo.EmpVO;

public class AdminEmpDAO {
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
	public AdminEmpDAO() {
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

	public ArrayList<EmpVO> selectEmp(EmpVO getVo) {
		ArrayList<EmpVO> list = new ArrayList<EmpVO>(); // 조회 결과값에 따라 size가 0 ~ 
		
		sb.setLength(0);
		sb.append("SELECT ");
		sb.append("E.EMP_NO, P.POS_NO, D.DEPT_NO, ");
		sb.append("POS_NAME, DEPT_NAME, EMP_NAME, EMP_PW, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, CELLPHONE, ");
		sb.append("HIREDATE, QUITDATE, BIRTHDATE, ");
		sb.append("CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM EMP ");
		sb.append("WHERE 1=1 ");

//		AND ( (QUITDATE IS NULL) OR ( QUITDATE < current_timestamp()) ); -- 퇴사일자가 없거나, 퇴사일자가 아직 안 지난 사람
//		AND ( (QUITDATE IS NULL) OR ( QUITDATE <  STR_TO_DATE( '2024-12-16 23:26:09', '%Y,%m,%d %H:%i:%s' ) ) );
		
		// 사원관리 조회 조건
		// 부서명, 사원번호, 사원명, 입사일자, 퇴사일자 (모두 LIKE, RANGE DATE)
		
		// 부서명
		if(getVo.getDeptNo() != 0 ) sb.append("AND DEPT_NO IN (SELECT DEPT_NO FROM DEPT WHERE DEPT_NAME LIKE ? )");
		// 사원번호
		if(getVo.getEmpNo() != 0 ) sb.append("AND EMP_NO LIKE ? ");
		// 사원명
		if(getVo.getEmpName().equals("") && getVo.getEmpName() != null ) sb.append("AND EMP_NAME LIKE ? ");
		// 입사일자
		if(getVo.getHiredate().equals("") && getVo.getHiredate() != null ) {
			// 입사일이 12월17일부터 ~ 12월18일인 사원만 조회
		}
		// 퇴사일자
		if(getVo.getQuitdate().equals("") && getVo.getQuitdate() != null ) {
			sb.append("AND ( (QUITDATE IS NULL) OR ( QUITDATE < current_timestamp()) ) ");
		}
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			// where 문
			int cnt = 0; // match idx
			
//			if(getVo.getArm_name().equals("") && getVo.getArm_name() != null ) {
//				pstmt.setString(++cnt, "%"+getVo.getArm_name()+"%");
//			}
//			if(getVo.getArm_email().equals("") && getVo.getArm_email() != null ) {
//				pstmt.setString(++cnt, "%"+getVo.getArm_email()+"%");
//			}
//			if(getVo.getArm_deptno() != 0 ) {
//				pstmt.setInt(++cnt, getVo.getArm_deptno());
//			}
//			if(getVo.getArm_dept() != 0 ) {
//				pstmt.setInt(++cnt, getVo.getArm_dept());
//			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				EmpVO vo = null;
				int emp_no = rs.getInt("EMP_NO");
				String emp_pw = rs.getString("EMP_PW");
				
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
		return list;
	}
}
