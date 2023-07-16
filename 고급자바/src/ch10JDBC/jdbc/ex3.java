package ch10JDBC.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//����. Lprod_id���� 2�� �Է� �޾Ƽ� �� �� �� ���� ������ ū �������� �����͸� ����Ͻÿ�.
public class ex3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("ù��° id�� �Է��ϼ��� >> ");
		int id1 = sc.nextInt();
		System.out.print("�ι�° id�� �Է��ϼ��� >> ");
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
			
			//2 ó�� �ӵ��� �ξ� ����.
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
