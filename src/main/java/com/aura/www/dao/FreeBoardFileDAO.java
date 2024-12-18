package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.vo.freeboard.FreeBoardFileVO;

public class FreeBoardFileDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/aura"; // mysql port -> 3306 / 3307
	String user = "aura";
	String password = "tigertiger12$$";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public FreeBoardFileDAO(){
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
	
	// 특정 게시글에 첨부된 모든 파일 찾기
	public ArrayList<FreeBoardFileVO> selectFileList(int freeBNo){
		ArrayList<FreeBoardFileVO> list = new ArrayList<FreeBoardFileVO>();
		sb.setLength(0);
		sb.append("SELECT FILE_NO, FILE_NAME, FILE_ROUTE, FREEB_NO ");
		sb.append("FROM FBFILE ");
		sb.append("WHERE FREEB_NO = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, freeBNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int fileNo = rs.getInt("FILE_NO");
				String fileName = rs.getString("FILE_NAME");
				String fileRoute = rs.getString("FILE_ROUTE");
				
				FreeBoardFileVO vo = new FreeBoardFileVO(fileNo, fileName, fileRoute, freeBNo);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 첨부파일 DB에 저장
	public void insertFile(FreeBoardFileVO vo) {
		sb.setLength(0);
		sb.append("INSERT INTO FBFILE ");
		sb.append("VALUES(NEXTVAL('FILENO'), ?, ?, ? )");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getFileName());
			pstmt.setString(2, vo.getFileRoute());
			pstmt.setInt(3, vo.getFreeBNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 특정 첨부파일만 삭제할 때
	public void deleteFileOne(int fileNo) {
		sb.setLength(0);
		sb.append("DELETE FROM FBFILE ");
		sb.append("WHERE FILE_NO = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, fileNo);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 게시글이 삭제될 때 첨부파일도 함께 삭제
	public void deleteFileAll(int freeBNo) {
		sb.setLength(0);
		sb.append("DELETE FROM FBFILE ");
		sb.append("WHERE FREEB_NO = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, freeBNo);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
