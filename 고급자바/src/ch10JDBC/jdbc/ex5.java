package ch10JDBC.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ch10JDBC.Util.DBUtil1;

/*
	LPROD���̺� ���ο� ������ �߰��ϱ�
	
	Lprod_gu�� Lprod_nm�� ���� �Է� �޾Ƽ� ó���ϰ�,
	Lprod_id�� ������ Lprod_id �߿��� ���� ū ������ 1 ũ�� �Ѵ�.
	
	�Է¹��� Lprod_gu�� �̹� ��ϵǾ� ������ �ٽ� �Է¹޾Ƽ� ó���Ѵ�.
	
*/
public class ex5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil1.getConnection();
			
			String sql = "select max(lprod_id) +1 maxid from l_prod";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			int maxid = 0;
			
			if(rs.next()) {
				maxid = rs.getInt("maxid");
			}
			
			String gu;
			String nm;
			int count = 0;
			
			do {
				System.out.print("��ǰ �з� �ڵ�(LPROD_GU) �Է� >> ");
				gu = sc.next();
				
				String sql2 = "select count(*) cnt from l_prod where lprod_gu = ? ";
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count>0) {
					System.out.println("�Է��� ��ǰ �з� �ڵ� " + gu + "�� �̹� ��ϵ� �ڵ��Դϴ�.");
					System.out.println("�ٽ� �Է��ϼ���");
				}
			} while (count>0);
			
			System.out.print("��ǰ �з���(LPROD_NM) �Է� >> ");
			nm = sc.next();
			
			String sql3 = "insert into l_prod(lprod_id, lprod_gu, lprod_nm) values(?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, maxid);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("��� ����!");
			} else {
				System.out.println("��� ����!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) try { stmt.close(); } catch (SQLException e) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
			if(conn != null) try { conn.close(); } catch (SQLException e) {}
			if(rs != null) try { rs.close(); } catch (SQLException e) {}
		}
	}
}
