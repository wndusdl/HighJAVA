package ch10JDBC.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ch10JDBC.Util.DBUtil1;

/*
	LPROD테이블에 새로운 데이터 추가하기
	
	Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
	Lprod_id는 현재의 Lprod_id 중에서 제일 큰 값보다 1 크게 한다.
	
	입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
	
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
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				gu = sc.next();
				
				String sql2 = "select count(*) cnt from l_prod where lprod_gu = ? ";
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count>0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요");
				}
			} while (count>0);
			
			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			nm = sc.next();
			
			String sql3 = "insert into l_prod(lprod_id, lprod_gu, lprod_nm) values(?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, maxid);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("등록 성공!");
			} else {
				System.out.println("등록 실패!");
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
