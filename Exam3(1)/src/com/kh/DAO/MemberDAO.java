package com.kh.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

	public Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "kh";
		String pw = "kh";
		
		return DriverManager.getConnection(url, id, pw);
	}
	
	public boolean isEmailExist(String email) throws Exception{

		String sql = "select email from members where email=?";

		Connection con = this.getConnection();

		PreparedStatement pstat = con.prepareStatement(sql);

		 {

			pstat.setString(1, email);
			ResultSet rs = pstat.executeQuery();
			{
				return rs.next();

			}

		}

	}

	public boolean login(String id, String pwd) throws Exception {
		String sql = "select id from members where id=? and pwd=?";
		
		Connection conn = this.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		ResultSet rs = pstmt.executeQuery();
		
		return rs.next(); // 결과가 1개라도 있으면 true반환하니 바로 리턴받는다.
	}
	
	
	
}
