package com.aura.www.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.vo.PositionVO;

public class AdminPositionDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://192.168.90.65:3306/aura"; // 학원에서 사용시
//	String url = "jdbc:mysql://localhost:3306/aura"; // mysql port -> 집에서 사용시
	String user = "aura";
	String password = "tigertiger12$$";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public AdminPositionDAO() {
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

	// 모든 직급 리스트 조회
	public ArrayList<PositionVO> selectPosAll() {
		ArrayList<PositionVO> list = new ArrayList<PositionVO>();

		sb.setLength(0);
		sb.append("SELECT POS_NO, POS_NAME ");
		sb.append("FROM POSITION ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int posNo = rs.getInt("POS_NO");
				String posName = rs.getString("POS_NAME");

				PositionVO vo = new PositionVO(posNo, posName);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 직급번호 or 직급명으로 검색
	public ArrayList<PositionVO> searchPosList(PositionVO vo) {
		ArrayList<PositionVO> list = new ArrayList<PositionVO>();
		
		sb.setLength(0);
		sb.append("SELECT POS_NO, POS_NAME ");
		sb.append("FROM POSITION ");
		sb.append("WHERE 1=1 ");
		
		if(vo.getPosNo() != 0 ) sb.append("AND POS_NO LIKE ? ");
		if(vo.getPosName().equals("") && vo.getPosName() != null ) sb.append("AND POS_NAME LIKE ? ");
		sb.append("ORDER BY POS_NO ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			int cnt = 0;
			
			if(vo.getPosNo() != 0 ) {
				pstmt.setInt(++cnt, vo.getPosNo());
			}
			if(vo.getPosName().equals("") && vo.getPosName() != null ) {
				pstmt.setString(++cnt, "%"+vo.getPosName()+"%");
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int posNo = rs.getInt("POS_NO");
				String posName = rs.getString("POS_NAME");
				
				PositionVO pvo = new PositionVO(posNo, posName);
				list.add(pvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	

	// 직급번호로 하나의 직급 조회
	public PositionVO selectPosOne(int posNo) {
		sb.setLength(0);
		sb.append("SELECT POS_NO, POS_NAME ");
		sb.append("FROM POSITION ");
		sb.append("WHERE POS_NO = ? ");

		PositionVO vo = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, posNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String posName = rs.getString("POS_NAME");

				vo = new PositionVO(posNo, posName);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
	}

	// 직급 등록
	// 아직 시퀀스 적용안됨!!!
	public void insertPos(PositionVO vo) {
		sb.setLength(0);
		sb.append("INSERT INTO POSITION ");
		sb.append("VALUES( NEXTVAL('POSNO') , ? )");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getPosName());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 직급 삭제
	public void deletePos(int posNo) {
		sb.setLength(0);
		sb.append("DELETE FROM POSITION ");
		sb.append("WHERE POS_NO = ? ");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, posNo);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 직급 수정
	public void updatePos(PositionVO vo) {

		sb.setLength(0);
		sb.append("UPDATE POSITION ");
		sb.append("SET POS_NAME = ? ");
		sb.append("WHERE POS_NO = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getPosName());
			pstmt.setInt(2, vo.getPosNo());

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
