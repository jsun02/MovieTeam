package MovieTeam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.domain.Movie;

public class MovieTeam {
   public static void main(String[] args) throws SQLException {
	   ArrayList<Movie> movies = new ArrayList<Movie>();
	   
	   
	   Document doc;
	   try {

		   
		   doc = Jsoup.connect("https://movie.naver.com/movie/running/current.naver").get();
//		   Elements newMovies = doc.select("#content > div.article > div > div.lst_wrap > ul li .lst_dsc ");
		   Elements newMovies = doc.select(".lst_detail_t1").select("li");
		   for (Element movie : newMovies) {
			   
			   // 러닝타임 / 개봉일 가져오기 
//			   String verticalbar = doc.select("#content > div.article > div > div.lst_wrap > ul > li > dl > dd:nth-child(3) > dl > dd:nth-child(2)").text();
			   String verticalbar = movie.select(".info_txt1 dd:nth-child(2)").text();
			   String[] verticalArray = verticalbar.split("\\|");
			   
			   // 타이틀
			   String title = movie.select(".tit a").text();
			   // 장르
			   String genre = movie.select("dd > dl > dd:nth-child(2) > span.link_txt > a:nth-child(1)").text();
			   
			   String runningTime  = null;
			   String  openDate = null;
			   if(verticalArray.length == 3) {
				   // 러닝타임
				   runningTime  = verticalArray[1];
				   // 개봉일
				   openDate = verticalArray[2];
			   }else {
				   // 러닝타임
				   runningTime  = verticalArray[0];
				   // 개봉일
				   openDate = verticalArray[1];
			   }
			   // 감독
			   String director = movie.select("dl > dd:nth-child(3) > dl > dd:nth-child(4) > span").text();
			  
			   // syso test
//			   System.out.println(openDate);
//			   System.out.println("=====================");

			   // ArrayList 추가
//			   movies.add(new Movie().getTitle(title));
//			   movies.add(new Movie());
//			   movies.add(new Movie(title, genre, runningTime, openDate, director));
			   
			   // DB 추가
				MovieDao.insertMovie(new Movie(title, genre, runningTime, openDate, director));
		   }
		   
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
   	}
   
}
