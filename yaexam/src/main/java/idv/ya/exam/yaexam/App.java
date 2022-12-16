package idv.ya.exam.yaexam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws SQLException {
		System.out.println("祐安考題");

		// 一、JAVA

		// 一、A、1
		System.out.print("一、A\t 1:");
		System.out.println("vector 支持同步化");

		// 一、A、2
		System.out.print("\t 2:");
		System.out.println("封裝、繼承、多形");

		// 一、A、3
		System.out.print("\t 3:");
		System.out.println("static: 類別的靜態成員,且只有一個值;");
		System.out.println("\t   final: 用在變數,該變數不可改變;用在方法,\n\t   該方法不可被覆寫;用在類別,該類別不可被繼承\n");

		// 一、B、1
		System.out.println("一、B、1:");
		String str = "a,b,c,d,e,f,g";
		String[] result = str.split(",");
		for (String element : result) {
			System.out.println(element + "1");
		}

		// 一、B、2
		System.out.println("\n一、B、2:");
		final int line = 5;
		for (int i = 0; i < line; i++) {
			for (int j = line; j >= 0; j--) {
				System.out.print(j <= i ? "*" : " ");
			}
			System.out.println();
		}

		// 二、DB
		Connection conn = SqliteConnection.getConn();
		Statement statement = null;
		ResultSet queryResult = null;
		String sql = null;
		String name = null;
		int age = 0;
		String subject = null;
		int score = 0;

		// 二、一
		System.out.println("\n二、一:");
		sql = "select stdnt.s_no, stdnt.s_name, stdnt.s_age, sub.s_no, sub.subject, sub.score "
				+ "from student as stdnt, subject as sub "
				+ "where stdnt.s_no=sub.s_no and stdnt.s_name='JOE' and sub.score>=60";
		statement = conn.createStatement();
		queryResult = statement.executeQuery(sql);
		while (queryResult.next()) {
			name = queryResult.getString("s_name");
			age = queryResult.getInt("s_age");
			subject = queryResult.getString("subject");
			score = queryResult.getInt("score");
			System.out.printf("name=%s, age=%d, subject=%s, score=%d\n", name, age, subject, score);
		}

		// 二、二
		System.out.println("\n二、二:");
		sql = "update subject set score=60 where subject='Math' and s_no="
				+ "(select s_no from student where s_name='JOHN')";
		statement = conn.createStatement();
		statement.execute(sql);

		// 二、三
		System.out.println("\n二、三:");
		sql = "select stdnt.s_no, stdnt.s_name, sub.s_no, sum(sub.score) " 
				+ "from student as stdnt, subject as sub "
				+ "where stdnt.s_no=sub.s_no group by stdnt.s_name";
		statement = conn.createStatement();
		queryResult = statement.executeQuery(sql);
		while (queryResult.next()) {
			name = queryResult.getString("s_name");
			score = queryResult.getInt("sum(sub.score)");
			System.out.printf("name=%s, sum score=%d\n", name, score);
		}

	}
}
