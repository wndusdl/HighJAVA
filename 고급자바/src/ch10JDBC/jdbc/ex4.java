package ch10JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ex4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jypc", "java");
			
			System.out.println("계좌 번호 정보 추가하기");
			System.out.println("추가할 계좌 번호 정보를 입력하세요");
			
			System.out.print("계좌번호 >> ");
			String bankNo = sc.next();
			
			System.out.print("은행이름 >> ");
			String bankName = sc.next();
			
			System.out.print("예금주명 >> ");
			String bankUserName = sc.next();
			
			String sql = "insert into bankinfo(bank_no, bank_name, bank_user_name, bank_date) "
					+ "values(?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, bankUserName);
			
			int cnt = pstmt.executeUpdate();
			System.out.println("반환값 => " + cnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
