package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.vo.AttendanceVO;
import com.aura.www.vo.DeptBoardVO;
import com.aura.www.vo.EmpVO;
import com.aura.www.vo.FreeBoardVO;

public class MainDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://192.168.90.65:3306/aura"; // 학원에서 사용시
	String url = "jdbc:mysql://localhost:3306/aura"; // mysql port -> 집에서 사용시
	String user = "aura";
	String password = "tigertiger12$$";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	public MainDAO() {
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
	

	// 자유 게시판 조회
	public int getFreeBTotalCount(FreeBoardVO vo) {
		int total = 0;
		
		sb.setLength(0);
		sb.append("SELECT COUNT(*) TOTAL ");
		sb.append("FROM FREEBOARD ");
		sb.append("WHERE FREEB_STATUS !=0 "); // 임시저장이 아닌 것
		if (vo.getFreeBCrtr() != 2024000) {
			sb.append("AND ( FREEB_PBLC = 1 OR ( FREEB_PBLC = 0 AND FREEB_CRTR = ? ) ) ");
		}
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			if (vo.getFreeBCrtr() != 2024000) {
				pstmt.setInt(1, vo.getFreeBCrtr() );
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt("TOTAL");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public ArrayList<FreeBoardVO> selectFreeBoard(FreeBoardVO vo, int limitNo) {
		ArrayList<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		sb.setLength(0);
		sb.append("SELECT FREEB_NO, FREEB_TITLE, FREEB_CONTENT, FREEB_VIEW, FREEB_NOTICE, FREEB_STATUS, FREEB_PBLC, FREEB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM FREEBOARD ");
		sb.append("WHERE FREEB_STATUS !=0 ");
		if (vo.getFreeBCrtr() != 2024000) {
			sb.append("AND ( FREEB_PBLC = 1 OR ( FREEB_PBLC = 0 AND FREEB_CRTR = ? ) ) ");
		}
		sb.append("ORDER BY CREATE_DATE DESC ");
		sb.append("LIMIT ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());

			int cnt = 0;
			
			if (vo.getFreeBCrtr() != 2024000) {
				pstmt.setInt(++cnt, vo.getFreeBCrtr() );
			}
			if (limitNo >= 0) {
				pstmt.setInt(++cnt, limitNo);
			}
			
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

				list.add(fbvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 부서 게시판 조회
	public int getDeptBTotalCount(DeptBoardVO vo) {
		int total = 0;
		
		sb.setLength(0);
		sb.append("SELECT COUNT(*) TOTAL ");
		sb.append("FROM DEPTBOARD ");
		sb.append("WHERE DEPTB_STATUS !=0 "); // 임시저장이 아닌 것
		if (vo.getDeptBCrtr() != 2024000) {
			sb.append("AND DEPT_NO = ? ");
			sb.append("AND ( DEPTB_PBLC = 1 OR ( DEPTB_PBLC = 0 AND DEPTB_CRTR = ? ) ) ");
		}
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			if (vo.getDeptBCrtr() != 2024000) {
				pstmt.setInt(1, vo.getDeptNo() );
				pstmt.setInt(2, vo.getDeptBCrtr() );
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt("TOTAL");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public ArrayList<DeptBoardVO> selectDeptBoard(DeptBoardVO vo, int limitNo) {
		ArrayList<DeptBoardVO> list = new ArrayList<DeptBoardVO>();
		sb.setLength(0);
		
		sb.append("SELECT DEPTB_NO, DEPTB_TITLE, DEPTB_CONTENT, DEPTB_VIEW, DEPTB_NOTICE, ");
	    sb.append("DEPTB_STATUS, DEPTB_PBLC, DEPT_NO, DEPTB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM DEPTBOARD ");
		
		sb.append("WHERE DEPTB_STATUS !=0 ");
		if (vo.getDeptBCrtr() != 2024000) {
			sb.append("AND DEPT_NO = ? ");
			sb.append("AND ( DEPTB_PBLC = 1 OR ( DEPTB_PBLC = 0 AND DEPTB_CRTR = ? ) ) ");
		}
		sb.append("ORDER BY CREATE_DATE DESC ");
		sb.append("LIMIT ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
	
			int cnt = 0;
			
			if (vo.getDeptBCrtr() != 2024000) {
				pstmt.setInt(++cnt, vo.getDeptNo() );
				pstmt.setInt(++cnt, vo.getDeptBCrtr() );
			}
			if (limitNo >= 0) {
				pstmt.setInt(++cnt, limitNo);
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
				int deptBCrtr = rs.getInt("DEPTB_CRTR");
				String createDate = rs.getString("CREATE_DATE");
				String updateDate = rs.getString("UPDATE_DATE");
	
				DeptBoardVO dbvo = new DeptBoardVO();
	
				dbvo.setDeptBNo(deptBNo);
				dbvo.setDeptBTitle(deptBTitle);
				dbvo.setDeptBContent(deptBContent);
				dbvo.setDeptBView(deptBView);
				dbvo.setDeptBNotice(deptBNotice);
				dbvo.setDeptBStatus(deptBStatus);
				dbvo.setDeptBPblc(deptBPblc);
				dbvo.setDeptBCrtr(deptBCrtr);
				dbvo.setCreateDate(createDate);
				dbvo.setUpdateDate(updateDate);
	
				list.add(dbvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//
	public AttendanceVO selectNowWork(int getEmpNo) {
		sb.setLength(0);
		sb.append("SELECT ATTEN_DATE, EMP_NO, STARTWORK_TIME, ENDWORK_TIME ");
		sb.append("FROM ATTENDANCE ");
		sb.append("WHERE EMP_NO = ? ");
		sb.append("AND DATE_FORMAT(ATTEN_DATE, '%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') ");
		
		AttendanceVO vo = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, getEmpNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int empNo = rs.getInt("EMP_NO");
				String attenDate = rs.getString("ATTEN_DATE");
				String startworkTime = rs.getString("STARTWORK_TIME");
				String endworkTime = rs.getString("ENDWORK_TIME");
				
				vo = new AttendanceVO(attenDate, empNo, startworkTime, endworkTime, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
}
