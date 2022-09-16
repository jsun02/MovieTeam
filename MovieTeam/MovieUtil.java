package MovieTeam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieUtil {
	// 1단계 : Driver 로딩 
	// DB랑 연곃하기 위해 미리 준비를 시켜줘야 한다.
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
		
		
	// 2단계 : DB연결 가능 메소드	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/scott?serverTimezone=Asia/Seoul","scott", "tiger");
	}
	
	// 6단계 : 자원반환 메소드
	public static void close(ResultSet rset, Statement stmt, Connection con) throws SQLException {
		if(rset != null) {
			rset.close();
		}
		if(stmt != null) {
			stmt.close();
		}
		if(con != null) {
			con.close();
		}
	}
	
	// 오버로딩 
	// 2개짜리
	public static void close(Statement stmt, Connection con) throws SQLException {
		if(stmt != null) {
			stmt.close();
		}
		if(con != null) {
			con.close();
		}
	}
		
}
