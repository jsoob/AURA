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
	
	
	
	// 관리자 공지사항 최신 3개 
	public ArrayList<DeptBoardVO> selectThreeRecentNotice() {
	    ArrayList<DeptBoardVO> list = new ArrayList<>();
	    sb.setLength(0);
	    sb.append("SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, ");
	    sb.append("DEPTB_STATUS, DEPTB_PBLC, DEPT_NO, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
	    sb.append("FROM DEPTBOARD WHERE DEPTB_NOTICE=1 ORDER BY CREATE_DATE DESC LIMIT 3 "); // 부서 번호 조건 제거

	    try {
	        pstmt = conn.prepareStatement(sb.toString());
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

	
	///////// 공지사항 ///////
	
	
	// 관리자 공지사항 최신 3개 제외 전체 글들  
	public ArrayList<DeptBoardVO> getRestOfAllDeptBoard(int notice1, int notice2, int notice3) {
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
	public ArrayList<DeptBoardVO> selectThreeRecentNoticeByDept(int deptno) {
	    ArrayList<DeptBoardVO> list = new ArrayList<>();
	    sb.setLength(0);
	    sb.append("SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, ");
	    sb.append("DEPTB_STATUS, DEPTB_PBLC, DEPT_NO, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
	    sb.append("FROM DEPTBOARD WHERE DEPTB_NOTICE=1 AND DEPT_NO=? ORDER BY CREATE_DATE DESC LIMIT 3 "); // 부서 번호 조건 제거

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
	public ArrayList<DeptBoardVO> getRestOfDeptBoard(int deptno, int notice1, int notice2, int notice3) {
	    ArrayList<DeptBoardVO> list = new ArrayList<>();
	    sb.setLength(0);
	    sb.append("SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, ");
	    sb.append("DEPTB_STATUS, DEPTB_PBLC, DEPT_NO, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
	    sb.append("FROM DEPTBOARD WHERE DEPT_NO = ? AND DEPTB_NO NOT IN ( ? , ? , ? ) ORDER BY CREATE_DATE DESC "); // 부서 번호 조건 제거

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
	
	///// 페이징 //////
	
	public ArrayList<DeptBoardVO> getDeptBoardWithPaging(int deptNo, int empNo, boolean isAdmin, int currentPage, int recordPerPage) {
	    ArrayList<DeptBoardVO> list = new ArrayList<>();
	    sb.setLength(0);

	    // 첫 페이지 처리 (공지사항 3개 + 일반 글)
	    if (currentPage == 1) {
	        // 공지사항 쿼리
	        sb.append("SELECT * FROM DEPTBOARD WHERE DEPTB_NOTICE = 1 ORDER BY CREATE_DATE DESC LIMIT 3");
	        try {
	            pstmt = conn.prepareStatement(sb.toString());
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                DeptBoardVO vo = mapResultSetToDeptBoardVO(rs);
	                list.add(vo);
	            }

	            // 일반 글 쿼리
	            sb.setLength(0);
	            sb.append("SELECT * FROM DEPTBOARD WHERE DEPTB_NOTICE = 0 ");
	            if (!isAdmin) {
	                sb.append("AND (DEPTB_PBLC = 1 OR DEPTB_CRTR = ?) ");
	            }
	            sb.append("ORDER BY CREATE_DATE DESC LIMIT ? OFFSET 0");

	            pstmt = conn.prepareStatement(sb.toString());
	            if (!isAdmin) {
	                pstmt.setInt(1, empNo);
	                pstmt.setInt(2, recordPerPage - list.size());
	            } else {
	                pstmt.setInt(1, recordPerPage - list.size());
	            }

	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                DeptBoardVO vo = mapResultSetToDeptBoardVO(rs);
	                list.add(vo);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } else {
	        // 두 번째 페이지부터 일반 글 처리
	        sb.append("SELECT * FROM DEPTBOARD WHERE DEPTB_NOTICE = 0 ");
	        if (!isAdmin) {
	            sb.append("AND (DEPTB_PBLC = 1 OR DEPTB_CRTR = ?) ");
	        }
	        sb.append("ORDER BY CREATE_DATE DESC LIMIT ? OFFSET ?");

	        try {
	            pstmt = conn.prepareStatement(sb.toString());
	            if (!isAdmin) {
	                pstmt.setInt(1, empNo);
	                pstmt.setInt(2, recordPerPage);
	                pstmt.setInt(3, (currentPage - 1) * recordPerPage);
	            } else {
	                pstmt.setInt(1, recordPerPage);
	                pstmt.setInt(2, (currentPage - 1) * recordPerPage);
	            }

	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                DeptBoardVO vo = mapResultSetToDeptBoardVO(rs);
	                list.add(vo);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return list;
	}
	
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
	    return DeptBoardVO.builder()
	        .deptBNo(rs.getInt("DEPTB_NO"))
	        .deptBTitle(rs.getString("DEPTB_TITLE"))
	        .deptBContent(rs.getString("DEPTB_CONTENT"))
	        .deptBView(rs.getInt("DEPTB_VIEW"))
	        .deptBNotice(rs.getInt("DEPTB_NOTICE"))
	        .deptBStatus(rs.getInt("DEPTB_STATUS"))
	        .deptBPblc(rs.getInt("DEPTB_PBLC"))
	        .deptNo(rs.getInt("DEPT_NO"))
	        .deptBCrtr(rs.getInt("DEPTB_CRTR"))
	        .createDate(rs.getString("CREATE_DATE"))
	        .updateDate(rs.getString("UPDATE_DATE"))
	        .build();
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
