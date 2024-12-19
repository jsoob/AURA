package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.vo.FreeBoardCommentVO;


public class FreeBoardCommentDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/aura";
	String user = "aura";
	String password = "tigertiger12$$";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public FreeBoardCommentDAO() {
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

	// 특정 게시글의 모든 댓글 조회
	public ArrayList<FreeBoardCommentVO> selectCommentAll(int freeBNo) {
		ArrayList<FreeBoardCommentVO> list = new ArrayList<FreeBoardCommentVO>();
		sb.setLength(0);
		sb.append("SELECT FBCMNT_NO, FBCMNT_CONTENT, CREATE_DATE, UPDATE_DATE, EMP_NO, FREEB_NO ");
		sb.append("FROM FREEBCOMMENT ");
		sb.append("WHERE FREEB_NO = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int fBCmntNo = rs.getInt("FBCMNT_NO");
				String fBCmntContent = rs.getString("FBCMNT_CONTENT");
				String createDate = rs.getString("CREATE_DATE");
				String updateDate = rs.getString("UPDATE_DATE");
				int empNo = rs.getInt("EMP_NO");

				FreeBoardCommentVO vo = new FreeBoardCommentVO(fBCmntNo, fBCmntContent, createDate, updateDate, empNo,
						freeBNo);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 댓글 작성
	// 시퀀스 적용해야함
	public void insertComment(FreeBoardCommentVO vo) {
		sb.setLength(0);
		sb.append("INSERT INTO FREEBCOMMENT ");
		sb.append("VALUES(NEXTVAL('FBCOMNTNO'), ? ,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?)");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getFBCmntContent());
			pstmt.setInt(2, vo.getEmpNo());
			pstmt.setInt(3, vo.getFreeBNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 댓글 삭제
	public void deleteComment(int fBCmntNo) {
		sb.setLength(0);
		sb.append("DELETE FROM FREEBCOMMENT ");
		sb.append("WHERE FBCMNT_NO = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, fBCmntNo);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 댓글 수정
	public void updateComment(FreeBoardCommentVO vo) {

		sb.setLength(0);
		sb.append("UPDATE FREEBCOMMENT ");
		sb.append("SET FBCMNT_CONTENT = ? , UPDATE_DATE = CURRENT_TIMESTAMP ");
		sb.append("WHERE FBCMNT_NO = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getFBCmntContent());
			pstmt.setInt(2, vo.getFBCmntNo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
