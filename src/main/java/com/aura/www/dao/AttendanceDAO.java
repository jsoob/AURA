package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.aura.www.vo.AttendanceVO;

public class AttendanceDAO {

//	1. 변수선언

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/aura";
	String user = "aura";
	String password = "tigertiger12$$";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public AttendanceDAO() {
		//	2. 드라이버 로딩
		try {
			Class.forName(driver);
			//	3. 연결 ( Connection )
				conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
				e.printStackTrace();
		} catch (SQLException e) {
				System.out.println("DB 연결 실패");
				e.printStackTrace();
		}
	}	// constructor end
	
	
	///////////////////////////////////////////// 전체조회 /////////////////////////////////////////////
	
	public ArrayList<AttendanceVO> selectAll(AttendanceVO vo){
		ArrayList<AttendanceVO> list = new ArrayList<AttendanceVO>();
		
		//	4. SQL문 작성
		sb.setLength(0);
		
		// 이렇게 서브쿼리로 작성하는 방법도 있고
//		sb.append("SELECT (SELECT DEPT_NAME FROM DEPT D WHERE E.DEPT_NO=D.DEPT_NO), (SELECT POS_NAME FROM POSITION P WHERE E.POS_NO=P.POS_NO), A.*, E.* ");
//		sb.append("FROM ATTENDANCE A ");
//		sb.append("INNER JOIN EMP E ");
//		sb.append("ON A.EMP_NO = E.EMP_NO ");
//		sb.append("WHERE E.EMP_NO = ? ");
		
		// 이렇게 JOIN해서 작성하는 방법도 있음 
		sb.append("SELECT ");
		sb.append("E.EMP_NO, P.POS_NO, D.DEPT_NO, ATTEN_DATE, STARTWORK_TIME, ENDWORK_TIME, ");
		sb.append("POS_NAME, DEPT_NAME, EMP_NAME ");
		sb.append("FROM ATTENDANCE A ");
		sb.append("INNER JOIN EMP E ON A.EMP_NO = E.EMP_NO ");
		sb.append("LEFT OUTER JOIN DEPT D ON E.DEPT_NO = D.DEPT_NO ");
		sb.append("LEFT OUTER JOIN POSITION P ON E.POS_NO = P.POS_NO ");
		sb.append("WHERE 1=1 ");	
		
		// sb.append("WHERE E.EMP_NO = ? ");
		
		// 관리자와 일반 사용자 구분이 필요
		if (vo.getEmpNo() == 2024000) {				// 관리자 계정(2024000)일 경우
						// 조건 없음 (모든 데이터를 조회)
													// 조건이 없을 경우에도 WHERE 절을 유지할 수 있도록 1=1을 추가
													// WHERE 1=1 : 처음부터 조건을 추가할 수 있는 조건 값
													//			   조건이 없더라도 쿼리가 동작할 수 있게끔 설정 (항상 '참'인 설정)		
													// 일반 사용자일 경우 (2024001부터 시작되는 모든 사원번호)
													// 본인 데이터만 조회
			// 만약, 사원번호 or 사원명 or 등록일자를 입력해서 조회버튼을 클릭한다면?
		if (vo.getSearchEmpNo() != null && !vo.getSearchEmpNo().isEmpty()) {		// 사원번호가 입력되었을 경우
				sb.append("AND E.EMP_NO LIKE ? ");
				System.out.println("empNo : " + vo.getSearchEmpNo());
			}
		if (vo.getEmpName() != null && !vo.getEmpName().isEmpty()) {				// 사원명이 입력되었을 경우
				sb.append("AND E.EMP_NAME LIKE ? ");
			}
		if (vo.getAttenDate() != null && !vo.getAttenDate().isEmpty()) {			// 날짜가 입력되었을 경우
				sb.append("AND DATE_FORMAT(A.ATTEN_DATE, '%Y-%m-%d') = ? ");
				System.out.println("attenDate 가 제대로 출력? : " + vo.getAttenDate());
			}
		} else {
			sb.append("AND E.EMP_NO = ? ");
		}
		
		// System.out.println("실행될 SQL 쿼리: " + sb.toString()); // 제대로 실행되고 있음
		
		//	5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			System.out.println(sb.toString());
			
			
			// 사원번호 바인딩 (사원번호가 입력되었을 경우)
			if (vo.getEmpNo() != 2024000) {
				pstmt.setInt(1, vo.getEmpNo());
			}	

			// 관리자가 조건 검색하는 부분
			int paramIndex = 1;			// 바인딩할 파라미터 인덱스 초기화

			if(vo.getSearchEmpNo() != null && !vo.getSearchEmpNo().isEmpty()) {			// 관리자 계정이 아닌 경우만 바인딩(고정) : 본인 데이터만 조회
				// System.out.println("searchEmpNo 잘 나옴 : " + vo.getSearchEmpNo());
				pstmt.setString(paramIndex++, "%" + vo.getSearchEmpNo() + "%");
			}
	
			
			// 사원명 바인딩 (사원명이 입력되었을 경우)
			if (vo.getEmpName() != null && !vo.getEmpName().isEmpty()) {
				pstmt.setString(paramIndex++, "%" + vo.getEmpName() + "%");		// LIKE 연산자 사용 (LIKE %를 통해서 값 가져오기)
			}
			
			// 날짜 바인딩 (등록일자에 날짜가 입력되었을 경우)
			if (vo.getAttenDate() != null && !vo.getAttenDate().isEmpty()) {
				pstmt.setString(paramIndex++, vo.getAttenDate());
			}
			
			
			rs = pstmt.executeQuery();
			//	6. 실행 (SELECT ==> ResultSet 객체 )
			while(rs.next()) {
				
				 // 디버깅을 위해 각 데이터를 출력 (정상적으로 불러와지고 있음을 확인)
//			    System.out.println("ATTEN_DATE: " + rs.getString("ATTEN_DATE"));
//			    System.out.println("EMP_NO: " + rs.getInt("EMP_NO"));  
			    
    		    
				String attenDate = rs.getString("ATTEN_DATE");
				int empNo = rs.getInt("EMP_NO");
				String startworkTime = rs.getString("STARTWORK_TIME");
				String endworkTime = rs.getString("ENDWORK_TIME");
				
				String empName = rs.getString("EMP_NAME");
				
				int posNo = rs.getInt("POS_NO");
				int deptNo = rs.getInt("DEPT_NO");
				String posName = rs.getString("POS_NAME");
				String deptName = rs.getString("DEPT_NAME");
				
				// 반복문 안에서 매번 새로운 객체 생성 후 하나씩 할당
					// 자바에서는 ArrayList는 객체의 참조를 저장
					// vo 객체를 반복문 안에서 계속 덮어쓰게 되면, list의 모든 요소가 같은 객체 (즉, 마지막 값)로 덮어씌워짐
				vo = new AttendanceVO();
				vo.setAttenDate(attenDate);
				vo.setStartworkTime(startworkTime);
				vo.setEndworkTime(endworkTime);
				vo.setEmpNo(empNo);
				vo.setEmpName(empName);
				vo.setPosNo(posNo);
				vo.setPosName(posName);
				vo.setDeptNo(deptNo);
				vo.setDeptName(deptName);
				
				list.add(vo);	// 생성된 객체(vo)를 리스트에 추가
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 발생 : " + e.getMessage());	// 오류 메시지 출력
			e.printStackTrace();
		}		
	return list;
	}	// selectAll() end
	
