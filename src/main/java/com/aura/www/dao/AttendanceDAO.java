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
	String url = "jdbc:mysql://localhost:3306:aura";
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
	
	public ArrayList<AttendanceVO> selectAll(){
		ArrayList<AttendanceVO> list = new ArrayList<AttendanceVO>();
		
		//	4. SQL문 작성
		sb.setLength(0);
		sb.append("SELECT ATTEN_DATE, EMP_NO, STARTWORK_TIME, ENDWORK_TIME ");
		sb.append("FROM ATTENDANCE ");
		
		//	5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			//	6. 실행 (SELECT ==> ResultSet 객체 )
			while(rs.next()) {
				String attenDate = rs.getString("ATTEN_DATE");
				int empNo = rs.getInt("EMP_NO");
				String startworkTime = rs.getString("STARTWORK_TIME");
				String endworkTime = rs.getString("ENDWORK_TIME");
				
				AttendanceVO vo = new AttendanceVO(attenDate, empNo, startworkTime, endworkTime);
				
				list.add(vo);
			}
		} catch (SQLException e) {	
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
				
				vo = new AttendanceVO(attenDate, empNo, startworkTime, endworkTime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
		
	
	//////////////////////////////////// 특정 조건을 검색 (날짜 범위 및 직원 번호 검색) ////////////////////////////////////
	
	public ArrayList<AttendanceVO> AttendanceSearch (String startDate, String endDate, Integer empNo){
		ArrayList<AttendanceVO> list = new ArrayList<AttendanceVO>();
		
		StringBuilder sb = new StringBuilder();
		
		//		4. SQL문 작성
		sb.append("SELECT ATTEN_DATE, EMP_NO, STARTWORK_TIME, ENDWORK_TIME ");
		sb.append("FROM ATTENDANCE ");
		sb.append("WHERE 1=1 ");		// 조건이 없을 경우에도 WHERE 절을 유지할 수 있도록 1=1을 추가
										// WHERE 1=1 : 처음부터 조건을 추가할 수 있는 조건 값
										//			   조건이 없더라도 쿼리가 동작할 수 있게끔 설정 (항상 '참'인 설정)
		
		// 조건 추가 (날짜 범위, 직원 번호)
			// 모든 기록을 다 가져오는 것이 아님
			// 특정 조건에 맞는 기록만 조회가 가능하게끔 설정
		
		// 특정 날짜 범위에 대한 조건 검색	
		if (empNo != null) {								// empNo()가 null이 아니라면?
			sb.append("AND EMP_NO = ? ");					// 사용자가 사원번호를 입력(?)하면, 해당하는 사원번호의 근태 기록을 조회하는 조건 추가
			// 사용자가 직원번호를 입력했을 때 조건을 수행
		}
		
		if (startDate != null && !startDate.isEmpty()) {	// startDate()가 null이 아니면서, 공백이 아니어야 함
			// 이 조건이 만족되어져 수행된다면 아래의 쿼리를 실행
			sb.append("AND ATTEN_DATE >= ? ");				// 내가 선택한 날짜(?) 이후에 나오는 근태 기록 조회
		} 
		
		if (endDate != null && !endDate.isEmpty()) {		// endDate()가 null이 아니면서, 공백이 아니라면
			// 이 조건이 만족되어져 수행된다면 아래의 쿼리를 실행
			sb.append("AND ATTEN_DATE <= ? ");				// 내가 선택한 날짜(?) 이후에 나오는 근태 기록 조회
		}
		
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			int paramIndex = 1;
			  
	        // 파라미터 설정
				// paramIndex++ 방식 : 파라미터의 순서대로 값을 세팅
			
	        if (startDate != null && !startDate.isEmpty()) {
	            pstmt.setString(paramIndex++, startDate);
	        }
	        if (endDate != null && !endDate.isEmpty()) {
	            pstmt.setString(paramIndex++, endDate);
	        }
	        if (empNo != null) {
	            pstmt.setInt(paramIndex++, empNo);
	        }
	        
	        rs = pstmt.executeQuery();
	        
	        //		6. 실행 (SELECT ==> ResultSet 객체 )
	        while(rs.next()) {
	        	String attenDate = rs.getString("ATTEN_DATE");
	        	int empNumber = rs.getInt("EMP_NO");
	        	String startworkTime = rs.getString("STARTWORK_TIME");
	        	String endworkTime = rs.getString("ENDWORK_TIME");
	        	
	        	AttendanceVO vo = new AttendanceVO(attenDate, empNumber, startworkTime, endworkTime);
	        	
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
		sb.append("ORDER BY CREATE_DATE DESC ");	// 생성된 날짜 기준으로 내림차순
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
		
		
	public void addOne(AttendanceVO vo) {
		
		//		4. SQL문 작성
		sb.setLength(0);
		sb.append("INSERT INTO ATTENDANCE (ATTEN_DATE, EMP_NO, STARTWORK_TIME, ENDWORK_TIME ) ");
		sb.append("VALUES (CURRENT_TIMESTAMP, ?, ?, ? ) ");
		
		//		5. 문장 객체 생성
	    // Timestamp.valueOf를 사용해서 String을 데이터베이스에서 사용하는 TIMESTAMP 형식으로 변환
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, vo.getEmpNo());
	        pstmt.setTimestamp(2, Timestamp.valueOf(vo.getStartworkTime())); // String -> Timestamp 변환
	        pstmt.setTimestamp(3, Timestamp.valueOf(vo.getEndworkTime()));  // String -> Timestamp 변환

			//		6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	// addOne() end
	
	
	//////////////////////////////////// 수정(변경) ////////////////////////////////////
	
	public void updateOne(AttendanceVO vo) {
		
		//		4. SQL문 작성
		sb.setLength(0);
	    sb.append("UPDATE ATTENDANCE ");
	    sb.append("SET ATTEN_DATE = ?, STARTWORK_TIME = ?, ENDWORK_TIME = ? ");
	    sb.append("WHERE EMP_NO = ?");

	    // 		5. 문장 객체 생성
	    // Timestamp.valueOf를 사용해서 String을 데이터베이스에서 사용하는 TIMESTAMP 형식으로 변환
	    try {
	        pstmt = conn.prepareStatement(sb.toString());
	        pstmt.setTimestamp(1, Timestamp.valueOf(vo.getAttenDate())); // String -> Timestamp 변환
	        pstmt.setTimestamp(2, Timestamp.valueOf(vo.getStartworkTime())); // String -> Timestamp 변환
	        pstmt.setTimestamp(3, Timestamp.valueOf(vo.getEndworkTime())); // String -> Timestamp 변환
	        pstmt.setInt(4, vo.getEmpNo()); // WHERE 절의 EMP_NO 바인딩
	        
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
			pstmt.setInt(1, empNo);
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
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // close() end
		
}