package ch10JDBC.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ch10JDBC.Util.DBUtil1;

//문제. 사용자로부터 Lprod_id값을 입력받아 입력한 값보다 Lprod_id가 큰 자료들을 출력하시오.
public class ex2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("아이디 입력 >> ");
		int input = sc.nextInt();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jypc", "java");
			
			String sql = "select * from l_prod where lprod_id >" + input; 
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				System.out.println("=========================================");
				System.out.println("LPROD_ID : " + rs.getInt("lprod_id"));	
				System.out.println("LPROD_GU : " + rs.getString("lprod_gu"));	
				System.out.println("LPROD_NM : " + rs.getString("lprod_nm"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) try { conn.close(); } catch (SQLException e) {}
			if(stmt!=null) try { stmt.close(); } catch (SQLException e) {}
			if(rs!=null) try { rs.close(); } catch (SQLException e) {}
		}
	}
}
