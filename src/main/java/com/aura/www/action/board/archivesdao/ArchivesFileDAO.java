package com.aura.www.action.board.archivesdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.action.board.archivesvo.ArchivesFileVO;

public class ArchivesFileDAO {

	// 1. 환경변수 선언

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/aura";
		String user = "aura";
		String password = "tigertiger12$$";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		public ArchivesFileDAO() {
			// 2. 드라이버 로딩
			try {
				Class.forName(driver);
				// 3. Connection
				conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				System.out.println("MYSQL 드라이버 로딩 실패");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("MYSQL DB 연결 실패");
				e.printStackTrace();
			}
		} // constructor end

	/////////////////////////////////// 전체조회 //////////////////////////////////////

	public ArrayList<ArchivesFileVO> selectAll() {
		ArrayList<ArchivesFileVO> list = new ArrayList<ArchivesFileVO>();

		// 4. SQL문 작성
		sb.setLength(0);
		sb.append("SELECT FILE_NO, FILE_NAME, FILE_ROUTE, ARC_NO ");
		sb.append("FROM ARCHIVESFILE ");

		// 5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			// 6. 실행 (SELECT ==> ResultSet 객체 )
			while (rs.next()) {
				int fileNo = rs.getInt("FILE_NO");
				String fileName = rs.getString("FILE_NAME");
				String fileRoute = rs.getString("FILE_ROUTE");
				int arcNo = rs.getInt("ARC_NO");

				ArchivesFileVO vo = new ArchivesFileVO(fileNo, fileName, fileRoute, arcNo);

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}	// selectAll() end
	
	
	/////////////////////////////////// 1건조회 //////////////////////////////////////
	
	
	public ArchivesFileVO selectOne (int fileNo) {

		//		4. SQL문 작성
		sb.setLength(0);
		sb.append("SELECT FILE_NO, FILE_NAME, FILE_ROUTE, ARC_NO ");
		sb.append("FROM ARCHIVESFILE ");
		sb.append("WHERE FILE_NO ");
		
		// ArchivesFileVO vo 변수의 값을 try ~ catch문 밖에서 별도로 변수 null 값 선언
		ArchivesFileVO vo = null;
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, fileNo);
			rs = pstmt.executeQuery();
			//		6. 실행 (SELECT ==> ResultSet 객체 )
			while(rs.next()) {
				String fileName = rs.getString("FILE_NAME");
				String fileRoute = rs.getString("FILE_ROUTE");
				int arcNo = rs.getInt("ARC_NO");
				
				vo = new ArchivesFileVO(fileNo, fileName, fileRoute, arcNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}	// selectOne() end
	
	
	   ////////////////////////////////////	추가 //////////////////////////////////////
	
	
	public void addOne(ArchivesFileVO vo) {
		
		//		4. SQL문 작성
		sb.setLength(0);
		sb.append("INSERT INTO ARCHIVESFILE (FILE_NO, FILE_NAME, FILE_ROUTE, ARC_NO ) ");
		sb.append("VALUES (ARCHIVESFILE_FILE_NO_SEQ.NEXTVAL, ?, ?, ? ");
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getFileName());
			pstmt.setString(2, vo.getFileRoute());
			pstmt.setInt(3, vo.getArcNo());
			//		6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	// addOne() end
	
	
		
	   //////////////////////////////////// 수정(변경) //////////////////////////////////////
		
		
	public void updateOne (ArchivesFileVO vo) {
		
		//		4. SQL문 작성
		sb.setLength(0);
		sb.append("UPDATE ARCHIVESFILE ");
		sb.append("SET FILE_NAME, FILE_ROUTE, ARC_NO ");
		sb.append("WHERE FILE_NO = ? ");
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getFileName());
			pstmt.setString(2, vo.getFileRoute());
			pstmt.setInt(3, vo.getArcNo());
			//		6. 실행 (SELECT ==> ResultSet 객체 )
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	// updateOne() end
	
	
	   //////////////////////////////////// 삭제 //////////////////////////////////////
	
	
	public void deleteOne(int FILE_NO) {
		
		//		4. SQL문 작성
		sb.setLength(0);
		sb.append("DELETE FROM ARCHIVESFILE ");
		sb.append("WHERE FILE_NO = ? ");
		
		//		5. 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, FILE_NO);
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
