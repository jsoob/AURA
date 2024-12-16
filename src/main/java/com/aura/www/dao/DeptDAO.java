package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.vo.DeptVO;

public class DeptDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306:/aura";
	String user = "aura";
	String password = "tigertiger12$$";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	public DeptDAO() {
		// 2. 클래스 로딩
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패 ");
			e.printStackTrace();
		}

	} // constructor end
	
	// 전체 조회
		public ArrayList<DeptVO> selectAll() {

			ArrayList list = new ArrayList<>();
			// 4. sql 문장
			sb.setLength(0);
			sb.append("SELECT * ");
			sb.append("FROM DEPT ");
			// 5. 문장 객체
			try {
				pstmt = conn.prepareStatement(sb.toString());
				// 6. 실행 (select ==> ResultSet)
				rs = pstmt.executeQuery();

				while (rs.next()) {
					int deptno = rs.getInt("deptno");
					String dname = rs.getString("dname");
					String loc = rs.getString("loc");
					DeptVO vo = new DeptVO(deptno, dname, loc);

					list.add(vo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 7. 레코드별 로직처리
			return list;
		}

		/*
		 * // 1건 조회 public DeptVO selectOne(int deptno) { // 4. sql 문장 sb.setLength(0);
		 * sb.append("SELECT DEPTNO, DNAME, LOC "); sb.append("FROM DEPT ");
		 * sb.append("WHERE deptno =? ");
		 * 
		 * DeptVO vo = null;
		 * 
		 * // 5. 문장 객체 try { pstmt = conn.prepareStatement(sb.toString());
		 * pstmt.setInt(1, deptno); rs = pstmt.executeQuery(); while (rs.next()) {
		 * String dname = rs.getString("dname"); String loc = rs.getString("loc"); vo =
		 * new DeptVO(); vo.setDeptno(deptno); vo.setDname(dname); vo.setLoc(loc);
		 * 
		 * } } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } // 6. 실행 (select ==> ResultSet)
		 * 
		 * // 7. 레코드별 로직처리
		 * 
		 * return vo; }
		 * 
		 * // 추가 public void insertOne(DeptVO vo) { // 시퀀스명 : DEPT_DEPTNO
		 * 
		 * // 4. sql 문장 sb.setLength(0);
		 * sb.append("INSERT INTO DEPT (DEPTNO, DNAME, LOC) ");
		 * sb.append("VALUES (DEPT_DEPTNO.NEXTVAL, ? , ?)  "); try { // 5. 문장 객체 pstmt =
		 * conn.prepareStatement(sb.toString()); // 6. 실행 (select ===> ResultSet 객체)
		 * pstmt.setString(1, vo.getDname()); pstmt.setString(2, vo.getLoc());
		 * pstmt.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); }
		 * 
		 * }
		 * 
		 * // 변경 public void updateOne(DeptVO vo) { // 4. sql 문장 sb.setLength(0);
		 * sb.append("UPDATE DEPT "); sb.append("SET DNAME = ? , LOC = ? ");
		 * sb.append("WHERE DEPTNO = ? "); try { // 5. 문장 객체 pstmt =
		 * conn.prepareStatement(sb.toString()); pstmt.setString(1, vo.getDname());
		 * pstmt.setString(2, vo.getLoc()); pstmt.setInt(3, vo.getDeptno());
		 * 
		 * // 6. 실행 (select ===> ResultSet 객체) pstmt.executeUpdate();
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 * 
		 * // 삭제 public void deleteOne(int deptno) { // 4. sql 문장 sb.setLength(0);
		 * sb.append("DELETE FROM DEPT "); sb.append("WHERE DEPTNO = ? ");
		 * 
		 * // 5. 문장 객체 생성 try { pstmt = conn.prepareStatement(sb.toString());
		 * pstmt.setInt(1, deptno); // 6. 실행 (select ===> ResultSet 객체)
		 * pstmt.executeUpdate(); } catch (SQLException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 * 
		 * }
		 */
		public void close() {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	
}
