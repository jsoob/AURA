package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.vo.DeptVO;

public class AdminDeptDAO {

	// 1. 환경변수

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://192.168.90.65:3306/aura"; // 학원에서 사용시
//	String url = "jdbc:mysql://localhost:3306/aura"; // mysql port -> 집에서 사용시
	String user = "aura";
	String password = "tigertiger12$$";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public AdminDeptDAO() {
		// 2. 클래스 로딩
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}
		// 3. Connection
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn : " + conn);
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();

		}
	} // 생성자

	// 부서 1 조회
	public DeptVO selectOne(int deptno) {
		// 4. sql문장
		sb.setLength(0);
		// 띄어쓰기 주의바람
		sb.append("SELECT deptno, dname, loc ");
		sb.append("FROM dept ");
		sb.append("WHERE deptno = ?");

		DeptVO vo = null;

		// 5. 문장 객체
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, deptno);
			// 6. 실행 (select ==> ResultSet)
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 7. 레코드별 로직처리
				String dname = rs.getString("dname");
				
				
				vo = new DeptVO(deptno, dname);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// vo 리턴
		return vo;
	} // selectOne end

	// 전체조회
	public ArrayList<DeptVO> selectAll() {
		ArrayList<DeptVO> list = new ArrayList<DeptVO>();

//		4. SQL문 작성
		sb.setLength(0);
		sb.append("SELECT deptno, dname, loc ");
		sb.append("FROM dept ");

//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
//		6. 실행 (SELECT ==> ResultSet 객체 )
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				
				DeptVO vo = new DeptVO(deptno, dname);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	} // selectAll() end

	// 추가
	public void insertOne(DeptVO vo) {

		// 4. SQL문 작성
		sb.setLength(0);
		sb.append("INSERT INTO DEPT (DEPTNO, DNAME, LOC)");
		sb.append("VALUES (DEPT_DEPTNO.NEXTVAL, ?, ? ) ");

//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getDeptName());
//		6. 실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // insertOne end

	// 변경
	public void updateOne(DeptVO vo) {
		// 부서명, 부서위치를 수정
//		4. SQL문 작성
		sb.setLength(0);
		sb.append("UPDATE DEPT ");
		sb.append("SET dname = ? , loc = ? ");
		sb.append("WHERE deptno = ? ");

//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			// append 나온 순서 그대로 입력할 것
			pstmt.setString(1, vo.getDeptName());
			pstmt.setInt(2, vo.getDeptNo());

//		6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	} // updateOne end

	// 삭제
	public void deleteOne(int deptno) {
//		4. SQL문 작성
		sb.setLength(0);
		sb.append("DELETE FROM dept ");
		sb.append("WHERE deptno = ? ");
//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, deptno);
//		6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // deleteOne end

	
	
	// 부서 검색 조회
	// 여기 그냥 수빈님 거 갖다쓴 거라 확실하지 않음
	public ArrayList<DeptVO> searchDeptList(DeptVO getVo) {
		ArrayList<DeptVO> list = new ArrayList<DeptVO>();

		sb.setLength(0);
		sb.append("SELECT DEPTNO, DNAME, LOC FROM DEPT ");
		sb.append("WHERE 1=1 ");
		if (getVo.getDeptName().equals("") && getVo.getDeptName() != null) {
			sb.append("AND DNAME LIKE ? ");
		} 

		sb.append("ORDER BY DEPTNO ");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");

				DeptVO vo = new DeptVO(deptno, dname);
				list.add(vo);
			}
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	// 8. 자원반납
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
	} // close end

}