	///////////////////////////////////////////// 1건 조회 /////////////////////////////////////////////
		
	public AttendanceVO selectOne(int empNo) {
	
		//		4. SQL문 작성
		sb.setLength(0);
		sb.append("SELECT ATTEN_DATE, EMP_NO, STARTWORK_TIME, ENDWORK_TIME ");
		sb.append("FROM ATTENDANCE ");
		sb.append("WHERE EMP_NO = ? ");
		
		AttendanceVO vo = null;
		
		//		5. 문장 객체 생성
		try {
		pstmt = conn.prepareStatement(sb.toString());
		pstmt.setInt(1, empNo);
		rs = pstmt.executeQuery();
		//		6. 실행 (SELECT ==> ResultSet 객체 )
		while(rs.next()) {
		String attenDate = rs.getString("ATTEN_DATE");
		String startworkTime = rs.getString("STARTWORK_TIME");
		String endworkTime = rs.getString("ENDWORK_TIME");
		
		vo = new AttendanceVO(attenDate, empNo, startworkTime, endworkTime, null, null);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return vo;
		}
	
	//////////////////////////////////// 특정 조건을 검색 (사원번호, 사원명, 등록일자 (날짜) 검색) : 필요 없음 안해도 됨 ////////////////////////////////////
	
	public ArrayList<AttendanceVO> AttendanceSearch (int empNo, String empName, String attenDate){
		ArrayList<AttendanceVO> list = new ArrayList<AttendanceVO>();
		
		StringBuilder sb = new StringBuilder();
		
		//		4. SQL문 작성
		sb.append("SELECT");
		sb.append("E.EMP_NO, P.POS_NO, D.DEPT_NO, ATTEN_DATE, STARTWORK_TIME, ENDWORK_TIME, ");
		sb.append("POS_NAME, DEPT_NAME, EMP_NAME ");
		sb.append("FROM ATTENDANCE A ");
		sb.append("INNER JOIN EMP E ON A.EMP_NO = E.EMP_NO ");
		sb.append("LEFT OUTER JOIN DEPT D ON E.DEPT_NO = D.DEPT_NO ");
		sb.append("LEFT OUTER JOIN POSITION P ON E.POS_NO = P.POS_NO ");
		sb.append("WHERE 1=1 ");		
		
		
		// 조건 추가 (사원번호, 사원명, 날짜)
			// 모든 기록을 다 가져오는 것이 아님
			// 특정 조건에 맞는 기록만 조회가 가능하게끔 설정
		
		// 특정 날짜 범위에 대한 조건 검색	
		if (empNo != 0) {								// empNo()가 null이 아니라면?
			sb.append("AND EMP_NO = ? ");					// 사용자가 사원번호를 입력(?)하면, 해당하는 사원번호의 근태 기록을 조회하는 조건 추가
			// 사용자가 직원번호를 입력했을 때 조건을 수행
		}
		
		if (empName != null && !empName.isEmpty()) {	// empName()가 null이 아니면서, 공백이 아니어야 함
			// 이 조건이 만족되어져 수행된다면 아래의 쿼리를 실행
			sb.append("AND EMP_NAME LIKE ? ");				
		} 
		
		if (attenDate != null && !attenDate.isEmpty()) {		// attenDate()가 null이 아니면서, 공백이 아니라면
			// 이 조건이 만족되어져 수행된다면 아래의 쿼리를 실행
			sb.append("AND ATTEN_DATE = ? ");				
		}
		
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			int Index = 1;
			  
			
	        if (empNo != 0) {
	            pstmt.setInt(Index++, empNo);
	        }
	        if (empName != null && !empName.isEmpty()) {
	            pstmt.setString(Index++, empName);
	        }
	        if (attenDate != null && !attenDate.isEmpty()) {
	            pstmt.setString(Index++, attenDate);
	        }
	        
	        rs = pstmt.executeQuery();
	        
	        //		6. 실행 (SELECT ==> ResultSet 객체 )
	        while(rs.next()) {
	        	// String attenDate = rs.getString("ATTEN_DATE");
	        	int empNumber = rs.getInt("EMP_NO");
	        	String startworkTime = rs.getString("STARTWORK_TIME");
	        	String endworkTime = rs.getString("ENDWORK_TIME");
	        	
	        	AttendanceVO vo = new AttendanceVO(attenDate, empNumber, startworkTime, endworkTime, null, null);
	        	
	        	list.add(vo);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
			
	}	// AttendanceSearch() end
		
	
	
	//////////////////////////////////// 페이지번호 ////////////////////////////////////
	
	public List<AttendanceVO> getAttendanceByPage(int page){
		List<AttendanceVO> attendanceList = new ArrayList<AttendanceVO>();
		
		StringBuilder sb = new StringBuilder();
		
		//		4. SQL문 작성
		sb.append("SELECT ATTEN_DATE, EMP_NO, STARTWORK_TIME, ENDWORK_TIME ");
		sb.append("FROM ATTENDANCE ");
		sb.append("ORDER BY ATTEN_DATE DESC ");		// 생성된 날짜 기준으로 내림차순
		sb.append("LIMIT 10 OFFSET ? ");			// (예시) 한 페이지당 10개로 출력, 원한다면 숫자 조절 가능
		
		int offset = (page - 1) * 10;				// 0일 때 1번 게시글, 1일 때 11번 게시글이 보여짐
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, offset);
			rs = pstmt.executeQuery();
			//		6. 실행 (SELECT ==> ResultSet 객체 )
			while(rs.next()) {
				AttendanceVO attendance = new AttendanceVO();
				attendance.setAttenDate(rs.getString("ATTEN_DATE"));
				attendance.setEmpNo(rs.getInt("EMP_NO"));
				attendance.setStartworkTime(rs.getString("STARTWORK_TIME"));
				attendance.setEndworkTime(rs.getString("ENDWORK_TIME"));
				attendanceList.add(attendance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return attendanceList;
	}	// getAttendenceByPage() end
		
	// 전체 게시글 수 조회 메서드
	public int getAttendanceByPage() {

		StringBuilder sb = new StringBuilder();

		// 4. SQL문 작성
		sb.append("SELECT COUNT(*) AS total FROM ATTENDANCE ");		// 게시글을 조회하기 위해서는 전체적으로 조회할 필요가 있다
		int totalCount = 0;											// totalCount 변수 초기화 선언

		// 5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totalCount = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCount;
	} // getAttendenceByPage() end
		

	//////////////////////////////////// 추가 ////////////////////////////////////
		
		
	public void insertOne(AttendanceVO vo) {
		
		//		4. SQL문 작성
		sb.setLength(0);
		sb.append("INSERT INTO ATTENDANCE (ATTEN_DATE, EMP_NO ) ");
		sb.append("VALUES (CURRENT_TIMESTAMP, ? ) ");
		
		//		5. 문장 객체 생성
	    // Timestamp.valueOf를 사용해서 String을 데이터베이스에서 사용하는 TIMESTAMP 형식으로 변환
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, vo.getEmpNo());
//	        pstmt.setTimestamp(2, Timestamp.valueOf(vo.getStartworkTime())); // String -> Timestamp 변환
//	        pstmt.setTimestamp(3, Timestamp.valueOf(vo.getEndworkTime()));  // String -> Timestamp 변환

			//		6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	// insertOne() end
	
	//////////////////////////////////// 등록 유무 ////////////////////////////////////
	
	public int checkAttendance(AttendanceVO vo) {
		
		//		4. SQL문 작성
		sb.setLength(0);
		sb.append("SELECT COUNT(*) CNT ");
		sb.append("FROM ATTENDANCE ");
		sb.append("WHERE EMP_NO = ? ");
		sb.append("AND DATE_FORMAT(ATTEN_DATE, '%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') ");
		
		if(vo.getWorkGubun() == null || vo.getWorkGubun().equals("start") ) {
	         
	    } else if(vo.getWorkGubun().equals("end") ) {
	    	sb.append("AND ENDWORK_TIME IS NOT NULL ");
	    }
		
		int cnt = 0;
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, vo.getEmpNo());
			rs = pstmt.executeQuery();
			//		6. 실행 (SELECT ==> ResultSet 객체 )
			if(rs.next()) {
				cnt = rs.getInt("CNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	//////////////////////////////////// 출퇴근 상태 추가 ////////////////////////////////////
	
	// 출퇴근 시간 등록 메서드
    public boolean insertWork(AttendanceVO vo) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ATTENDANCE (EMP_NO, STARTWORK_TIME, ENDWORK_TIME) ");
        sb.append("VALUES (?, ?, ?) ");

        try (PreparedStatement pstmt = conn.prepareStatement(sb.toString())) {
            // 사원 번호 설정
            pstmt.setInt(1, vo.getEmpNo()); 
            // 출근 시간, 퇴근 시간은 Timestamp로 변환하여 설정
            pstmt.setTimestamp(2, Timestamp.valueOf(vo.getStartworkTime())); 
            pstmt.setTimestamp(3, Timestamp.valueOf(vo.getEndworkTime()));

            // 쿼리 실행        
            int result = pstmt.executeUpdate();
            return result > 0; // 쿼리 실행 결과가 1 이상이면 성공
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 예외 발생 시 false 반환
        }
    }



	
	//////////////////////////////////// 수정(변경) ////////////////////////////////////
	
	public void updateEndWork(AttendanceVO vo) {
		
		//		4. SQL문 작성
		sb.setLength(0);
	    sb.append("UPDATE ATTENDANCE ");
	    sb.append("SET ENDWORK_TIME = CURRENT_TIMESTAMP() ");
	    sb.append("WHERE EMP_NO = ?");
	
	    // 		5. 문장 객체 생성
	    // Timestamp.valueOf를 사용해서 String을 데이터베이스에서 사용하는 TIMESTAMP 형식으로 변환
	    try {
	        pstmt = conn.prepareStatement(sb.toString());
	        // String -> Timestamp 변환
	        // pstmt.setTimestamp(2, Timestamp.valueOf(vo.getStartworkTime()));  // 출근 시간
	        // pstmt.setTimestamp(3, Timestamp.valueOf(vo.getEndworkTime()));    // 퇴근 시간
	        pstmt.setInt(1, vo.getEmpNo());  // 사원 번호 (WHERE 조건 : WHERE 절의 EMP_NO 바인딩)
	        pstmt.executeUpdate();
	        
			//		6. 실행 (SELECT ==> ResultSet 객체 )
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	// updateOne() end

	//////////////////////////////////// 삭제 //////////////////////////////////////

	public void deleteOne(int empNo) {

		// 4. SQL문 작성
		sb.setLength(0);
		sb.append("DELETE FROM ATTENDANCE ");
		sb.append("WHERE EMP_NO = ? ");

		// 5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);			// 삭제할 사원 번호
			// 6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // deleteOne() end

	//////////////////////////////////// 자원반납 //////////////////////////////////////

	public void close() {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // close() end



}