package MovieTeam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.domain.Movie;

public class MovieDao {
	
	
	// select
//		public static ArrayList<Movie> getAllMovie(Movie movie) throws SQLException{
//			ArrayList<Movie>
//			allData = null;
//			// DB 연결
//			Connection con = null;
//			Statement stmt = null;
//			ResultSet rset = null; 
//			
//			try {
//				con = MovieUtil.getConnection();
//				stmt = con.createStatement();
//				rset = stmt.executeQuery("select * from Movie;");
//				
//				allData = new ArrayList<Movie>();
//				while(rset.next()) {
//					allData.add(new Movie(rset.getString("title"),rset.getString("genre"),rset.getString("runningTime"), rset.getString("openDate"),rset.getString("director")));
//				}
//				
//			} finally {
//				MovieUtil.close(stmt, con);
//			}
//			
//			return allData;
//		}
		
	
		// 값 넣기
		 public static boolean insertMovie(Movie movie) throws SQLException {
				Connection con = null;
				PreparedStatement pstmt = null;	
				
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scott?serverTimezone=Asia/Seoul","scott", "tiger");
					
					pstmt = con.prepareStatement("insert into Movie(title,genre,runningTime,openDate,director) values(?,?,?,?,?)");
					
					pstmt.setString(1, movie.getTitle());
					pstmt.setString(2, movie.getGenre());
					pstmt.setString(3, movie.getRunningTime());
					pstmt.setString(4, movie.getOpenDate());
					pstmt.setString(5, movie.getDirector());
					
					// DB 내용 생성 : 몇개의 행이 적용 되었는지 그 반환값을 숫자로 나타냄 
					int result = pstmt.executeUpdate();
					
					if(result != 0) {
						return true;
					}

				} finally {
					// close 에 순서가 있다. 중요
					pstmt.close();	
					con.close();	
					// 2개짜리
					MovieUtil.close(pstmt, con);
				}
				
				return false;
			}
	 }
