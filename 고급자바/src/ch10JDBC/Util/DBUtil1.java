package ch10JDBC.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//JDBC����̹��� �ε��ϰ� Connection��ü�� �����Ͽ� ��ȯ�ϴ� �޼���� ������ class �ۼ��ϱ�
public class DBUtil1 {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
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
			System.out.println("DB���� ����!!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
