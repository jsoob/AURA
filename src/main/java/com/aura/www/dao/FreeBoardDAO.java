package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.vo.FreeBoardEmpVO;
import com.aura.www.vo.FreeBoardVO;

public class FreeBoardDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/aura";
	String user = "aura";
	String password = "tigertiger12$$";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public FreeBoardDAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn : " + conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 전체 조회
	public ArrayList<FreeBoardVO> selectAll() {
		ArrayList<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		sb.setLength(0);
		sb.append(
				"SELECT FREEB_NO, FREEB_TITLE, FREEB_CONTENT, FREEB_VIEW, FREEB_NOTICE, FREEB_STATUS, FREEB_PBLC, FREEB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM FREEBOARD ");
		sb.append("ORDER BY CREATE_DATE DESC ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int freeBNo = rs.getInt("FREEB_NO");
				String freeBTitle = rs.getString("FREEB_TITLE");
				String freeBContent = rs.getString("FREEB_CONTENT");
				int freeBView = rs.getInt("FREEB_VIEW");
				int freeBNotice = rs.getInt("FREEB_NOTICE");
				int freeBStatus = rs.getInt("FREEB_STATUS");
				int freeBPblc = rs.getInt("FREEB_PBLC");
				int freeBCrtr = rs.getInt("FREEB_CRTR");
				String createDate = rs.getString("CREATE_DATE");
				String updateDate = rs.getString("UPDATE_DATE");

				FreeBoardVO vo = new FreeBoardVO();

				vo.setFreeBNo(freeBNo);
				vo.setFreeBTitle(freeBTitle);
				vo.setFreeBContent(freeBContent);
				vo.setFreeBView(freeBView);
				vo.setFreeBNotice(freeBNotice);
				vo.setFreeBStatus(freeBStatus);
				vo.setFreeBPblc(freeBPblc);
				vo.setFreeBCrtr(freeBCrtr);
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
//	public ArrayList<FreeBoardVO> searchFreeBoard(FreeBoardVO vo, String order, int limitNo, int offsetNo) {
//		ArrayList<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
//		sb.setLength(0);
//		sb.append("SELECT FREEB_NO, FREEB_TITLE, FREEB_CONTENT, FREEB_VIEW, FREEB_NOTICE, FREEB_STATUS, FREEB_PBLC, FREEB_CRTR, CREATE_DATE, UPDATE_DATE ");
//		sb.append("FROM FREEBOARD ");
//		sb.append("WHERE FREEB_STATUS !=0 "); // 임시저장이 아닌 것
//		if(vo.getEmpNo() != 2024000) {
//			 sb.append(" AND (FREEB_CRTR= ? OR FREEB_PBLC = 1) ");	
//		}
//		if (vo.getFreeBTitle() != null && !vo.getFreeBTitle().equals(""))
//			sb.append("AND FREEB_TITLE LIKE ? ");
//		if (vo.getFreeBContent() != null && !vo.getFreeBContent().equals(""))
//			sb.append("AND FREEB_CONTENT LIKE ? ");
//		if (vo.getFreeBCrtr() != 0)
//			sb.append("AND FREEB_CRTR = ? ");
//		if (order != null && order.equals("recent"))
//			sb.append("ORDER BY CREATE_DATE DESC ");
//		if (order != null && order.equals("old"))
//			sb.append("ORDER BY CREATE_DATE ASC ");
//		if (order != null && order.equals("view"))
//			sb.append("ORDER BY FREEB_VIEW DESC ");
//		sb.append("LIMIT ? OFFSET ? ");
//		
//		try {
//			pstmt = conn.prepareStatement(sb.toString());
//
//			// System.out.println("searchFreeBoard =" + sb.toString());
//			int cnt = 0;
//
//			if(vo.getEmpNo() != 2024000) {
//				pstmt.setInt(++cnt, vo.getEmpNo());
//			}
//			
//			if (vo.getFreeBTitle() != null && !vo.getFreeBTitle().equals("")) {
//				pstmt.setString(++cnt, "%" + vo.getFreeBTitle() + "%");
//			}
//			if (vo.getFreeBContent() != null && !vo.getFreeBContent().equals("")) {
//				pstmt.setString(++cnt, "%" + vo.getFreeBContent() + "%");
//			}
//			if (vo.getFreeBCrtr() != 0) {
//				pstmt.setInt(++cnt, vo.getFreeBCrtr());
//			}
//			pstmt.setInt(++cnt, limitNo);
//	        pstmt.setInt(++cnt, offsetNo);
//	         
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				int freeBNo = rs.getInt("FREEB_NO");
//				String freeBTitle = rs.getString("FREEB_TITLE");
//				String freeBContent = rs.getString("FREEB_CONTENT");
//				int freeBView = rs.getInt("FREEB_VIEW");
//				int freeBNotice = rs.getInt("FREEB_NOTICE");
//				int freeBStatus = rs.getInt("FREEB_STATUS");
//				int freeBPblc = rs.getInt("FREEB_PBLC");
//				int freeBCrtr = rs.getInt("FREEB_CRTR");
//				String createDate = rs.getString("CREATE_DATE");
//				String updateDate = rs.getString("UPDATE_DATE");
//
//				FreeBoardVO fbvo = new FreeBoardVO();
//
//				fbvo.setFreeBNo(freeBNo);
//				fbvo.setFreeBTitle(freeBTitle);
//				fbvo.setFreeBContent(freeBContent);
//				fbvo.setFreeBView(freeBView);
//				fbvo.setFreeBNotice(freeBNotice);
//				fbvo.setFreeBStatus(freeBStatus);
//				fbvo.setFreeBPblc(freeBPblc);
//				fbvo.setFreeBCrtr(freeBCrtr);
//				fbvo.setCreateDate(createDate);
//				fbvo.setUpdateDate(updateDate);
//
//				list.add(fbvo);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	
	
	// 검색해서 게시글 찾기
	// 제목, 내용, 작성자로 검색가능
	// 공지사항 위에 3개 고정되어 나오게끔하기위한 코드
	public ArrayList<FreeBoardVO> searchFreeBoard(FreeBoardVO vo, String order, int limitNo, int offsetNo) {
		ArrayList<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		sb.setLength(0);
		
		sb.append("SELECT t.FREEB_NO, t.FREEB_TITLE, t.FREEB_CONTENT, t.FREEB_VIEW, t.FREEB_NOTICE, t.FREEB_STATUS, t.FREEB_PBLC, t.FREEB_CRTR, t.CREATE_DATE, t.UPDATE_DATE, t.PRIORITY, EMP_NAME, DEPT_NAME, POS_NAME ");
		sb.append("from (SELECT FREEB_NO, FREEB_TITLE, FREEB_CONTENT, FREEB_VIEW, FREEB_NOTICE, FREEB_STATUS, FREEB_PBLC, FREEB_CRTR, CREATE_DATE, UPDATE_DATE, 1 AS PRIORITY FROM FREEBOARD WHERE FREEB_STATUS !=0 AND FREEB_NOTICE=1 AND FREEB_PBLC=1 ");
		sb.append("ORDER BY CREATE_DATE DESC ");
		sb.append("LIMIT 3 ) t ");
		sb.append("inner join EMP e on t.FREEB_CRTR = e.EMP_NO ");
		sb.append("left outer join dept d on e.dept_no = d.dept_no "); 
		sb.append("left outer join position p on e.pos_no = p.pos_no "); 
		sb.append(" UNION ALL ");
		
		sb.append(" select s.FREEB_NO, s.FREEB_TITLE, s.FREEB_CONTENT, s.FREEB_VIEW, s.FREEB_NOTICE, s.FREEB_STATUS, s.FREEB_PBLC, s.FREEB_CRTR, s.CREATE_DATE, s.UPDATE_DATE, s.PRIORITY, EMP_NAME, DEPT_NAME, POS_NAME ");
		sb.append(" from (SELECT FREEB_NO, FREEB_TITLE, FREEB_CONTENT, FREEB_VIEW, FREEB_NOTICE, FREEB_STATUS, FREEB_PBLC, FREEB_CRTR, CREATE_DATE, UPDATE_DATE, 2 AS PRIORITY FROM FREEBOARD WHERE FREEB_STATUS !=0 ");
		
//		sb.append("SELECT FREEB_NO, FREEB_TITLE, FREEB_CONTENT, FREEB_VIEW, FREEB_NOTICE, FREEB_STATUS, FREEB_PBLC, FREEB_CRTR, CREATE_DATE, UPDATE_DATE ");
//		sb.append("FROM FREEBOARD ");
//		sb.append("WHERE FREEB_STATUS !=0 "); // 임시저장이 아닌 것
		if(vo.getEmpNo() != 2024000) {
			sb.append(" AND (FREEB_CRTR= ? OR FREEB_PBLC = 1) ");	
		}
		if (vo.getFreeBTitle() != null && !vo.getFreeBTitle().equals(""))
			sb.append("AND FREEB_TITLE LIKE ? ");
		if (vo.getFreeBContent() != null && !vo.getFreeBContent().equals(""))
			sb.append("AND FREEB_CONTENT LIKE ? ");
		if (vo.getFreeBCrtr() != 0)
			sb.append("AND FREEB_CRTR = ? ");
		sb.append(") s ");
		
		sb.append("inner join emp e on s.FREEB_CRTR = e.emp_no ");
		sb.append("left outer join dept d on e.dept_no = d.dept_no "); 
		sb.append("left outer join position p on e.pos_no = p.pos_no "); 
		
		sb.append("ORDER BY PRIORITY ASC");
		if (order == null || (order!=null && order.equals("recent")))
			sb.append(", CREATE_DATE DESC ");
		if (order != null && order.equals("old"))
			sb.append(", CREATE_DATE ASC ");
		if (order != null && order.equals("view"))
			sb.append(", FREEB_VIEW DESC ");
		sb.append("LIMIT ? OFFSET ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			System.out.println("searchFreeBoard =" + sb.toString());
			int cnt = 0;
			
			if(vo.getEmpNo() != 2024000) {
				pstmt.setInt(++cnt, vo.getEmpNo());
			}
			
			if (vo.getFreeBTitle() != null && !vo.getFreeBTitle().equals("")) {
				pstmt.setString(++cnt, "%" + vo.getFreeBTitle() + "%");
			}
			if (vo.getFreeBContent() != null && !vo.getFreeBContent().equals("")) {
				pstmt.setString(++cnt, "%" + vo.getFreeBContent() + "%");
			}
			if (vo.getFreeBCrtr() != 0) {
				pstmt.setInt(++cnt, vo.getFreeBCrtr());
			}
			pstmt.setInt(++cnt, limitNo);
			pstmt.setInt(++cnt, offsetNo);
			
			System.out.println("여기 확인  : " + sb.toString());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int freeBNo = rs.getInt("FREEB_NO");
				String freeBTitle = rs.getString("FREEB_TITLE");
				String freeBContent = rs.getString("FREEB_CONTENT");
				int freeBView = rs.getInt("FREEB_VIEW");
				int freeBNotice = rs.getInt("FREEB_NOTICE");
				int freeBStatus = rs.getInt("FREEB_STATUS");
				int freeBPblc = rs.getInt("FREEB_PBLC");
				int freeBCrtr = rs.getInt("FREEB_CRTR");
				String createDate = rs.getString("CREATE_DATE");
				String updateDate = rs.getString("UPDATE_DATE");
				String empName = rs.getString("EMP_NAME");
				String deptName = rs.getString("DEPT_NAME");
				String posName = rs.getString("POS_NAME");
				
				FreeBoardVO fbvo = new FreeBoardVO();
				
				fbvo.setFreeBNo(freeBNo);
				fbvo.setFreeBTitle(freeBTitle);
				fbvo.setFreeBContent(freeBContent);
				fbvo.setFreeBView(freeBView);
				fbvo.setFreeBNotice(freeBNotice);
				fbvo.setFreeBStatus(freeBStatus);
				fbvo.setFreeBPblc(freeBPblc);
				fbvo.setFreeBCrtr(freeBCrtr);
				fbvo.setCreateDate(createDate);
				fbvo.setUpdateDate(updateDate);
				fbvo.setEmpName(empName);
				fbvo.setDeptName(deptName);
				fbvo.setPosName(posName);
				
				list.add(fbvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 임시저장 글 가져오기
	public ArrayList<FreeBoardVO> saveList(int loginEmp) {
		ArrayList<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		sb.setLength(0);
		sb.append("SELECT FREEB_NO, FREEB_TITLE, FREEB_CONTENT, FREEB_VIEW, FREEB_NOTICE, FREEB_STATUS, FREEB_PBLC, FREEB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM FREEBOARD ");
		sb.append("WHERE FREEB_STATUS = 0 AND FREEB_CRTR = ? ");
		sb.append("ORDER BY CREATE_DATE DESC ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, loginEmp);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int freeBNo = rs.getInt("FREEB_NO");
				String freeBTitle = rs.getString("FREEB_TITLE");
				String freeBContent = rs.getString("FREEB_CONTENT");
				int freeBView = rs.getInt("FREEB_VIEW");
				int freeBNotice = rs.getInt("FREEB_NOTICE");
				int freeBStatus = rs.getInt("FREEB_STATUS");
				int freeBPblc = rs.getInt("FREEB_PBLC");
				int freeBCrtr = rs.getInt("FREEB_CRTR");
				String createDate = rs.getString("CREATE_DATE");
				String updateDate = rs.getString("UPDATE_DATE");

				FreeBoardVO fvo = new FreeBoardVO();

				fvo.setFreeBNo(freeBNo);
				fvo.setFreeBTitle(freeBTitle);
				fvo.setFreeBContent(freeBContent);
				fvo.setFreeBView(freeBView);
				fvo.setFreeBNotice(freeBNotice);
				fvo.setFreeBStatus(freeBStatus);
				fvo.setFreeBPblc(freeBPblc);
				fvo.setFreeBCrtr(freeBCrtr);
				fvo.setCreateDate(createDate);
				fvo.setUpdateDate(updateDate);
				

				list.add(fvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 게시물번호로 검색
	public FreeBoardVO selectOne(int freeBNo) {
		sb.setLength(0);
	
		
		sb.append("SELECT EMP_NAME, DEPT_NAME, POS_NAME, FREEB_NO, FREEB_TITLE, FREEB_CONTENT, FREEB_VIEW, FREEB_NOTICE, FREEB_STATUS, FREEB_PBLC, FREEB_CRTR, f.CREATE_DATE, f.UPDATE_DATE ");
		sb.append("FROM FREEBOARD f INNER JOIN EMP e ");
		sb.append("on f.FREEB_CRTR = e.EMP_NO left outer join DEPT d ");
		sb.append("on e.DEPT_NO = d.DEPT_NO left outer join POSITION p ");
		sb.append("on e.POS_NO = p.POS_NO ");
		
		sb.append("WHERE FREEB_NO = ? ");

		FreeBoardVO vo = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, freeBNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String freeBTitle = rs.getString("FREEB_TITLE");
				String freeBContent = rs.getString("FREEB_CONTENT");
				int freeBView = rs.getInt("FREEB_VIEW");
				int freeBNotice = rs.getInt("FREEB_NOTICE");
				int freeBStatus = rs.getInt("FREEB_STATUS");
				int freeBPblc = rs.getInt("FREEB_PBLC");
				int freeBCrtr = rs.getInt("FREEB_CRTR");
				String createDate = rs.getString("CREATE_DATE");
				String updateDate = rs.getString("UPDATE_DATE");
				String empName = rs.getString("EMP_NAME");
				String deptName = rs.getString("DEPT_NAME");
				String posName = rs.getString("POS_NAME");
		
				vo = new FreeBoardVO();

				vo.setFreeBNo(freeBNo);
				vo.setFreeBTitle(freeBTitle);
				vo.setFreeBContent(freeBContent);
				vo.setFreeBView(freeBView);
				vo.setFreeBNotice(freeBNotice);
				vo.setFreeBStatus(freeBStatus);
				vo.setFreeBPblc(freeBPblc);
				vo.setFreeBCrtr(freeBCrtr);
				vo.setCreateDate(createDate);
				vo.setUpdateDate(updateDate);
				vo.setEmpName(empName);
				vo.setDeptName(deptName);
				vo.setPosName(posName);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
	}

	// 게시물 작성
	public void insertOne(FreeBoardVO vo) {
		sb.setLength(0);
		sb.append("INSERT INTO FREEBOARD ");
		sb.append("VALUES(NULL,?,?,0,?,?,?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getFreeBTitle());
			pstmt.setString(2, vo.getFreeBContent());
			pstmt.setInt(3, vo.getFreeBNotice());
			pstmt.setInt(4, vo.getFreeBStatus());
			pstmt.setInt(5, vo.getFreeBPblc());
			pstmt.setInt(6, vo.getFreeBCrtr());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 게시글 삭제
	public void deleteOne(int freeBNo) {
		sb.setLength(0);
		sb.append("DELETE FROM FREEBOARD ");
		sb.append("WHERE FREEB_NO = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, freeBNo);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 게시글 수정
	public void updateOne(FreeBoardVO vo) {

		sb.setLength(0);
		sb.append("UPDATE FREEBOARD ");
		sb.append("SET FREEB_TITLE = ?, FREEB_CONTENT = ?, FREEB_NOTICE = ?, FREEB_STATUS = ?, FREEB_PBLC = ?, UPDATE_DATE = CURRENT_TIMESTAMP ");
		sb.append("WHERE FREEB_NO = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getFreeBTitle());
			pstmt.setString(2, vo.getFreeBContent());
			pstmt.setInt(3, vo.getFreeBNotice());
			pstmt.setInt(4, vo.getFreeBStatus());
			pstmt.setInt(5, vo.getFreeBPblc());
			pstmt.setInt(6, vo.getFreeBNo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	// 임시저장글 등록
	public void updateTempSave(FreeBoardVO vo) {
		
		sb.setLength(0);
		sb.append("UPDATE FREEBOARD ");
		sb.append("SET FREEB_TITLE = ?, FREEB_CONTENT = ?, FREEB_NOTICE = ?, FREEB_STATUS = ?, FREEB_PBLC = ?, UPDATE_DATE = CURRENT_TIMESTAMP, CREATE_DATE = CURRENT_TIMESTAMP ");
		sb.append("WHERE FREEB_NO = ?");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getFreeBTitle());
			pstmt.setString(2, vo.getFreeBContent());
			pstmt.setInt(3, vo.getFreeBNotice());
			pstmt.setInt(4, vo.getFreeBStatus());
			pstmt.setInt(5, vo.getFreeBPblc());
			pstmt.setInt(6, vo.getFreeBNo());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// 조회수 증가 메서드
	public void raiseView(int freeBNo) {
		sb.setLength(0);
		sb.append("UPDATE FREEBOARD ");
		sb.append("SET FREEB_VIEW = FREEB_VIEW + 1 ");
		sb.append("WHERE FREEB_NO = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, freeBNo);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 총 게시물 수 구하는 메서드
	public int getTotalCount() {
		sb.setLength(0);
		sb.append("SELECT COUNT(*) CNT ");
		sb.append("FROM FREEBOARD ");
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

	// 검색 조건에 따른 총 게시물수
	public int getTotalCountSearch(FreeBoardVO vo) {
		int cnt = 0;
		
		sb.setLength(0);
		sb.append("SELECT COUNT(*) CNT ");
		sb.append("FROM FREEBOARD ");
		sb.append("WHERE FREEB_STATUS !=0 "); // 임시저장이 아닌 것
		if(vo.getEmpNo() != 2024000) { // 관리자가 아니라면
			 sb.append(" AND (FREEB_CRTR= ? OR FREEB_PBLC = 1) ");
		}
		if (vo.getFreeBTitle() != null && !vo.getFreeBTitle().equals(""))
			sb.append("AND FREEB_TITLE LIKE ? ");
		if (vo.getFreeBContent() != null && !vo.getFreeBContent().equals(""))
			sb.append("AND FREEB_CONTENT LIKE ? ");
		if (vo.getFreeBCrtr() != 0)
			sb.append("AND FREEB_CRTR = ? ");

		try {
			// System.out.println(sb.toString());
			pstmt = conn.prepareStatement(sb.toString());
		
			// System.out.println("getTotalCountSearch =" + sb.toString());
			if(vo.getEmpNo() != 2024000) {
				pstmt.setInt(++cnt, vo.getEmpNo());
			}
			if (vo.getFreeBTitle() != null && !vo.getFreeBTitle().equals("") ) {
				pstmt.setString(++cnt, "%" + vo.getFreeBTitle() + "%");
			}
			if (vo.getFreeBContent() != null && !vo.getFreeBContent().equals("")) {
				pstmt.setString(++cnt, "%" + vo.getFreeBContent() + "%");
			}
			if (vo.getFreeBCrtr() != 0) {
				pstmt.setInt(++cnt, vo.getFreeBCrtr());
			}

			rs = pstmt.executeQuery();
			
			rs.next();
			cnt = rs.getInt("CNT");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	// 사원번호로 사원명, 부서명, 직급명 가져오기
//	select e.EMP_NAME,d.DEPT_NAME, p.POS_NAME
//	from EMP e left outer join DEPT d
//	on e.DEPT_NO=d.DEPT_NO left outer join POSITION p
//	on e.POS_NO = p.POS_NO
//	where EMP_NO= 2024001 ;
	public FreeBoardEmpVO selectEmpOne(int empNo) {
		sb.setLength(0);
		sb.append("SELECT e.EMP_NAME,d.DEPT_NAME, p.POS_NAME ");
		sb.append("FROM EMP e left outer join DEPT d on e.DEPT_NO=d.DEPT_NO ");
		sb.append("FROM left outer join POSITION p on e.POS_NO = p.POS_NO ");
		sb.append("WHERE EMP_NO = ? ");

		FreeBoardEmpVO vo = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String empName = rs.getString("EMP_NAME");
				String deptName = rs.getString("DEPT_NAME");
				String posName = rs.getString("POS_NAME");

				vo = new FreeBoardEmpVO();
				
				vo.setEmpName(empName);
				vo.setDeptName(deptName);
				vo.setPosName(posName);
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
	}
	
	// 자원반납
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
