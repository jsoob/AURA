package com.aura.www.action.attendencedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aura.www.action.attendencevo.AttendenceVO;

public class AttendenceDAO {

//	1. 변수선언

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "scott";
	String password = "tiger";
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();

	public AttendenceDAO() {
		//	2. 드라이버 로딩
		try {
			Class.forName(driver);
			//	3. 연결 ( Connection )
				conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패");
				e.printStackTrace();
		} catch (SQLException e) {
				System.out.println("DB 연결 실패");
				e.printStackTrace();
		}
	}	// constructor end
	
	
	///////////////////////////////////////////// 전체조회 /////////////////////////////////////////////
	
	public ArrayList<AttendenceVO> vo
		//	4. SQL문 작성
		//	5. 문장 객체 생성
		//	6. 실행 (SELECT ==> ResultSet 객체 )
		//	7. 레코드별 로직 처리 : db row, 프로그램 레코드

	
}
