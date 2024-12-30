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

	// 부서별 조회
	public ArrayList<DeptBoardVO> selectByDeptNo(int deptNo) {
		ArrayList<DeptBoardVO> list = new ArrayList<>();
		sb.setLength(0);
		sb.append("SELECT * FROM DEPTBOARD WHERE DEPT_NO = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, deptNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DeptBoardVO vo = new DeptBoardVO();
				vo.setDeptBNo(rs.getInt("DEPTB_NO"));
				vo.setDeptBTitle(rs.getString("DEPTB_TITLE"));
				vo.setDeptBContent(rs.getString("DEPTB_CONTENT"));
				vo.setDeptBView(rs.getInt("DEPTB_VIEW"));
				vo.setDeptBNotice(rs.getInt("DEPTB_NOTICE"));
				vo.setDeptBStatus(rs.getInt("DEPTB_STATUS"));
				vo.setDeptBPblc(rs.getInt("DEPTB_PBLC"));
				vo.setDeptNo(rs.getInt("DEPT_NO"));
				vo.setDeptBCrtr(rs.getInt("DEPTB_CRTR"));
				vo.setCreateDate(rs.getString("CREATE_DATE"));
				vo.setUpdateDate(rs.getString("UPDATE_DATE"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 게시물 번호로 검색

	public DeptBoardVO selectOne(int deptBNo) {
		// 4. SQL문 작성
		sb.setLength(0);
		sb.append(
				"SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, DEPTB_STATUS, DEPTB_PBLC, DEPT_NO, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM DEPTBOARD ");
		sb.append("WHERE DEPTB_NO = ? ");

		DeptBoardVO vo = null;
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

	public void insertOne(DeptBoardVO vo) {
		sb.setLength(0);
		sb.append("INSERT INTO DEPTBOARD ");
		sb.append("(DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, ");
		sb.append("DEPTB_STATUS, DEPTB_PBLC, DEPT_NO, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE) ");
		sb.append("VALUES (NULL, ?, ?, 0, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getDeptBTitle());
			pstmt.setString(2, vo.getDeptBContent());
			pstmt.setInt(3, vo.getDeptBNotice());
			pstmt.setInt(4, vo.getDeptBStatus());
			pstmt.setInt(5, vo.getDeptBPblc());
			pstmt.setInt(6, vo.getDeptNo());
			pstmt.setInt(7, vo.getDeptBCrtr());
			pstmt.executeUpdate();
			System.out.println("SQL 실행 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL 오류 발생: " + e.getMessage());
		}
	}

	// 게시글 수정

	public void updateOne(com.aura.www.vo.DeptBoardVO vo) {
		System.out.println(vo.toString());

		sb.setLength(0);
		sb.append("UPDATE DEPTBOARD ");
		sb.append(
				"SET DEPTB_TITLE = ?, DEPTB_CONTENT = ?, DEPTB_NOTICE = ?, DEPTB_STATUS = ?, DEPTB_PBLC = ?, UPDATE_DATE = CURRENT_TIMESTAMP  ");
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

	// 검색
	public ArrayList<DeptBoardVO> searchDeptBoard(int empNo, int deptNo, String search, String searchWord, String order,
			int startIndex, int recordPerPage) {
		ArrayList<DeptBoardVO> list = new ArrayList<>();

		sb.setLength(0);

		if (startIndex == 0) {

			sb.append("SELECT t.* ");
			sb.append("FROM ( ");
			sb.append("SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, DEPTB_STATUS, ");
			sb.append(
					"   DEPTB_PBLC, DB.DEPT_NO, DEPTB_CRTR, DB.CREATE_DATE, DB.UPDATE_DATE , e.EMP_NAME, d.DEPT_NAME, p.POS_NAME ");
			sb.append("   FROM DEPTBOARD DB ");
			sb.append("     INNER JOIN EMP e on DB.DEPTB_CRTR = e.EMP_NO ");
			sb.append("   LEFT OUTER JOIN DEPT d on e.DEPT_NO = d.DEPT_NO ");
			sb.append("   LEFT OUTER JOIN POSITION p on e.POS_NO = p.POS_NO  ");
			sb.append("   WHERE DEPTB_NOTICE=1 ");

			if (empNo != 2024000) {
				sb.append("     AND DEPT_NO = ? ");
				sb.append("     AND (DEPT_STATUS = 1 OR DEPT_STATUS = 0 AND DEPT_CRTR = ? )  ");

			}

			sb.append("    ORDER BY DB.CREATE_DATE DESC LIMIT 3 ");
			sb.append(" ) t ");

			sb.append(" UNION ALL ");

		} else {
			// action 에서 recordPerPage 가 1페이지의 공지사항 때문에 9로 고정되어있는데
			// 2페이지부터는 공지를 안 보여주기에 + 3 을 해준다.
			recordPerPage += 3;
		}

		sb.append(" SELECT s.* ");
		sb.append(" FROM ( ");
		sb.append("SELECT DB.*, e.EMP_NAME, d.DEPT_NAME, p.POS_NAME ");
		sb.append("FROM DEPTBOARD DB ");
		sb.append("INNER JOIN EMP e on DB.DEPTB_CRTR = e.EMP_NO ");
		sb.append("LEFT OUTER JOIN DEPT d on e.DEPT_NO = d.DEPT_NO ");
		sb.append("LEFT OUTER JOIN POSITION p on e.POS_NO = p.POS_NO  ");
		sb.append("WHERE 1=1 ");

		// sb.append("-- AND DEPT_NO = ? ");

		if (empNo != 2024000) {
			sb.append("     AND DEPT_NO = ? ");
			sb.append("     AND (DEPT_STATUS = 1 OR DEPT_STATUS = 0 AND DEPT_CRTR = ? )  ");

		}

		if (search != null && searchWord != null && !searchWord.trim().isEmpty()) {
			if ("title".equals(search)) {
				sb.append("AND DB.DEPTB_TITLE LIKE ? ");
			} else if ("content".equals(search)) {
				sb.append("AND DB.DEPTB_CONTENT LIKE ? ");
			} else if ("writer".equals(search)) {
				sb.append("AND E.EMP_NAME LIKE ? ");
			}
		}

		if ("recent".equals(order)) {
			sb.append("ORDER BY DB.CREATE_DATE DESC ");
		} else if ("old".equals(order)) {
			sb.append("ORDER BY DB.CREATE_DATE ASC ");
		} else if ("view".equals(order)) {
			sb.append("ORDER BY DB.DEPTB_VIEW DESC ");
		}

		// sb.append("LIMIT 9 OFFSET 0 ");

		sb.append("LIMIT ? OFFSET ?");
		sb.append(") s; ");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			System.out.println("검색 쿼리 : " + sb.toString());
			int index = 1;

			if (empNo != 2024000) {
				// sb.append(" AND DEPT_NO = ? ");
				// sb.append(" AND (DEPT_STATUS = 1 OR DEPT_STATUS = 0 AND DEPT_CRTR = ? ) ");
				pstmt.setInt(index++, deptNo);
				pstmt.setInt(index++, empNo);

				pstmt.setInt(index++, deptNo);
				pstmt.setInt(index++, empNo);

			}

			if (search != null && searchWord != null && !searchWord.trim().isEmpty()) {
				if ("title".equals(search)) {
//					sb.append("AND DB.DEPTB_TITLE LIKE ? ");
					pstmt.setString(index++, "%" + searchWord + "%");
				} else if ("content".equals(search)) {
//					sb.append("AND DB.DEPTB_CONTENT LIKE ? ");
					pstmt.setString(index++, "%" + searchWord + "%");

				} else if ("writer".equals(search)) {
//					sb.append("AND E.EMP_NAME LIKE ? ");
					pstmt.setString(index++, "%" + searchWord + "%");
				}
			}

			// if (search != null && searchWord != null && !searchWord.trim().isEmpty()) {
//				pstmt.setString(index++, "%" + searchWord.trim() + "%");
//			}

			pstmt.setInt(index++, recordPerPage);
			pstmt.setInt(index, startIndex);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeptBoardVO vo = new DeptBoardVO();
				vo.setDeptBNo(rs.getInt("DEPTB_NO"));
				vo.setDeptBTitle(rs.getString("DEPTB_TITLE"));
				vo.setDeptBCrtr(rs.getInt("DEPTB_CRTR"));
				vo.setCreateDate(rs.getString("CREATE_DATE"));
				vo.setDeptBView(rs.getInt("DEPTB_VIEW"));
				vo.setDeptBPblc(rs.getInt("DEPTB_PBLC"));
				vo.setDeptBNotice(rs.getInt("DEPTB_NOTICE"));
				vo.setEmpName(rs.getString("EMP_NAME")); // 작성자 이름 추가
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<DeptBoardVO> old_searchDeptBoard(String search, String searchWord, String order, int startIndex,
			int recordPerPage) {
		ArrayList<DeptBoardVO> list = new ArrayList<>();
		sb.setLength(0);
		sb.append("SELECT DB.*, E.EMP_NAME ");
		sb.append("FROM DEPTBOARD DB ");
		sb.append("LEFT JOIN EMP E ON DB.DEPTB_CRTR = E.EMP_NO ");
		sb.append("WHERE 1=1 ");

		if (search != null && searchWord != null && !searchWord.trim().isEmpty()) {
			if ("title".equals(search)) {
				sb.append("AND DB.DEPTB_TITLE LIKE ? ");
			} else if ("content".equals(search)) {
				sb.append("AND DB.DEPTB_CONTENT LIKE ? ");
			} else if ("writer".equals(search)) {
				sb.append("AND E.EMP_NAME LIKE ? ");
			}
		}

		if ("recent".equals(order)) {
			sb.append("ORDER BY DB.CREATE_DATE DESC ");
		} else if ("old".equals(order)) {
			sb.append("ORDER BY DB.CREATE_DATE ASC ");
		} else if ("view".equals(order)) {
			sb.append("ORDER BY DB.DEPTB_VIEW DESC ");
		}

		sb.append("LIMIT ?, ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			int index = 1;

			if (search != null && searchWord != null && !searchWord.trim().isEmpty()) {
				pstmt.setString(index++, "%" + searchWord.trim() + "%");
			}
			pstmt.setInt(index++, startIndex);
			pstmt.setInt(index, recordPerPage);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeptBoardVO vo = new DeptBoardVO();
				vo.setDeptBNo(rs.getInt("DEPTB_NO"));
				vo.setDeptBTitle(rs.getString("DEPTB_TITLE"));
				vo.setDeptBCrtr(rs.getInt("DEPTB_CRTR"));
				vo.setCreateDate(rs.getString("CREATE_DATE"));
				vo.setDeptBView(rs.getInt("DEPTB_VIEW"));
				vo.setDeptBPblc(rs.getInt("DEPTB_PBLC"));
				vo.setDeptBNotice(rs.getInt("DEPTB_NOTICE"));
				vo.setEmpName(rs.getString("EMP_NAME")); // 작성자 이름 추가
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 부서명 가져오기
	public String getDeptNameByDeptNo(int deptNo) {
		String deptName = null;
		sb.setLength(0);
		sb.append("SELECT DEPT_NAME FROM DEPT WHERE DEPT_NO = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, deptNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				deptName = rs.getString("DEPT_NAME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deptName;
	}

	///////// 공지사항 ///////

	public ArrayList<DeptBoardVO> selectAllWithNotice() {
		ArrayList<DeptBoardVO> list = new ArrayList<>();
		sb.setLength(0);

		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			System.out.println(sb.toString());

			while (rs.next()) {
				DeptBoardVO vo = new DeptBoardVO();
				vo.setDeptBNo(rs.getInt("DEPTB_NO"));
				vo.setDeptBTitle(rs.getString("DEPTB_TITLE"));
				vo.setDeptBContent(rs.getString("DEPTB_CONTENT"));
				vo.setDeptBView(rs.getInt("DEPTB_VIEW"));
				vo.setDeptBNotice(rs.getInt("DEPTB_NOTICE"));
				vo.setDeptBStatus(rs.getInt("DEPTB_STATUS"));
				vo.setDeptBPblc(rs.getInt("DEPTB_PBLC"));
				vo.setDeptNo(rs.getInt("DEPT_NO")); // DEPT_NO 추가
				vo.setDeptBCrtr(rs.getInt("DEPTB_CRTR"));
				vo.setCreateDate(rs.getString("CREATE_DATE"));
				vo.setUpdateDate(rs.getString("UPDATE_DATE"));
				vo.setUpdateDate(rs.getString("UPDATE_DATE"));
				vo.setUpdateDate(rs.getString("UPDATE_DATE"));

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	// 관리자 공지사항 최신 3개
	public ArrayList<DeptBoardVO> old_selectThreeRecentNotice() {
		ArrayList<DeptBoardVO> list = new ArrayList<>();
		sb.setLength(0);
		sb.append("SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, ");
		sb.append("DEPTB_STATUS, DEPTB_PBLC, DEPT_NO, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM DEPTBOARD WHERE DEPTB_NOTICE=1 ORDER BY CREATE_DATE DESC LIMIT 3 "); // 부서 번호 조건 제거

		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			System.out.println(sb.toString());

			while (rs.next()) {
				DeptBoardVO vo = new DeptBoardVO();
				vo.setDeptBNo(rs.getInt("DEPTB_NO"));
				vo.setDeptBTitle(rs.getString("DEPTB_TITLE"));
				vo.setDeptBContent(rs.getString("DEPTB_CONTENT"));
				vo.setDeptBView(rs.getInt("DEPTB_VIEW"));
				vo.setDeptBNotice(rs.getInt("DEPTB_NOTICE"));
				vo.setDeptBStatus(rs.getInt("DEPTB_STATUS"));
				vo.setDeptBPblc(rs.getInt("DEPTB_PBLC"));
				vo.setDeptNo(rs.getInt("DEPT_NO")); // DEPT_NO 추가
				vo.setDeptBCrtr(rs.getInt("DEPTB_CRTR"));
				vo.setCreateDate(rs.getString("CREATE_DATE"));
				vo.setUpdateDate(rs.getString("UPDATE_DATE"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 관리자 공지사항 최신 3개 제외 전체 글들
	public ArrayList<DeptBoardVO> old_getRestOfAllDeptBoard(int notice1, int notice2, int notice3) {
		ArrayList<DeptBoardVO> list = new ArrayList<>();
		sb.setLength(0);
		sb.append("SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, ");
		sb.append("DEPTB_STATUS, DEPTB_PBLC, DEPT_NO, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM DEPTBOARD WHERE DEPTB_NO NOT IN ( ? , ? , ? ) ORDER BY CREATE_DATE DESC "); // 부서 번호 조건 제거

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, notice1);
			pstmt.setInt(2, notice2);
			pstmt.setInt(3, notice3);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				DeptBoardVO vo = new DeptBoardVO();
				vo.setDeptBNo(rs.getInt("DEPTB_NO"));
				vo.setDeptBTitle(rs.getString("DEPTB_TITLE"));
				vo.setDeptBContent(rs.getString("DEPTB_CONTENT"));
				vo.setDeptBView(rs.getInt("DEPTB_VIEW"));
				vo.setDeptBNotice(rs.getInt("DEPTB_NOTICE"));
				vo.setDeptBStatus(rs.getInt("DEPTB_STATUS"));
				vo.setDeptBPblc(rs.getInt("DEPTB_PBLC"));
				vo.setDeptNo(rs.getInt("DEPT_NO")); // DEPT_NO 추가
				vo.setDeptBCrtr(rs.getInt("DEPTB_CRTR"));
				vo.setCreateDate(rs.getString("CREATE_DATE"));
				vo.setUpdateDate(rs.getString("UPDATE_DATE"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 부서 공지사항 최신 3개
	public ArrayList<DeptBoardVO> old_selectThreeRecentNoticeByDept(int deptno) {
		ArrayList<DeptBoardVO> list = new ArrayList<>();
		sb.setLength(0);
		sb.append("SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, ");
		sb.append("DEPTB_STATUS, DEPTB_PBLC, DEPT_NO, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM DEPTBOARD WHERE DEPTB_NOTICE=1 AND DEPT_NO=? ORDER BY CREATE_DATE DESC LIMIT 3 "); // 부서 번호 조건
																											// 제거

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, deptno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DeptBoardVO vo = new DeptBoardVO();
				vo.setDeptBNo(rs.getInt("DEPTB_NO"));
				vo.setDeptBTitle(rs.getString("DEPTB_TITLE"));
				vo.setDeptBContent(rs.getString("DEPTB_CONTENT"));
				vo.setDeptBView(rs.getInt("DEPTB_VIEW"));
				vo.setDeptBNotice(rs.getInt("DEPTB_NOTICE"));
				vo.setDeptBStatus(rs.getInt("DEPTB_STATUS"));
				vo.setDeptBPblc(rs.getInt("DEPTB_PBLC"));
				vo.setDeptNo(rs.getInt("DEPT_NO")); // DEPT_NO 추가
				vo.setDeptBCrtr(rs.getInt("DEPTB_CRTR"));
				vo.setCreateDate(rs.getString("CREATE_DATE"));
				vo.setUpdateDate(rs.getString("UPDATE_DATE"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 부서 공지사항 3개 제외 글들
	public ArrayList<DeptBoardVO> old_getRestOfDeptBoard(int deptno, int notice1, int notice2, int notice3) {
		ArrayList<DeptBoardVO> list = new ArrayList<>();
		sb.setLength(0);
		sb.append("SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, ");
		sb.append("DEPTB_STATUS, DEPTB_PBLC, DEPT_NO, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM DEPTBOARD WHERE DEPT_NO = ? AND DEPTB_NO NOT IN ( ? , ? , ? ) ORDER BY CREATE_DATE DESC "); // 부서
																													// 번호
																													// 조건
																													// 제거

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, deptno);
			pstmt.setInt(2, notice1);
			pstmt.setInt(3, notice2);
			pstmt.setInt(4, notice3);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				DeptBoardVO vo = new DeptBoardVO();
				vo.setDeptBNo(rs.getInt("DEPTB_NO"));
				vo.setDeptBTitle(rs.getString("DEPTB_TITLE"));
				vo.setDeptBContent(rs.getString("DEPTB_CONTENT"));
				vo.setDeptBView(rs.getInt("DEPTB_VIEW"));
				vo.setDeptBNotice(rs.getInt("DEPTB_NOTICE"));
				vo.setDeptBStatus(rs.getInt("DEPTB_STATUS"));
				vo.setDeptBPblc(rs.getInt("DEPTB_PBLC"));
				vo.setDeptNo(rs.getInt("DEPT_NO")); // DEPT_NO 추가
				vo.setDeptBCrtr(rs.getInt("DEPTB_CRTR"));
				vo.setCreateDate(rs.getString("CREATE_DATE"));
				vo.setUpdateDate(rs.getString("UPDATE_DATE"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	///// 페이징 및 조회 //////

	public ArrayList<DeptBoardVO> getDeptBoardWithPaging(int deptNo, int empNo, int currentPage, int recordPerPage) {
		ArrayList<DeptBoardVO> list = new ArrayList<>();
		sb.setLength(0);

		if (currentPage == 0) {

			sb.append("SELECT t.* ");
			sb.append("FROM ( ");
			sb.append("SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, DEPTB_STATUS, ");
			sb.append(
					"   DEPTB_PBLC, DB.DEPT_NO, DEPTB_CRTR, DB.CREATE_DATE, DB.UPDATE_DATE , e.EMP_NAME, d.DEPT_NAME, p.POS_NAME ");
			sb.append("   FROM DEPTBOARD DB ");
			sb.append("     INNER JOIN EMP e on DB.DEPTB_CRTR = e.EMP_NO ");
			sb.append("   LEFT OUTER JOIN DEPT d on e.DEPT_NO = d.DEPT_NO ");
			sb.append("   LEFT OUTER JOIN POSITION p on e.POS_NO = p.POS_NO  ");
			sb.append("   WHERE DEPTB_NOTICE=1 ");

			if (empNo != 2024000) {
				sb.append("     AND DEPT_NO = ? ");
				sb.append("     AND (DEPT_STATUS = 1 OR DEPT_STATUS = 0 AND DEPT_CRTR = ? )  ");

			}

			sb.append("    ORDER BY DB.CREATE_DATE DESC LIMIT 3 ");
			sb.append(" ) t ");

			sb.append(" UNION ALL ");

		} else {
			// action 에서 recordPerPage 가 1페이지의 공지사항 때문에 9로 고정되어있는데
			// 2페이지부터는 공지를 안 보여주기에 + 3 을 해준다.
			recordPerPage += 3;
		}

		sb.append(" SELECT s.* ");
		sb.append(" FROM ( ");
		sb.append("SELECT DB.*, e.EMP_NAME, d.DEPT_NAME, p.POS_NAME ");
		sb.append("FROM DEPTBOARD DB ");
		sb.append("INNER JOIN EMP e on DB.DEPTB_CRTR = e.EMP_NO ");
		sb.append("LEFT OUTER JOIN DEPT d on e.DEPT_NO = d.DEPT_NO ");
		sb.append("LEFT OUTER JOIN POSITION p on e.POS_NO = p.POS_NO  ");
		sb.append("WHERE 1=1 ");

		// sb.append("-- AND DEPT_NO = ? ");

		if (empNo != 2024000) {
			sb.append("     AND DEPT_NO = ? ");
			sb.append("     AND (DEPT_STATUS = 1 OR DEPT_STATUS = 0 AND DEPT_CRTR = ? )  ");

		}

		sb.append("ORDER BY DB.CREATE_DATE DESC ");

		// sb.append("LIMIT 9 OFFSET 0 ");

		sb.append("LIMIT ? OFFSET ?");
		sb.append(") s; ");

		// 기본 SQL 쿼리
//		sb.append("SELECT DB.*, E.EMP_NAME ");
//		sb.append("FROM DEPTBOARD DB ");
//		sb.append("LEFT JOIN EMP E ON DB.DEPTB_CRTR = E.EMP_NO ");
//		sb.append("WHERE 1=1 ");
//
//		// 관리자가 아닌 경우 조건 추가
//		if (empNo != 2024000) { // 관리자 사번이 아닌 경우
//			sb.append("AND (DB.DEPT_NO = ? or DB.DEPTB_CRTR = 2024000 ) ");
//			sb.append("AND (DB.DEPTB_PBLC = 1 "); // 공개글 조건
//			sb.append("OR DB.DEPTB_CRTR = ?) "); // 본인이 작성한 글 조건
//		}
//
//		sb.append("ORDER BY DB.CREATE_DATE DESC ");
//		sb.append("LIMIT ? OFFSET ?");_+_+_+

		try {
			System.out.println(sb.toString());

			pstmt = conn.prepareStatement(sb.toString());

			int index = 1;

			if (empNo != 2024000) {
				// sb.append(" AND DEPT_NO = ? ");
				// sb.append(" AND (DEPT_STATUS = 1 OR DEPT_STATUS = 0 AND DEPT_CRTR = ? ) ");
				pstmt.setInt(index++, deptNo);
				pstmt.setInt(index++, empNo);

				pstmt.setInt(index++, deptNo);
				pstmt.setInt(index++, empNo);

			}

			pstmt.setInt(index++, recordPerPage);
			pstmt.setInt(index, currentPage);

//			int index = 1;
//
//			// 관리자가 아닌 경우 본인 확인 조건 추가
//			if (empNo != 2024000) {
//				pstmt.setInt(index++, deptNo);
//				pstmt.setInt(index++, empNo);
//			}
//
//			pstmt.setInt(index++, recordPerPage);
//			pstmt.setInt(index, (currentPage - 1) * recordPerPage);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				DeptBoardVO vo = DeptBoardVO.builder().deptBNo(rs.getInt("DEPTB_NO"))
						.deptBTitle(rs.getString("DEPTB_TITLE")).deptBContent(rs.getString("DEPTB_CONTENT"))
						.deptBView(rs.getInt("DEPTB_VIEW")).deptBNotice(rs.getInt("DEPTB_NOTICE"))
						.deptBStatus(rs.getInt("DEPTB_STATUS")).deptBPblc(rs.getInt("DEPTB_PBLC"))
						.deptNo(rs.getInt("DEPT_NO")).deptBCrtr(rs.getInt("DEPTB_CRTR"))
						.createDate(rs.getString("CREATE_DATE")).updateDate(rs.getString("UPDATE_DATE"))
						.empName(rs.getString("EMP_NAME")) // 작성자 이름 추가
						.build();
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 총 게시물 개수
	public int getDeptBoardCount(int deptNo, int empNo, boolean isAdmin) {
		int totalCount = 0;
		sb.setLength(0);
		sb.append("SELECT COUNT(*) AS CNT FROM DEPTBOARD WHERE DEPTB_NOTICE = 0 ");
		if (!isAdmin) {
			sb.append("AND (DEPTB_PBLC = 1 OR DEPTB_CRTR = ?)");
		}

		try {
			pstmt = conn.prepareStatement(sb.toString());
			if (!isAdmin) {
				pstmt.setInt(1, empNo);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt("CNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	private DeptBoardVO mapResultSetToDeptBoardVO(ResultSet rs) throws SQLException {
		return DeptBoardVO.builder().deptBNo(rs.getInt("DEPTB_NO")).deptBTitle(rs.getString("DEPTB_TITLE"))
				.deptBContent(rs.getString("DEPTB_CONTENT")).deptBView(rs.getInt("DEPTB_VIEW"))
				.deptBNotice(rs.getInt("DEPTB_NOTICE")).deptBStatus(rs.getInt("DEPTB_STATUS"))
				.deptBPblc(rs.getInt("DEPTB_PBLC")).deptNo(rs.getInt("DEPT_NO")).deptBCrtr(rs.getInt("DEPTB_CRTR"))
				.createDate(rs.getString("CREATE_DATE")).updateDate(rs.getString("UPDATE_DATE")).build();
	}

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
