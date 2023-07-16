package ch10JDBC.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//문제. Lprod_id값을 2개 입력 받아서 두 값 중 작은 값부터 큰 값까지의 데이터를 출력하시오.
public class ex3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫번째 id를 입력하세요 >> ");
		int id1 = sc.nextInt();
		System.out.print("두번째 id를 입력하세요 >> ");
		int id2 = sc.nextInt();
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int max = Math.max(id1, id2);
		int min = Math.min(id1, id2);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jypc", "java");
			
			//1.
//			String sql = "select * from l_prod where lprod_id between " + min + " and " + max;
//			
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
			
			//2 처리 속도가 훨씬 빠름.
			String sql = "select * from l_prod where lprod_id between ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, min);
			pstmt.setInt(2, max);
			
			rs = pstmt.executeQuery();
			
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
