package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.vo.DeptBoardVO;

public class DeptBoardDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306:/aura";
	String user = "aura";
	String password = "tigertiger12$$";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public DeptBoardDAO() {
		// 2. 클래스 로딩
		try {
			Class.forName(driver);
			// 3. Connection
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패 ");
			e.printStackTrace();
		}

	} // constructor end

////////////////////////////////////게시판 전체조회 //////////////////////////////////////

// 게시판의 전체 게시물 수를 가져오는 기능
	public int getTotalCount() {
		sb.setLength(0);
		sb.append("SELECT COUNT(*) CNT FROM BOARD ");
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("cnt");
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

//////////////////////////////////// 페이지번호 //////////////////////////////////////

	// 21 40
	public ArrayList<com.aura.www.vo.DeptBoardVO> selectAll(int startNo, int endNo) {
		ArrayList<com.aura.www.vo.DeptBoardVO> list = new ArrayList<com.aura.www.vo.DeptBoardVO>();

//4. SQL문 작성
		sb.setLength(0);
		sb.append("SELECT RN, BNO, WRITER, TITLE, CONTENTS, REGDATE, HITS, IP, STATUS ");
		sb.append("FROM ( SELECT ROWNUM RN , BNO, WRITER, TITLE, CONTENTS, REGDATE, HITS, IP, STATUS ");
		sb.append("		FROM  ( SELECT BNO, WRITER, TITLE, CONTENTS, REGDATE, HITS, IP, STATUS ");
		sb.append("	        FROM BOARD ");
		sb.append("	        ORDER BY BNO DESC ) ");
		sb.append("		WHERE ROWNUM <= ?) ");
		sb.append("WHERE RN >= ? ");

//5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, endNo);
			pstmt.setInt(2, startNo);
			rs = pstmt.executeQuery();
//6. 실행 (SELECT ==> ResultSet 객체 )
			while (rs.next()) {
				int bno = rs.getInt("bno");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String regdate = rs.getString("regdate");
				int hits = rs.getInt("hits");
				String ip = rs.getString("ip");
				int status = rs.getInt("status");

// 기본생성자를 불러서 setter로 담아도 되고
//BoardVO vo = new BoardVO(bno, writer, title, contents, regdate, hits, ip, status);

// 위와 같은 방식이 아닌 @Build 로 객체를 만드는 방법도 있다
// build 객체인 나 자신을 불러옴
				com.aura.www.vo.DeptBoardVO vo = com.aura.www.vo.DeptBoardVO.builder().bno(bno).writer(writer).title(title)
						.contents(contents).regdate(regdate).hits(hits).ip(ip).status(status).build();

				list.add(vo);
			}
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
// 여러번 반복 이후에 return 실행
		return list;
	} // selectAll() end

//////////////////////////////////// 1건조회 //////////////////////////////////////

	public com.aura.www.vo.DeptBoardVO selectOne(int bno) {
//4. SQL문 작성
		sb.setLength(0);
		sb.append("SELECT BNO, WRITER, TITLE, CONTENTS, REGDATE, HITS, IP, STATUS ");
		sb.append("FROM BOARD ");
		sb.append("WHERE BNO = ? ");

		com.aura.www.vo.DeptBoardVO vo = null;
//5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
//6. 실행 (SELECT ==> ResultSet 객체 )
			while (rs.next()) {
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				String regdate = rs.getString("regdate");
				int hits = rs.getInt("hits");
				String ip = rs.getString("ip");
				int status = rs.getInt("status");

				vo = new com.aura.www.vo.DeptBoardVO(bno, writer, title, contents, regdate, hits, ip, status);
			}

		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	} // selectOne end

//////////////////////////////////// 추가 //////////////////////////////////////

	public void insertOne(com.aura.www.vo.DeptBoardVO vo) {
//4. SQL문 작성
		sb.setLength(0);
		sb.append("INSERT INTO BOARD (BNO, WRITER, TITLE, CONTENTS, REGDATE, HITS, IP, STATUS )");
		sb.append("VALUES (BOARD_BNO_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, 0, ?, 1 ) ");

// 내가 했던 것
//sb.append("INSERT INTO BOARD ");
//sb.append("VALUES (BOARD_BNO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ? ) ");

//5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContents());
			pstmt.setString(4, vo.getIp());

// 내가 했던 것
//pstmt.setString(1, vo.getWriter());
//pstmt.setString(2, vo.getTitle());
//pstmt.setString(3, vo.getContents());
//pstmt.setString(4, vo.getRegdate());
//pstmt.setInt(5, vo.getHits());
//pstmt.setString(6, vo.getIp());
//pstmt.setInt(7, vo.getStatus());

//6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // insertOne() end

//////////////////////////////////// 수정(변경) //////////////////////////////////////

	public void updateOne(com.aura.www.vo.DeptBoardVO vo) {
		System.out.println(vo.toString());
//4. SQL문 작성
		sb.setLength(0);
		sb.append("UPDATE BOARD ");
		sb.append("SET writer = ? , title = ? , contents = ? , regdate = sysdate , status = ? ");
		sb.append("WHERE bno = ? ");

//5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContents());
			pstmt.setInt(4, vo.getStatus());
//pstmt.setInt(5, vo.getHits());
//pstmt.setString(6, vo.getIp());
			pstmt.setInt(5, vo.getBno());

//6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // updateOne() end

//////////////////////////////////// 조회수 증가(수정, 추가) //////////////////////////////////////

	public void raiseHits(int bno) {
// 4. SQL문 작성
		sb.setLength(0);
		sb.append("UPDATE BOARD "); // BOARD에서 업데이트를 하겠다
		sb.append("SET HITS = HITS+1 "); // 기존 HITS(조회수)에서 +1을 하겠다
		sb.append("WHERE BNO = ? "); // BNO=1이라는 게시글 번호 1번에서

// 해석 : 1번 게시글에서 기존 조회수가 있는 값에서 조회수를 하나씩 더 더하겠다

//5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, bno);
//6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // raiseHits() end

//////////////////////////////////// 삭제 //////////////////////////////////////

	public void deleteOne(int bno) {
//4. SQL문 작성
		sb.append("DELETE FROM BOARD ");
		sb.append("WHERE BNO = ? ");

//5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, bno);
//6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // deleteOne() end

//	// 부서 검색 조회
//	public ArrayList<DeptBoardVO> searchDeptList(DeptBoardVO getVo) {
//		ArrayList<DeptBoardVO> list = new ArrayList<DeptBoardVO>();
//
//		sb.setLength(0);
//		sb.append("SELECT DEPTNO, DNAME, LOC FROM DEPT ");
//		sb.append("WHERE 1=1 ");
//		if (getVo.getDname().equals("") && getVo.getDname() != null) {
//			sb.append("AND DNAME LIKE ? ");
//		} else if (getVo.getLoc().equals("") && getVo.getLoc() != null) {
//			sb.append("AND LOC LIKE ? ");
//		}
//
//		sb.append("ORDER BY DEPTNO ");
//
//		try {
//			pstmt = conn.prepareStatement(sb.toString());
//
//			while (rs.next()) {
//				int deptno = rs.getInt("deptno");
//				String dname = rs.getString("dname");
//				String loc = rs.getString("loc");
//
//				DeptBoardVO vo = new DeptBoardVO(deptno, dname, loc);
//				list.add(vo);
//			}
//		} catch (SQLException e) {
//
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return list;
//
//	}

	// 자원 반납
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

	}

}
