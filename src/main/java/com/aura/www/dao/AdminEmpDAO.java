package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.vo.EmpVO;

public class AdminEmpDAO {
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
	
	// 페이지없는 조회
	public ArrayList<EmpVO> selectEmpAll(EmpVO getVo) {
		ArrayList<EmpVO> list = new ArrayList<EmpVO>(); 
		
		sb.setLength(0);
		sb.append("SELECT ");
		sb.append("E.EMP_NO, P.POS_NO, D.DEPT_NO, ");
		sb.append("POS_NAME, DEPT_NAME, EMP_NAME, EMP_PW, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, CELLPHONE, ");
//		sb.append("HIREDATE, QUITDATE, BIRTHDATE, ");
		
		// 유저들은 YYYY-MM-DD 로 보이게
		sb.append("DATE_FORMAT(HIREDATE, '%Y-%m-%d') AS HIREDATE, ");
		sb.append("DATE_FORMAT(QUITDATE, '%Y-%m-%d') AS QUITDATE, ");
		sb.append("DATE_FORMAT(BIRTHDATE, '%Y-%m-%d') AS BIRTHDATE, ");
		
		sb.append("CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM EMP E ");
		sb.append("INNER JOIN POSITION P ");
		sb.append("ON E.POS_NO = P.POS_NO ");
		sb.append("LEFT OUTER JOIN DEPT D ");
		sb.append("ON E.DEPT_NO = D.DEPT_NO ");
		sb.append("WHERE 1=1 ");
		
		// 퇴사여부
		if(getVo.getQdYN() == null || getVo.getQdYN().equals("ALL") ) {
			
		} else if(getVo.getQdYN().equals("N") ) {
			sb.append("AND QUITDATE IS NULL ");
		} else if(getVo.getQdYN().equals("Y") ) {
			sb.append("AND QUITDATE IS NOT NULL ");
		}
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
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
	
	// 검색조건x 페이징 
	public int getTotalCount(EmpVO getVo) {
		int cnt = 0;
		
		sb.setLength(0);
		sb.append("SELECT COUNT(*) cnt " );
		sb.append("FROM EMP E " );
		sb.append("INNER JOIN POSITION P ");
		sb.append("ON E.POS_NO = P.POS_NO ");
		sb.append("LEFT OUTER JOIN DEPT D ");
		sb.append("ON E.DEPT_NO = D.DEPT_NO ");
		sb.append("WHERE 1=1 ");
		
		// 퇴사여부
		if(getVo.getQdYN() == null || getVo.getQdYN().equals("ALL") ) {
			
		} else if(getVo.getQdYN().equals("N") ) {
			sb.append("AND QUITDATE IS NULL ");
		} else if(getVo.getQdYN().equals("Y") ) {
			sb.append("AND QUITDATE IS NOT NULL ");
		}
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	// 검색조건x 페이징 
	public ArrayList<EmpVO> selectEmpAllPage(EmpVO getVo, int limitNo, int offsetNo) {
		ArrayList<EmpVO> list = new ArrayList<EmpVO>(); 
		
		sb.setLength(0);
		sb.append("SELECT ");
		sb.append("E.EMP_NO, P.POS_NO, D.DEPT_NO, ");
		sb.append("POS_NAME, DEPT_NAME, EMP_NAME, EMP_PW, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, CELLPHONE, ");
//		sb.append("HIREDATE, QUITDATE, BIRTHDATE, ");
		
		// 유저들은 YYYY-MM-DD 로 보이게
		sb.append("DATE_FORMAT(HIREDATE, '%Y-%m-%d') AS HIREDATE, ");
		sb.append("DATE_FORMAT(QUITDATE, '%Y-%m-%d') AS QUITDATE, ");
		sb.append("DATE_FORMAT(BIRTHDATE, '%Y-%m-%d') AS BIRTHDATE, ");
		
		sb.append("CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM EMP E ");
		sb.append("INNER JOIN POSITION P ");
		sb.append("ON E.POS_NO = P.POS_NO ");
		sb.append("LEFT OUTER JOIN DEPT D ");
		sb.append("ON E.DEPT_NO = D.DEPT_NO ");
		sb.append("WHERE 1=1 ");
		
		// 퇴사여부
		if(getVo.getQdYN() == null || getVo.getQdYN().equals("ALL") ) {
			
		} else if(getVo.getQdYN().equals("N") ) {
			sb.append("AND QUITDATE IS NULL ");
		} else if(getVo.getQdYN().equals("Y") ) {
			sb.append("AND QUITDATE IS NOT NULL ");
		}
		sb.append(" LIMIT ? OFFSET ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			// 9
			pstmt.setInt(1, limitNo);
			// 1, 9, 17
			pstmt.setInt(2, offsetNo);
			
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
	
	// 검색 조건 있음 페이징
	public int getTotalCountSearch(EmpVO getVo) {
		int cnt = 0;
		
		sb.setLength(0);
		sb.append("SELECT COUNT(*) cnt " );
		
		sb.append("FROM EMP E " );
		
		sb.append("INNER JOIN POSITION P ");
		sb.append("ON E.POS_NO = P.POS_NO ");
		sb.append("LEFT OUTER JOIN DEPT D ");
		sb.append("ON E.DEPT_NO = D.DEPT_NO ");
		sb.append("WHERE 1=1 ");
		
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
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			// where 문
			// cnt = 0; // match idx
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
			rs.next();
			cnt = rs.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	// 검색 조건 있음 
	public ArrayList<EmpVO> selectEmpSearchPage(EmpVO getVo, int limitNo, int offsetNo) {
		ArrayList<EmpVO> list = new ArrayList<EmpVO>(); // 조회 결과값에 따라 size가 0 ~ 
		
		sb.setLength(0);
		sb.append("SELECT ");
		sb.append("E.EMP_NO, P.POS_NO, D.DEPT_NO, ");
		sb.append("POS_NAME, DEPT_NAME, EMP_NAME, EMP_PW, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, CELLPHONE, ");
//		sb.append("HIREDATE, QUITDATE, BIRTHDATE, ");
		
		// 유저들은 YYYY-MM-DD 로 보이게
		sb.append("DATE_FORMAT(HIREDATE, '%Y-%m-%d') AS HIREDATE, ");
		sb.append("DATE_FORMAT(QUITDATE, '%Y-%m-%d') AS QUITDATE, ");
		sb.append("DATE_FORMAT(BIRTHDATE, '%Y-%m-%d') AS BIRTHDATE, ");
		
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
		sb.append(" LIMIT ? OFFSET ? ");
		try {
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
			
			pstmt.setInt(++cnt, limitNo);
			pstmt.setInt(++cnt, offsetNo);
			
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
	
	public ArrayList<EmpVO> selectEmpSearch(EmpVO getVo) {
		ArrayList<EmpVO> list = new ArrayList<EmpVO>(); // 조회 결과값에 따라 size가 0 ~ 
		
		sb.setLength(0);
		sb.append("SELECT ");
		sb.append("E.EMP_NO, P.POS_NO, D.DEPT_NO, ");
		sb.append("POS_NAME, DEPT_NAME, EMP_NAME, EMP_PW, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, CELLPHONE, ");
//		sb.append("HIREDATE, QUITDATE, BIRTHDATE, ");
		
		// 유저들은 YYYY-MM-DD 로 보이게
		sb.append("DATE_FORMAT(HIREDATE, '%Y-%m-%d') AS HIREDATE, ");
		sb.append("DATE_FORMAT(QUITDATE, '%Y-%m-%d') AS QUITDATE, ");
		sb.append("DATE_FORMAT(BIRTHDATE, '%Y-%m-%d') AS BIRTHDATE, ");
		
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
	
	// 사원 상세조회
	public EmpVO selectEmpOne(int empNo) {
		EmpVO vo = null;
		
		sb.setLength(0);
		sb.append("SELECT ");
		sb.append("E.EMP_NO, P.POS_NO, D.DEPT_NO, ");
		sb.append("POS_NAME, DEPT_NAME, EMP_NAME, EMP_PW, EMP_IMAGE, CMP_EMAIL, EMP_EMAIL, CELLPHONE, ");
		
		// 유저들은 YYYY-MM-DD 로 보이게
		sb.append("DATE_FORMAT(HIREDATE, '%Y-%m-%d') AS HIREDATE, ");
		sb.append("DATE_FORMAT(QUITDATE, '%Y-%m-%d') AS QUITDATE, ");
		sb.append("DATE_FORMAT(BIRTHDATE, '%Y-%m-%d') AS BIRTHDATE, ");
		
		sb.append("CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM EMP E ");
		sb.append("INNER JOIN POSITION P ");
		sb.append("ON E.POS_NO = P.POS_NO ");
		sb.append("LEFT OUTER JOIN DEPT D ");
		sb.append("ON E.DEPT_NO = D.DEPT_NO ");
		sb.append("WHERE E.EMP_NO = ? ");
		
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
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
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return vo;
	}
	
	public String getEmpKey() {
		sb.setLength(0);
		sb.append("SELECT fn_seq_no('EMP', YEAR(NOW()) ) AS SEQ_NO ");
		
		String key = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				key = rs.getString("SEQ_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}
	
	public int insertEmp(EmpVO vo) {
		sb.setLength(0);
		sb.append("INSERT INTO EMP ");
		sb.append("(EMP_NO, EMP_PW, EMP_NAME, CMP_EMAIL, DEPT_NO, POS_NO ");
		if(vo.getHiredate() != null && !vo.getHiredate().equals("") ) sb.append(", HIREDATE ");
		sb.append(" ) ");
		sb.append("VALUES( ?, ?, ?, ?, ?, ?");
		if(vo.getHiredate() != null && !vo.getHiredate().equals("") ) sb.append(", STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s') ");
		sb.append(")");
		
		int rst = 0;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, vo.getEmpNo());
			pstmt.setString(2, vo.getEmpPw());
			pstmt.setString(3, vo.getEmpName());
			pstmt.setString(4, vo.getCmpEmail());
			pstmt.setInt(5, vo.getDeptNo());
			pstmt.setInt(6, vo.getPosNo());
			
			if(vo.getHiredate() != null && !vo.getHiredate().equals("") )
				pstmt.setString(7, vo.getHiredate()+" 09");

			rst = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}
	
	public int updateEmpOne(EmpVO vo) {
		sb.setLength(0);
		sb.append("UPDATE EMP SET ");
		sb.append("EMP_NAME = ? ");
		sb.append(", DEPT_NO = ? ");
		sb.append(", POS_NO = ? ");
		sb.append(", UPDATE_DATE  = CURRENT_TIMESTAMP() "); // 수정
		
		if( vo.getHiredate() == null ) {
			sb.append(", HIREDATE  = CURRENT_TIMESTAMP() ");
		} else if(vo.getHiredate() != null && !vo.getHiredate().equals("")) {
			sb.append(", HIREDATE  = STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s') ");
		}
		
		if(vo.getQuitdate() == null ) {
			sb.append(", QUITDATE = NULL ");
		} else if(vo.getQuitdate() != null && !vo.getQuitdate().equals("") ) {
			sb.append(", QUITDATE = STR_TO_DATE(?, '%Y-%m-%d %H:%i:%s') ");
		}
		
		if(vo.getEmpImage() == null ) { 
			sb.append(", EMP_IMAGE = NULL ");
		} else if(vo.getEmpImage() != null && !vo.getEmpImage().equals("") ) {
			sb.append(", EMP_IMAGE = ? ");
		}
		
		sb.append("WHERE EMP_NO = ? " );
		
		int rst = 0;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			System.out.println("sb = " + sb.toString());
			
			int cnt = 3;
			
			pstmt.setString(1, vo.getEmpName());
			pstmt.setInt(2, vo.getDeptNo());
			pstmt.setInt(3, vo.getPosNo());
			
			if(vo.getHiredate() != null && !vo.getHiredate().equals("") )
				pstmt.setString(++cnt, vo.getHiredate()+" 09");
			if(vo.getQuitdate() != null && !vo.getQuitdate().equals("") )
				pstmt.setString(++cnt, vo.getQuitdate()+" 09");
			
			if(vo.getEmpImage() != null && !vo.getEmpImage().equals("") )
				pstmt.setString(++cnt, vo.getEmpImage());
			
			pstmt.setInt(++cnt, vo.getEmpNo());
			
			rst = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}

	public int resetEmpPwOne(int empNo) {
		int result = 0;
		
		sb.setLength(0);
		sb.append("UPDATE EMP " );
		sb.append("SET EMP_PW = ? " );
		sb.append(", UPDATE_DATE  = CURRENT_TIMESTAMP() "); // 수정
		
		sb.append("WHERE EMP_NO = ? " );
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			pstmt.setInt(2, empNo);
			
			result = pstmt.executeUpdate();
			System.out.println("disableEmpOne result : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int disableEmpOne(int empNo) {
		int result = 0;
		
		sb.setLength(0);
		sb.append("UPDATE EMP " );
		sb.append("SET QUITDATE = CURRENT_TIMESTAMP() " );
		sb.append(", UPDATE_DATE  = CURRENT_TIMESTAMP() "); // 수정
		sb.append("WHERE EMP_NO = ? " );
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			
			result = pstmt.executeUpdate();
			System.out.println("disableEmpOne result : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteEmpOne(int empNo) {
		int result = 0;
		sb.setLength(0);
		sb.append("DELETE FROM EMP " );
		sb.append(" WHERE EMP_NO = ? " );
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			
			result = pstmt.executeUpdate();
			System.out.println("deleteOne result : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
