package idv.ya.exam.yaexam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {
	static public Connection getConn() {
		String url = "jdbc:sqlite:c:/java/yoexam/school.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			//System.out.println("Connection successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
