package util;

import java.sql.*;

public class DBUtil {
	public static Connection getConnection() throws Exception {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","gdj58","java1234");
		conn.setAutoCommit(false); // 오토커밋 안되게끔 통일
		return conn;
	}
}
