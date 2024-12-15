package com.aura.www.action.board.archivesdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.action.board.archivesvo.ArchivesVO;

public class ArchivesDAO {

	// 1. 환경변수 선언

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:aura";
	String user = "aura";
	String password = "tigertiger12$$";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public ArchivesDAO() {
		// 2. 드라이버 로딩
		try {
			Class.forName(driver);
			// 3. Connection
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
	} // constructor end

	/////////////////////////////////// 전체조회 //////////////////////////////////////

	public ArrayList<ArchivesVO> selectAll() {
		ArrayList<ArchivesVO> list = new ArrayList<ArchivesVO>();

		// 4. SQL문 작성
		sb.setLength(0);
		sb.append("SELECT ARC_NO, ARC_TITLE, ARC_CONTENT, ARC_VIEW, ARC_NOTICE, EMP_NO, CREATE_DATE ");
		sb.append("FROM ARCHIVES ");

		// 5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			// 6. 실행 (SELECT ==> ResultSet 객체 )
			while (rs.next()) {
				int arcNo = rs.getInt("ARC_NO");
				String arcTitle = rs.getString("ARC_TITLE");
				String arcContent = rs.getString("ARC_CONTENT");
				int arcView = rs.getInt("ARC_VIEW");
				int arcNotice = rs.getInt("ARC_NOTICE");
				int empNo = rs.getInt("EMP_NO");
				String createDate = rs.getString("CREATE_DATE");

				ArchivesVO vo = new ArchivesVO(arcNo, arcTitle, arcContent, arcView, arcNotice, empNo, createDate);

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // selectAll() end

		/////////////////////////////////// 1건조회 //////////////////////////////////////

	public ArchivesVO selectOne(int arcNo) {

		// 4. SQL문 작성
		sb.setLength(0);
		sb.append("SELECT ARC_NO, ARC_TITLE, ARC_CONTENT, ARC_VIEW, ARC_NOTICE, EMP_NO, CREATE_DATE ");
		sb.append("FROM ARCHIVES ");
		sb.append("WHERE ARC_NO = ? ");

		// ArchivesVO vo (100번 라인) 변수의 값 try~catch문 바깥에서 따로 변수 null값으로 주기
		ArchivesVO vo = null;
		
		// 5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, arcNo);
			rs = pstmt.executeQuery();
			// 6. 실행 (SELECT ==> ResultSet 객체)
			while (rs.next()) {
				String arcTitle = rs.getString("ARC_TITLE");
				String arcContent = rs.getString("ARC_CONTENT");
				int arcView = rs.getInt("ARC_VIEW");
				int arcNotice = rs.getInt("ARC_NOTICE");
				int empNo = rs.getInt("EMP_NO");
				String createDate = rs.getString("CREATE_DATE");

				vo = new ArchivesVO(arcNo, arcTitle, arcContent, arcView, arcNotice, empNo, createDate);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	} // selectOne() end
	
	   ////////////////////////////////////	추가 //////////////////////////////////////
	
	public void addOne(ArchivesVO vo) {
		
		// 4. SQL문 작성
		sb.setLength(0);
		sb.append("INSERT INTO ARCHIVES (ARC_NO, ARC_TITLE, ARC_CONTENT, ARC_VIEW, ARC_NOTICE, EMP_NO, CREATE_DATE ) ");
			// ARC_VIEW (조회수) : 0부터 시작 
			// ARC_NOTICE (공지) : 
		sb.append("VALUES (ARCHIVES_ARC_NO_SEQ.NEXTVAL, ?, ?, 0, 0, ?, DATETIME ) ");
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getArcTitle());
			pstmt.setString(2, vo.getArcContent());
			pstmt.setInt(3, vo.getArcView());
			pstmt.setInt(4, vo.getArcNotice());
			pstmt.setInt(5, vo.getEmpNo());
			pstmt.setString(6, vo.getCreateDate());
			
			//		6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	// addOne() end
			
		
	   //////////////////////////////////// 수정(변경) //////////////////////////////////////
	
	public void updateOne(ArchivesVO vo) {
				
		//		4. SQL문 작성
		sb.setLength(0);
		sb.append("UPDATE ARCHIVES ");
		sb.append("SET ARC_TITLE, ARC_CONTENT, ARC_VIEW, ARC_NOTICE, EMP_NO, CREATE_DATE ) ");
		sb.append("WHERE ARC_NO = ? ");
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getArcTitle());
			pstmt.setString(2, vo.getArcTitle());
			pstmt.setInt(3, vo.getArcView());
			pstmt.setInt(4, vo.getArcNotice());
			pstmt.setInt(5, vo.getEmpNo());
			pstmt.setString(6, vo.getCreateDate());
			//		6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	// updateOne() end
			
		
	   //////////////////////////////////// 조회수 증가 (수정, 추가) //////////////////////////////////////

	
	public void arcView(int ARC_NO) {
		
		//		4. SQL문 작성
			// 선택이 된 게시판번호 기준으로 기존 조회수가 있는 값에서 조회수를 하나씩 더할 것
		sb.setLength(0);
		sb.append("UPDATE ARCHIVES ");					// ARCHIVES (자료실)에서 업데이트 하겠다
		sb.append("SET ARC_VIEW = ARC_VIEW+1 ");		// 기존 ARC_VIEW(조회수)에서 +1을 하겠다는 의미
		sb.append("WHERE ARC_NO = ? ");					// ARC_NO (게시판번호) 기준으로
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, ARC_NO);
			//		6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}	// arcView() end
		

	   //////////////////////////////////// 삭제 //////////////////////////////////////
	
	
	public void deleteOne(int ARC_NO) {
		
		//		4. SQL문 작성
		sb.setLength(0);
		sb.append("DELETE FROM ARCHIVES ");
		sb.append("WHERE ARC_NO = ? ");
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, ARC_NO);
			//		6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	// deleteOne() end
	

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
	}	// close() end


}
