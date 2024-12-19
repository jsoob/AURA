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
//	String url = "jdbc:mysql://192.168.90.65:3306/aura"; // 학원에서 사용시
	String url = "jdbc:mysql://localhost:3306/aura"; // mysql port -> 집에서 사용시
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
		sb.append("FROM EMP E ");
		sb.append("INNER JOIN POSITION P ");
		sb.append("ON E.POS_NO = P.POS_NO ");
		sb.append("LEFT OUTER JOIN DEPT D ");
		sb.append("ON E.DEPT_NO = D.DEPT_NO ");
		sb.append("WHERE 1=1 ");

//		AND ( (QUITDATE IS NULL) OR ( QUITDATE < current_timestamp()) ); -- 퇴사일자가 없거나, 퇴사일자가 아직 안 지난 사람
//		AND ( (QUITDATE IS NULL) OR ( QUITDATE <  STR_TO_DATE( '2024-12-16 23:26:09', '%Y,%m,%d %H:%i:%s' ) ) );
		
		// 사원관리 조회 조건
		// 부서명, 사원번호, 사원명, 입사일자(모두 LIKE, RANGE DATE), 퇴사여부
		
		// 부서명
		if(getVo.getDeptName() != null && !getVo.getDeptName().equals("") ) sb.append("AND E.DEPT_NO IN (SELECT DEPT_NO FROM DEPT WHERE DEPT_NAME LIKE ? ) ");
		// 사원번호
		if(getVo.getEmpNo() != 0 ) sb.append("AND EMP_NO LIKE ? ");
		// 사원명
		if(getVo.getEmpName() != null && !getVo.getEmpName().equals("") ) sb.append("AND EMP_NAME LIKE ? ");
		
		int hd_gubun = 0; // 0 없음. 1 BETWEEN. 2 st >= / 3 ed >=
		
		// 입사일자
		if( ( getVo.getHiredate_st() != null && !getVo.getHiredate_st().equals("") ) && (getVo.getHiredate_ed() != null && !getVo.getHiredate_ed().equals("")) ) {
			hd_gubun = 1;
			// 입사일이 12월17일부터 ~ 12월18일인 사원만 조회
			sb.append("AND HIREDATE BETWEEN STR_TO_DATE( ?, '%Y-%m-%d' ) AND STR_TO_DATE( ?, '%Y-%m-%d' ) ");
		} else if( getVo.getHiredate_st() != null && !getVo.getHiredate_st().equals("") ) {
			hd_gubun = 2;
			// 입사일이 12월17일부터~인 사원만 조회
			sb.append("AND HIREDATE >= STR_TO_DATE( ?, '%Y-%m-%d' ) ");
		} else if( getVo.getHiredate_ed() != null && !getVo.getHiredate_ed().equals("") ) {
			hd_gubun = 3;
			// 입사일이 ~12월18일부터인 사원만 조회
			sb.append("AND HIREDATE <= STR_TO_DATE( ?, '%Y-%m-%d' ) ");
		}
		
		// 퇴사여부
		if(getVo.getQdYN() == null || getVo.getQdYN().equals("ALL") ) {
			
		} else if(getVo.getQdYN().equals("N") ) {
			sb.append("AND QUITDATE IS NULL ");
		} else if(getVo.getQdYN().equals("Y") ) {
			sb.append("AND QUITDATE IS NOT NULL ");
		}
		// sb.append("AND ( (QUITDATE IS NULL) OR ( QUITDATE < current_timestamp()) ) ");
		
		try {
			System.out.println("sb");
			System.out.println(sb.toString());
			pstmt = conn.prepareStatement(sb.toString());
			
			// where 문
			int cnt = 0; // match idx
			
			if(getVo.getDeptName() != null && !getVo.getDeptName().equals("") )
				pstmt.setString(++cnt, "%"+getVo.getDeptName()+"%");
			
			if(getVo.getEmpNo() != 0 )
				pstmt.setString(++cnt, "%"+getVo.getEmpNo()+"%");
			
			if(getVo.getEmpName() != null && !getVo.getEmpName().equals("") )
				pstmt.setString(++cnt, "%"+getVo.getEmpName()+"%");
			
			if( hd_gubun == 1 ) {
				pstmt.setString(++cnt, getVo.getHiredate_st());
				pstmt.setString(++cnt, getVo.getHiredate_ed());
			} else if( hd_gubun == 2 ) {
				pstmt.setString(++cnt, getVo.getHiredate_st());
			} else if( hd_gubun == 3 ) {
				pstmt.setString(++cnt, getVo.getHiredate_ed());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpVO vo = null;
				int empNo = rs.getInt("EMP_NO");
				String empPw = rs.getString("EMP_PW");
				
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
				vo.setPosName(posName);
				vo.setDeptNo(deptNo);
				vo.setDeptName(deptName);
				vo.setCreateDate(createDate);
				vo.setUpdateDate(updateDate);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return list;
	}
}
