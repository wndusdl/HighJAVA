package ch10JDBC.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//JDBC드라이버를 로딩하고 Connection객체를 생성하여 반환하는 메서드로 구성된 class 작성하기
public class DBUtil1 {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "jypc", "java");
			
		} catch (SQLException e) {
			System.out.println("DB연결 실패!!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
