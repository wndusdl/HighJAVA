package ch10JDBC.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
	JDBC�� �̿��� �����ͺ��̽� ó�� ����
	1. ����̹� �ε� ==> JDBC���̺귯���� ����� �� �ְ� �޸�̷� �о� ���̴� �۾�
	   (JDBC API���� 4�̻󿡼��� hetConnecion()�޼��忡�� �ڵ����� �ε��� �ֱ� ������
	   	�� �ܰ�� ���� �� �� �ִ�.) ==> �׷����� �������� �ʰ� ����� ����
	   Class.forName("oracle.jdbc.driver.OracleDriver")
	   
	2. DB�� �����ϱ� ==> ������ �Ϸ�Ǹ� Connection��ü�� ��ȯ�ȴ�.
	   DriverManager.getConnection()�޼��带 �̿��Ѵ�.
	   
	3. ���� �ϱ� ==> SQL������ DB������ ������ ���� ����� ���´�.
	   (Statement��ü�� PreparedStatement��ü�� �̿��Ͽ� �۾��Ѵ�.)
	   
	4. ��� ó���ϱ� ==> ���� ����� �޾Ƽ� ���ϴ� �۾��� �����Ѵ�.
	   1) SQL���� select���� ������ select�� ����� ResultSet��ü�� ����Ǿ� ��ȯ�ȴ�.(insert, update, delete�� ��)
	   2) SQL���� select���� �ƴ� ������ �������� ��ȯ�ȴ�.
	   	  (�������� ���� �۾��� ������ ���ڵ� ���� �ǹ��Ѵ�.)
	   	  
	5. ����� �ڿ��� �ݳ��Ѵ�. ==> close()�޼��带 �̿��ؼ� �ݳ��Ѵ�.
*/
public class ex1 {

	public static void main(String[] args) {
		//DB�۾��� �ʿ��� ���� ����
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1. ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver"); //catch
			
			//2. DB���� => Connection ��ü ����
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "jypc", "java");
			
			//3. ����
			//3-1 SQL�� �ۼ�
			String sql = "select * from l_prod";
			
			//3-2 Statement ��ü ����(�Ǵ� PreparedStatement ��ü ����)
			// 	  => SQL���� DB������ ������ ó���� ����� ������ ��ü�� Connection ��ü�� �̿��Ͽ� �����Ѵ�.)
			stmt = conn.createStatement();
			
			//3-3 SQL���� DB������ ������ ó���� ����� ���´�.
			//	  => ������ SQL���� SELECT���̱� ������ ����� ResultSet ��ü�� ����Ǿ� ��ȯ�Ѵ�.
			rs = stmt.executeQuery(sql);
			
			//4. ��� ó��		=>	�� ���ڵ徿 ȭ�鿡 ����ϱ�
			//ResultSet ��ü�� ����� �����͸� ���ʷ� ���������� �ݺ����� next()�޼��带 �̿��Ѵ�.
			System.out.println("== ������ ó�� ��� ==");
			
			//ResultSet��ü�� next()�޼��� => ResultSet��ü�� �����͸� ����Ű�� �����͸�
			//������° ���ڵ� ��ġ�� �̵� ��Ű�� �� ���� �����Ͱ� ������ true, ������ false�� ��ȯ�Ѵ�.
			
			//���� while���� ����Ѵ�.
			while (rs.next()) {
				//�����Ͱ� ����Ű�� ��ġ�� �ڷḦ �������� ���
				//����1) ResultSet��ü.get�ڷ����̸�("�÷��� �Ǵ� �÷���Alias��")
				//����2) ResultSet��ü.get�ڷ����̸�(�÷���ȣ ==>	�÷���ȣ�� 1������ �����Ѵ�.)
				System.out.println("=========================================");
				System.out.println("LPROD_ID : " + rs.getInt("lprod_id"));	//1����
				System.out.println("LPROD_GU : " + rs.getString("lprod_gu"));	//2����
				System.out.println("LPROD_NM : " + rs.getString("lprod_nm"));	//3����
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			//5. ����ߴ� �ڿ� �ݳ�
			if(conn!=null) try { conn.close(); } catch (SQLException e) {}
			if(stmt!=null) try { stmt.close(); } catch (SQLException e) {}
			if(rs!=null) try { rs.close(); } catch (SQLException e) {}
 		}
	}
}
