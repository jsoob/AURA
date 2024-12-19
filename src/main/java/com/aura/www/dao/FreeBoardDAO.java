package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	public FreeBoardDAO(){
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
	public ArrayList<FreeBoardVO> selectAll(){
		ArrayList<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		sb.setLength(0);
		sb.append("SELECT FREEB_NO, FREEB_TITLE, FREEB_CONTENT, FREEB_VIEW, FREEB_NOTICE, FREEB_STATUS, FREEB_PBLC, FREEB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM FREEBOARD ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
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
				
				FreeBoardVO vo = new FreeBoardVO(freeBNo, freeBTitle, freeBContent, freeBView, freeBNotice, freeBStatus, freeBPblc, freeBCrtr, createDate, updateDate);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 검색해서 게시글 찾기
	// 제목, 내용, 작성자로 검색가능
	public ArrayList<FreeBoardVO> searchFreeBoard( FreeBoardVO vo ){
		ArrayList<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		sb.setLength(0);
		sb.append("SELECT FREEB_NO, FREEB_TITLE, FREEB_CONTENT, FREEB_VIEW, FREEB_NOTICE, FREEB_STATUS, FREEB_PBLC, FREEB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM FREEBOARD ");
		sb.append("WHERE 1=1 ");
		if(vo.getFreeBTitle().equals("") && vo.getFreeBTitle() != null ) sb.append("AND FREEB_TITLE LIKE ? ");
		if(vo.getFreeBContent().equals("") && vo.getFreeBContent() != null ) sb.append("AND FREEB_CONTENT LIKE ? ");
		if(vo.getFreeBCrtr() != 0 ) sb.append("AND FREEB_CRTR = ? ");
		sb.append("ORDER BY CREATE_DATE DESC ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			int cnt = 0;
			
			if(vo.getFreeBTitle().equals("") && vo.getFreeBTitle() != null ) {
				pstmt.setString(++cnt, "%"+vo.getFreeBTitle()+"%");
			}
			if(vo.getFreeBContent().equals("") && vo.getFreeBContent() != null ) {
				pstmt.setString(++cnt, "%"+vo.getFreeBContent()+"%");
			}
			if(vo.getFreeBCrtr() != 0) {
				pstmt.setInt(++cnt, vo.getFreeBCrtr());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
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
				
				FreeBoardVO findvo = new FreeBoardVO(freeBNo, freeBTitle, freeBContent, freeBView, freeBNotice, freeBStatus, freeBPblc, freeBCrtr, createDate, updateDate);
				list.add(findvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 게시물번호로 검색
	public FreeBoardVO selectOne(int freeBNo) {
		sb.setLength(0);
		sb.append("SELECT FREEB_NO, FREEB_TITLE, FREEB_CONTENT, FREEB_VIEW, FREEB_NOTICE, FREEB_STATUS, FREEB_PBLC, FREEB_CRTR, CREATE_DATE, UPDATE_DATE ");
		sb.append("FROM FREEBOARD ");
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

				vo = new FreeBoardVO(freeBNo, freeBTitle, freeBContent, freeBView, freeBNotice, freeBStatus, freeBPblc, freeBCrtr, createDate, updateDate);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
	}

	// 게시물 작성
	// 시퀀스 적용해야함
	public void insertOne(FreeBoardVO vo) {
		sb.setLength(0);
		sb.append("INSERT INTO BOARD ");
		sb.append("VALUES(NEXTVAL('FREEBNO'),?,?,0,?,?,?,?,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)");

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
		sb.append("WHERE BNO = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getFreeBTitle());
			pstmt.setString(2, vo.getFreeBContent());
			pstmt.setInt(3, vo.getFreeBNotice());
			pstmt.setInt(4, vo.getFreeBStatus());
			pstmt.setInt(5, vo.getFreeBPblc());

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
	
	// 페이징 처리해야함
//	SELECT FREEB_NO, FREEB_TITLE, FREEB_CONTENT, FREEB_VIEW, FREEB_NOTICE, FREEB_STATUS, FREEB_PBLC, FREEB_CRTR, CREATE_DATE, UPDATE_DATE
//	FROM FREEBOARD
//	ORDER BY CREATE_DATE DESC
//	LIMIT ? ,10
//	1,STARTNO-1
	
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
