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
	String url = "jdbc:mysql://localhost:3306/aura"; 
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
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패 ");
			e.printStackTrace();
		}

	} // constructor end

	// 전체 조회
	public ArrayList<DeptBoardVO> selectAll() {
		ArrayList<DeptBoardVO> list = new ArrayList<DeptBoardVO>();
		sb.setLength(0);
		sb.append("SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, DEPTB_STATUS, DEPTB_PBLC, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM DEPTBOARD ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int deptBNo = rs.getInt("DEPTB_NO");
				String deptBTitle = rs.getString("DEPTB_TITLE");
				String deptBContent = rs.getString("DEPTB_CONTENT");
				int deptBView = rs.getInt("DEPTB_VIEW");
				int deptBNotice = rs.getInt("DEPTB_NOTICE");
				int deptBStatus = rs.getInt("DEPTB_STATUS");
				int deptBPblc = rs.getInt("DEPTB_PBLC");
				int deptBCrtr = rs.getInt("DEPTB_CRTR");
				String createDate = rs.getString("CREATE_DATE");
				String updateDate = rs.getString("UPDATE_DATE");

				DeptBoardVO vo = new DeptBoardVO();

				vo.setDeptBNo(deptBNo);
				vo.setDeptBTitle(deptBTitle);
				vo.setDeptBContent(deptBContent);
				vo.setDeptBView(deptBView);
				vo.setDeptBNotice(deptBNotice);
				vo.setDeptBStatus(deptBStatus);
				vo.setDeptBPblc(deptBPblc);
				vo.setDeptBCrtr(deptBCrtr);
				vo.setCreateDate(createDate);
				vo.setUpdateDate(updateDate);

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 검색해서 게시글 찾기
	// 제목, 내용, 작성자로 검색가능
	public ArrayList<DeptBoardVO> searchFreeBoard(DeptBoardVO vo) {
		ArrayList<DeptBoardVO> list = new ArrayList<DeptBoardVO>();
		sb.setLength(0);
		sb.append(
				"SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, DEPTB_STATUS, DEPTB_PBLC, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM DEPTBOARD ");
		sb.append("WHERE 1=1 ");
		if (vo.getDeptBTitle().equals("") && vo.getDeptBTitle() != null)
			sb.append("AND DEPTB_TITLE LIKE ? ");
		if (vo.getDeptBContent().equals("") && vo.getDeptBContent() != null)
			sb.append("AND DEPTB_CONTENT LIKE ? ");
		if (vo.getDeptBCrtr() != 0)
			sb.append("AND DEPTB_CRTR = ? ");
		sb.append("ORDER BY CREATE_DATE DESC ");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			int cnt = 0;

			if (vo.getDeptBTitle().equals("") && vo.getDeptBTitle() != null) {
				pstmt.setString(++cnt, "%" + vo.getDeptBTitle() + "%");
			}
			if (vo.getDeptBContent().equals("") && vo.getDeptBContent() != null) {
				pstmt.setString(++cnt, "%" + vo.getDeptBContent() + "%");
			}
			if (vo.getDeptBCrtr() != 0) {
				pstmt.setInt(++cnt, vo.getDeptBCrtr());
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int deptBNo = rs.getInt("DEPTB_NO");
				String deptBTitle = rs.getString("DEPTB_TITLE");
				String deptBContent = rs.getString("DEPTB_CONTENT");
				int deptBView = rs.getInt("DEPTB_VIEW");
				int deptBNotice = rs.getInt("DEPTB_NOTICE");
				int deptBStatus = rs.getInt("DEPTB_STATUS");
				int deptBPblc = rs.getInt("DEPTB_PBLC");
				int deptNo = rs.getInt("DEPT_NO");				
				int deptBCrtr = rs.getInt("DEPTB_CRTR");
				String createDate = rs.getString("CREATE_DATE");
				String updateDate = rs.getString("UPDATE_DATE");

				DeptBoardVO findvo = new DeptBoardVO();

				vo.setDeptBNo(deptBNo);
				vo.setDeptBTitle(deptBTitle);
				vo.setDeptBContent(deptBContent);
				vo.setDeptBView(deptBView);
				vo.setDeptBNotice(deptBNotice);
				vo.setDeptBStatus(deptBStatus);
				vo.setDeptBPblc(deptBPblc);
				vo.setDeptNo(deptNo);
				vo.setDeptBCrtr(deptBCrtr);
				vo.setCreateDate(createDate);
				vo.setUpdateDate(updateDate);

				list.add(findvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 게시물 번호로 검색

	public com.aura.www.vo.DeptBoardVO selectOne(int deptBNo) {
		// 4. SQL문 작성
		sb.setLength(0);
		sb.append("SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, DEPTB_STATUS, DEPTB_PBLC, DEPT_NO, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM DEPTBOARD ");
		sb.append("WHERE DEPTB_NO = ? ");

		com.aura.www.vo.DeptBoardVO vo = null;
		// 5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, deptBNo);
			rs = pstmt.executeQuery();
			// 6. 실행 (SELECT ==> ResultSet 객체 )
			while (rs.next()) {
				String deptBTitle = rs.getString("DEPTB_TITLE");
				String deptBContent = rs.getString("DEPTB_CONTENT");
				int deptBView = rs.getInt("DEPTB_VIEW");
				int deptBNotice = rs.getInt("DEPTB_NOTICE");
				int deptBStatus = rs.getInt("DEPTB_STATUS");
				int deptBPblc = rs.getInt("DEPTB_PBLC");
				int deptNo = rs.getInt("DEPT_NO");
				int deptBCrtr = rs.getInt("DEPTB_CRTR");
				String createDate = rs.getString("CREATE_DATE");
				String updateDate = rs.getString("UPDATE_DATE");

				vo = new DeptBoardVO();

				vo.setDeptBNo(deptBNo);
				vo.setDeptBTitle(deptBTitle);
				vo.setDeptBContent(deptBContent);
				vo.setDeptBView(deptBView);
				vo.setDeptBNotice(deptBNotice);
				vo.setDeptBStatus(deptBStatus);
				vo.setDeptBPblc(deptBPblc);
				vo.setDeptNo(deptNo);
				vo.setDeptBCrtr(deptBCrtr);
				vo.setCreateDate(createDate);
				vo.setUpdateDate(updateDate);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	} // selectOne end

	// 총 게시물 수 
	public int getTotalCount() {
		sb.setLength(0);
		sb.append("SELECT COUNT(*) CNT FROM DEPTBOARD ");
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("CNT");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 게시물 작성

	public void insertOne(com.aura.www.vo.DeptBoardVO vo) {
		sb.setLength(0);
		sb.append("INSERT INTO DEPTBOARD ");
		sb.append("VALUES (3,?,?,0,?,?,?,?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) ");


		try {
			System.out.println("sb = " + sb.toString());
	        pstmt = conn.prepareStatement(sb.toString());
	        pstmt.setString(1, vo.getDeptBTitle());
	        pstmt.setString(2, vo.getDeptBContent());
	        pstmt.setInt(3, vo.getDeptBNotice());
	        pstmt.setInt(4, vo.getDeptBStatus());
	        pstmt.setInt(5, vo.getDeptBPblc());
	        pstmt.setInt(6, vo.getDeptNo());	        
	        pstmt.setInt(7, vo.getDeptBCrtr());

	        pstmt.executeUpdate();

	    } catch (SQLException e) {
	        System.err.println("SQL 오류: " + e.getMessage());
	        e.printStackTrace();
	    }
	} // insertOne() end

	// 게시글 수정
	
	public void updateOne(com.aura.www.vo.DeptBoardVO vo) {
		System.out.println(vo.toString());

		sb.setLength(0);
		sb.append("UPDATE DEPTBOARD ");
		sb.append("SET DEPTB_TITLE = ?, DEPTB_CONTENT = ?, DEPTB_NOTICE = ?, DEPTB_STATUS = ?, DEPTB_PBLC = ?, UPDATE_DATE = CURRENT_TIMESTAMP  ");
		sb.append("WHERE DEPTB_NO = ? "); 

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getDeptBTitle());
			pstmt.setString(2, vo.getDeptBContent());
			pstmt.setInt(3, vo.getDeptBNotice());
			pstmt.setInt(4, vo.getDeptBStatus());
			pstmt.setInt(5, vo.getDeptBPblc());
			pstmt.setInt(6, vo.getDeptBNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // updateOne() end


	// 조회수 증가 
	
	public void raiseViews(int DEPTB_NO) {

		sb.setLength(0);
		sb.append("UPDATE DEPTBOARD "); // BOARD에서 업데이트를 하겠다
		sb.append("SET DEPTB_VIEW = DEPTB_VIEW+1 "); // 기존 HITS(조회수)에서 +1을 하겠다
		sb.append("WHERE DEPTB_NO = ? "); // BNO=1이라는 게시글 번호 1번에서

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, DEPTB_NO);

			pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // 조회수 end


	// 게시글 삭제 
	public void deleteOne(int deptNo) {

		sb.append("DELETE FROM DEPTBOARD ");
		sb.append("WHERE DEPTB_NO = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, deptNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // 삭제 end


	
	
	// 페이징 
//	public ArrayList<com.aura.www.vo.DeptBoardVO> selectAllbyPage(int startNo, int endNo) {
//		ArrayList<com.aura.www.vo.DeptBoardVO> list = new ArrayList<com.aura.www.vo.DeptBoardVO>();
//
////4. SQL문 작성
//		sb.setLength(0);
//		sb.append("SELECT SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, DEPTB_STATUS, DEPTB_PBLC, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
//		sb.append("FROM ( SELECT ROWNUM RN , BNO, WRITER, TITLE, CONTENTS, REGDATE, HITS, IP, STATUS ");
//		sb.append("		FROM  ( SELECT BNO, WRITER, TITLE, CONTENTS, REGDATE, HITS, IP, STATUS ");
//		sb.append("	        FROM BOARD ");
//		sb.append("	        ORDER BY BNO DESC ) ");
//		sb.append("		WHERE ROWNUM <= ?) ");
//		sb.append("WHERE RN >= ? ");
//
////5. 문장 객체 생성
//		try {
//			pstmt = conn.prepareStatement(sb.toString());
//			pstmt.setInt(1, endNo);
//			pstmt.setInt(2, startNo);
//			rs = pstmt.executeQuery();
////6. 실행 (SELECT ==> ResultSet 객체 )
//			while (rs.next()) {
//				int bno = rs.getInt("bno");
//				String writer = rs.getString("writer");
//				String title = rs.getString("title");
//				String contents = rs.getString("contents");
//				String regdate = rs.getString("regdate");
//				int hits = rs.getInt("hits");
//				String ip = rs.getString("ip");
//				int status = rs.getInt("status");
//
////기본생성자를 불러서 setter로 담아도 되고
////BoardVO vo = new BoardVO(bno, writer, title, contents, regdate, hits, ip, status);
//
////위와 같은 방식이 아닌 @Build 로 객체를 만드는 방법도 있다
////build 객체인 나 자신을 불러옴
//				com.aura.www.vo.DeptBoardVO vo = com.aura.www.vo.DeptBoardVO.builder().bno(bno).writer(writer)
//						.title(title).contents(contents).regdate(regdate).hits(hits).ip(ip).status(status).build();
//
//				list.add(vo);
//			}
//		} catch (SQLException e) {
////TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////여러번 반복 이후에 return 실행
//		return list;
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
